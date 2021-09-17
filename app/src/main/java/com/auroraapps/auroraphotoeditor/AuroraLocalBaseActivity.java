package com.auroraapps.auroraphotoeditor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraDeanandDanCaten;


public class AuroraLocalBaseActivity extends AppCompatActivity {


    private static final int REQUEST_PLAY_STORE = 0x99;
    private final String TAG = getClass().getName();
    private OnRateListner onRateListner;

    public FragmentActivity getActivity() {
        return this;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public interface OnRateListner {
        void onReminderLater();
    }

    public void showRatingDialog(final boolean isAdsShown, OnRateListner onRateListner1) {
        onRateListner = onRateListner1;
        final AuroraDeanandDanCaten objPref = new AuroraDeanandDanCaten(getActivity());

        if (objPref.getisRatingDialog() <= 10) {


            final Dialog alertDialogBuilder = new Dialog(getActivity());
//        alertDialogBuilder.setTitle("Select Language");
            // set prompts.xml to alertdialog builder
            alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
            alertDialogBuilder.setContentView(R.layout.rating_dialog);

            final TextView btncancel = (TextView) alertDialogBuilder.findViewById(R.id.btncancel);
            final TextView btnsubmit = (TextView) alertDialogBuilder.findViewById(R.id.btnsubmit);
            final RatingBar ratingbar = (RatingBar) alertDialogBuilder.findViewById(R.id.ratingbar);

            ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//                finalratings = rating;
                }
            });

            btncancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isAdsShown) {
                        alertDialogBuilder.dismiss();
                        alertDialogBuilder.cancel();
//                objPref.setisRatingDialog("true");
                        onRateListner.onReminderLater();
                    } else {
                        alertDialogBuilder.dismiss();
                        alertDialogBuilder.cancel();
//                objPref.setisRatingDialog("true");
                        onRateListner.onReminderLater();
                    }

                }
            });

            btnsubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialogBuilder.dismiss();
                    objPref.setisRatingDialog(objPref.getisRatingDialog() + 1);
                    Intent i3 = new Intent(Intent.ACTION_VIEW, Uri
                            .parse("market://details?id=" + getActivity().getPackageName()));
                    getActivity().startActivityForResult(i3, REQUEST_PLAY_STORE);
                }
            });

            alertDialogBuilder.setCancelable(false);

            // create alert dialog

            // show it
            alertDialogBuilder.show();
        } else {
            onRateListner.onReminderLater();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_PLAY_STORE) {
            if (onRateListner != null) {
                onRateListner.onReminderLater();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void showResetDialog(Context context, final OnResetListner onResetListner) {

        final Dialog alertDialogBuilder = new Dialog(getActivity());
//        alertDialogBuilder.setTitle("Select Language");
        // set prompts.xml to alertdialog builder
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialogBuilder.getWindow().setBackgroundDrawableResource(R.drawable.round_corner_bg);
        alertDialogBuilder.setContentView(R.layout.reset_dialog);

        final TextView btnNo = (TextView) alertDialogBuilder.findViewById(R.id.btnNo);
        final TextView btnYes = (TextView) alertDialogBuilder.findViewById(R.id.btnYes);



        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialogBuilder.dismiss();
                alertDialogBuilder.cancel();
//                objPref.setisRatingDialog("true");
            }
        });

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.dismiss();
                onResetListner.onReset();
            }
        });

        alertDialogBuilder.setCancelable(false);

        // create alert dialog

        // show it
        alertDialogBuilder.show();
    }

    public interface OnResetListner {
        void onReset();
    }
}
