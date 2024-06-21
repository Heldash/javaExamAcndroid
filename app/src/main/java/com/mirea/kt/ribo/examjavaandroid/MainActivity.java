package com.mirea.kt.ribo.examjavaandroid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mirea.kt.ribo.examjavaandroid.databinding.ActivityMainBinding;
import com.mirea.kt.ribo.examjavaandroid.task11.task11_activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnTask11.setOnClickListener(this);
        binding.btnTask12.setOnClickListener(this);
        binding.btnTask13.setOnClickListener(this);
        binding.btnTask14.setOnClickListener(this);
        binding.btnTask15.setOnClickListener(this);
        binding.btnTask16.setOnClickListener(this);
        binding.btnTask17.setOnClickListener(this);
        binding.btnTask18.setOnClickListener(this);
        binding.btnTask19.setOnClickListener(this);
        binding.btnTask20.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Log.d("onClicldsdas", "onClick: "+
                v.getId()+" "+R.id.btn_task_11);
        if (v.getId() == R.id.btn_task_11){
            startActivity(new Intent(this, task11_activity.class));
        }
    }
}