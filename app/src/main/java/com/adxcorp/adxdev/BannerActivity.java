package com.adxcorp.adxdev;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.adxcorp.ads.BannerAd;
import com.adxcorp.ads.common.AdConstants;

public class BannerActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + BannerActivity.class.getSimpleName();

    private BannerAd bannerAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);

        bannerAd = findViewById(R.id.banner);
        bannerAd.setBannerListener(new BannerAd.BannerListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "Banner onAdLoaded");
                Toast.makeText(BannerActivity.this, "onAdLoaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdError(int errorCode) {
                Log.d(TAG, "Banner onAdError : " + errorCode);
                Toast.makeText(BannerActivity.this, "onAdError : " + errorCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked() {
                Log.d(TAG, "Banner onAdClicked");
            }
        });

        Log.d(TAG, "Banner loadAd");
        bannerAd.loadAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (bannerAd != null) {
            bannerAd.destroy();
            bannerAd = null;
        }
    }
}