package com.adxcorp.adxdev;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.ads.BannerAd;
import com.adxcorp.ads.common.AdConstants;

public class CloseAdActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + CloseAdActivity.class.getSimpleName();

    private BannerAd mBannerAd;
    private CloseAdDialog mCloseDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            if (mBannerAd == null) {
                mBannerAd = new BannerAd(this, getString(R.string.mrec_unit_id), AdConstants.BANNER_AD_SIZE.AD_SIZE_300x250);
                mBannerAd.setBannerListener(new BannerAd.BannerListener() {
                    @Override
                    public void onAdLoaded() {
                        Log.d(TAG, "Banner onAdLoaded");
                        mBannerAd.setTag(1);
                    }

                    @Override
                    public void onAdError(int errorCode) {
                        Log.d(TAG, "Banner onAdError : " + errorCode);
                    }

                    @Override
                    public void onAdClicked() {
                        Log.d(TAG, "Banner onAdClicked");
                    }
                });
                mBannerAd.loadAd();

                mCloseDialog = new CloseAdDialog.Builder(this).setBannerAd(mBannerAd).create();
                mCloseDialog.setPositiveButtonClickListener(new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (mCloseDialog == null) {
            super.onBackPressed();
        } else {
            mCloseDialog.show();
        }
    }

    @Override
    protected void onDestroy() {
        if (mBannerAd != null) {
            mBannerAd.destroy();
            mBannerAd = null;
        }

        super.onDestroy();
    }
}
