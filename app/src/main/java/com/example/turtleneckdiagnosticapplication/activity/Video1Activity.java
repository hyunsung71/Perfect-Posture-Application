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

public class Video1Activity extends AppCompatActivity {
    static VideoView videoView1;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_page1);

        videoView1 = (VideoView)findViewById(R.id.videoView1);
        Resources res = getResources();
        int id_video = res.getIdentifier("chimaek", "raw", getPackageName());

        Uri uri = Uri.parse("android.resource://com.example.turtleneckdiagnosticapplication/" + id_video);

        // 해당하는 비디오 uri 장착
        videoView1.setVideoURI(uri);
        // 비디오 시작
        videoView1.start();
        // 화면 아래에 비디오 컨트롤러 만들기
        MediaController mcontroller = new MediaController(this);
        videoView1.setMediaController((mcontroller));
        videoView1.setOnTouchListener(new View.OnTouchListener()
        {
            // 터치 시 비디오 정지, 시작 처리
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(videoView1.isPlaying()){//비디오의 재생여부를 boolean 값으로 반환
                    videoView1.pause();
                    return false;
                } else{
                    videoView1.start();
                    return false;
                }
            }
        });
    }
}