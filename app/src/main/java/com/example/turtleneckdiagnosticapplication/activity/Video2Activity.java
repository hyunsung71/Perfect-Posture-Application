package com.example.turtleneckdiagnosticapplication.activity;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.turtleneckdiagnosticapplication.R;

public class Video2Activity extends AppCompatActivity {
    static VideoView videoView2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_page2);

        videoView2 = (VideoView)findViewById(R.id.videoView2);
        Resources res = getResources();
        int id_video = res.getIdentifier("diagnostic", "raw", getPackageName());

        Uri uri = Uri.parse("android.resource://com.example.turtleneckdiagnosticapplication/" + id_video);

        // 해당하는 비디오 uri 장착
        videoView2.setVideoURI(uri);
        // 비디오 시작
        videoView2.start();
        // 화면 아래에 비디오 컨트롤러 만들기
        MediaController mcontroller = new MediaController(this);
        videoView2.setMediaController((mcontroller));
        videoView2.setOnTouchListener(new View.OnTouchListener()
        {
            // 터치 시 비디오 정지, 시작 처리
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(videoView2.isPlaying()){//비디오의 재생여부를 boolean 값으로 반환
                    videoView2.pause();
                    return false;
                } else{
                    videoView2.start();
                    return false;
                }
            }
        });
    }
}