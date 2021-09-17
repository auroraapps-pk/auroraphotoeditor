package com.auroraapps.auroraphotoeditor.AuroraCantista;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.auroraapps.auroraphotoeditor.AuroraClaudiaChanShaw.AuroraLindaBritten;
import com.auroraapps.auroraphotoeditor.AuroraDovCharney.AuroraPatrickCox;
import com.auroraapps.auroraphotoeditor.AuroraLocalBaseActivity;
import com.auroraapps.auroraphotoeditor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.io.File;
import java.util.ArrayList;

public class AuroraPrexissesta extends AuroraLocalBaseActivity implements View.OnClickListener {
    private static final String ADMOB_AD_UNIT_ID = "ca-app-pub-1831280108269984~6556742594";

    private UnifiedNativeAd nativeAd;



    ImageView BackImage;
    Bundle bundle;

    private static Context mContext;
    ImageView imgWhatsup, imgfacebook, imgInsta, imgSnapchat, imgMoreApp, imgShare;

    RelativeLayout scrollView;
    private AuroraLindaBritten objDb;

    @Override
    public void onBackPressed() {
        showRatingDialog(true, new OnRateListner() {
            @Override
            public void onReminderLater() {
                startActivity(new Intent(getActivity(), AuroraMantid.class));
                getActivity().finish();
                overridePendingTransition(R.anim.right_in, R.anim.left_out);

            }
        });
    }

