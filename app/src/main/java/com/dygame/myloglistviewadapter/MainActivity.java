package com.dygame.myloglistviewadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    protected Button testButton1 ;
    protected Button testButton2 ;
    protected Button testButton3 ;
    protected ListView lv ;
    protected MyLogListViewAdapter pListViewAdapter ;
    protected String TAG = "TAG" ;
    protected final Random pRoll = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Uncaught Exception Handler(Crash Exception)
        MyCrashHandler pCrashHandler = MyCrashHandler.getInstance();
        pCrashHandler.init(getApplicationContext());
        TAG = pCrashHandler.getTag() ;
        //find resource
        testButton1 = (Button)findViewById(R.id.button) ;
        testButton2 = (Button)findViewById(R.id.button2) ;
        testButton3 = (Button)findViewById(R.id.button3) ;
        lv = (ListView)findViewById(R.id.listView) ;
        //set OnClickListener
        testButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i = pRoll.nextInt(6)+1 ;
                String str = "get " + i + " !" ;
                Log.i(TAG, str) ;
                pListViewAdapter.getInstance().addLog(str);
            }
        });
        testButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i = pRoll.nextInt(6)+1 ;
                String str = "get " + i + " !" ;
                Log.i(TAG, str);
                pListViewAdapter.getInstance().addLog(str);
            }
        });
        testButton3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int i = pRoll.nextInt(6) + 1;
                String str = "get " + i + " !";
                Log.i(TAG, str);
                pListViewAdapter.getInstance().addLog(str);
            }
        });
        //set adapter
        pListViewAdapter.getInstance().create(this);
        //set listView
        lv.setAdapter(pListViewAdapter.getInstance());
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String str = "Pressed ListView ="+position ;
                Log.i(TAG, str) ;
                pListViewAdapter.getInstance().addLog(str);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
