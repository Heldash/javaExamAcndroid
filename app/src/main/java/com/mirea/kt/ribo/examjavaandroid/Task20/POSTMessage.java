package com.mirea.kt.ribo.examjavaandroid.Task20;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;


public class POSTMessage extends Task20 {

    //Переменные, которые нужно будет заполнить после получения варианта на экзамене
    private static String serverAddress = "АДРЕС НА САЙТЕ";
    private static String messagePOSTKey = "КЛЮЧ ЗНАЧЕНИЯ \"сообщение\" СПРОСИТЬ";

    //Формируем из данных POST-запрос
    private static String generateStringBody(HashMap<String, String> userCredentialsMap) {
        StringBuilder sbParams = new StringBuilder();

        int i = 0;

        for (String key : userCredentialsMap.keySet()) {
            try {
                if (i != 0) sbParams.append("&");
                sbParams.append(key).append("=").append(URLEncoder.encode(userCredentialsMap.get(key), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            i++;
        }
        return sbParams.toString();
    }

    //Метод для приёмопередачи HTTP-пакетов
    public static void messageTransceiver(String message) {
        Thread t = new Thread(() ->
        {

            //В Map сохраняем все пары данных в виде ключ+значение, которые должны присутствовать в POST-запросе
            HashMap<String, String> messageToSendMap = new HashMap<>();
            messageToSendMap.put(messagePOSTKey, message);

            try {

                //Сюда в самом конце будет сохранён ответ сервера
                String responseBody;

                //Сохраняем ссылку на страницу сайта в объект класса URL
                URL url = new URL(serverAddress);

                //Настраиваем соединение по HTTP
                URLConnection connection = url.openConnection();
                HttpURLConnection httpConnection = (HttpURLConnection) connection;
                httpConnection.setRequestMethod("POST");
                httpConnection.setDoOutput(true);

                //Открываем поток для записи и отправки на сервер имеющегося POST-запроса
                OutputStreamWriter osw = new OutputStreamWriter(httpConnection.getOutputStream());

                //Отправляем POST
                osw.write(generateStringBody(messageToSendMap));

                //Чистим поток от предыдущего потока данных
                osw.flush();

                //Сохраняем код ответа от сервера
                int responseCode = httpConnection.getResponseCode();

                //Если ответ от сервера "всё норм", то...
                if (responseCode == 200) {

                    //Сохраняем весь ответ от сервера, который следует сразу после отправки POST
                    InputStreamReader isr = new InputStreamReader(httpConnection.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String currentLine;
                    StringBuilder sbResponse = new StringBuilder();

                    while ((currentLine = br.readLine()) != null) sbResponse.append(currentLine);

                    responseBody = sbResponse.toString();
                    //Log.d("Ответ сервера:\n", responseBody);
                    System.out.println(responseBody);

                    //Закрываем потоки
                    isr.close();
                    br.close();
                } else Log.e("Связь с сервером", "Доступ к объекту сайта запрещён.");

                //Закрываем потоки
                osw.close();
                httpConnection.disconnect();

            } catch (MalformedURLException e) {
                Log.e("Связь с сервером", "Адрес сервера указан неверно. Протокол тоже должен быть указан.");
            } catch (IOException e) {
                Log.e("Связь с сервером", "Проблемы с подключением к интернету или сайту.");
            }
        });
        t.start();
    }
}
