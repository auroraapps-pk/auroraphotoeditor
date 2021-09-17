package com.auroraapps.auroraphotoeditor.AuroraDionLee;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.auroraapps.auroraphotoeditor.BuildConfig;
import com.auroraapps.auroraphotoeditor.AuroraCantista.AuroraJentalmenata;
import com.auroraapps.auroraphotoeditor.AuroraCantista.AuroraMantid;
import com.auroraapps.auroraphotoeditor.AuroraClaudiaChanShaw.AuroraLindaBritten;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.R;

public class AuroraBerilJents extends Fragment implements View.OnClickListener {


    private AuroraLindaBritten objdb;
    private ImageView iv_share;
    private ImageView iv_reta;
    private ImageView iv_privecy;

    public AuroraBerilJents() {
    }

    RelativeLayout RL_Main;
    LinearLayout Ll_Menu;
    ImageView LL_PhotoEditor, LL_LightLikes, ll_PhotoFrame, ll_Mywork, LL_PhotoFilter;


    public static int Counter = 0;

    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getFragmentManager();
        try {


            switch (v.getId()) {
                case R.id.LL_PhotoFilter:
                    AuroraMantid.Cat = 3;
                    AuroraMantid.counter = 1;
                    AuroraMantid.pickFromGallery();
                    break;
                case R.id.LL_PhotoEditor:
                    AuroraMantid.Cat = 2;
                    AuroraMantid.counter = 1;
                    AuroraMantid.pickFromGallery();
                    break;

                case R.id.LL_LightLikes:
                    AuroraMantid.Cat = 4;
                    AuroraMantid.counter = 1;
                    AuroraMantid.pickFromGallery();
                    break;

                case R.id.LL_PhotoFrame:
                    AuroraRebeccaJudd previewFragment = new AuroraRebeccaJudd();
                    fragmentManager.beginTransaction().replace(R.id.MainContainer, previewFragment).addToBackStack(null).commit();
                    break;

                case R.id.iv_share:

                    try {
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setType("text/plain");
                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                        String shareMessage = "\nLet me recommend you this application\n\n";
                        shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                        startActivity(Intent.createChooser(shareIntent, "choose one"));
                    } catch (Exception e) {
                    }
                    break;

                case R.id.iv_reta:

                    AuroraPatrickCox.ratingDialog(getActivity());
                    break;

                case R.id.iv_privecy:

                    AuroraStormKeating privacyPolicyFragment = new AuroraStormKeating();
                    fragmentManager.beginTransaction().replace(R.id.MainContainer, privacyPolicyFragment).addToBackStack(null).commit();
                    break;

                case R.id.LL_Mywork:
                    startActivity(new Intent(getActivity(), AuroraJentalmenata.class));
                    getActivity().finish();
                    getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main1, container, false);

        try {
            FindControls(rootView);

            objdb = new AuroraLindaBritten(getActivity());


        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;
    }

    private void FindControls(View view) {
        RL_Main = (RelativeLayout) view.findViewById(R.id.RL_Main);
        Ll_Menu = (LinearLayout) view.findViewById(R.id.Ll_Menu);

        LL_PhotoEditor = (ImageView) view.findViewById(R.id.LL_PhotoEditor);
        ll_Mywork = (ImageView) view.findViewById(R.id.LL_Mywork);
        LL_LightLikes = (ImageView) view.findViewById(R.id.LL_LightLikes);
        ll_PhotoFrame = (ImageView) view.findViewById(R.id.LL_PhotoFrame);
        LL_PhotoFilter = (ImageView) view.findViewById(R.id.LL_PhotoFilter);

        iv_share = (ImageView) view.findViewById(R.id.iv_share);
        iv_reta = (ImageView) view.findViewById(R.id.iv_reta);
        iv_privecy = (ImageView) view.findViewById(R.id.iv_privecy);


        iv_share.setOnClickListener(this);
        iv_reta.setOnClickListener(this);
        iv_privecy.setOnClickListener(this);

        ll_Mywork.setOnClickListener(this);
        LL_LightLikes.setOnClickListener(this);
        ll_PhotoFrame.setOnClickListener(this);
        LL_PhotoEditor.setOnClickListener(this);
        LL_PhotoFilter.setOnClickListener(this);

    }


}
