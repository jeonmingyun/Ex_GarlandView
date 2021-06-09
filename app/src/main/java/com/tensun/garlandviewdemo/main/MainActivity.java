package com.tensun.garlandviewdemo.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.ramotion.garlandview.TailLayoutManager;
import com.ramotion.garlandview.TailRecyclerView;
import com.ramotion.garlandview.TailSnapHelper;
import com.ramotion.garlandview.header.HeaderTransformer;
import com.tensun.garlandviewdemo.GarlandApp;
import com.tensun.garlandviewdemo.R;
import com.tensun.garlandviewdemo.details.DetailsActivity;
import com.tensun.garlandviewdemo.details.DetailsData;
import com.tensun.garlandviewdemo.main.inner.InnerData;
import com.tensun.garlandviewdemo.main.inner.InnerItem;
import com.tensun.garlandviewdemo.main.outer.OuterAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

/**
 * Q: Faker 是什麼?
 * A: Java库生成用于测试或填充开发数据库的假数据
 */
public class MainActivity extends AppCompatActivity {

    private final static int OUTER_COUNT = 10;
    private final static int INNER_COUNT = 20;

    private Images images = new Images();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setListItem();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    public void setListItem() {
        final List<List<InnerData>> outerData = new ArrayList<>();
        for (int i = 0; i < OUTER_COUNT; i++) {
            final List<InnerData> innerData = new ArrayList<>();
            for (int j = 0; j < INNER_COUNT; j++) {
                innerData.add(createInnerData(j));
            }
            outerData.add(innerData);
        }

        initRecyclerView(outerData);
    }

    private void initRecyclerView(List<List<InnerData>> data) {
        findViewById(R.id.progressBar).setVisibility(View.GONE);

        final TailRecyclerView rv = (TailRecyclerView) findViewById(R.id.recycler_view);
        rv.setLayoutManager(
                new TailLayoutManager(this).setPageTransformer(new HeaderTransformer()));
        rv.setAdapter(new OuterAdapter(data));

        new TailSnapHelper().attachToRecyclerView(rv);
    }

    private InnerData createInnerData(int j) {
        return new InnerData(
                "inner title"+j,
                "inner txt"+j,
                "inner address+j",
                images.randomImages(),
                22
        );
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void OnInnerItemClick(InnerItem item) {
        final InnerData itemData = item.getItemData();
        if (itemData == null) {
            return;
        }

        DetailsActivity.start(
                this,
                item.getItemData().name,
                item.mAddress.getText().toString(),
                item.getItemData().avatarUrl,
                item.itemView,
                item.mAvatarBorder
        );
    }
}
