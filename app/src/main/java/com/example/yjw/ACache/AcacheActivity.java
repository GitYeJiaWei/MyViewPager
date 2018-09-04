package com.example.yjw.ACache;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.DialogUtils;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**

 * @Author: YeJW

 * @Date: 2018/6/11 16:13

 * @Description:

 *

 */
public class AcacheActivity extends BaseActivity {
    @BindView(R.id.acache_text)
    TextView acacheText;
    @BindView(R.id.save_acache)
    Button saveAcache;
    @BindView(R.id.read_acache)
    Button readAcache;
    @BindView(R.id.clear_acache)
    Button clearAcache;
    private ACache aCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acache);
        ButterKnife.bind(this);


        aCache = ACache.get(this);
        String acValue = aCache.getAsString("ac");
        String acaValue = aCache.getAsString("aca");
        if (acaValue==null || acValue ==null){
            DialogUtils.ShowToast(AcacheActivity.this,"This is null");
            return;
        }
        int a=(acaValue!=null)? 0: 1;
        /**
         * 相当于 if 语句
         * 如果acaValue!=null为真，则取0
         * 如果acaValue!=null为假，则取1
         */

        acacheText.setText("a"+a+"acValue:"+acValue +"\nacaValue:"+acaValue);
    }


    @OnClick({R.id.save_acache, R.id.read_acache, R.id.clear_acache})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.save_acache:
               /* int a= Integer.parseInt("q");//当用户点击按钮的时候特意写了一个异常*/
                aCache.put("ac", "123345456"); //保存
                aCache.put("aca", "354345098", 2 * ACache.TIME_DAY); //保存两天
                break;
            case R.id.read_acache:
                String acValue = aCache.getAsString("ac");
                String acaValue = aCache.getAsString("aca");
                if (acaValue==null || acValue ==null){
                    DialogUtils.ShowToast(AcacheActivity.this,"This is null");
                    return;
                }
                acacheText.setText("acValue:"+acValue +"\nacaValue:"+acaValue);
                break;
            case R.id.clear_acache:
                aCache.remove("ac");
                aCache.remove("aca");
                break;
        }
    }
}
