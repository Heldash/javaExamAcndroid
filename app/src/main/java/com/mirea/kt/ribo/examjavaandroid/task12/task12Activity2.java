package com.mirea.kt.ribo.examjavaandroid.task12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.kt.ribo.examjavaandroid.R;
import com.mirea.kt.ribo.examjavaandroid.databinding.ActivityTask122Binding;

public class task12Activity2 extends AppCompatActivity {
    private ActivityTask122Binding binding;
    long lastResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTask122Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.calcResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = binding.editTextNumber.getText().toString();
                if (num != null&&!num.isEmpty()){
                    Long number = Long.parseLong(num);
                    binding.resultTask12.setText(String.valueOf(number*number));
                    lastResult = number*number;
                }else{
                    Toast.makeText(getApplicationContext(),"Поле числа пустое",
                                    Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        binding.returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lastResult != 0){
                    Intent intent = new Intent();
                    intent.putExtra("lastResult",lastResult);
                    setResult(RESULT_OK,intent);
                    finish();
                }else{
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }
        });
    }
}