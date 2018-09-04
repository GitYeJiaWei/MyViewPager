package com.example.yjw.recyclerview;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yjw.listview.Fruit;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends BaseActivity {
    private List<Fruit> fruitList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initFruits();//初始化水果数据

        RecyclerView recyclerView =findViewById(R.id.recycle_view);
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter =new FruitAdapter(fruitList);
        recyclerView.setAdapter(adapter);

    }

    private void initFruits(){
        for (int i=0;i<8;i++){
            Fruit a =new Fruit("a",R.mipmap.ket);
            fruitList.add(a);
            Fruit b =new Fruit("b",R.mipmap.ket);
            fruitList.add(b);
            Fruit c =new Fruit("c",R.mipmap.ket);
            fruitList.add(c);
            Fruit d =new Fruit("d",R.mipmap.ket);
            fruitList.add(d);
            Fruit f =new Fruit("f",R.mipmap.ket);
            fruitList.add(f);
        }
    }
}
