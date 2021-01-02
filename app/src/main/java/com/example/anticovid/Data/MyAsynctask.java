package com.example.anticovid.Data;

import android.os.AsyncTask;

import com.example.anticovid.util.AsynctaskCallBack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAsynctask extends AsyncTask<String, Void, String> {

    private AsynctaskCallBack callBack;

    public MyAsynctask(AsynctaskCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder content = new StringBuilder();
        try {
            URL url = new URL(strings[0]);
            InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                content.append(line);
            }
            bufferedReader.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s == null) callBack.onError("Không có dữ liệu");
        callBack.onSuccess(s);
    }
}
