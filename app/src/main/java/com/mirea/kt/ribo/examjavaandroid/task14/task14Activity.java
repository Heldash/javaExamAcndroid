package com.mirea.kt.ribo.examjavaandroid.task14;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.mirea.kt.ribo.examjavaandroid.R;
import com.mirea.kt.ribo.examjavaandroid.databinding.ActivityTask14Binding;

public class task14Activity extends AppCompatActivity {
    private ActivityTask14Binding binding;
    int fragmentCnt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTask14Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        fragmentCnt = 1;
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getSupportFragmentManager();
                if (fragmentCnt == 2){
                    manager.beginTransaction().replace(
                            R.id.fragment_container, Task14_1.class,null
                    ).commit();
                    fragmentCnt = 1;
                }else{
                    manager.beginTransaction().replace(
                            R.id.fragment_container, Task14Fragment_2.class,null
                    ).commit();
                    fragmentCnt = 2;
                }
            }
        });
    }
}