package com.example.yjw.MakeView;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yjw.myviewpager.R;

public class MakeView extends Activity implements OnClickListener {
    private LinearLayout myLinearLayout;
    private Button bt_add, bt_del, bt_save;
    private View childView;
    private LayoutInflater inflater;
    private int mark = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        initView();
        initListener();
    }

    private List<ViewHolder> ls_vh;

    private void initView() {
        // TODO Auto-generated method stub
        setContentView(R.layout.dongtai);
        myLinearLayout = (LinearLayout) findViewById(R.id.ll_dongtai_main);
        bt_add = (Button) findViewById(R.id.bt_add);
        bt_del = (Button) findViewById(R.id.bt_del);
        bt_save = (Button) findViewById(R.id.bt_save);

        ls_vh = new ArrayList<ViewHolder>();// 保存View的实例
        // 布局选择器
        inflater = LayoutInflater.from(getApplicationContext());
        childView = inflater.inflate(R.layout.view, null);
        // 默认添加一条
        myLinearLayout.addView(childView, mark);
        saveViewInstance(childView);// 实例化View
    }

    private void initListener() {
        // TODO Auto-generated method stub
        bt_add.setOnClickListener(this);
        bt_del.setOnClickListener(this);
        bt_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
            case R.id.bt_add:
                mark++;
                // 新增布局必须每次获取一下布局，否则不能作区分
                childView = inflater.inflate(R.layout.view, null);
                // myLinearLayout.addView(childView);
                myLinearLayout.addView(childView, mark);
                saveViewInstance(childView);// 实例化View
                break;
            case R.id.bt_del:
                if (myLinearLayout.getChildCount() > 0) {
                    // 删除动态生成View的最后一条
                    myLinearLayout.removeViewAt(myLinearLayout.getChildCount() - 1);
                    mark--;
                    if (ls_vh.size() > 0) {
                        // 删除集合中最后一条
                        ls_vh.remove(ls_vh.size() - 1);
                    }
                }
                break;
            case R.id.bt_save:
                if (ls_vh.size() == 0) {
                    showToast("请增加一条数据");
                    break;
                }
                StringBuffer s = new StringBuffer();
                String str = "";
                for (int i = 0; i < ls_vh.size(); i++) {
                    ViewHolder v = ls_vh.get(i);
                    s.append("达成目标:").append(v.getTv_target().getText().toString())
                            .append("\n").append("工作说明:")
                            .append(v.getEt_work().getText().toString())
                            .append("\n").append("拜访次数:")
                            .append(v.getEt_count().getText().toString())
                            .append("\n");
                    str = s.toString();
                }
                str.trim();
                if (!str.equals("")) {
                    showToast(str);
                    finish();
                }
                break;
            default:
                break;
        }

    }

    private void saveViewInstance(View inflatView) {
        ViewHolder vh = new ViewHolder();
        vh.setId(mark);
        TextView tv_target = (TextView) inflatView.findViewById(R.id.et_target);
        EditText et_work = (EditText) inflatView.findViewById(R.id.et_work);
        EditText et_count = (EditText) inflatView.findViewById(R.id.et_count);

        // 注册监听事件
        tv_target.setOnClickListener(targetListener);
        et_work.setOnClickListener(workListener);

        vh.setTv_target(tv_target);
        vh.setEt_work(et_work);
        vh.setEt_count(et_count);
        ls_vh.add(vh);
    }

    private String[] targetAry = {"目标1", "目标2", "目标3"};
    private String[] workAry = {"工作说明1", "工作说明2", "工作说明3"};

    OnClickListener targetListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // View parentView = (View) v.getParent().getParent();
            // int vid = parentView.getId();
            TextView tv = (TextView) v;
            showDialogList("请选择目标", tv, targetAry);
        }
    };
    OnClickListener workListener = new OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // View parentView = (View) v.getParent().getParent();
            TextView tv = (TextView) v;
            showDialogList("请选择工作说明", tv, workAry);

            // for (int i = 0; i < myLinearLayout.getChildCount(); i++) {
            // if(vid==ls_vh.get(i).getId()){
            // EditText et = ls_vh.get(i).getEt_work();
            // showDialogList("请选择目标", et, targetAry);
            // break;
            // }
            // }
        }
    };

    private void showToast(String msg) {
        Toast.makeText(MakeView.this, msg, Toast.LENGTH_LONG).show();
    }

    public void showDialogList(final String tittle, final TextView tv,
                               final String[] array) {
        AlertDialog.Builder builder = null;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                MakeView.this, android.R.layout.simple_list_item_1,
                array);
        // if (Build.VERSION.SDK_INT >= 14) {
        // builder = new AlertDialog.Builder(this, R.style.style_date_picker);
        // }
        builder = new AlertDialog.Builder(this);

        builder.setTitle(tittle).setNeutralButton("取消", null)
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        tv.setText(array[which]);
                        // mTvBelongProvince.setTag(mProvinceList.get(which).areaId);
                    }
                }).show();
    }

    /**
     * 保存动态生成的View的实例
     */
    private class ViewHolder implements Serializable {

        private static final long serialVersionUID = 1L;
        public int id;
        public TextView tv_target;
        public EditText et_work;
        public EditText et_count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public TextView getTv_target() {
            return tv_target;
        }

        public void setTv_target(TextView tv_target) {
            this.tv_target = tv_target;
        }

        public EditText getEt_work() {
            return et_work;
        }

        public void setEt_work(EditText et_work) {
            this.et_work = et_work;
        }

        public EditText getEt_count() {
            return et_count;
        }

        public void setEt_count(EditText et_count) {
            this.et_count = et_count;
        }

    }
}
