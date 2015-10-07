package com.example.hw7;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Ыг on 30.09.2015.
 */
public class DisplayItemActivity extends ActionBarActivity {
    private static final String TAG = "DisplayItemActivity";
    private static TextView timeText;
    private static TextView dateText;
    private static TextView placeText;
    private static TextView districtText;
    private static ImageView image;
    private String place;
    private String date;
    private String time;
    private Bitmap bmp;
    private String district;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_items_list);
        Intent intent = getIntent();
        place = intent.getStringExtra("placeEditedText");
        date = intent.getStringExtra("dateText");
        time = intent.getStringExtra("timeText");
        district = intent.getStringExtra("district");
        bmp = null;
        try {
            bmp = intent.getParcelableExtra("image");
        } catch (NullPointerException e) {
            Log.e(TAG, "bytearray is null", e);
        }

        image = (ImageView) findViewById(R.id.main_list_image);
        timeText = (TextView) findViewById(R.id.time_main);
        dateText = (TextView) findViewById(R.id.date_main);
        placeText = (TextView) findViewById(R.id.main_place_text);
        districtText = (TextView) findViewById(R.id.district_main);

        image.setImageBitmap(bmp);
        dateText.setText(date);
        timeText.setText(time);
        placeText.setText(place);
        districtText.setText("District: " + district);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                Intent intent = new Intent();
                intent.putExtra("placeEditedText", bmp);
                intent.putExtra("dateText", date);
                intent.putExtra("timeText", time);
                intent.putExtra("placeEditedText", place);
                intent.putExtra("district", district);
                Toast.makeText(getApplicationContext(), "Deleting the item ", Toast.LENGTH_LONG).show();
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
        return true;
    }

}
