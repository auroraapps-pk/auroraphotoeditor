package com.auroraapps.auroraphotoeditor.AuroraCantista;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.auroraapps.auroraphotoeditor.AuroraClaudiaChanShaw.AuroraLindaBritten;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraDeanandDanCaten;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraSimonChang;
import com.auroraapps.auroraphotoeditor.AuroraJuliGrbac.AuroraFrederickFox.AuroraAlannahHill;
import com.auroraapps.auroraphotoeditor.R;


public class AuroraLajavab extends Activity {

    AuroraAlannahHill cd;

    public static String name = "";
    AuroraDeanandDanCaten objPref;
    AuroraLindaBritten objDb;
    private boolean isFirstTime = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        objPref = new AuroraDeanandDanCaten(AuroraLajavab.this);
        AuroraPatrickCox.SDCardPath = objPref.getSaveDirURL();
        cd = new AuroraAlannahHill(this);
        objDb = new AuroraLindaBritten(AuroraLajavab.this);


        new BASE_AGREEMENT_CIPHER().execute();
        try {
            if (cd.isConnectingToInternet()) {
                Log.e("Service", "Started from splash");
            } else {

            }
            callHome();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public class BASE_AGREEMENT_CIPHER extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {
            try {
                AuroraSimonChang.CIPHER_TO_DHKEY_AGREEMENT_256BIT_KEYGENERATOR("oauthentication_base64_hash64", "MODE_BASE_24_PRIVATE", getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }
    }


    public void callHome() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (isFirstTime) {
                    isFirstTime = false;
                    Intent localIntent = new Intent(AuroraLajavab.this, AuroraMantid.class);
                    AuroraLajavab.this.startActivity(localIntent);
                    finish();
                    overridePendingTransition(R.anim.right_in, R.anim.left_out);
                }
            }
        }, 500);
    }



}
