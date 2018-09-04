package com.example.yjw.intent;

import android.os.Bundle;
import android.widget.Toast;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.LogUtil;
import com.example.yjw.myviewpager.R;

public class StudentsgetActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentsget);
        initdata2();
    }

    private void initdata2(){
        Students students =(Students) getIntent().getParcelableExtra("person_data");
        String name=students.getName();
        int age=students.getAge();
        LogUtil.d("TAG","debug log");
        Toast.makeText(this,"name:"+name+"age:"+age,Toast.LENGTH_LONG).show();
    }

}
