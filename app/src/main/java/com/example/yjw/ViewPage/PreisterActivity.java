package com.example.yjw.ViewPage;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 注册viewpage
 */
public class PreisterActivity extends BaseActivity {

    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.tv_CurrentName)
    TextView tvCurrentName;
    @BindView(R.id.txtOne)
    TextView txtOne;
    @BindView(R.id.txtTwo)
    TextView txtTwo;
    @BindView(R.id.txtThree)
    TextView txtThree;
    @BindView(R.id.viewPage)
    ViewPager viewPage;

    private Button btnSelected, btnValide, btnRegister;
    private View view1 = null;
    private View view2 = null;
    private View view3 = null;
    private List<View> viewList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preister);
        ButterKnife.bind(this);

        initPage();

    }

    private void initPage() {
        LayoutInflater inflater = LayoutInflater.from(PreisterActivity.this);
        view1 = inflater.inflate(R.layout.activity_register_01, null);
        view2 = inflater.inflate(R.layout.activity_register_02, null);
        view3 = inflater.inflate(R.layout.activity_register_03, null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);

        PresiterAdapter presiterAdapter = new PresiterAdapter(PreisterActivity.this, viewList);
        btnSelected = view1.findViewById(R.id.btnSelected);
        btnValide = view2.findViewById(R.id.btnValide);
        btnRegister = view3.findViewById(R.id.btnRegister);

        btnSelected.setOnClickListener(new OnClick1());
        btnValide.setOnClickListener(new OnClick1());
        btnRegister.setOnClickListener(new OnClick1());


        viewPage.setAdapter(presiterAdapter);
        viewPage.setCurrentItem(0, true);
        viewPage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setpage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    void setpage(int position) {
        if (position == 0) {
            txtOne.setTextColor(this.getResources().getColor(R.color.title_bar_color));
            txtTwo.setTextColor(this.getResources().getColor(R.color.txt_color_gray));
            txtThree.setTextColor(this.getResources().getColor(R.color.txt_color_gray));
        } else if (position == 1) {
            txtOne.setTextColor(this.getResources().getColor(R.color.txt_color_gray));
            txtTwo.setTextColor(this.getResources().getColor(R.color.title_bar_color));
            txtThree.setTextColor(this.getResources().getColor(R.color.txt_color_gray));
        } else if (position == 2) {
            txtOne.setTextColor(this.getResources().getColor(R.color.txt_color_gray));
            txtTwo.setTextColor(this.getResources().getColor(R.color.txt_color_gray));
            txtThree.setTextColor(this.getResources().getColor(R.color.title_bar_color));
        }
    }

    @butterknife.OnClick(R.id.btnBack)
    public void onViewClicked() {
                if(viewPage.getCurrentItem() == 0)
                {
                    PreisterActivity.this.finish();
                    return;
                }
                viewPage.setCurrentItem(viewPage.getCurrentItem() - 1, true);
            }



    class OnClick1 implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnSelected) {
                viewPage.setCurrentItem(1, true);
            }
            if (v.getId() == R.id.btnValide) {
                viewPage.setCurrentItem(2, true);
            }
        }
    }

}
