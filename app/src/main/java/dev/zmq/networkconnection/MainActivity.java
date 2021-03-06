package dev.zmq.networkconnection;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private BroadcastReceiver mNetworkReceiver;
    static TextView tv_check_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_check_connection = (TextView)findViewById(R.id.txt_check_connection);
        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetwork();
    }
    public static void  dialog(Boolean value)
    {
        if (value)
        {
            tv_check_connection.setText("We are back");
            tv_check_connection.setBackgroundColor(Color.GREEN);
            tv_check_connection.setTextColor(Color.BLACK);
            Handler handler = new Handler();
            Runnable dRun = new Runnable()
            {
                @Override
                public void run()
                {
//                    tv_check_connection.setVisibility(View.GONE);
                }

            };
            handler.postDelayed(dRun, 3000);
        }
        else
        {
            tv_check_connection.setText("we are lost");
            tv_check_connection.setBackgroundColor(Color.RED);
            tv_check_connection.setTextColor(Color.WHITE);
        }
    }
    public void registerNetwork()
    {
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.N)
        {
            registerReceiver(mNetworkReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
        {
            registerReceiver(mNetworkReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        }

    }
    protected void unRegisterNetwork()
    {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unRegisterNetwork();
    }
}
