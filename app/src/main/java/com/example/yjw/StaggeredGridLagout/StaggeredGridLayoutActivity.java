package com.example.yjw.StaggeredGridLagout;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.example.yjw.listview.Fruit;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StaggeredGridLayoutActivity extends AppCompatActivity {
    private List<Fruit> fruitList =new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private FruitAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staggered_grid_layout);

        initFruits();
        RecyclerView recyclerView =findViewById(R.id.recycle_view);
        StaggeredGridLayoutManager staggeredGridLayoutManager =new
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        adapter =new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout =findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshfruit();
            }
        });


    }

    private void onRefreshfruit(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }

    private void initFruits(){
        for (int i=0;i<8;i++){
            Fruit a =new Fruit(getRand("a"),R.mipmap.ket);
            fruitList.add(a);
            Fruit b =new Fruit(getRand("b"),R.mipmap.ket);
            fruitList.add(b);
            Fruit c =new Fruit(getRand("c"),R.mipmap.ket);
            fruitList.add(c);
            Fruit d =new Fruit(getRand("d"),R.mipmap.ket);
            fruitList.add(d);
            Fruit f =new Fruit(getRand("f"),R.mipmap.ket);
            fruitList.add(f);
        }
    }

    private String getRand(String name){
        Random random =new Random();
        int length =random.nextInt(20)+1;
        StringBuilder stringBuilder =new StringBuilder();
        for(int i=0;i<length;i++){
            stringBuilder.append(name);
        }
        return  stringBuilder.toString();
    }
}
