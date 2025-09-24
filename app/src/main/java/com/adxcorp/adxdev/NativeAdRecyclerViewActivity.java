package com.adxcorp.adxdev;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adxcorp.ads.nativeads.AdxNativeAdFactory;
import com.adxcorp.ads.nativeads.AdxRecyclerAdapter;
import com.adxcorp.ads.nativeads.event.NativeAdLoadedListener;
import com.adxcorp.ads.nativeads.position.NativeAdPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class NativeAdRecyclerViewActivity extends BaseActivity {

    private static final String TAG = "ADX:" + NativeAdRecyclerViewActivity.class.getSimpleName();

    private AdxRecyclerAdapter mRecyclerAdapter;
    private RecyclerView mRecyclerView;

    private List<String> mList = new ArrayList<>();

    private String mAdxUnitId;

    public NativeAdRecyclerViewActivity() {
        super(R.layout.activity_native_ad_recyclerview, R.id.content_main);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAdxUnitId = getString(R.string.native_unit_id);

        for (int i = 0; i < 150; i++) {
            mList.add(String.format(Locale.US, "Content Item #%d", i));
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.native_recycler_view);

        final RecyclerView.Adapter originalAdapter = new DemoRecyclerAdapter();

        NativeAdPosition.ClientPosition clientPosition = new NativeAdPosition.ClientPosition();
        clientPosition.addFixedPosition(2);
        clientPosition.enableRepeatingPositions(5);

        mRecyclerAdapter = AdxNativeAdFactory.getAdxRecyclerAdapter(this, originalAdapter, mAdxUnitId, clientPosition);
        mRecyclerAdapter.setAdLoadedListener(new NativeAdLoadedListener() {
            @Override
            public void onAdLoaded(int i) {
                Log.d(TAG, "onAdLoaded : " + i);
            }

            @Override
            public void onAdRemoved(int i) {
                Log.d(TAG, "onAdRemoved : " + i);
            }
        });
        mRecyclerAdapter.setContentChangeStrategy(AdxRecyclerAdapter.ContentChangeStrategy.MOVE_ALL_ADS_WITH_CONTENT);

        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

//        mRecyclerAdapter.loadAds(DefineAdUnitId.NATIVE_AD_UNIT_ID);
        mRecyclerAdapter.loadAds(mAdxUnitId);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecyclerAdapter.destroy();
    }

    private class DemoRecyclerAdapter extends RecyclerView.Adapter<DemoViewHolder> {
        private static final int ITEM_COUNT = 150;

        @Override
        public DemoViewHolder onCreateViewHolder(final ViewGroup parent,
                                                 final int viewType) {
            final View itemView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new DemoViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final DemoViewHolder holder, final int position) {
            holder.textView.setText(mList.get(position));
        }

        @Override
        public long getItemId(final int position) {
            return (long) position;
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    /**
     * A view holder for R.layout.simple_list_item_1
     */
    private static class DemoViewHolder extends RecyclerView.ViewHolder {
        public final TextView textView;

        public DemoViewHolder(final View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }
}
