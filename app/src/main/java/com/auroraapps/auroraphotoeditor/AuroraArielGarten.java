package com.auroraapps.auroraphotoeditor;

import android.content.Context;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.auroraapps.auroraphotoeditor.AuroraClaudiaChanShaw.AuroraLindaBritten;
public class AuroraArielGarten extends MultiDexApplication {

    private static Context mContext;

    private static AuroraArielGarten mInstance;
    AuroraLindaBritten objDb;


    @Override
    public void onCreate() {
        super.onCreate();
        try {
            mContext = getApplicationContext();
            MultiDex.install(this);

            mInstance = this;
            objDb = new AuroraLindaBritten(getApplicationContext());
            objDb.createDatabase();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
