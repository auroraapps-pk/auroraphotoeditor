package com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraAmberRenae;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.auroraapps.auroraphotoeditor.AuroraLocalBaseActivity;
import com.auroraapps.auroraphotoeditor.AuroraPentagon.AuroraSophiaTolli.AuroraAndyTruong;
import com.auroraapps.auroraphotoeditor.R;

public class AuroraTempoll extends AuroraLocalBaseActivity {

    public static AppCompatActivity activity;
    public static Bitmap bmpBlend = null;

    public static void startWithUri(Context context, Uri _uri) {
        Intent intent = new Intent(context, AuroraTempoll.class);
        intent.setData(_uri);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_photoeditor);

        activity = AuroraTempoll.this;

        AuroraAndyTruong mainFragment = new AuroraAndyTruong();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.Container, mainFragment).commit();

    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.Container);
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragment instanceof AuroraAndyTruong) {
            AuroraAndyTruong.ManageBackPrace();
        } else {
            super.onBackPressed();
        }
    }

}
