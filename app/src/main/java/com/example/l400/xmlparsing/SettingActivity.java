package com.example.l400.xmlparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class SettingActivity extends AppCompatActivity {
    TextView name ,department,type,email;
    Flower flower;
    DataSource dataSource;
    DatabaseHelper databaseHelper;
    Sdatabase sdatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Bundle b = getIntent().getExtras();
        sdatabase = new Sdatabase(SettingActivity.this);
        dataSource = new DataSource(SettingActivity.this);
        dataSource.open();
        flower = b.getParcelable(".model.Flower");
        getrefreshdisplay();

    }
    public void getrefreshdisplay(){
         name = (TextView) findViewById(R.id.name);
         department = (TextView)findViewById(R.id.department);
         type = (TextView)findViewById(R.id.type);
         email = (TextView)findViewById(R.id.email);
        name.setText(flower.getName());
        department.setText(flower.getDepartment());
        type.setText(flower.getType());
        email.setText(flower.getEmail());


    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setting_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        switch (id){
            case R.id.menu_add:
                dataSource.open();
                boolean inserted = sdatabase.inserData(name.getText().toString(),department.getText().toString(),type.getText().toString(),email.getText().toString());
                if(inserted == true)
                    Toast.makeText(SettingActivity.this, "data inserted succesfully", Toast.LENGTH_SHORT).show();

                else
                    Toast.makeText(SettingActivity.this, "data not added", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
