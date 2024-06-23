package com.mirea.kt.ribo.examjavaandroid.Task20;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.mirea.kt.ribo.examjavaandroid.R;
import com.mirea.kt.ribo.examjavaandroid.task19.POSTMessage;

public class Task20 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task20);

        Button sendMessageButton = findViewById(R.id.sendMessageButton);
        sendMessageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Получаем сообщение из EditText
        EditText messageToSendEditText = findViewById(R.id.messageEditText);
        String messageToSend = messageToSendEditText.getText().toString();

        //Сообщение передаём в функцию для работы с HTTP
        POSTMessage.messageTransceiver(messageToSend);
    }
}