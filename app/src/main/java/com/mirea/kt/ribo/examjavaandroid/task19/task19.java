package com.mirea.kt.ribo.examjavaandroid.task19;

import static android.widget.Toast.LENGTH_LONG;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mirea.kt.ribo.examjavaandroid.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class task19 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task19);
        Button urlApplyButton = this.findViewById(R.id.urlApplyButton);
        urlApplyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Введённый адрес сайта
        EditText urlField = this.findViewById(R.id.urlEditText);
        String urlChosen = urlField.getText().toString();

        //Если EditText не пустой, когда нажата кнопка
        if (!urlChosen.isEmpty()) {
            Runnable thread = () -> {
                try {

                    //Создаём объект класса URL
                    URL url = new URL(urlChosen);

                    //Сюда будет сохранено содержимое страницы
                    BufferedReader readr = new BufferedReader(new InputStreamReader(url.openStream()));

                    //Записываем в логи построчно загружаемую страницу
                    String line;
                    while ((line = readr.readLine()) != null) Log.i("site", line);

                    //Закрываем поток читателя
                    readr.close();

                    //"От лица" главного потока показываем тост
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(this, "Загрузка страница успешно завершена.", LENGTH_LONG).show();
                    });

                    Log.i("site download", "Загрузка страница успешно завершена.");
                }

                //Если адрес сайта указан неправильно - нет "https", например
                catch (MalformedURLException mue) {
                    Log.e("site download", "Адрес сайта указан неправильно. Протокол подключения необходимо указывать.");
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(this, "Адрес сайта указан неправильно.", LENGTH_LONG).show();
                        Toast.makeText(this, "Протокол подключения необходимо указывать.", LENGTH_LONG).show();
                    });
                }
                //Если адрес написан правильно, но такой сайт не существует
                catch (UnknownHostException uhe) {
                    Log.e("site download", "Страница с указанным адресом не найдена. Опечатка, проблемы с интернетом?");
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(this, "Страница с указанным адресом не найдена. Опечатка, проблемы с интернетом?", LENGTH_LONG).show();
                        Toast.makeText(this, "Опечатка, проблемы с интернетом?", LENGTH_LONG).show();
                    });
                }
                //Проблемы с записью файла или сайт нельзя сохранить (код 403)
                catch (IOException e) {
                    Log.e("site download", "Cайт не позволяет себя так сохранить или иная проблема с получением данных.");
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(this, "Cайт не позволяет себя так сохранить или иная проблема с получением данных.", LENGTH_LONG).show();
                    });
                }
            };
            new Thread(thread).start();
        } else {
            Toast.makeText(this, "URL не введён.", LENGTH_LONG).show();
        }
    }
}