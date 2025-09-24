package com.adxcorp.adxdev;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.ads.InterstitialAd;

public class InterstitialActivity extends BaseActivity {

    private static final String TAG = "ADX:" + InterstitialActivity.class.getSimpleName();

    private InterstitialAd interstitialAd;

    public InterstitialActivity() {
        super(R.layout.activity_fullscreen, R.id.cl_content);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (interstitialAd != null && interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
            }
        });

        interstitialAd = new InterstitialAd(this, getString(R.string.interstitial_unit_id));
        interstitialAd.setInterstitialListener(new InterstitialAd.InterstitialListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "Interstitial onAdLoaded");
                Toast.makeText(InterstitialActivity.this, "onAdLoaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdError(int errorCode) {
                Log.d(TAG, "Interstitial onAdError : " + errorCode);
                Toast.makeText(InterstitialActivity.this, "onAdError : " + errorCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked() {
                Log.d(TAG, "Interstitial onAdClicked");
            }

            @Override
            public void onAdImpression() {
                Log.d(TAG, "Interstitial onAdImpression");
            }

            @Override
            public void onAdClosed() {
                Log.d(TAG, "Interstitial onAdClosed");
            }

            @Override
            public void onAdFailedToShow() {
                Log.d(TAG, "Interstitial onAdFailedToShow");
            }
        });
        Log.d(TAG, "Interstitial loadAd");
        interstitialAd.loadAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (interstitialAd != null) {
            interstitialAd.destroy();
            interstitialAd = null;
        }
    }
}