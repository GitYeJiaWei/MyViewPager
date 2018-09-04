package com.example.yjw.Dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.DialogUtils;
import com.example.yjw.myviewpager.R;

import java.util.Iterator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * spinner下拉框和复杂的dialog对话框
 */
public class DialogActivity extends BaseActivity {

    @BindView(R.id.tiaoma)
    ImageView tiaoma;
    @BindView(R.id.tiaoma1)
    ImageView tiaoma1;
    @BindView(R.id.spin)
    Spinner spin;
    @BindView(R.id.unique_et)
    EditText uniqueEt;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_connect)
    Button btnConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog2);
        ButterKnife.bind(this);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.spin,R.layout.support_simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

    }

    @OnClick({R.id.btn_cancel, R.id.btn_connect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_cancel:
                String spin1=spin.getSelectedItem().toString();
                DialogUtils.ShowToast(DialogActivity.this,spin1);
                break;
            case R.id.btn_connect:
                break;
        }
    }
}
