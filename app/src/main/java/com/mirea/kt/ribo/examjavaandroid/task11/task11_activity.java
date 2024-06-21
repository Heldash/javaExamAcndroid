package com.mirea.kt.ribo.examjavaandroid.task11;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.kt.ribo.examjavaandroid.R;
import com.mirea.kt.ribo.examjavaandroid.databinding.ActivityTask11Binding;

public class task11_activity extends AppCompatActivity {

    private ActivityTask11Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTask11Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstSum = binding.firstSum.getText().toString();
                String secondSum = binding.secondSum.getText().toString();
                if (firstSum!=null&&secondSum!=null&&
                        !firstSum.isEmpty()&&!secondSum.isEmpty()){
                    int firstNum,secondNum;
                    firstNum = Integer.parseInt(firstSum);
                    secondNum = Integer.parseInt(secondSum);
                    binding.firstSum.getText().clear();
                    binding.secondSum.getText().clear();
                    Log.d("result_sum","result = "+(firstNum+secondNum));
                    binding.result.setText(String.valueOf((firstNum+secondNum)));
                }else {
                    Toast.makeText(getApplicationContext(),"Пустое первое или второе поле",
                                    Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
    }
}