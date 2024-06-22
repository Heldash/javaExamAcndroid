package com.mirea.kt.ribo.examjavaandroid.task18;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mirea.kt.ribo.examjavaandroid.R;

public class Task18 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task18);

        Button startStopServiceButton = findViewById(R.id.startServiceButton);
        startStopServiceButton.setOnClickListener(this);
    }

    //Это переменная кнопки-тумблера startServiceButton - при false в каком-нибудь if выполняются
    //одни действия, при true - другие.
    //Смена false <-> true происходит циклично при нажатии на кнопку
    boolean buttonState = false;

    @Override
    public void onClick(View v) {

        //Сработал слушатель, меняем состояние buttonState на противоположное.
        //Допустим, после первого нажатия на кнопку buttonState из false станет true, в следующем блоке if из-за
        //этого запустится сервис
        buttonState = !buttonState;

        //Инициализируем переменную Button для изменения потом текста на кнопке
        Button startStopServiceButton = findViewById(R.id.startServiceButton);

        //Хотим запустить сервис:
        if (buttonState)
        {
            startStopServiceButton.setText("Остановить сервис");

            //Запускаем сервис
            Intent serviceTogglerIntent = new Intent(this, SomeService.class);
            this.startService(serviceTogglerIntent);

            //Отладка, которая по заданию не требуется: работает ли сервис?
            System.out.println("Сервис сейчас работает? -> " + isMyServiceRunning(SomeService.class));
        }
        //Хотим остановить сервис:
        else
        {
            startStopServiceButton.setText("Запустить сервис");

            //Останавливаем сервис
            Intent serviceTogglerIntent = new Intent(this, SomeService.class);
            this.stopService(serviceTogglerIntent);

            //Отладка, которая по заданию не требуется: работает ли сервис?
            System.out.println("Сервис сейчас работает? -> " + isMyServiceRunning(SomeService.class));
        }
    }

    //Отладка, которая по заданию не требуется: работает ли сервис?
    //На экзамене это писать не нужно
    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}