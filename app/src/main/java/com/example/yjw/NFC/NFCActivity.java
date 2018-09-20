package com.example.yjw.NFC;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yjw.myviewpager.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * NFC-V 的读取demo，ISO 15693-1
 */
public class NFCActivity extends AppCompatActivity {
    @BindView(R.id.TextView)
    TextView TextView;
    @BindView(R.id.btn_uuid)
    Button btnUuid;
    private NfcAdapter mAdapter;
    private PendingIntent mPendingIntent;
    private IntentFilter[] mFilters;
    private String[][] mTechLists;
    private int mCount = 0;
    private NfcV nfcv = null;
    private byte[] UID, UID_zan, uidShow;
    private StringBuilder stringbuilder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
        ButterKnife.bind(this);
        TextView.setText("扫描标签");

        mAdapter = NfcAdapter.getDefaultAdapter(this);
        mPendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter nfcv = new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED);
        mFilters = new IntentFilter[]{nfcv};
        mTechLists = new String[][]{new String[]{NfcV.class.getName()}};
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdapter.enableForegroundDispatch(this, mPendingIntent, mFilters, mTechLists);
    }

    @Override
    public void onNewIntent(Intent intent) {
        Log.d("intent", "intent action=" + intent.getAction());
        if (intent.getAction().trim().equals("android.nfc.action.TECH_DISCOVERED")) {
            Tag tagFromIntent = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            Log.d("intent", "intent tag=" + NfcAdapter.EXTRA_TAG + ", " + NfcAdapter.EXTRA_ID);
            nfcv = NfcV.get(tagFromIntent);
            UID = tagFromIntent.getId();
            String[] techlist = tagFromIntent.getTechList();
            stringbuilder1 = new StringBuilder();
            for (int i = 0; i < techlist.length; i++)
                stringbuilder1.append(techlist[i] + "\n");
        }
        int length = UID.length;
        UID_zan = new byte[8];
        uidShow = new byte[8];
        for (int i = 0; i < UID.length; i++) {
            UID_zan[i] = UID[i];
        }
        for (int i = 0; i < UID_zan.length; i++) {
            uidShow[length - i - 1] = UID_zan[i];
        }

        TextView.setText(Convert.bytesToHexString(uidShow) + "\n" + UID.length + "\n" + stringbuilder1.toString());

        //读数据
        try {
            if (nfcv.isConnected())
                nfcv.close();
            nfcv.connect();
            byte[] command = new byte[11];
            byte[] responsebyte = null;
            command[0] = (byte) 0x22;     //标志位
            command[1] = (byte) 0x20;     //命令位
            System.arraycopy(UID, 0, command, 2, UID.length);
            command[10] = (byte) 0x8;
            try {
                responsebyte = nfcv.transceive(command);
            } catch (TagLostException e) {
                e.printStackTrace();
            }
            if (responsebyte != null) {
                Log.d("---read---", responsebyte.toString());
                String data = Convert.bytesToHexString(responsebyte);
                String dedata = Convert.decode(data);
                Toast.makeText(NFCActivity.this, data + "|" + dedata, Toast.LENGTH_SHORT).show();
                //Log.d("intent", "intent response="+Convert.toStringHex(data.substring(2, data.length())));
            }
            //else
            Log.d("intent", "intent response is null");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //写数据
        try {
            if (nfcv.isConnected())
                nfcv.close();
            nfcv.connect();
            byte[] command = new byte[15];
            byte[] response = null;
            command[0] = (byte) 0x22;
            command[1] = (byte) 0x21;
            System.arraycopy(UID, 0, command, 2, UID.length);
            command[10] = (byte) 0x8;
            String code = Convert.encode("abcd");
            byte[] bcode = Convert.hexStringToBytes(code);
            Log.d("---write---", bcode.toString());
            System.arraycopy(bcode, 0, command, 11, 4);
            response = nfcv.transceive(command);
            if (response == null)
                Log.d("intent", "intent response is null");
            else {
                Log.d("intent", "intent response=" + Convert.bytesToHexString(response));
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        if (nfcv != null && nfcv.isConnected())
            try {
                nfcv.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        super.onDestroy();
    }


    @OnClick(R.id.btn_uuid)
    public void onViewClicked() {
        Intent btn_uuid = new Intent(this,UUIDActivity.class);
        startActivityForResult(btn_uuid,1);
    }
}
