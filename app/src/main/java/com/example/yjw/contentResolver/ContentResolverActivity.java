package com.example.yjw.contentResolver;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView获取联系人信息
 */
public class ContentResolverActivity extends BaseActivity {

    ArrayAdapter<String> adapter ;
    List<String> contactsList =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_resolver);

        ListView contactsView =findViewById(R.id.content);
        adapter =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactsList);
        contactsView.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);

        }else{
            readContacts();
        }
    }

    private void readContacts(){
        Cursor cursor =null;
        try{
            //查询联系人数据
            cursor =getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,null,null,null);
            if (cursor !=null){
                while (cursor.moveToNext()){
                    //获取联系人姓名
                    String displayName =cursor.getString(cursor.getColumnIndex
                            (ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    //获取联系人手机号
                    String number =cursor.getString(cursor.getColumnIndex
                            (ContactsContract.CommonDataKinds.Phone.NUMBER));
                    contactsList.add(displayName +"\n" +number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor !=null){
                cursor.close();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if (grantResults.length >0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else{
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
                default:
        }
    }
}
