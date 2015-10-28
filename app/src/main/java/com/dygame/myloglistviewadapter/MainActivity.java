package com.dygame.myloglistviewadapter;

import android.content.Intent;
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
    protected Button testButton4 ;
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
        pCrashHandler.init(this , getApplicationContext());
        TAG = pCrashHandler.getTag() ;
        //find resource
        testButton1 = (Button)findViewById(R.id.button) ;
        testButton2 = (Button)findViewById(R.id.button2) ;
        testButton3 = (Button)findViewById(R.id.button3) ;
        testButton4 = (Button)findViewById(R.id.button4) ;
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
                int i = pRoll.nextInt(3) + 1;
                //make crash
                if (i == 1)
                {
                    double db = 12 / 0 ;//Devide zero
                }
                else if (i == 2)
                {
                    throw new NullPointerException() ;
                }
                else if (i == 3)
                {
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            testButton2.setBackgroundResource(R.mipmap.ic_launcher);//CalledFromWrongThreadException
                        }
                    }).start();
                }
            }
        });
        testButton3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this , MyLogActivity.class);
                startActivity(intent);
            }
        });
        testButton4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this , MyLogListActivity.class);
                startActivity(intent);
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
                String str = "Pressed ListView =" + position;
                Log.i(TAG, str);
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
