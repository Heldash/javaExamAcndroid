package com.mirea.kt.ribo.examjavaandroid.task13;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.kt.ribo.examjavaandroid.R;
import com.mirea.kt.ribo.examjavaandroid.databinding.ActivityTask13Binding;

public class task13 extends AppCompatActivity {
    private ActivityTask13Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTask13Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sharText = binding.sharedText.getText().toString();
                if (sharText != null&& !sharText.isEmpty()){
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra(Intent.EXTRA_TEXT,sharText);
                    intent.setType("text/plain");
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),
                            "Поле с текстом пустое",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}