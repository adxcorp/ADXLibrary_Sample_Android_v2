package com.adxcorp.adxdev;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.ads.nativeads.AdxNativeAdFactory;

public class NativeAdFactoryActivity extends BaseActivity {

    private static final String TAG = "ADX:" + NativeAdFactoryActivity.class.getSimpleName();

    LinearLayout mContentView;

    private View mAdView;
    private com.adxcorp.ads.nativeads.NativeAd mNativeAd;
    private String mAdxUnitId;

    public NativeAdFactoryActivity() {
        super(R.layout.activity_native_ad_factory, R.id.content_main);
    }

    private final AdxNativeAdFactory.NativeAdListener mListener = new AdxNativeAdFactory.NativeAdListener() {
        @Override
        public void onSuccess(String s, com.adxcorp.ads.nativeads.NativeAd nativeAd) {
            Log.d(TAG, "onSuccess");
            if (mAdxUnitId.equals(s)) {
                mNativeAd = nativeAd;
                mAdView = AdxNativeAdFactory.getNativeAdView(
                        NativeAdFactoryActivity.this,
                        mAdxUnitId,
                        mContentView,
                        new com.adxcorp.ads.nativeads.NativeAd.NativeEventListener() {
                            @Override
                            public void onImpression(View view) {
                                Log.d(TAG, "onImpression");
                            }

                            @Override
                            public void onClick(View view) {
                                Log.d(TAG, "onClick");
                            }
                        });
                mContentView.addView(mAdView);
            }
        }

        @Override
        public void onFailure(String s) {
            Log.d(TAG, "onFailure");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdxUnitId = getString(R.string.native_unit_id);

        mContentView = findViewById(R.id.content_main);

        AdxNativeAdFactory.addListener(mListener);
        AdxNativeAdFactory.loadAd(mAdxUnitId);
    }

    @Override
    protected void onDestroy() {
        AdxNativeAdFactory.removeListener(mListener);

        if (mNativeAd != null) {
            mNativeAd.destroy();
            mNativeAd = null;
        }

        super.onDestroy();
    }
}
