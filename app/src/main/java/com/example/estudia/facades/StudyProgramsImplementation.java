package com.example.estudia.facades;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.PROGRAMS;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.estudia.services.PreferencesEstudiaService;
import com.example.estudia.services.ToastEstudiaService;

import org.json.JSONObject;

public class StudyProgramsImplementation {

    Context context;
    PreferencesEstudiaService preferencesEstudiaService;
    ToastEstudiaService toastEstudiaService;
    private RequestQueue mQueue;
    private static final String URL = "https://a6cjblxjh7.execute-api.us-east-1.amazonaws.com";

    public StudyProgramsImplementation(Context context) {
        this.context = context;
        this.preferencesEstudiaService = new PreferencesEstudiaService(this.context);
        this.toastEstudiaService = new ToastEstudiaService(this.context);
        mQueue = Volley.newRequestQueue(this.context);
    }

    public void getPrograms() {
        String endpoint = URL + "/all_programs";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, endpoint, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response);
                        writePrograms(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    public void writePrograms(JSONObject response) {
        this.preferencesEstudiaService.writeAttribute(PROGRAMS, response.toString());
        System.out.println("PROGRAMAS!!!");
        System.out.println(this.preferencesEstudiaService.getAttribute(PROGRAMS));
        this.toastEstudiaService.showToast("Se han guardado los programas!");
    }
}
