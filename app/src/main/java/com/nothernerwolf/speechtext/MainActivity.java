package com.nothernerwolf.speechtext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button text_to_speech, speech_to_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text_to_speech = findViewById(R.id.text_to_speech);
        speech_to_text = findViewById(R.id.speech_to_text);


        text_to_speech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TextToSpeechActivity.class);
                startActivity(i);

            }
        });

        speech_to_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SpeechToTextActivity.class);
                startActivity(i);

            }
        });
    }
}