package com.adxcorp.adxdev;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.ads.NewsBannerAd;
import com.adxcorp.ads.NewsViewBinder;
import com.adxcorp.ads.common.AdConstants;

public class NewsBannerActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + NewsBannerActivity.class.getSimpleName();

    private NewsBannerAd bannerAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_banner);

        NewsViewBinder viewBinder = new NewsViewBinder.Builder(R.layout.layout_news_banner)
                .setBannerId(R.id.banner)
                .setNewsTitleId(R.id.news_title)
                .setNewsIconId(R.id.news_icon)
                .build();

        ViewGroup adContainer = findViewById(R.id.ad_container);
        bannerAd = new NewsBannerAd(this, getString(R.string.news_banner_unit_id), AdConstants.BANNER_AD_SIZE.AD_SIZE_320x50, viewBinder, adContainer);
        bannerAd.setNewsBannerListener(new NewsBannerAd.NewsBannerListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "NewsBanner onAdLoaded");
                Toast.makeText(NewsBannerActivity.this, "onAdLoaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdError(int errorCode) {
                Log.d(TAG, "NewsBanner onAdError : " + errorCode);
                Toast.makeText(NewsBannerActivity.this, "onAdError : " + errorCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked() {
                Log.d(TAG, "NewsBanner onAdClicked");
            }

            @Override
            public void onNewsClicked() {
                Log.d(TAG, "NewsBanner onNewsClicked");
            }
        });

        Log.d(TAG, "NewsBanner loadAd");
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