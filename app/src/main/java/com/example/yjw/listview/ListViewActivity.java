package com.example.yjw.listview;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * listview 下拉刷新 上拉加载 点击返回顶部 长点击事件删除
 */
public class ListViewActivity extends BaseActivity {
    //private String[] data ={"a","b","c","d","e","f","g","h","i","j"};
    private List<Fruit> fruitList =new ArrayList<>();
    private Button mBtn;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        initFruit();
        mBtn =findViewById(R.id.btn);
        FruitAdapter fruitAdapter =new FruitAdapter(ListViewActivity.this,R.layout.fruit_item,fruitList);

       /* ArrayAdapter<String> adapter =new ArrayAdapter<String>(
                ListViewActivity.this,android.R.layout.simple_list_item_1,data);*/
        listView =findViewById(R.id.list_view);
        listView.setAdapter(fruitAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit =fruitList.get(position);
                Toast.makeText(ListViewActivity.this,
                        fruit.getName(),Toast.LENGTH_LONG).show();
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem>=2){
                    mBtn.setVisibility(View.VISIBLE);
                }else {
                    mBtn.setVisibility(View.GONE);
                }
            }
        });

        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT>=8){
                    listView.smoothScrollToPosition(0);//返回顶部由滑动效果
                }else {
                    listView.setSelection(0);//直接返回顶部
                }
            }
        });

}

    private void initFruit(){
        for (int i=0;i<8;i++){
            Fruit a =new Fruit("a",R.mipmap.ker);
            fruitList.add(a);
            Fruit b =new Fruit("b",R.mipmap.ker);
            fruitList.add(b);
            Fruit c =new Fruit("c",R.mipmap.ker);
            fruitList.add(c);
            Fruit d =new Fruit("d",R.mipmap.ker);
            fruitList.add(d);
            Fruit f =new Fruit("f",R.mipmap.ker);
            fruitList.add(f);
        }
    }
}
