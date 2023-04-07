package com.example.app1;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class Consql {
    Connection con;
    @SuppressLint("NewApi")
    public Connection conclass(){
        String ip="192.168.1.6",port="1433",db="Location";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String ConnectURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectURL = "jdbc:jtds:sqlserver://"+ip+":"+port+";databasename="+db+";";
            con = DriverManager.getConnection(ConnectURL);
        }
        catch(Exception e)
        {
            Log.e("Error is",e.getMessage());
        }
        return con;
    }
}
