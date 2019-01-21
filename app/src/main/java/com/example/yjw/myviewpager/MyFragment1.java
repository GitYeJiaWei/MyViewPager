package com.example.yjw.myviewpager;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yjw.Fragment.frag1.FragmentActivity;
import com.example.yjw.Fragment.frag2.Fragment1Activity;
import com.example.yjw.common.ScreenUtils;
import com.example.yjw.intent.OneActivity;

import static android.app.Activity.RESULT_OK;


/**
 * Created by YJW on 2018/1/2.
 * Fragment 调用startActivityForResult;
 */

public class MyFragment1 extends Fragment implements View.OnClickListener{
    public static final int TAG=1;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
       View view =inflater.inflate(R.layout.fragment1,container,false);
        TextView textView = view.findViewById(R.id.txt_content);
        textView.setText("第一个Fragment");

        progressBar = view.findViewById(R.id.progressBar);

        Button button4 =view.findViewById(R.id.btn_dialog1);
        Button button5 =view.findViewById(R.id.btn_dialog2);
        Button btn_dialog3 =view.findViewById(R.id.btn_dialog3);
        Button button = view.findViewById(R.id.btn_intent);
        Button button2 = view.findViewById(R.id.btn_intent1);
        Button button1 = view.findViewById(R.id.btn_url);
        Button button3 =view.findViewById(R.id.btn_progress);
        Button button6 =view.findViewById(R.id.btn_fragment);
        Button btn_serializable=view.findViewById(R.id.btn_serializable);
        Button btn_frag1 =view.findViewById(R.id.btn_frag1);
        button3.setOnClickListener(this);
        button.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        btn_serializable.setOnClickListener(this);
        btn_frag1.setOnClickListener(this);
        btn_dialog3.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_intent:
                //Intent intent = new Intent(getActivity(),FirstActivity.class);
                String data ="hello";
                Intent intent =new Intent("android.intent.action.FistActivity");
                intent.addCategory("com.example.FistActivity");
                intent.putExtra("data",data);
                startActivityForResult(intent, TAG);
                break;
            case R.id.btn_url:
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent1);
                break;
            case R.id.btn_intent1:
                /*Intent intent2 =new Intent(getActivity(),SecondActivity.class);
                startActivity(intent2);*/
                SecondActivity.actionStart(getActivity(),"MyFragment-data1","MyFragment-data2");
                break;
            case R.id.btn_progress:
                if (progressBar.getVisibility() ==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
                break;
            case R.id.btn_dialog1:
                AlertDialog.Builder dialog =new AlertDialog.Builder(getActivity());
                dialog.setTitle("这是一个对话框");
                dialog.setMessage("这是一个对话框");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                dialog.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.btn_dialog2:
                ProgressDialog progressDialog =new ProgressDialog(getActivity());
                progressDialog.setTitle("这是一个对话框");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            case R.id.btn_fragment:
                Intent intent2 =new Intent(getActivity(), FragmentActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("123","asdfasldf");
                intent2.putExtras(bundle);
                startActivityForResult(intent2,1);
                break;
            case R.id.btn_serializable:
                Intent intent12 =new Intent(getActivity(), OneActivity.class);
                startActivityForResult(intent12,1);
                break;
            case R.id.btn_frag1:
                Intent btn_frag1 =new Intent(getActivity(), Fragment1Activity.class);
                startActivityForResult(btn_frag1,1);
                break;
            case R.id.btn_dialog3:
                showDialog();
                break;
            default:
                break;
        }
    }

    //初始化并弹出对话框方法
    private void showDialog(){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.cw_location_dialog_dong,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).setView(view).create();

        Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
        Button btn_agree = (Button) view.findViewById(R.id.btn_agree);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //... To-do
                dialog.dismiss();
            }
        });

        btn_agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //... To-do
                dialog.dismiss();
            }
        });

        dialog.show();
        //此处设置位置窗体大小，我这里设置为了手机屏幕宽度的3/4
        dialog.getWindow().setLayout((ScreenUtils.getScreenWidth(getContext())/4*3), LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAG:
                Log.d("hehe", ""+resultCode);
                Log.d("hehe", ""+RESULT_OK);
                if (resultCode == RESULT_OK) {
                    String data1 = data.getStringExtra("first");
                    Log.d("hehe", data1);
                }
                break;
            default:
        }
    }
}
