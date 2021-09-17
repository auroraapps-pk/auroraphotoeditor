package com.auroraapps.auroraphotoeditor.AuroraUtil;

import android.content.Context;

import com.auroraapps.auroraphotoeditor.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AuroraAds {


    public static InterstitialAd   mInterstitialAd;

    public static void LoadAd(Context context){
        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId(context.getResources().getString(R.string.interstitial));
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public static void Loadd(final Ad_lisoner ad_lisoner){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    // Code to be executed when an ad request fails.
                    ad_lisoner.onun();
                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when the ad is displayed.
                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                }

                @Override
                public void onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the interstitial ad is closed.
                    mInterstitialAd.loadAd(new AdRequest.Builder().build());
                    ad_lisoner.onSucssec(mInterstitialAd);
                }
            });
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
            } else {
                ad_lisoner.onun();
            }
        } else {
            ad_lisoner.onun();
        }
    }

    public interface Ad_lisoner {
        void onSucssec(InterstitialAd mInterstitialAd);
        void onun();
    }

}
