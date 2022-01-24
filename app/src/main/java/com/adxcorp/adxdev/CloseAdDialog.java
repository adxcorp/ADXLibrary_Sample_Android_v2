package com.adxcorp.adxdev;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.adxcorp.ads.BannerAd;

public class CloseAdDialog extends Dialog {
    public CloseAdDialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.close_ad_dialog_layout);

        mContentLayout = (RelativeLayout) findViewById(R.id.contentLayout);

        if (mBannerAd != null) {
            mContentLayout.addView(mBannerAd);
        }

        mNegativeButton = (TextView) findViewById(R.id.negativeButton);
        mNegativeButton.setOnClickListener(mNegativeButtonListener);
        mPositiveButton = (TextView) findViewById(R.id.positiveButton);
        mPositiveButton.setOnClickListener(mPositiveButtonListener);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    public void setPositiveButtonClickListener(final OnClickListener listener) {
        mPositiveButtonListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(CloseAdDialog.this, Dialog.BUTTON_POSITIVE);
                }
                dismiss();
            }
        };
    }

    public void setNegativeButtonClickListener(final OnClickListener listener) {
        mNegativeButtonListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(CloseAdDialog.this, Dialog.BUTTON_NEGATIVE);
                }
                dismiss();
            }
        };
    }

    public void setBannerAd(BannerAd bannerAd) {
        mBannerAd = bannerAd;
    }

    public void setView(View view) {

    }

    public static class Builder {
        public Builder(Context context) {
            mContext = context;
        }

        public Builder setBannerAd(BannerAd bannerAd) {
            mBannerAd = bannerAd;
            return this;
        }

        public CloseAdDialog create() {
            final CloseAdDialog dialog = new CloseAdDialog(mContext);
            dialog.setNegativeButtonClickListener(mNegativeButtonListener);
            dialog.setPositiveButtonClickListener(mPositiveButtonListener);
            dialog.setBannerAd(mBannerAd);
            return dialog;
        }

        private Context mContext;
        private OnClickListener mNegativeButtonListener;
        private OnClickListener mPositiveButtonListener;

        private BannerAd mBannerAd;
    }

    private TextView mNegativeButton;
    private TextView mPositiveButton;

    private View.OnClickListener mPositiveButtonListener;
    private View.OnClickListener mNegativeButtonListener;

    private BannerAd mBannerAd;

    private RelativeLayout mContentLayout;
}