package com.example.yjw.BaseMaterial;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import com.example.yjw.Fragment.frag1.DanFragment;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.DialogUtils;
import com.example.yjw.myviewpager.R;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MaterialActivity extends BaseActivity {

    @BindView(R.id.content_fragment)
    FrameLayout contentFragment;
    @BindView(R.id.base_Drawerlayout)
    DrawerLayout baseDrawerlayout;
    @BindView(R.id.recycle_view)
    RecyclerView recycleView;

    private List<MaterialModel> _materialModelList = new ArrayList<>();
    private MaterialAdapter materialAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material);
        ButterKnife.bind(this);

        initview();
        initParkingRecord();
    }

    private void initview() {
        DanFragment danFragment =new DanFragment();
        FragmentManager fragmentManager=getFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_fragment,danFragment).commit();


        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleView.setLayoutManager(linearLayoutManager);

        materialAdapter =new MaterialAdapter(MaterialActivity.this);
        recycleView.setAdapter(materialAdapter);
        materialAdapter.setOnItemClickListener(new MaterialAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                DialogUtils.ShowToast(MaterialActivity.this,"第"+position+"个");
            }
        });
    }

    private void initParkingRecord(){
        for (int i=0;i<10;i++){
            MaterialModel a =new MaterialModel();
            a.setName("闽DC8U81"+i);
            if (i==2 || i==4){
                a.setSex("未停车");
            }else if (i==1 || i==6 || i==7){
                a.setSex("已停车");
            }else{
                a.setSex("已支付");
            }
            a.setAge(10+i);
            a.setStarttime("2018-12-12 22:22"+i);
            a.setEndtime("2018-12-12 22:22"+i);
            _materialModelList.add(a);
        }
        materialAdapter.materialModelList = _materialModelList;
        materialAdapter.notifyDataSetChanged();
    }


}
