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
     * @param json json body
     */
    public void sendMail(String json){

        String sendUrl = this.url + "mail/send";

        //temp json!!!! (twilio example json-body) currently replaces json input
        json = """
                {"personalizations": [{"to": [{"email": "Hakimslives@gmail.com"}]}],"from": {"email": "Hakimslives@gmail.com"},"subject": "Sending with SendGrid is Fun","content": [{"type": "text/plain", "value": "and easy to do anywhere, even with cURL"}]}
                """;

        Headers headers = new Headers.Builder()
                .add("Authorization", "Bearer "+ System.getenv("SECRET_MAIL_APIKEY"))
                .add("Content-Type", "application/json")
                .build();

        String temp = Connection.httpRequest(sendUrl,"POST", json, headers);

        System.out.println("POST Request:\n" + temp);
    }

}
