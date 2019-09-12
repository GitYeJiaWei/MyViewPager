package com.example.yjw.myviewpager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.yjw.ACache.AcacheActivity;
import com.example.yjw.AddView.AddViewActivity;
import com.example.yjw.AlarmManager.MessageActivity;
import com.example.yjw.AsyncTask.AsyncTask;
import com.example.yjw.BaiduTTS.TTS.TTB.MiniActivity;
import com.example.yjw.BaiduTTS.TTS.TTS.BaiduTTSActivity;
import com.example.yjw.BaseMaterial.MaterialActivity;
import com.example.yjw.BroadCasrTest.LoginActivity;
import com.example.yjw.Clothestore.ClotheStore;
import com.example.yjw.Dagger.TestActivity;
import com.example.yjw.DatePicker.DatePickActivity;
import com.example.yjw.Dialog.DialogActivity;
import com.example.yjw.Glide.GlideActivity;
import com.example.yjw.HandlerThread.HandlerThreadActivity;
import com.example.yjw.HorizontalScrollView.HorizontalScrollViewActivity;
import com.example.yjw.Interface.MyInterfaceActivity;
import com.example.yjw.JsonObject.JsonActivity;
import com.example.yjw.MPAndroidChart.CombinedChart.CombinedActivity;
import com.example.yjw.MPAndroidChart.MAPAndroidChart;
import com.example.yjw.MakeView.MakeView;
import com.example.yjw.MaterialDesign.DrawerLayoutActivity;
import com.example.yjw.OnRefreshList.OnRefreshActivity;
import com.example.yjw.OnTouchEvent.OnTouchEvent;
import com.example.yjw.Retrofit.DataServiceActivity;
import com.example.yjw.Socket.Mina.MinaActivity;
import com.example.yjw.Socket.Socket2Activity;
import com.example.yjw.Socket.SocketActivity;
import com.example.yjw.Socket.UDPService.UDPActivity;
import com.example.yjw.ThreadPoolExecutor.Queue;
import com.example.yjw.ThreadPoolExecutor.ThreadPool;
import com.example.yjw.UFH.Main2Activity;
import com.example.yjw.ViewPage.PreisterActivity;
import com.example.yjw.ViewPage.TViewPage.ViewPageActivity;
import com.example.yjw.ViewPage.WViewPage.WViewActivity;
import com.example.yjw.broadcast.BroadCastActivity;
import com.example.yjw.broadcast.MyBroadActivity;
import com.example.yjw.broadcast.localbroadcast.LocalBroadCastActivity;
import com.example.yjw.camera.ChosePhotoActivity;
import com.example.yjw.camera.PhotoActivity;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.handler.HandlerActivity;
import com.example.yjw.http.OkHttpActivity;
import com.example.yjw.http.WebViewActivity;
import com.example.yjw.playaudio.PlayAudioActivity;
import com.example.yjw.playaudio.PlayVideoActivity;
import com.example.yjw.service.MyServiceActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FirstActivity extends BaseActivity {
    public static final int RAG = 1;
    @BindView(R.id.btn_http)
    Button btnHttp;
    @BindView(R.id.btn_pictureup)
    Button btnPictureup;
    @BindView(R.id.btn_chose)
    Button btnChose;
    @BindView(R.id.btn_webview)
    Button btnWebview;
    @BindView(R.id.btn_okhttp)
    Button btnOkhttp;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.btn_frame)
    Button btnFrame;
    @BindView(R.id.btn_broadcast)
    Button btnBroadcast;
    @BindView(R.id.btn_mybroadcast)
    Button btnMybroadcast;
    @BindView(R.id.btn_mybroadlocalcast)
    Button btnMybroadlocalcast;
    @BindView(R.id.btn_tiao)
    Button btnTiao;
    @BindView(R.id.btn_yipin)
    Button btnYipin;
    @BindView(R.id.btn_shipin)
    Button btnShipin;
    @BindView(R.id.btn_handler)
    Button btnHandler;
    @BindView(R.id.btn_myservice)
    Button btnMyservice;
    @BindView(R.id.btn_Drawerlayout)
    Button btnDrawerlayout;
    @BindView(R.id.btn_BackLogin)
    Button btnBackLogin;
    @BindView(R.id.BaiduTTS)
    Button BaiduTTS;
    @BindView(R.id.BaiduTTS1)
    Button BaiduTTS1;
    @BindView(R.id.Zxing)
    Button Zxing;
    @BindView(R.id.DatePickerDialog)
    Button DatePickerDialog;
    @BindView(R.id.MyInterface)
    Button MyInterface;
    @BindView(R.id.HorizontalScrollViewActivity)
    Button HorizontalScrollViewActivity;
    @BindView(R.id.Dagger)
    Button Dagger;
    @BindView(R.id.btn_ACache)
    Button btnACache;
    @BindView(R.id.btn_BaseMaterial)
    Button btnBaseMaterial;
    @BindView(R.id.btn_BaseDialog)
    Button btnBaseDialog;
    @BindView(R.id.btn_Retrofit)
    Button btnRetrofit;
    @BindView(R.id.btn_MAPAndroidChar)
    Button btnMAPAndroidChar;
    @BindView(R.id.btn_PreisterActivity)
    Button btnPreisterActivity;
    @BindView(R.id.btn_CombinedActivity)
    Button btnCombinedActivity;
    @BindView(R.id.btn_ViewPageActivity)
    Button btnViewPageActivity;
    @BindView(R.id.btn_MessageActivity)
    Button btnMessageActivity;
    @BindView(R.id.btn_Main2Activity)
    Button btnMain2Activity;
    @BindView(R.id.btn_AddViewActivity)
    Button btnAddViewActivity;
    @BindView(R.id.btn_WViewActivity)
    Button btnWViewActivity;
    @BindView(R.id.btn_OnRefreshActivity)
    Button btnOnRefreshActivity;
    @BindView(R.id.btn_OnTouchEvent)
    Button btnOnTouchEvent;
    @BindView(R.id.btn_JsonActivity)
    Button btnJsonActivity;
    @BindView(R.id.btn_ThreadPool)
    Button btnThreadPool;
    @BindView(R.id.btn_Queue)
    Button btnQueue;
    @BindView(R.id.btn_ClotheStore)
    Button btnClotheStore;
    @BindView(R.id.btn_Glide)
    Button btnGlide;
    @BindView(R.id.btn_AsyncTask)
    Button btnAsyncTask;
    @BindView(R.id.tv_HandlerThreadActivity)
    Button tvHandlerThreadActivity;
    @BindView(R.id.tv_SocketActivity)
    Button tvSocketActivity;
    @BindView(R.id.tv_Socket2Activity)
    Button tvSocket2Activity;
    @BindView(R.id.tv_MinaActivity)
    Button tvMinaActivity;
    @BindView(R.id.tv_MakeView)
    Button tvMakeView;
    @BindView(R.id.tv_MinaService)
    Button tvMinaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        Log.d("hehe", data);
    }


    //获取系统的返回键
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("first", "hehehehhe");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RAG) {
            if (resultCode == RESULT_OK) {
                Log.d("hehe", "onActivityResult: ");
            }
        }
    }

    @OnClick({R.id.tv_MinaService,R.id.tv_MakeView, R.id.tv_MinaActivity, R.id.tv_Socket2Activity, R.id.tv_SocketActivity, R.id.tv_HandlerThreadActivity, R.id.btn_AsyncTask, R.id.btn_Glide, R.id.btn_ClotheStore, R.id.btn_Queue, R.id.btn_ThreadPool, R.id.btn_JsonActivity, R.id.btn_OnTouchEvent, R.id.btn_OnRefreshActivity, R.id.btn_WViewActivity, R.id.btn_AddViewActivity, R.id.btn_Main2Activity, R.id.btn_MessageActivity, R.id.btn_ViewPageActivity, R.id.btn_CombinedActivity, R.id.btn_PreisterActivity, R.id.btn_MAPAndroidChar, R.id.btn_Retrofit, R.id.btn_BaseDialog, R.id.btn_BaseMaterial, R.id.btn_http, R.id.btn_pictureup, R.id.btn_chose, R.id.btn_webview, R.id.btn_okhttp, R.id.btn_back, R.id.btn_frame, R.id.btn_broadcast, R.id.btn_mybroadcast, R.id.btn_mybroadlocalcast, R.id.btn_tiao, R.id.btn_yipin, R.id.btn_shipin, R.id.btn_handler, R.id.btn_myservice, R.id.btn_Drawerlayout, R.id.btn_BackLogin, R.id.BaiduTTS, R.id.BaiduTTS1, R.id.Zxing, R.id.DatePickerDialog, R.id.MyInterface, R.id.HorizontalScrollViewActivity, R.id.Dagger, R.id.btn_ACache})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_MakeView:
                Intent tv_MakeView = new Intent(this, MakeView.class);
                startActivityForResult(tv_MakeView, RAG);
                break;
            case R.id.tv_MinaService:
                Intent tv_MinaService = new Intent(this, UDPActivity.class);
                startActivityForResult(tv_MinaService, RAG);
                break;
            case R.id.tv_MinaActivity:
                Intent tv_MinaActivity = new Intent(this, MinaActivity.class);
                startActivityForResult(tv_MinaActivity, RAG);
                break;
            case R.id.tv_Socket2Activity:
                Intent tv_Socket2Activity = new Intent(this, Socket2Activity.class);
                startActivityForResult(tv_Socket2Activity, RAG);
                break;
            case R.id.tv_SocketActivity:
                Intent tv_SocketActivity = new Intent(this, SocketActivity.class);
                startActivityForResult(tv_SocketActivity, RAG);
                break;
            case R.id.tv_HandlerThreadActivity:
                Intent tv_HandlerThreadActivity = new Intent(this, HandlerThreadActivity.class);
                startActivityForResult(tv_HandlerThreadActivity, RAG);
                break;
            case R.id.btn_AsyncTask:
                Intent btn_AsyncTask = new Intent(this, AsyncTask.class);
                startActivityForResult(btn_AsyncTask, RAG);
                break;
            case R.id.btn_Glide:
                Intent btn_Glide = new Intent(this, GlideActivity.class);
                startActivityForResult(btn_Glide, RAG);
                break;
            case R.id.btn_ClotheStore:
                Intent btn_ClotheStore = new Intent(this, ClotheStore.class);
                startActivityForResult(btn_ClotheStore, RAG);
                break;
            case R.id.btn_Queue:
                Intent btn_Queue = new Intent(this, Queue.class);
                startActivityForResult(btn_Queue, RAG);
                break;
            case R.id.btn_ThreadPool:
                Intent btn_ThreadPool = new Intent(this, ThreadPool.class);
                startActivityForResult(btn_ThreadPool, RAG);
                break;
            case R.id.btn_JsonActivity:
                Intent btn_JsonActivity = new Intent(this, JsonActivity.class);
                startActivityForResult(btn_JsonActivity, RAG);
                break;
            case R.id.btn_OnTouchEvent:
                Intent btn_OnTouchEvent = new Intent(this, OnTouchEvent.class);
                startActivityForResult(btn_OnTouchEvent, RAG);
                break;
            case R.id.btn_OnRefreshActivity:
                Intent btn_OnRefreshActivity = new Intent(this, OnRefreshActivity.class);
                startActivityForResult(btn_OnRefreshActivity, RAG);
                break;
            case R.id.btn_WViewActivity:
                Intent btn_WViewActivity = new Intent(this, WViewActivity.class);
                startActivityForResult(btn_WViewActivity, RAG);
                break;
            case R.id.btn_AddViewActivity:
                Intent btn_AddViewActivity = new Intent(this, AddViewActivity.class);
                startActivityForResult(btn_AddViewActivity, RAG);
                break;
            case R.id.btn_Main2Activity:
                Intent btn_Main2Activity = new Intent(this, Main2Activity.class);
                startActivityForResult(btn_Main2Activity, RAG);
                break;
            case R.id.btn_MessageActivity:
                Intent btn_MessageActivity = new Intent(this, MessageActivity.class);
                startActivityForResult(btn_MessageActivity, RAG);
                break;
            case R.id.btn_ViewPageActivity:
                Intent btn_ViewPageActivity = new Intent(this, ViewPageActivity.class);
                startActivityForResult(btn_ViewPageActivity, RAG);
                break;
            case R.id.btn_CombinedActivity:
                Intent btn_CombinedActivity = new Intent(this, CombinedActivity.class);
                startActivityForResult(btn_CombinedActivity, RAG);
                break;
            case R.id.btn_PreisterActivity:
                Intent btn_PreisterActivity = new Intent(this, PreisterActivity.class);
                startActivityForResult(btn_PreisterActivity, RAG);
                break;
            case R.id.btn_MAPAndroidChar:
                Intent btn_MAPAndroidChar = new Intent(this, MAPAndroidChart.class);
                startActivityForResult(btn_MAPAndroidChar, RAG);
                break;
            case R.id.btn_Retrofit:
                Intent btn_Retrofit = new Intent(this, DataServiceActivity.class);
                startActivityForResult(btn_Retrofit, RAG);
                break;
            case R.id.btn_BaseDialog:
                Intent basedialog = new Intent(this, DialogActivity.class);
                startActivityForResult(basedialog, RAG);
                break;
            case R.id.btn_http:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
                break;
            case R.id.btn_back:
                Intent intent1 = new Intent();
                intent1.putExtra("first", "helloheool");
                setResult(RESULT_OK, intent1);
                finish();
                break;
            case R.id.btn_frame:
                Intent intent2 = new Intent(this, PencentFrameActivity.class);
                startActivityForResult(intent2, RAG);
                break;
            case R.id.btn_broadcast:
                Intent intent3 = new Intent(this, BroadCastActivity.class);
                startActivityForResult(intent3, RAG);
                break;
            case R.id.btn_mybroadcast:
                Intent intent4 = new Intent(this, MyBroadActivity.class);
                startActivityForResult(intent4, RAG);
                break;
            case R.id.btn_mybroadlocalcast:
                Intent intent5 = new Intent(this, LocalBroadCastActivity.class);
                startActivityForResult(intent5, RAG);
                break;
            case R.id.btn_tiao:
                Intent intent6 = new Intent(this, TiaoZhuanActivity.class);
                startActivityForResult(intent6, RAG);
                break;
            case R.id.btn_yipin:
                Intent intent7 = new Intent(this, PlayAudioActivity.class);
                startActivityForResult(intent7, RAG);
                break;
            case R.id.btn_shipin:
                Intent intent8 = new Intent(this, PlayVideoActivity.class);
                startActivityForResult(intent8, RAG);
                break;
            case R.id.btn_pictureup:
                Intent btn_pictureup = new Intent(this, PhotoActivity.class);
                startActivityForResult(btn_pictureup, RAG);
                break;
            case R.id.btn_webview:
                Intent btn_webview = new Intent(this, WebViewActivity.class);
                startActivityForResult(btn_webview, RAG);
                break;
            case R.id.btn_okhttp:
                Intent btn_okhttp = new Intent(this, OkHttpActivity.class);
                startActivityForResult(btn_okhttp, RAG);
                break;
            case R.id.btn_handler:
                Intent btn_handler = new Intent(this, HandlerActivity.class);
                startActivityForResult(btn_handler, RAG);
                break;
            case R.id.btn_myservice:
                Intent btn_myservice = new Intent(this, MyServiceActivity.class);
                startActivityForResult(btn_myservice, RAG);
                break;
            case R.id.btn_chose:
                Intent btn_chose = new Intent(this, ChosePhotoActivity.class);
                startActivityForResult(btn_chose, RAG);
                break;
            case R.id.btn_Drawerlayout:
                Intent btn_Drawerlayout = new Intent(this, DrawerLayoutActivity.class);
                startActivityForResult(btn_Drawerlayout, RAG);
                break;
            case R.id.btn_BackLogin:
                Intent btn_BackLogin = new Intent(this, LoginActivity.class);
                startActivityForResult(btn_BackLogin, RAG);
                break;
            case R.id.BaiduTTS:
                Intent BaiduTTS = new Intent(this, BaiduTTSActivity.class);
                startActivityForResult(BaiduTTS, RAG);
                break;
            case R.id.BaiduTTS1:
                Intent BaiduTTS1 = new Intent(this, MiniActivity.class);
                startActivityForResult(BaiduTTS1, RAG);
                break;
            case R.id.Zxing:
                Intent Zxing = new Intent(this, zxingActivity.class);
                startActivityForResult(Zxing, RAG);
                break;
            case R.id.DatePickerDialog:
                Intent DatePickerDialog = new Intent(this, DatePickActivity.class);
                startActivityForResult(DatePickerDialog, RAG);
                break;
            case R.id.MyInterface:
                Intent MyInterface = new Intent(this, MyInterfaceActivity.class);
                startActivityForResult(MyInterface, RAG);
                break;
            case R.id.HorizontalScrollViewActivity:
                Intent HorizontalScroll = new Intent(this, HorizontalScrollViewActivity.class);
                startActivityForResult(HorizontalScroll, RAG);
                break;
            case R.id.Dagger:
                Intent Dagger = new Intent(this, TestActivity.class);
                startActivityForResult(Dagger, RAG);
                break;
            case R.id.btn_ACache:
                Intent ACache = new Intent(this, AcacheActivity.class);
                startActivityForResult(ACache, RAG);
                break;
            case R.id.btn_BaseMaterial:
                Intent BaseMaterial = new Intent(this, MaterialActivity.class);
                startActivityForResult(BaseMaterial, RAG);
                break;
        }
    }


}
