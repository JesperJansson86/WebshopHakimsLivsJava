package com.example.hakimlivs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@AutoConfigureWebTestClient
class CustomerControllerTest {

    @Autowired
    private WebTestClient webTestClient;
    private final static String TEST_ID = "" + System.currentTimeMillis();

    @Test
    void shouldBeAbleToCreateUser() throws JSONException {
        String signupBody = "{\n" +
                "        \"firstname\":\"firstname" + TEST_ID + "\",\n" +
                "        \"lastname\":\"lastname" + TEST_ID + "\",\n" +
                "        \"email\":\"email" + TEST_ID + "\",\n" +
                "        \"password\":\"password" + TEST_ID + "\",\n" +
                "        \"phone\":\"phone" + TEST_ID + "\",\n" +
                "        \"address\":\"address" + TEST_ID +"\",\n" +
                "        \"areaCode\":\"areaCode" + TEST_ID + "\",\n" +
                "        \"city\":\"city" + TEST_ID + "\"\n" +
                "}";

        webTestClient.post()
                .uri("api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(signupBody)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();

        String s = webTestClient.get()
                .uri("api/customer/all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();

        JSONObject testUser = null;
        testUser = getTestCreatedUser(s, testUser);

        System.out.println(testUser);
        assertNotNull(testUser);
        assertTrue(testUser.getString("firstName").endsWith(TEST_ID));
        assertTrue(testUser.getString("lastName").endsWith(TEST_ID));
        assertTrue(testUser.getString("email").endsWith(TEST_ID));
        //assertTrue(testUser.getString("password").endsWith(TEST_ID));
        assertTrue(testUser.getString("phoneNumber").endsWith(TEST_ID));
        assertTrue(testUser.getString("email").endsWith(TEST_ID));
    }

    private JSONObject getTestCreatedUser(String s, JSONObject testUser) throws JSONException {
        JSONArray allUsers = new JSONArray(s);
        for (int i = 0; i < allUsers.length() ; i++) {
            JSONObject user = allUsers.getJSONObject(i);
            if (user.getString("firstName").endsWith(TEST_ID)) {
                return user;
            }
        }
        return null;
    }
}
