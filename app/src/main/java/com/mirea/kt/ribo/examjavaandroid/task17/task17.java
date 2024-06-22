package com.mirea.kt.ribo.examjavaandroid.task17;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mirea.kt.ribo.examjavaandroid.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class task17 extends AppCompatActivity {

    EditText filenameEditText, dataEditText;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task17);

        filenameEditText = findViewById(R.id.fileNameEditText);
        dataEditText = findViewById(R.id.textEditText);
        saveButton = findViewById(R.id.saveButton); //Привязка элементов по их идентификаторам

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Установка слушателя кликов на кнопку saveButton
                String filename = filenameEditText.getText().toString();
                String data = dataEditText.getText().toString(); //Получение текста из EditText

                // Создание нового потока для сохранения данных в файл
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        File file = saveDataToFile(filename, data); //Вызов метода saveDataToFile
                        //Проверка на успешность сохранения данных в файл
                        if (file != null) {
                            final String filePath = file.getAbsolutePath(); //Получение абсолютного пути к созданному файлу
                            //Запуск Runnable на основном UI потоке для обновления пользовательского интерфейса
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(task17.this, "Data saved to file: " + filePath, Toast.LENGTH_SHORT).show();
                                    System.out.println("Data saved to file: " + filePath); //Вывод в лог пути для проверки
                                }

                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(task17.this, "Error saving data", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();

            }
        });
    }

    //Объявление метода для сохранения
    private File saveDataToFile(String filename, String data) {
        File directory = getFilesDir(); //Получение директории для файлов приложения
        File file = new File(directory, filename + ".txt"); //Создание нового файла с именем от пользователя

        try {
            FileWriter writer = new FileWriter(file);
            writer.append(data); //Запись данных в файл с использованием объекта FileWriter
            writer.flush(); //Принудительное очищение буфера для записи данных
            writer.close(); //Закрытие потока записи данных
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            return null; //Вывод стека вызовов при возникновении исключения и возврат null
        }
    }
}
