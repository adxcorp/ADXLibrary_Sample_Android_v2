package com.adxcorp.adxdev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.ads.ADXConfiguration;
import com.adxcorp.ads.ADXSdk;
import com.adxcorp.ads.nativeads.AdxNativeAdFactory;
import com.adxcorp.ads.nativeads.AdxViewBinder;
import com.adxcorp.gdpr.ADXGDPR;
import com.adxcorp.util.ADXLogUtil;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ADX:" + MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // ADX 로그 활성화
        ADXLogUtil.setLogEnable(true);

        // ADX 초기화 관련 설정
        ADXConfiguration adxConfiguration = new ADXConfiguration.Builder()
                .setAppId(getString(R.string.adx_app_id))
                .setGdprType(ADXConfiguration.GdprType.POPUP_LOCATION)
                .build();

        // ADX Native AD 초기화
        initAdxNative();

        ADXSdk.getInstance().initialize((Activity) this, adxConfiguration, new ADXSdk.OnInitializedListener() {
            @Override
            public void onCompleted(boolean result, ADXGDPR.ADXConsentState adxConsentState) {
                // 광고 초기화 완료
            }
        });
    }

    private void initAdxNative() {
        AdxNativeAdFactory.init(this);

        AdxNativeAdFactory.setAdxViewBinder(getString(R.string.native_unit_id), new AdxViewBinder.Builder(R.layout.layout_adx_native_ad)
                .mediaViewContainerId(R.id.mediaContainerId)
                .iconImageId(R.id.adIconId)
                .titleId(R.id.titleId)
                .adChoiceContainerId(R.id.adChoicesContainerId)
                .callToActionId(R.id.callToActionId)
                .build());
    }

    @OnClick(R.id.btn_interstitial)
    void onInterstitial() {
        Intent intent = new Intent(this, InterstitialActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_banner)
    void onBanner() {
        Intent intent = new Intent(this, BannerActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_close_ad)
    void onCloseAd() {
        Intent intent = new Intent(this, CloseAdActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_native_close_ad)
    void onNativeCloseAd() {
        Intent intent = new Intent(this, CloseNativeAdActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_native_ad_factory)
    void onNativeAdFactory() {
        Intent intent = new Intent(this, NativeAdFactoryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_native_ad_recycler_view)
    void onNativeRecyclerView() {
        Intent intent = new Intent(this, NativeAdRecyclerViewActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_rewarded_ad)
    void onRewardedAd() {
        Intent intent = new Intent(this, RewardedAdActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}