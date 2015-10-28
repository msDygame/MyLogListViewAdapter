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

public class MyLogActivity extends AppCompatActivity
{
    protected Button quitButton ;
    protected ListView lvLog ;
    protected String TAG = "MyCrashHandler" ;
    protected MyLogListViewAdapter mListViewAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        //findResource
        quitButton = (Button) findViewById(R.id.buttonQuit);
        lvLog = (ListView) findViewById(R.id.listView2);
        //setOnClickListener
        quitButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                finish();
            }
        });
        //
        ArrayList<String> thumb = MyLogListViewAdapter.getInstance().getArrayList();
        if (thumb.size() <= 0)
        {
            mListViewAdapter.getInstance().create(this);
            //getData
            Bundle bundle = this.getIntent().getExtras();
            String str = bundle.getString("GameID");
            String sArrayField[] = str.split("\n", -1);
            Log.i(TAG, "split size=" + sArrayField.length);
            for (int i = 0; i < sArrayField.length; i++)
            {
                mListViewAdapter.getInstance().addLog(sArrayField[i]);
            }
            //set listView
            lvLog.setAdapter(mListViewAdapter.getInstance());
        }
        else
        {
            lvLog.setAdapter(MyLogListViewAdapter.getInstance());
        }
        lvLog.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String str = "Pressed ListView =" + position;
                Log.i(TAG, str) ;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_log, menu);
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
