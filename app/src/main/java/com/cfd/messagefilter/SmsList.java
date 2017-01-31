package com.cfd.messagefilter;

import android.app.ListActivity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 1/2/17.
 */
public class SmsList extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<SmsData> smsList = new ArrayList<SmsData>();

        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c= getContentResolver().query(uri, null, null ,null,null);
        startManagingCursor(c);

        // Read the sms data and store it in the list
        if(c.moveToFirst()) {
            for(int i=0; i < c.getCount(); i++) {
                SmsData sms = new SmsData();
                sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
                sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
                smsList.add(sms);

                c.moveToNext();
            }
        }
        c.close();

        // Set smsList in the ListAdapter
        setListAdapter(new ListAdapter(this, smsList));

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        SmsData sms = (SmsData)getListAdapter().getItem(position);

        Toast.makeText(getApplicationContext(), sms.getBody(), Toast.LENGTH_LONG).show();

    }

}