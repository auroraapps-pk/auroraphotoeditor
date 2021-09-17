package com.auroraapps.auroraphotoeditor.AuroraCantista;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.auroraapps.auroraphotoeditor.AuroraLocalBaseActivity;
import com.auroraapps.auroraphotoeditor.R;

import java.io.File;


public class AuroraZeqale extends AuroraLocalBaseActivity implements View.OnClickListener, View.OnTouchListener {

    Bundle bundle;
    ImageView imgMain;

    public static ProgressDialog dia;

    LinearLayout LL_Done, mainlayout;
    TextView txtHeaderName;
    ImageView imgButtonImage;

    View header;

    private void shareImage() {

        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("*/*");
        share.putExtra(Intent.EXTRA_TEXT, "Download App From here : https://play.google.com/store/apps/details?id=" + getPackageName());
        String path = Environment.getExternalStorageDirectory().toString();
        File file1 = new File(path + "/RPMovieFXPhoto/Gallery/" + bundle.get("FileName"));
        Uri uri = Uri.fromFile(file1);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        startActivity(Intent.createChooser(share, "Share Image!"));
    }

    public void showProgress() {

        dia = new ProgressDialog(this);
        dia.setMessage("Loading ...");
        dia.setIndeterminate(false);
        dia.setCancelable(false);
        dia.setCanceledOnTouchOutside(false);
        dia.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery_fullview);

        showProgress();

        LL_Done = (LinearLayout) findViewById(R.id.LL_Done);
        txtHeaderName = (TextView) findViewById(R.id.txtHeaderName);
        imgButtonImage = (ImageView) findViewById(R.id.imgButtonImage);

        LL_Done.setVisibility(View.VISIBLE);
        txtHeaderName.setText("My Gallery ");
        txtHeaderName.setTextSize(16);
        txtHeaderName.setGravity(Gravity.CENTER);
        imgButtonImage.setImageResource(R.drawable.ic_share);

        bundle = getIntent().getExtras();
        imgMain = (ImageView) findViewById(R.id.imgGallerImageView);

        header = (View) findViewById(R.id.header);

        fillData();

        LL_Done.setOnClickListener(this);

        imgMain.setOnTouchListener(this);

    }

    public void dismissProgress() {
        if (dia.isShowing())
            dia.dismiss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LL_Done:
                shareImage();
                break;
        }
    }

    private void fillData() {
        String path = Environment.getExternalStorageDirectory().toString();
        File file1 = new File(path + "/RPMovieFXPhoto/Gallery/" + bundle.get("FileName"));
        if (file1.exists()) {
            imgMain.setImageURI(Uri.fromFile(file1));
        }
        dismissProgress();
    }

    int Counter = 0;


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                    if(Counter == 0){
                        SingleHeaderOut(header);
                        Counter = 1;
                    }else {
                        SingleHeaderIn(header);
                        Counter = 0;
                    }
                break;

        }

        return false;
    }


    private static Animation animation;
    public void SingleHeaderOut(final View view) {
        animation = AnimationUtils.loadAnimation(this, R.anim.header_bottom_back_fast);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animation.setAnimationListener(null);
                view.clearAnimation();
                view.setVisibility(View.GONE);
            }

        });
    }

    public void SingleHeaderIn(final View view) {

        animation = AnimationUtils.loadAnimation(this, R.anim.header_bottom_fast);
        view.startAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation arg0) {
            }

            @Override
            public void onAnimationRepeat(Animation arg0) {
            }

            @Override
            public void onAnimationEnd(Animation arg0) {
                animation.setAnimationListener(null);
                view.clearAnimation();
                view.setVisibility(View.VISIBLE);
            }

        });
    }

}
