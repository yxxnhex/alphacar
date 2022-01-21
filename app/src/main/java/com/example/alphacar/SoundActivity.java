package com.example.alphacar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoundActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSound;
    static public SoundPool soundPool;
    private int sound;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                .build();

        //maxStreams : 미리 로드할 사운드 스트림의 갯수
        //srcQuality : 품질을 위한 샘플링, 기본값은 0
        soundPool = new SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(audioAttributes)
                .build();

        Uri bipUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.bibip);
        sound = soundPool.load(this, R.raw.bibip, 1);
        btnSound = findViewById(R.id.btnSound);
        btnSound.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        soundPool.play(sound,1,1,0,3,1);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        soundPool.release();
        soundPool=null;
    }

}