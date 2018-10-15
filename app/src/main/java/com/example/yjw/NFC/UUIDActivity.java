package com.example.yjw.NFC;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.yjw.NFC.mifare.StringUtils;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * NFC-A,B  NFC卡片读取
 */
public class UUIDActivity extends AppCompatActivity {
    @BindView(R.id.listview)
    ListView listview;
    private Intent mIntent = null;
    private static NfcAdapter nfcAdapter;
    private static PendingIntent mPendingIntent;
    private static IntentFilter[] mFilters;
    private static String[][] mTechLists;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uuid);
        ButterKnife.bind(this);

        //获取默认的NFC控制器，并进行判断
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if (nfcAdapter == null) {
            Log.d("h_bl", "No support NFC！");
            Toast.makeText(getApplicationContext(), "No support NFC！", Toast.LENGTH_SHORT).show();

            return;
        }
        if (!nfcAdapter.isEnabled()) {
            Toast.makeText(getApplicationContext(), "Please enable the NFC function first in the system settings！", Toast.LENGTH_SHORT).show();
            Log.d("h_bl", "请在系统设置中先启用NFC功能！");
        }

        //启用一个PendingIntent
        /*mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()), 0);*/
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);


        //intent匹配NfcAdapter的活动
        // 前台activity过滤获得所有的ACTION_TECH_DISCOVERED的intent
        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        try {
            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mFilters = new IntentFilter[]{ndef};

        //设置IsoDep,MifareClassic,MifareUltralight
        // 声明前台activity能处理的NFC标签技术的数组
        mTechLists = new String[][]{new String[]{IsoDep.class.getName()},
                new String[]{MifareUltralight.class.getName()}, new String[]{MifareClassic.class.getName()}};


        //捕获NFC Intent
        mIntent = this.getIntent();

        ReadCardSN();
    }

    private void ReadCardSN() {
        // 解析该Intent的Action
        String action = mIntent.getAction();

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {//当前的action
            //获取标签tag
            Tag tag = mIntent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            //获取NFC卡的唯一值
            byte[] bytCardSN = tag.getId();
            String strCardSN = StringUtils.bytesToHexString(bytCardSN);
            //调用getTechList()方法来确定该tag能支持的技术
            String[] arTeachList = tag.getTechList();

            NfcA nfcA = NfcA.get(tag);

            byte[] bytAtqa = nfcA.getAtqa();
            short Sak = nfcA.getSak();
            int maxTranLen = nfcA.getMaxTransceiveLength();

            String strAtqa = StringUtils.bytesToHexString(bytAtqa);
            String strSAK = String.format("%02X", Sak);
            String strCardType;
            if (Sak == 0x08) {
                strCardType = "M1 card";
            } else if (Sak == 0x20) {
                strCardType = "CPU card";
            } else if (Sak == 0x28 || Sak == 0x38) {
                strCardType = "Composite card";
            } else {
                strCardType = "Unknown type";
            }

            ArrayList<String> blockData = new ArrayList<>();
            blockData.add("NFC卡唯一值");
            blockData.add(">" + strCardSN);
            blockData.add("提供的功能");
            for (int i = 0; i < arTeachList.length; i++) {
                String strTeach = arTeachList[i];

                blockData.add(">" + strTeach);
            }
            blockData.add("ATQA");
            blockData.add(">" + strAtqa);

            blockData.add("SAK");
            blockData.add(">" + strSAK + " " + strCardType);

            blockData.add("Max transmission length");
            blockData.add(">" + maxTranLen);
            //---------------------显示输出---------------------
            String[] contents = new String[blockData.size()];
            blockData.toArray(contents);
            listview.setAdapter(new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, contents));
        } else {
            showAlert("Do not find card");
        }
    }

    /**
     * 显示对话框
     *
     * @param alertCase
     */
    private void showAlert(String alertCase) {
        // prepare the alert box
        AlertDialog.Builder alertbox = new AlertDialog.Builder(this);

        alertbox.setMessage(alertCase);
        // set a positive/yes button and create a listener
        alertbox.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            // Save the data from the UI to the database - already done
            public void onClick(DialogInterface arg0, int arg1) {
                //clearFields();
            }
        });
        // display box
        alertbox.show();
    }

    @Override
    protected void onPause() {
        if (nfcAdapter != null)
            nfcAdapter.disableForegroundDispatch(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        //mFilters,前台调度系统activity能过滤的nfc标签，重写能调度的nfc标签过滤器，或者总是填null。
        //mTechLists,应用程序希望处理的NFC标签技术的数组
        //如果filters和techLists参数均为空，则会导致前台activity通过ACTION_TAG_DISCOVERED intent接收所有的标签。
        if (nfcAdapter != null)
            //-----------------非常关键，必要的哦，不能删除----------------
            nfcAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
                    mTechLists);
        super.onResume();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 当前app正在前端界面运行，这个时候有intent发送过来，那么系统就会调用onNewIntent回调方法，将intent传送过来
        // 我们只需要在这里检验这个intent是否是NFC相关的intent，如果是，就调用处理方法
        Log.d("h_bl", "onNewIntent");
        mIntent = intent;
        ReadCardSN();  //处理Intent
    }



}
