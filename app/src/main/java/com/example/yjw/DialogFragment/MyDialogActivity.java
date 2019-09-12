package com.example.yjw.DialogFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yjw.myviewpager.BaseActivity;
import com.example.yjw.myviewpager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyDialogActivity extends BaseActivity {

    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.btn_login1)
    Button btnLogin1;
    @BindView(R.id.tv_account)
    TextView tvAccount;
    @BindView(R.id.tv_password)
    TextView tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_dialog);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.btn_login, R.id.btn_login1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login1:
                FragmentManager fragmentManager1 = getSupportFragmentManager();
                MyDialogFragment2 myDialogFragment2 = MyDialogFragment2.
                        newInstance(tvAccount.getText().toString(),tvPassword.getText().toString());
                myDialogFragment2.show(fragmentManager1, "dialogFragment1");
                //获取登录信息
                myDialogFragment2.setMyDialogListener(new MyDialogFragment2.MyDialogListener() {
                    @Override
                    public void getLoginInfo(String account, String password) {
                        tvAccount.setText(account);
                        tvPassword.setText(password);
                    }
                });

                break;
            case R.id.btn_login:
                FragmentManager fragmentManager = getSupportFragmentManager();
                MyDialogFragment myDialogFragment = MyDialogFragment.newInstance();
                myDialogFragment.show(fragmentManager, "dialogFragment");
                break;
        }
    }
}
