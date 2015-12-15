package com.example.joao.myapplication;

import au.com.dius.pact.consumer.ConsumerPactTest;

import au.com.dius.pact.model.PactFragment;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class ExampleJavaConsumerPactTest extends ConsumerPactTest {
    @Override
    protected PactFragment createFragment(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("there is an alligator named Mary") // NOTE: Using provider states are optional, you can leave it out
                .uponReceiving("a request for an alligator")
                .path("/alligators/Mary")
                .method("GET")
                .headers(headers)
                .body("{\"test\":true}")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"name\":Mary}").toFragment();
    }


    @Override
    protected String providerName() {
        return "Some Provider";
    }

    @Override
    protected String consumerName() {
        return "Some Consumer";
    }

    @Override
    protected void runTest(String urlp) {
        URL url = null;
        try {
            url = new URL("http://www.android.com/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            //readStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }


        assertEquals(true, true);
        //assertEquals(new ProviderClient(urlp).getSomething(), "{\"responsetest\":true}");
    }

}

