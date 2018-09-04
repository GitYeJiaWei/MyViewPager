package com.example.yjw.AddView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 动态添加组件
 */
public class AddViewActivity extends BaseActivity {

    @BindView(R.id.additem)
    LinearLayout additem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_view);
        ButterKnife.bind(this);

        initview();
    }

    private void initview(){
        TextView textView =new TextView(this);
        textView.setText("saklfs");
        additem.addView(textView);

        Button button = new Button(this);
        button.setText("点击");
        additem.addView(button);

        ImageView imageView =new ImageView(this);//动态创建图片
        imageView.setImageDrawable(this.getResources().getDrawable(R.mipmap.ket));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);//使用 FILL 方式缩放图像，以适应视图边界
        additem.addView(imageView);

        LinearLayout.LayoutParams lp1 =new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        imageView.setLayoutParams(lp1);

       /* //创建一个相对布局relative
        RelativeLayout relative = new RelativeLayout(this);
        relative.setBackgroundColor(Color.YELLOW);
        // 将Button1 加入到RelativeLayout 中
        Button btn_r1 = new Button(this);
        btn_r1.setText("取消");//设置显示的字符
        relative.addView(btn_r1);

        // 将Button2 加入到RelativeLayout 中
        Button btn_r2 = new Button(this);
        btn_r2.setText("确定");//设置显示的字符
        relative.addView(btn_r2);
        // 设置RelativeLayout布局的宽高
        RelativeLayout.LayoutParams lp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        btn_r1.setLayoutParams(lp);   ////设置按钮的布局属性
        lp=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.RIGHT_OF, btn_r1.getId());
        btn_r2.setLayoutParams(lp);   ////设置按钮的布局属性
        setContentView(relative);*/
    }
}
