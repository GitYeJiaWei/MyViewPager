package com.example.yjw.OnRefreshList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;

import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.LogUtil;
import com.example.yjw.myviewpager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 每隔一秒刷新3组数据
 */
public class OnRefreshActivity extends BaseActivity {

    @BindView(R.id.onrefreshList)
    ListView onrefreshList;
    //数组要先赋值下标，不能为null，不能直接赋值，否则会报空指针
    private OnRefreshModel[] onRefresh=new OnRefreshModel[7];
    private int indexT=0;
    private static int WAG=1;
    private OnRefreshAdapter onRefreshAdapter;
    private List<OnRefreshModel> modelList =new ArrayList<>();
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_refresh);
        ButterKnife.bind(this);

        onRefreshAdapter =new OnRefreshAdapter(this,onrefreshList.getHeight());
        onrefreshList.setAdapter(onRefreshAdapter);

        timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Message message =new Message();
                message.what =WAG;
                message.obj = indexT;
                indexT++;
                handler.sendMessage(message);
                LogUtil.d("onrefresh","timer:"+message.obj);
            }
        },0,2000);

    }

    private Handler handler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    onRefreshAdapter.update(initview(Integer.valueOf(msg.obj.toString())));
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer !=null){
            timer.cancel();
            timer =null;
        }
    }

    private List<OnRefreshModel> initview(int index) {
        String[] content =new String[]{"12346","fseijfwa","ajksdlkfjewl","fsdjfkewf","fwehfrukews","fweuhfuwe45","asdfjewjfierwsnf"};
        final SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm-ss");
        Date date =new Date();
        for (int i=0;i<7;i++){
            onRefresh[i] =new OnRefreshModel(format.format(date),content[i]);
        }
        modelList.clear();
        if (index>4){
            indexT =1;
            index=0;
        }
        for (int j=0;j<3;j++){
                modelList.add(onRefresh[j+index]);
                LogUtil.d("onrefresh","for:"+(j+index));
        }

        return  modelList;
    }

}
