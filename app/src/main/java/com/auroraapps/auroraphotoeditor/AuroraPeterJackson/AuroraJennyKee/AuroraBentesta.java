package com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraJennyKee;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.auroraapps.auroraphotoeditor.AuroraLocalBaseActivity;
import com.auroraapps.auroraphotoeditor.AuroraPeterJackson.AuroraAnthonyPittorino.AuroraNevilleQuist;
import com.auroraapps.auroraphotoeditor.R;

public class AuroraBentesta extends AuroraLocalBaseActivity {


    public static AppCompatActivity activity;

    public static void startWithUri(Context context, Uri _uri) {
        Intent intent = new Intent(context, AuroraBentesta.class);
        intent.setData(_uri);
        context.startActivity(intent);
//        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    @Override
    public void onBackPressed() {

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.MainContainer);
//        super.onBackPressed();
        if (fragment instanceof AuroraNevilleQuist) {
            AuroraNevilleQuist.ManageBackpraced();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        activity = AuroraBentesta.this;

        FragmentManager fragmentManager = getSupportFragmentManager();
        AuroraNevilleQuist blankFragment = new AuroraNevilleQuist();
        fragmentManager.beginTransaction().replace(R.id.MainContainer, blankFragment).addToBackStack(null).commit();

    }
}
