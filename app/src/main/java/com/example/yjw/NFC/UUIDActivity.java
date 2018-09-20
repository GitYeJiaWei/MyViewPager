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
import android.widget.TextView;
import android.widget.Toast;

import com.example.yjw.NFC.mifare.StringUtils;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UUIDActivity extends AppCompatActivity {
    @BindView(R.id.listview)
    ListView listview;
    private Intent mIntent = null;
    private static NfcAdapter nfcAdapter;
    private static PendingIntent mPendingIntent;
    private static IntentFilter[] mFilters;
    private static String[][] mTechLists;

    @BindView(R.id.tv_uuid)
    TextView tvUuid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uuid);
        ButterKnife.bind(this);

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


        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,
                getClass()), 0);

        IntentFilter ndef = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);

        try {
            ndef.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            throw new RuntimeException("fail", e);
        }
        mFilters = new IntentFilter[]{ndef};

        //设置
        mTechLists = new String[][]{new String[]{IsoDep.class
                .getName()}, new String[]{MifareClassic.class.getName()}};//MifareClassic,MifareUltralight

        mIntent = this.getIntent();

        ReadCardSN();
    }

    private void ReadCardSN() {
        String action = mIntent.getAction();

        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {//当前的action
            Tag tag = mIntent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            byte[] bytCardSN = tag.getId();
            String strCardSN = StringUtils.bytesToHexString(bytCardSN);
            String[] arTeachList = tag.getTechList();

            NfcA nfcA = NfcA.get(tag);

            byte[] bytAtqa = nfcA.getAtqa();
            short Sak = nfcA.getSak();
            int maxTranLen = nfcA.getMaxTransceiveLength();

            String strAtqa = StringUtils.bytesToHexString(bytAtqa);
            String strSAK = String.format("%02X", Sak);
            String strCardType;
            if (Sak == 0x08){
                strCardType = "M1 card";
            }else if(Sak == 0x20){
                strCardType = "CPU card";
            }else if(Sak == 0x28 || Sak == 0x38){
                strCardType = "Composite card";
            }else{
                strCardType = "Unknown type";
            }

            ArrayList<String> blockData = new ArrayList<>();
            blockData.add("Read uniquq number of card");
            blockData.add(">" + strCardSN);
            blockData.add("Supported agreement");
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
    public void onResume() {
        super.onResume();

        //-----------------非常关键，必要的哦，不能删除----------------
        nfcAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters,
                mTechLists);
    }

    @Override
    public void onNewIntent(Intent intent) {
        mIntent = intent;
    }
}
