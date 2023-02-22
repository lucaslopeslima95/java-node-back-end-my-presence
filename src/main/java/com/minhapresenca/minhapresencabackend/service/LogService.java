package com.minhapresenca.minhapresencabackend.service;

import com.minhapresenca.minhapresencabackend.entity.Log;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class LogService  {
    public void saveLog(String nameStudent,String whoDo,String operation){
        Log log = new Log();
        log.setNameStudent(nameStudent);
        log.setWhoDo(whoDo);
        log.setOperation(operation);
        this.httpRequest(log);
    }
    public void httpRequest(Log log) {
        try {

            URL url = new URL("http://localhost:3333");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject input = new JSONObject();
            input.put("nameStudent", log.getNameStudent());
            input.put("whoDo", log.getWhoDo());
            input.put("operation",log.getOperation());
            input.put("createdAt",log.getCreatedAt());

            OutputStream os = conn.getOutputStream();
            os.write(input.toString().getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