    public void SetLayout() {
        Display display = getWindowManager().getDefaultDisplay();
        int w = display.getWidth();

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, (w * 45) / 100, 0, 0);

    }

    @Override
    public void onClick(View v) {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        String imagePath = bundle.get("FinalURI").toString();
        ArrayList<Uri> streamUris = new ArrayList<Uri>();
        for (int i = 0; i < 10; i++) {
            File tmpFile = new File(imagePath);
            String authority = getPackageName() + ".fileprovider";
            Uri tmp = FileProvider.getUriForFile(AuroraPrexissesta.this,authority, tmpFile);
            streamUris.add(tmp);
        }
        switch (v.getId()) {
            case R.id.imgMoreApp:
                shareImage(streamUris);
                break;
            case R.id.imgShare:
                shareImage(streamUris);
                break;
            case R.id.BackImage:
                startActivity(new Intent(this, AuroraJentalmenata.class));
                this.finish();
                break;
            case R.id.imgButtonImage:
                startActivity(new Intent(getActivity(), AuroraMantid.class));
                finish();
                break;
            case R.id.imgWhatsup:
                if (!streamUris.isEmpty()) {
                    whatsappIntent.setType("*/*");
                    whatsappIntent.setPackage("com.whatsapp");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Download App From here : https://play.google.com/store/apps/details?id=" + getPackageName());
                    whatsappIntent.putExtra(Intent.EXTRA_STREAM, streamUris.get(0));
                    try {
                        startActivity(Intent.createChooser(whatsappIntent, "Share Image!"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(this, "Whatsapp have not been installed.", Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.imgInsta:
                if (!streamUris.isEmpty()) {
                    whatsappIntent.setType("*/*");
                    whatsappIntent.setPackage("com.instagram.android");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Download App From here : https://play.google.com/store/apps/details?id=" + getPackageName());
                    whatsappIntent.putExtra(Intent.EXTRA_STREAM, streamUris.get(0));
                    try {
                        startActivity(Intent.createChooser(whatsappIntent, "Share Image!"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(this, "Instagram have not been installed.", Toast.LENGTH_LONG).show();
                        shareImage(streamUris);
                    }
                }
                break;
            case R.id.imgSnapchat:
                if (!streamUris.isEmpty()) {
                    whatsappIntent.setType("*/*");
                    whatsappIntent.setPackage("com.snapchat.android");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Download App From here : https://play.google.com/store/apps/details?id=" + getPackageName());
                    whatsappIntent.putExtra(Intent.EXTRA_STREAM, streamUris.get(0));
                    try {
                        startActivity(Intent.createChooser(whatsappIntent, "Share Image!"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(this, "Snapchat have not been installed.", Toast.LENGTH_LONG).show();
                        shareImage(streamUris);
                    }
                }
                break;
            case R.id.imgfacebook:
                if (!streamUris.isEmpty()) {
                    whatsappIntent.setType("*/*");
                    whatsappIntent.setPackage("com.facebook.katana");
                    whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Download App From here : https://play.google.com/store/apps/details?id=" + getPackageName());
                    whatsappIntent.putExtra(Intent.EXTRA_STREAM, streamUris.get(0));
                    try {
                        startActivity(Intent.createChooser(whatsappIntent, "Share Image!"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(this, "Snapchat have not been installed.", Toast.LENGTH_LONG).show();
                        shareImage(streamUris);
                    }
                }

                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_image);

        objDb = new AuroraLindaBritten(getActivity());

        imgWhatsup = (ImageView) findViewById(R.id.imgWhatsup);
        imgfacebook = (ImageView) findViewById(R.id.imgfacebook);
        imgInsta = (ImageView) findViewById(R.id.imgInsta);
        imgSnapchat = (ImageView) findViewById(R.id.imgSnapchat);
        imgShare = (ImageView) findViewById(R.id.imgShare);
        imgMoreApp = (ImageView) findViewById(R.id.imgMoreApp);
        scrollView = (RelativeLayout) findViewById(R.id.scrollView);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        refreshAd();
        imgWhatsup.setOnClickListener(this);
        imgShare.setOnClickListener(this);
        imgfacebook.setOnClickListener(this);
        imgInsta.setOnClickListener(this);
        imgSnapchat.setOnClickListener(this);
        imgMoreApp.setOnClickListener(this);

        ImageView imgButtonImage = (ImageView) findViewById(R.id.imgButtonImage);
        imgButtonImage.setVisibility(View.VISIBLE);
        imgButtonImage.setOnClickListener(this);
        TextView txtHeaderName = (TextView) findViewById(R.id.txtHeaderName);
        bundle = getIntent().getExtras();
        BackImage = (ImageView) findViewById(R.id.BackImage);
        BackImage.setOnClickListener(this);

        mContext = AuroraPrexissesta.this;

        try {
            BackImage.setImageBitmap(AuroraPatrickCox.FinalBitmap);
            SetLayout();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void shareImage(ArrayList<Uri> uris) {

        try {
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("*/*");
            share.putExtra(Intent.EXTRA_TEXT, "Download App From here : https://play.google.com/store/apps/details?id=" + getPackageName());
            share.putExtra(Intent.EXTRA_STREAM, uris.get(0));
            startActivity(Intent.createChooser(share, "Share Image!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Animation animation;

    public static void SingleIn(final View view) {

        animation = AnimationUtils.loadAnimation(mContext, R.anim.holder_bottom_fast);
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


    /**
     * Populates a {@link UnifiedNativeAdView} object with data from a given
     * {@link UnifiedNativeAd}.
     *
     * @param nativeAd the object containing the ad's assets
     * @param adView          the view to be populated
     */
    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {
        // Set the media view. Media content will be automatically populated in the media view once
        // adView.setNativeAd() is called.
        MediaView mediaView = adView.findViewById(R.id.ad_media);
        adView.setMediaView(mediaView);

        // Set other ad assets.
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));

        // The headline is guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad. The SDK will populate the adView's MediaView
        // with the media content from this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
    }

    /**
     * Creates a request for a new native ad based on the boolean parameters and calls the
     * corresponding "populate" method when one is successfully returned.
     *
     */
    private void refreshAd() {

        AdLoader.Builder builder = new AdLoader.Builder(this, ADMOB_AD_UNIT_ID);

        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            // OnUnifiedNativeAdLoadedListener implementation.
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                // You must call destroy on old ads when you are done with them,
                // otherwise you will have a memory leak.
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                nativeAd = unifiedNativeAd;
                FrameLayout frameLayout =
                        findViewById(R.id.fl_adplaceholder);
                UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.adunity, null);
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout.removeAllViews();
                frameLayout.addView(adView);
            }

        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .setStartMuted(false)
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(AuroraPrexissesta.this, "Failed to load native ad: "
                        + errorCode, Toast.LENGTH_SHORT).show();
            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }

    @Override
    protected void onDestroy() {
        if (nativeAd != null) {
            nativeAd.destroy();
        }
        super.onDestroy();
    }

}
