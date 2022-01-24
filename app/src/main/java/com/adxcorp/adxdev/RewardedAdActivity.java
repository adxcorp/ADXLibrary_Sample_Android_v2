package com.adxcorp.adxdev;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.ads.RewardedAd;

public class RewardedAdActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + RewardedAdActivity.class.getSimpleName();

    private RewardedAd rewardedAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedAd != null) {
                    rewardedAd.show();
                }
            }
        });

        rewardedAd = new RewardedAd(this, getString(R.string.rewarded_ad_unit_id));
        rewardedAd.setRewardedAdListener(new RewardedAd.RewardedAdListener() {
            @Override
            public void onAdLoaded() {
                Log.d(TAG, "RewardedAd onAdLoaded");
                Toast.makeText(RewardedAdActivity.this, "onAdLoaded", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdError(int errorCode) {
                Log.d(TAG, "RewardedAd onAdError : " + errorCode);
                Toast.makeText(RewardedAdActivity.this, "onAdError : " + errorCode, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onAdClicked() {
                Log.d(TAG, "RewardedAd onAdClicked");
            }

            @Override
            public void onAdImpression() {
                Log.d(TAG, "RewardedAd onAdImpression");
            }

            @Override
            public void onAdClosed() {
                Log.d(TAG, "RewardedAd onAdClosed");
            }

            @Override
            public void onAdRewarded() {
                Log.d(TAG, "RewardedAd onAdRewarded");
            }

            @Override
            public void onAdFailedToShow() {
                Log.d(TAG, "RewardedAd onAdFailedToShow");
            }
        });

        Log.d(TAG, "RewardedAd loadAd");
        rewardedAd.loadAd();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (rewardedAd != null) {
            rewardedAd.destroy();
            rewardedAd = null;
        }
    }
}