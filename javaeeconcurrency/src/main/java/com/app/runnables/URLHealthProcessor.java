package com.app.runnables;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class URLHealthProcessor implements Runnable {

    private static final String URL_NAME = "http://www.google.com";

    @Override
    public void run() {
        System.out.println("inside run " + Thread.currentThread().getName());
        String statusOfApp = "";
        int responseCode = 0;
        try {
            URL url = new URL(URL_NAME);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            responseCode = connection.getResponseCode();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(responseCode == 200)
            statusOfApp = "All good";
        else
            statusOfApp = "something is wrong";

        System.out.println(statusOfApp);
    }
}
