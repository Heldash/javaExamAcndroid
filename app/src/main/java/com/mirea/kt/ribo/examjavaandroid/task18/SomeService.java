package com.mirea.kt.ribo.examjavaandroid.task18;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;

public class SomeService extends Service {
    public SomeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //Переменная-выключатель для потока, создаваемого с помощью Worker.
    //Внутри кода указанного потока есть if, который при isToRun = true позволяет выполнить какую-нибудь
    //операцию, а при isToRun = false этот блок if блокирует выполнение этой самой операции,
    //позволяя нам затем этот поток спокойно завершить (без блока if и этой переменной правильно
    //завершить работу потока не получится).
    static boolean isToRun;

    //Код, который выполняется при запуске сервиса:
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Разблокируем тот самый if, дабы код внутри него выполнялся
        isToRun = true;

        //Создаём поток с помощью WorkManager
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TaskWorker.class).build();
        WorkManager.getInstance(this).enqueue(workRequest);

        return super.onStartCommand(intent, flags, startId);
    }

    //Код, который выполняется при остановке сервиса:
    @Override
    public void onDestroy() {
        super.onDestroy();

        //Блокируем тот самый if, дабы код внутри него не выполнялся
        isToRun = false;

        //Завершаем созданный WorkManager-ом поток
        OneTimeWorkRequest workRequest = new OneTimeWorkRequest.Builder(TaskWorker.class).build();
        WorkManager.getInstance(this).cancelWorkById(workRequest.getId());

        //Останавливаем сервис
        stopSelf();

        Log.i("service_stat", "Сервис остановлен.");
    }
}