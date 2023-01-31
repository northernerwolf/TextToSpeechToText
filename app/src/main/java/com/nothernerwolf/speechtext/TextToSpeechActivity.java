package com.nothernerwolf.speechtext;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class TextToSpeechActivity extends AppCompatActivity {

    TextToSpeech TTS;
    EditText edit_text;
    SeekBar seek_bar_pitch, seek_bar_speed;
    Button btn_speak;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_to_speech);

        edit_text = findViewById(R.id.edit_text);
        seek_bar_pitch = findViewById(R.id.seek_bar_pitch);
        seek_bar_speed = findViewById(R.id.seek_bar_speed);
        btn_speak = findViewById(R.id.btn_speak);


        TTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status ==  TextToSpeech.SUCCESS){
                    int result =  TTS.setLanguage(Locale.ENGLISH);
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("Tag", "Language not supported");

                    }else {
                        btn_speak.setEnabled(true);
                    }
                }else {
                    Log.e("Tag", "Initialization failed");
                }
            }
        });

        btn_speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });

    }

    private void speak(){
        String text = edit_text.getText().toString();
        float pitch = (float) seek_bar_pitch.getProgress() / 50;
        if (pitch < 0.1) {
            pitch = 0.1f;
        }
        float speed = (float) seek_bar_speed.getProgress() / 50;
        if (speed < 0.1){
            speed = 0.1f;
        }
        TTS.setPitch(pitch);
        TTS.setSpeechRate(speed);
        TTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    protected void onDestroy() {
        if (TTS != null){
            TTS.stop();
            TTS.shutdown();
        }
        super.onDestroy();

    }
}