package com.example.yjw.save;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yjw.myviewpager.R;

public class SharePreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preferences);
        Button button =findViewById(R.id.save_data);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor =getSharedPreferences("data",
                        MODE_PRIVATE).edit();
                editor.putString("name","Tom");
                editor.putBoolean("mairy",true);
                editor.putInt("age",13);
                editor.apply();
            }
        });

        Button button1 =findViewById(R.id.restore_data);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences perf =getSharedPreferences("data",
                        MODE_PRIVATE);
                String name =perf.getString("name","");
                Boolean mairy =perf.getBoolean("mairy",false);
                int age =perf.getInt("age",0);
                Log.d("save","name"+name);
                Log.d("save", "mairy"+mairy);
                Log.d("save", "age"+age);
            }
        });
    }
}
