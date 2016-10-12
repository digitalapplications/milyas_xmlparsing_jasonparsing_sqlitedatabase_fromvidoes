package com.example.l400.xmlparsing;

import android.content.Context;
import android.renderscript.ScriptGroup;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 9/8/2016.
 */
public class FlowerXMLParser {

    public  List<Flower> parseFeed(Context context)
    {
        try {

            boolean isDataItemTag = false;
            String CurrentTagName = "";
            Flower flower = null;
            List<Flower> flowerList = new ArrayList<>();

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            InputStream stream = context.getResources().openRawResource(R.raw.flower);
            parser.setInput(stream,null);
            int eventType = parser.getEventType();
           // String n = parser.getText();
            while(eventType != XmlPullParser.END_DOCUMENT)

            {
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        CurrentTagName = parser.getName();
                        if(CurrentTagName.equals("product"))
                        {
                            isDataItemTag = true;
                            flower = new Flower();
                            flowerList.add(flower);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("product"))
                        {
                            isDataItemTag=false;
                        }
                        CurrentTagName="";
                        break;
                    case XmlPullParser.TEXT:
                        if(isDataItemTag && flower !=null)
                        {
                            switch (CurrentTagName)
                            {

                                case "id":
                                    int i = Integer.parseInt(parser.getText());
                                   flower.setId(i);
                                    break;
                                case "name":
                                    flower.setName(parser.getText());
                                    Log.d("name",flower.getName());
                                    break;
                                case "department":
                                    flower.setDepartment(parser.getText());
                                    break;
                                case "type":
                                    flower.setType(parser.getText());
                                    break;
                                case "email":
                                    flower.setEmail(parser.getText());
                                    break;
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
            return flowerList;

        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
