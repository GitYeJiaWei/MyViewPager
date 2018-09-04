package com.example.yjw.playaudio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.yjw.common.ActivityCollecter;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.myviewpager.R;

import java.io.File;

/**
 * 视频播放
 */
public class PlayVideoActivity extends BaseActivity implements View.OnClickListener {
    private VideoView videoView;
    private MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        videoView = findViewById(R.id.video_view);
        Button paly = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button stop = findViewById(R.id.stop);
        paly.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);

        if (ContextCompat.checkSelfPermission(PlayVideoActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(PlayVideoActivity.this, new String[]
                    {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPlayer();//初始化MediaPlayer
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initVideoPlayer();
                } else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                    ActivityCollecter.finishAll();
                }
                break;
            default:
        }
    }

    private void initVideoPlayer() {
       /* File file = new File(Environment.getExternalStorageDirectory(), "movie.mp4");
            videoView.setVideoPath(file.getPath());//指定视频文件的路径*/

        mediaController = new MediaController(this);
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.movie;
        videoView.setVideoURI(Uri.parse(uri));
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();
        videoView.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if (!videoView.isPlaying()) {
                    videoView.start();//开始播放
                }
                break;
            case R.id.pause:
                if (videoView.isPlaying()) {
                    videoView.pause();//暂停播放
                }
                break;
            case R.id.stop:
                if (videoView.isPlaying()) {
                    videoView.resume();//重新播放
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();

        }
    }
}