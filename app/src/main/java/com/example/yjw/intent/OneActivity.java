package com.example.yjw.intent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

public class OneActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seriatizable);

        Button but1=findViewById(R.id.but1);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initdata1();
            }
        });

        Button but2=findViewById(R.id.but2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initdata2();
            }
        });

    }

    private void initdata1(){
        Person person =new Person();
        person.setName("tom");
        person.setAge(15);
        Intent intent=new Intent(OneActivity.this,PersongetActivity.class);
        intent.putExtra("person_data",person);
        startActivity(intent);
    }

    private void initdata2(){
        Students students =new Students();
        students.setName("jaon");
        students.setAge(16);
        Intent intent1=new Intent(this,StudentsgetActivity.class);
        intent1.putExtra("person_data",students);
        startActivity(intent1);
    }
}
