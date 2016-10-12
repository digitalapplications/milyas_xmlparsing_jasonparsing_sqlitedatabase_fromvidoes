package com.example.l400.xmlparsing;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    DataSource dataSource;
    DatabaseHelper databaseHelper;
    FlowerXMLParser parser;
    List<Flower> list;
    Flower flower;
    Sdatabase sdatabase;
    private SharedPreferences setting;
    public static final String USERNAME = "Pref_Username";
    public static final String VIEWMAGES = "pref_imageView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(MainActivity.this);
        lv = (ListView) findViewById(R.id.listView);
        dataSource = new DataSource(MainActivity.this);
        sdatabase = new Sdatabase(MainActivity.this);
        //CheckBox c =(CheckBox)findViewById(R.id.c)
        setting = PreferenceManager.getDefaultSharedPreferences(this);
        dataSource.open();
        list = dataSource.findAll();
        flower = new Flower();
            createe();
            list = dataSource.findAll();
       MainActivity.this.refesdlist();
    }
    public void refesdlist(){
        boolean imageview = true;
        if(imageview == true){
            ArrayAdapter<Flower> adapte = new TourListAdapter(MainActivity.this,list);
            lv.setAdapter(adapte);
        }
        ArrayAdapter<Flower> adapter = new ArrayAdapter<Flower>(MainActivity.this,android.R.layout.simple_list_item_1,list);
        lv.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        dataSource.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }




    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.my_tours){
            sdatabase.open();
          list = sdatabase.findAll2();

            refesdlist();

        }
        if(id == R.id.all){
          list = dataSource.findAll();
            refesdlist();
        }
        if(id == R.id.name)
        {
           List<String> list = dataSource.getname();
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
            lv.setAdapter(adapter);
        }

        if(id == R.id.email){
            List<String> lst = dataSource.getemail();
            ArrayAdapter<String> adaptr = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,lst);
            lv.setAdapter(adaptr);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        dataSource.close();
    }
    public void createe(){
//        Flower flower = new Flower();
//        flower.setId(5591);
//        flower.setName("ilyas");
//        flower.setDepartment("bsse");
//        flower.setType("student");
//        flower.setEmail("ilyas@gmail.com");
//        Log.d("log" , "id "+flower.getId());
//        flower = dataSource.create(flower);
//
//        flower.setId(5511);
//        flower.setName("fazal nabi");
//        flower.setDepartment("bsse");
//        flower.setType("internee");
//        flower.setEmail("fazal@gmail.com");
//        Log.d("log" , "id "+flower.getId());
//        flower = dataSource.create(flower);
//
//
//        flower.setId(5534);
//        flower.setName("hammad");
//        flower.setDepartment("bsse");
//        flower.setType("teacher");
//        flower.setEmail("hammad @gmail.com");
//        Log.d("log" , "id " +flower.getId());
//        flower = dataSource.create(flower);

        FlowerXMLParser parser = new FlowerXMLParser();
        final List<Flower> list = parser.parseFeed(MainActivity.this) ;
        for (Flower fl: list) {
            dataSource.create(fl);

        }
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Flower flower = list.get(i);
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                intent.putExtra(".model.Flower" ,flower);
                startActivity(intent);
            }
        });
    }
    public void setpreference(View view){
        Intent intent = new Intent(MainActivity.this, SettingA.class);
        startActivity(intent);
    }

}
