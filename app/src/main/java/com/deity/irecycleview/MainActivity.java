package com.deity.irecycleview;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.refresh_id)
    private SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.recycleview_test)
    private RecyclerView  recyclerView;
    List<String> mList;
    HeaderViewRecyclerAdapter stringAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        swipeRefreshLayout = (SwipeRefreshLayout) this.findViewById(R.id.refresh_id);
        recyclerView = (RecyclerView) this.findViewById(R.id.recycleview_test);
        swipeRefreshLayout.setColorSchemeResources(
                R.color.colorAccent,
                R.color.colorPrimary,
                R.color.colorPrimaryDark
        );
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        setData();
        BaseRecycleViewAdapter refreshAdapter = new BaseRecycleViewAdapter(mList, this);

        stringAdapter = new HeaderViewRecyclerAdapter(refreshAdapter);
        recyclerView.setAdapter(stringAdapter);
        createLoadMoreView();

        swipeRefreshLayout.setOnRefreshListener(this);

        recyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int currentPage) {
                simulateLoadMoreData();
            }
        });
    }

    private void createLoadMoreView() {
        View loadMoreView = LayoutInflater
                .from(MainActivity.this)
                .inflate(R.layout.load_more_view, recyclerView, false);
        stringAdapter.addFooterView(loadMoreView);
    }


    private void setData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("第" + i + "个");
        }
    }

    private void simulateLoadMoreData(){
        for (int i=0;i<30;i++){
            mList.add("New load->第" + i + "个");
        }
//        stringAdapter.notifyDataSetChanged();
    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

}
