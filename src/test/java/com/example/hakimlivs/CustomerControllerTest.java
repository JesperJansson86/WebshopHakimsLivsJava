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

    private static final String TEST_ID = "" + System.currentTimeMillis();
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String EMAIL = "email@email.com";
    private static final String PASSWORD = "Password1!";
    private static final String PHONE = "0789789";
    private static final String ADDRESS = "address";
    private static final String AREA_CODE = "15263";
    private static final String CITY = "city";
    private static final String ADMIN_STATUS = "adminStatus";
    private static final String LOYAL_CUSTOMER = "loyalCustomer";
    private static final String CUSTOMER_ID = "id";

    @Test
    void shouldBeAbleToCreateUser() throws JSONException {
        //Create a customer
        JSONObject body = generateCustomerBody();
        createNewCustomer(body);

        //Find and get the created customer
        String allCustomersAsJsonArray = getAllCustomers();
        JSONObject testCustomer = getCreatedTestCustomer(allCustomersAsJsonArray);

        //assert correct customer data
        assertNotNull(testCustomer);
        assertEquals(FIRST_NAME + TEST_ID, testCustomer.getString("firstName")); //Change in CustomerDTO for consistency getters and Json keys
        assertEquals(LAST_NAME + TEST_ID, testCustomer.getString("lastName")); //Same as above
        assertEquals(EMAIL + TEST_ID, testCustomer.getString(EMAIL));
        assertEquals(PHONE + TEST_ID, testCustomer.getString("phoneNumber")); //Same as above
        assertNotNull(testCustomer.getString(ADDRESS));
        assertNotNull(testCustomer.getString(PASSWORD));
        assertFalse(testCustomer.getBoolean(ADMIN_STATUS));
        assertFalse(testCustomer.getBoolean(LOYAL_CUSTOMER));

        //Remove created testCustomer from database
        deleteCustomer(testCustomer.getString(CUSTOMER_ID));
    }

    private void createNewCustomer(JSONObject body) {
        webTestClient.post()
                .uri("api/customer/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body.toString())
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    private String getAllCustomers() {
        return webTestClient.get()
                .uri("api/customer/all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .returnResult(String.class)
                .getResponseBody()
                .blockFirst();
    }

    private void deleteCustomer(String customerId) {
        webTestClient.delete()
                .uri("api/customer/deleteById?id=" + customerId)
                .exchange()
                .expectStatus()
                .is2xxSuccessful();
    }

    private JSONObject generateCustomerBody() throws JSONException {
        JSONObject body = new JSONObject();
        body.put(FIRST_NAME, FIRST_NAME + TEST_ID);
        body.put(LAST_NAME, LAST_NAME + TEST_ID);
        body.put(EMAIL, EMAIL + TEST_ID);
        body.put(PASSWORD, PASSWORD + TEST_ID);
        body.put(PHONE, PHONE + TEST_ID);
        body.put(ADDRESS, ADDRESS + TEST_ID);
        body.put(AREA_CODE, AREA_CODE + TEST_ID);
        body.put(CITY, CITY + TEST_ID);
        return body;
    }

    private JSONObject getCreatedTestCustomer(String allUsersAsJsonArray) throws JSONException {
        JSONArray allUsers = new JSONArray(allUsersAsJsonArray);
        for (int i = 0; i < allUsers.length() ; i++) {
            JSONObject user = allUsers.getJSONObject(i);
            if (user.getString(EMAIL).endsWith(TEST_ID)) {
                return user;
            }
        }
        return null;
    }
}
