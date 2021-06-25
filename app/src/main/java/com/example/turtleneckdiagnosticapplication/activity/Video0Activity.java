package com.example.turtleneckdiagnosticapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.turtleneckdiagnosticapplication.R;

public class Video0Activity extends AppCompatActivity {
    static VideoView videoView0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_page0);

        videoView0 = (VideoView)findViewById(R.id.videoView0);
        Resources res = getResources();
        int id_video = res.getIdentifier("stretching", "raw", getPackageName());

        Uri uri = Uri.parse("android.resource://com.example.turtleneckdiagnosticapplication/" + id_video);

        // 해당하는 비디오 uri 장착
        videoView0.setVideoURI(uri);
        // 비디오 시작
        videoView0.start();
        // 화면 아래에 비디오 컨트롤러 만들기
        MediaController mcontroller = new MediaController(this);
        videoView0.setMediaController((mcontroller));
        videoView0.setOnTouchListener(new View.OnTouchListener()
        {
            // 터치 시 비디오 정지, 시작 처리
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(videoView0.isPlaying()){//비디오의 재생여부를 boolean 값으로 반환
                    videoView0.pause();
                    return false;
                } else{
                    videoView0.start();
                    return false;
                }
            }
        });
    }
}