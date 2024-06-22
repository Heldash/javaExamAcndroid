package com.mirea.kt.ribo.examjavaandroid.task16;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mirea.kt.ribo.examjavaandroid.R;

public class task16 extends AppCompatActivity {

    private EditText editText;
    private TextView textView;

    private static final String SHARED_PREFS_KEY = "my_shared_prefs";
    private static final String TEXT_KEY = "text_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task16);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editText.getText().toString().isEmpty()) {
                saveTextToSharedPreferences();
                    Toast.makeText(task16.this, "Данные успешно сохранены", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(task16.this, "Данные не введены", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button loadButton = findViewById(R.id.loadButton);
        loadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTextFromSharedPreferences();
            }
        });
    }

    private void saveTextToSharedPreferences() {
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(TEXT_KEY, editText.getText().toString());
        editor.apply();
    }

    private void loadTextFromSharedPreferences() {
        SharedPreferences sharedPrefs = getSharedPreferences(SHARED_PREFS_KEY, Context.MODE_PRIVATE);
        String savedText = sharedPrefs.getString(TEXT_KEY, "");
        textView.setText("Сохраненные данные: "+savedText);

        // Вывод в лог
        Log.d("Text_from_SP: ", ""+savedText);
    }
}