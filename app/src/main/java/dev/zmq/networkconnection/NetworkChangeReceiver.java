package dev.zmq.networkconnection;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkChangeReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {

        try{
            if(isOnline(context))
            {
                MainActivity.dialog(true);
                Log.e("message", "Online Connect Internet ");
            }
            else{
                MainActivity.dialog(false);
                Log.e("message","Connectivity Failure !!! ");
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
    }

    public boolean isOnline(Context context)
    {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return (networkInfo!=null && networkInfo.isConnected());

        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            return false;
        }
    }
}
