package com.example.hakimlivs.mail;

import com.example.hakimlivs.Connection;
import okhttp3.Headers;

public class MailService {

    /**
     * SendGrid api URL
     */
    private final String url = "https://api.sendgrid.com/v3/";

    /**
     * Sends a mail using Twilio SendsGrid
     * @param apiKey twilio ApiKey
     * @param json json body
     */
    public void sendMail(String apiKey, String json){

        String sendUrl = this.url + "mail/send";

        //temp json!!!! (twilio example json-body) currently replaces json input
        json = "{\"personalizations\":[{\"to\":[{\"email\":\"Hakimslives@gmail.com\",\"name\":\"Hakim Test\"}],\"subject\":\"Mail Test!\"}],\"content\":\"Content Test\" [{\"type\": \"text/plain\", \"value\": \"Heya!\"}],\"from\":{\"email\":\"Hakimslives@gmail.com\",\"name\":\"HakimsLivs\"},\"reply_to\":{\"email\":\"Hakimslives@gmail.com\",\"name\":\"HakimsLivs\"}}";

        Headers headers = new Headers.Builder()
                .add("Authorization", "Bearer "+ apiKey)
                .add("Content-Type", "application/json")
                .build();

        String temp = Connection.httpRequest(sendUrl,"POST", json, headers);

        System.out.println("POST Request:\n" + temp);
    }

}
