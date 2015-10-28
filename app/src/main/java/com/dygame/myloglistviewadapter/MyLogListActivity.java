package com.dygame.myloglistviewadapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * Created by Administrator on 2015/9/25.
 */
public class MyLogListActivity extends ListActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
  //      setContentView(android.R.layout.simple_list_item_1);//no need?

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile", "Blackberry", "WebOS", "Ubuntu", "Windows7", "Windows10" , "Max OS X", "Linux", "OS/2" };
        setListAdapter( new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, values) );
    }
}
