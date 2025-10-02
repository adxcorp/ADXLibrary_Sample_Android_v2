package com.adxcorp.adxdev;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.OnBackPressedCallback;

import com.adxcorp.ads.nativeads.AdxCloseAdFactory;

public class CloseNativeAdActivity extends BaseActivity {

    private static final String TAG = "ADX:" + CloseNativeAdActivity.class.getSimpleName();

    public CloseNativeAdActivity() {
        super(R.layout.activity_empty, R.id.cl_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AdxCloseAdFactory.init(this, getString(R.string.native_close_unit_id), "");
        AdxCloseAdFactory.preloadAd();

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                AdxCloseAdFactory.showCloseAd(CloseNativeAdActivity.this, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "onClick positive button");

                        finish();
                    }
                }, new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        Log.d(TAG, "onCancel");
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        // Optional
        // AdxCloseAdFactory.destroy();

        super.onDestroy();
    }
}
