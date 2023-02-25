package com.minhapresenca.minhapresencabackend.service;

import com.minhapresenca.minhapresencabackend.entity.Log;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.ZonedDateTime;

@Service
public class LogService  {
    public JSONObject createLogJson(String nameStudent, String whoDo, String operation) {
        return new JSONObject()
                .put("nameStudent", nameStudent)
                .put("whoDo", whoDo)
                .put("operation", operation)
                .put("createdAt",String.valueOf(ZonedDateTime.now()));
    }

    public void sendLogHttpRequest(JSONObject logJson) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.get("application/json"), logJson.toString());
        Request request = new Request.Builder()
                .url("http://localhost:3333")
                .post(requestBody)
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            String responseBody = response.body().string();
            System.out.println("Output from Server .... \n" + responseBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveLogToServer(String nameStudent, String whoDo, String operation) {
        JSONObject logJson = createLogJson(nameStudent, whoDo, operation);
        sendLogHttpRequest(logJson);
    }
}
