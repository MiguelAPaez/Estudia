package com.example.estudia.facades;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.CUSTOMER_REGISTER_PREFERENCES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.CUSTOMER_REGISTER_QUESTIONS;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.EMAIL_USER;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_1;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_2;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PERSONALITY_3;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.estudia.services.PreferencesEstudiaService;
import com.example.estudia.services.ToastEstudiaService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class DynamoPersistenceImplementation {

    Context context;
    PreferencesEstudiaService preferencesEstudiaService;
    ToastEstudiaService toastEstudiaService;
    private RequestQueue mQueue;
    private static final String URL = "https://o4d9r7bur1.execute-api.us-east-1.amazonaws.com";

    public DynamoPersistenceImplementation(Context context) {
        this.context = context;
        this.preferencesEstudiaService = new PreferencesEstudiaService(this.context);
        this.toastEstudiaService = new ToastEstudiaService(this.context);
        mQueue = Volley.newRequestQueue(this.context);
    }

    public void getUsers() {
        String endpoint = URL + "/users";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endpoint, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        showToast("Se consultaron exitosamente los datos de los usuarios");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    public void getUser() {
        String userEmail = this.preferencesEstudiaService.getAttribute(EMAIL_USER);
        System.out.println("USER EMAIL!!! " + userEmail);
        String endpoint = URL + "/user/" + userEmail;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endpoint, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        showToast("Se consultaron exitosamente los datos del usuario");
                        try {
                            for (Iterator<String> it = response.keys(); it.hasNext(); ) {
                                String currentKey = it.next();
                                Object value = response.get(currentKey);
                                if (currentKey.equals("body")) {
                                    String body = response.getString("body");
                                    JSONObject json = new JSONObject(body);
                                    writeAttributes(json);
                                }
                            }
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Entré al error interno");
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    public void saveUserInfo() {
        try {
            JSONObject data = getUserDataJson();
            String endpoint = URL + "/user";
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, endpoint, data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            System.out.println(response);
                            showToast("Se han registrado los datos del usuario");
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            mQueue.add(request);
        } catch (JSONException error) {
            error.printStackTrace();
        }
    }

    private JSONObject getUserDataJson() throws JSONException {
        JSONObject json = new JSONObject();

        json.put(EMAIL_USER, this.preferencesEstudiaService.getAttribute(EMAIL_USER));

        json.put(PERSONALITY_1, this.preferencesEstudiaService.getAttribute(PERSONALITY_1));
        json.put(PERSONALITY_2, this.preferencesEstudiaService.getAttribute(PERSONALITY_2));
        json.put(PERSONALITY_3, this.preferencesEstudiaService.getAttribute(PERSONALITY_3));

        for (String customerRegisterQuestion : CUSTOMER_REGISTER_QUESTIONS) {
            json.put(customerRegisterQuestion, this.preferencesEstudiaService.getAttribute(customerRegisterQuestion));
        }
        ;

        for (String customerRegisterPreference : CUSTOMER_REGISTER_PREFERENCES) {
            json.put(customerRegisterPreference, this.preferencesEstudiaService.getAttribute(customerRegisterPreference));
        }

        return json;
    }

    private void showToast(String message) {
        this.toastEstudiaService.showToast(message);
    }

    private void writeAttributes(JSONObject json) throws JSONException {
        for (Iterator<String> it = json.keys(); it.hasNext(); ) {
            String currentKey = it.next();
            Object value = json.get(currentKey);
            if (!currentKey.equals("createdAt")) {
                System.out.println("CURRENT KEY!");
                System.out.println(currentKey);
                System.out.println("VALUE!!");
                System.out.println(value);
                this.preferencesEstudiaService.writeAttribute(currentKey, (String) value);
            }
        }
    }

}
