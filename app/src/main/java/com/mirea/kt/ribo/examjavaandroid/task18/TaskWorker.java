package com.mirea.kt.ribo.examjavaandroid.task18;

import static com.mirea.kt.ribo.examjavaandroid.task18.SomeService.isToRun;
import static java.lang.Thread.sleep;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class TaskWorker extends Worker {
    public TaskWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        int i = 0;

        //Просто считаем секунды
        while (true) {

            //Тот самый if, упомянутый в SomeService - без isToRun из вечного цикла не выйти
            if (isToRun)
            {
                Log.i("workerCounter", "Счётчик равен " + i);
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
            else break;
        }
        return Result.success();
    }

    @Override
    public void onStopped() {
        super.onStopped();
        Log.i("workerCounter", "Счётчик остановлен");
    }


}
