package com.example.yjw.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yjw.myviewpager.R;

public class MyDialogFragment2 extends DialogFragment {

    private String mHintAccount = "";
    private String mHintPassword = "";
    private MyDialogListener myDialogListener;
    //实例化接口的方法
    public void setMyDialogListener(MyDialogListener myDialogListener1){
        myDialogListener = myDialogListener1;
    }

    public static MyDialogFragment2 newInstance(String hintAccount,String hintPassword){
        MyDialogFragment2 myDialogFragment2 = new MyDialogFragment2();
        Bundle bundle = new Bundle();
        bundle.putString("hint_account",hintAccount);
        bundle.putString("hint_password",hintPassword);
        myDialogFragment2.setArguments(bundle);
        return myDialogFragment2;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle =getArguments();
        if (bundle!=null){
            mHintAccount = bundle.getString("hint_account");
            mHintPassword = bundle.getString("hint_password");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialogfragment_layout,null);
        dialog.setView(view);
        final EditText account = view.findViewById(R.id.account);
        final EditText password = view.findViewById(R.id.password);
        account.setText(mHintAccount);
        password.setText(mHintPassword);
        Button cancel = view.findViewById(R.id.cancel);
        Button submit = view.findViewById(R.id.submit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在确定点击事件里调用接口方法
                myDialogListener.getLoginInfo(account.getText().toString(),password.getText().toString());
                dismiss();
            }
        });

        return dialog.create();
    }

    //先定义一个回调接口
    protected interface MyDialogListener{
        void getLoginInfo(String account,String password);
    }
}
