package com.auroraapps.auroraphotoeditor.AuroraClaudiaChanShaw;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraAppDataBeans;

import java.util.ArrayList;
import java.util.Collections;


public class AuroraLindaBritten {

    public String TAG = "DatabaseAdapter";
    public static String DATABASE_NAME = "AuroraPhotoEditor.db";

    private static final String CREATE_AppMaster = "CREATE TABLE IF NOT EXISTS AppMaster(Application_name Text, Short_description Text, Logo Text, Download Text, Rating Text, Package_name Text);";
    private static final String CREATE_TodayTrandingMaster = "CREATE TABLE IF NOT EXISTS TodayTrandingMaster(TTID INTEGER, AMID INTEGER);";

    static SQLiteDatabase myDatabase;
    private static Context context;

    public AuroraLindaBritten(Context context) {

        this.context = context;
    }

    public void createDatabase() {
        try {
            myDatabase = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
            Log.e(TAG, "Step 1");
            myDatabase.execSQL(CREATE_AppMaster);
            myDatabase.execSQL(CREATE_TodayTrandingMaster);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myDatabase.close();
        }
    }

    public int getPIPMasterSize() {
        int counter = 0;
        String query = "SELECT * FROM AppMaster";
        try {
            myDatabase = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
            Cursor resultset = myDatabase.rawQuery(query, null);
            counter = resultset.getCount();
            resultset.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myDatabase.close();
        }
        return counter;
    }

    public ArrayList<AuroraAppDataBeans> getAppdata() {

        ArrayList<AuroraAppDataBeans> appDataBeanses = new ArrayList<>();

        String query = "SELECT * FROM AppMaster";
        try {
            myDatabase = context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
            Cursor resultset = myDatabase.rawQuery(query, null);
            while (resultset.moveToNext()) {
                appDataBeanses.add(new AuroraAppDataBeans(resultset.getString(0), resultset.getString(1), resultset.getString(2), resultset.getString(3), resultset.getString(4), resultset.getString(5)));
            }
            resultset.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myDatabase.close();
        }

        Collections.shuffle(appDataBeanses);

        return appDataBeanses;
    }

}
