package com.example.hw7;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_NEW_ADD = 1;//code for result
    private static final int REQUEST_CODE_SHOW_ITEM = 4;
    private static final String TAG = "AddsListActivity";

    private ListView list;//my listV of  items
    private ArrayList<AdvDBTableObject> items ;//the very list
    private ListAdapter adapter;//adapter for intent - List dapter as a basic adapter template
    private SQLLiteManager myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_list);///first screen activity

        myDB = new SQLLiteManager(this);//this - context
        items = myDB.getAllAdds();//we are taking all adds from database

         useArrayAdapter();//default adapter

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdvDBTableObject newObj = (AdvDBTableObject) list.getAdapter().getItem(position);
                Intent myListIntent = new Intent(MainActivity.this, DisplayItemActivity.class);
                myListIntent.putExtra("place", newObj.getPlace());
                myListIntent.putExtra("time", newObj.getTime());
                myListIntent.putExtra("date", newObj.getDate());
                myListIntent.putExtra("region", newObj.getDistrict());
                myListIntent.putExtra("image", newObj.getImage());
                startActivityForResult(myListIntent, REQUEST_CODE_SHOW_ITEM);
            }
        });
    }

    //adapter options in menu_adds_list - the  default adapter behaviour
    private void useArrayAdapter() {

        adapter = new MyItemListAdapter(this, items);//my custom adapter class
        list.setAdapter(adapter);


    }


    private void useSimpleAdapter() {
        //all my final items  list
        ArrayList<HashMap<String,Object>> listOfAllItems = new ArrayList<>();
        //array of places and  imes to count afrer - from  to
        String []  places = new String[items.size()];
        String [] datesAndTimes = new String[items.size()];
        //storing my place and value  in a hash map
        HashMap<String, Object> myMap;
        for(int i = 0; i<items.size();i++){//iterating all my irems from the list - it is all in DB
            myMap = new HashMap<>();
            myMap.put("place",items.get(i).getPlace());
            myMap.put("date_time",items.get(i).getDate() + " " + items.get(i).getTime());
            listOfAllItems.add(myMap);
        }

        String[] from = {"place", "date_time"};
        int[] to = {R.id.place, R.id.date_time};

        adapter = new SimpleAdapter(this, listOfAllItems, R.layout.item_list, from, to);
        list.setAdapter(adapter);




    }

    private void useCursorAdapter() {

        Cursor cursor = myDB.getWritableDatabase().rawQuery("SELECT rowid _id * FROM" + AdvDBTableObject.TABLE_NAME, null);

        String[] myFromColumns = {AdvDBTableObject.PLACE_COLUMN_KEY, AdvDBTableObject.DATE_COLUMN_KEY};
        int[] to =  new int[]{R.id.place, R.id.date_time};

        adapter = new SimpleCursorAdapter(this,R.layout.item_list,cursor,myFromColumns,to);
        list.setAdapter(adapter);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adds_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//the + button menu
        switch (item.getItemId()) {
            case R.id.action_add://case it is clicked
                Intent goToSecondActivityIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(goToSecondActivityIntent, REQUEST_CODE_NEW_ADD);
                break;
            case R.id.adapter_array:
                useArrayAdapter();
                break;
            case R.id.adapter_cursor:
                useCursorAdapter();
                break;
            case R.id.adapter_simple:
                useSimpleAdapter();
                break;
        }
        return true;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//getting data from second activity - extras
        if (resultCode != RESULT_CANCELED)//if not cancellaed
            if (requestCode == REQUEST_CODE_NEW_ADD && resultCode == RESULT_OK) {//if it is the result by our  code - not  other
                String place = data.getStringExtra("placeEditedText");//getting extras from second activity
                String date = data.getStringExtra("dateText");
                String time = data.getStringExtra("timeText");
                String description = data.getStringExtra("district");
                Bitmap bmp = null;
                try {
                    bmp = data.getParcelableExtra("image");
                } catch (NullPointerException e) {
                    Log.e(TAG, "byte array is null", e);
                }

                // items.add(new ListItemObject(place, time, date, bmp));//adding to my aaray list the  object - we pull our data into it
                AdvDBTableObject newAdToAdd = new AdvDBTableObject(place, date, time,description,bmp);//new database unit to be added lately
                myDB.addAdvertisementToTable(newAdToAdd);//adding a  new  item to database
                items.add(newAdToAdd);//simple addition to arraylist of adds

                //byDeafault
              useArrayAdapter();
            }

        else  if(requestCode == REQUEST_CODE_SHOW_ITEM && resultCode == RESULT_OK) {
                String place = data.getStringExtra("place");
                AdvDBTableObject item = myDB.getItemByPlace(place);
                myDB.deleteAdvertisemsnt(item);
                items = myDB.getAllAdds();
                useArrayAdapter();

            }
    }
}
