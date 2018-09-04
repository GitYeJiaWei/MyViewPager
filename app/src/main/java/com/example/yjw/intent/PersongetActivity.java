package com.example.yjw.intent;

import android.os.Bundle;
import android.widget.Toast;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.LogUtil;
import com.example.yjw.myviewpager.R;

public class PersongetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personget);
        initdata1();
    }

    private void initdata1(){
        Person person =(Person) getIntent().getSerializableExtra("person_data");
        String name=person.getName();
        int age=person.getAge();
        LogUtil.d("TAG","debug log");
        Toast.makeText(this,"name:"+name+"age:"+age,Toast.LENGTH_LONG).show();
    }



}
