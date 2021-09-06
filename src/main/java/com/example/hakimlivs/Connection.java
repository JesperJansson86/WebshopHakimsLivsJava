package com.example.hakimlivs;

import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

public class Connection {

    private static  final OkHttpClient client = new OkHttpClient().newBuilder()
            .build();

    /**
     * HTTP Request using okHttp
     * @param url Http Request url
     * @param method Request method | examples: "POST" or "GET"
     * @param json sends as a response with the request
     * @param headers the curl headers
     * @return String with the response from the request.
     */
    @NotNull
    public static String httpRequest(String url,String method, String json, Headers headers){

        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), json);

        assert false;
        Request request = new Request.Builder()
                .url(url)
                .method(method,body)
                .headers(headers)
                .build();

        try(Response response = client.newCall(request).execute()){

            if(!response.isSuccessful()) throw new IOException("\nConnection ERROR: \n" + response);

            return Objects.requireNonNull(response.body()).string();

        } catch (Exception e) {
            e.printStackTrace();
            return "\nHTTP Request ERROR: \n";
        }

    }

}
