package com.adxcorp.adxdev;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.adxcorp.ads.ADXConfiguration;
import com.adxcorp.ads.ADXSdk;
import com.adxcorp.ads.nativeads.AdxNativeAdFactory;
import com.adxcorp.ads.nativeads.AdxViewBinder;
import com.adxcorp.gdpr.ADXGDPR;
import com.adxcorp.util.ADXLogUtil;

public class MainActivity extends BaseActivity {

    private static final String TAG = "ADX:" + MainActivity.class.getSimpleName();

    public MainActivity() {
        super(R.layout.activity_main, R.id.cl_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initAction();

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

    private void initAction() {
        findViewById(R.id.btn_interstitial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInterstitial();
            }
        });

        findViewById(R.id.btn_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBanner();
            }
        });

        findViewById(R.id.btn_close_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCloseAd();
            }
        });

        findViewById(R.id.btn_native_close_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNativeCloseAd();
            }
        });

        findViewById(R.id.btn_native_ad_factory).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNativeAdFactory();
            }
        });

        findViewById(R.id.btn_native_ad_recycler_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNativeRecyclerView();
            }
        });

        findViewById(R.id.btn_rewarded_ad).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRewardedAd();
            }
        });

        findViewById(R.id.btn_news_banner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewsBanner();
            }
        });

        findViewById(R.id.btn_news_native).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewsNative();
            }
        });
    }

    private void initAdxNative() {
        AdxNativeAdFactory.init(this);

        // 네이티브 광고를 위한 ViewBinder 설정
        AdxNativeAdFactory.setAdxViewBinder(getString(R.string.native_unit_id), new AdxViewBinder.Builder(R.layout.layout_adx_native_ad)
                .mediaViewContainerId(R.id.mediaContainerId)
                .iconImageId(R.id.adIconId)
                .titleId(R.id.titleId)
                .adChoiceContainerId(R.id.adChoicesContainerId)
                .callToActionId(R.id.callToActionId)
                .build());

        // 뉴스 네이티브를 위한 ViewBinder 설정
        AdxNativeAdFactory.setAdxViewBinder(getString(R.string.news_native_unit_id), new AdxViewBinder.Builder(R.layout.layout_news_native_ad)
                .iconImageId(R.id.adIconId)
                .titleId(R.id.titleId)
                .textId(R.id.descriptionId)
                .adChoiceContainerId(R.id.adChoicesContainerId)
                .addExtra("news_title", R.id.news_title)
                .addExtra("news_icon", R.id.news_icon)
                .build());
    }

    void onInterstitial() {
        Intent intent = new Intent(this, InterstitialActivity.class);
        startActivity(intent);
    }

    void onBanner() {
        Intent intent = new Intent(this, BannerActivity.class);
        startActivity(intent);
    }

    void onCloseAd() {
        Intent intent = new Intent(this, CloseAdActivity.class);
        startActivity(intent);
    }

    void onNativeCloseAd() {
        Intent intent = new Intent(this, CloseNativeAdActivity.class);
        startActivity(intent);
    }

    void onNativeAdFactory() {
        Intent intent = new Intent(this, NativeAdFactoryActivity.class);
        startActivity(intent);
    }

    void onNativeRecyclerView() {
        Intent intent = new Intent(this, NativeAdRecyclerViewActivity.class);
        startActivity(intent);
    }

    void onRewardedAd() {
        Intent intent = new Intent(this, RewardedAdActivity.class);
        startActivity(intent);
    }

    void onNewsBanner() {
        Intent intent = new Intent(this, NewsBannerActivity.class);
        startActivity(intent);
    }

    void onNewsNative() {
        Intent intent = new Intent(this, NewsNativeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }
}