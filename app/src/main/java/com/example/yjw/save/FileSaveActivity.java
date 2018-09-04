package com.example.yjw.save;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yjw.myviewpager.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * save将数据存储到文件中
 * load从data文件中取出数据
 */
public class FileSaveActivity extends AppCompatActivity {
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_save);

        editText =findViewById(R.id.txt_edit);
        String inputText =load();
        if (!TextUtils.isEmpty(inputText)){
            editText.setText(inputText);
            editText.setSelection(inputText.length());
            Toast.makeText(this,"文件读取成功",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        String inputText =editText.getText().toString();
        save(inputText);
    }

    private String load(){
        FileInputStream inputStream =null;
        BufferedReader reader =null;
        StringBuilder content =new StringBuilder();
        try {
            inputStream =openFileInput("data");
            reader =new BufferedReader(new InputStreamReader(inputStream));
            String line ="";
            while((line =reader.readLine())!=null){
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    //保存数据到文件中
    private void save(String inputText){

        FileOutputStream out =null;
        BufferedWriter writer =null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer =new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (writer !=null){
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
