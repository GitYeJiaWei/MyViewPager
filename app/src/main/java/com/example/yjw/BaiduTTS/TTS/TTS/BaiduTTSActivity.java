package com.example.yjw.BaiduTTS.TTS.TTS;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;


public class BaiduTTSActivity extends BaseActivity implements View.OnClickListener{

    public PersonlSound personlSound;
    private Button speak;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_tts);

        //加一个语音提示
        personlSound=new PersonlSound(this);
        personlSound.ini();
        speak= findViewById(R.id.speak);
        speak.setOnClickListener(this);
    }

    /**
     * 界面上的按钮对应方法
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.speak:
                personlSound.speak(123456789+"元");
                break;

            default:
                break;
        }
    }




}
