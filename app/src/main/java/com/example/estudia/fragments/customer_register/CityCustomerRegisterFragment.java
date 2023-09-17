package com.example.estudia.fragments.customer_register;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.estudia.R;
import com.example.estudia.interfaces.OnFragmentCustomerRegisterInteractionListener;
import com.example.estudia.services.CustomerRegisterSlidesService;

public class CityCustomerRegisterFragment extends Fragment {

    private OnFragmentCustomerRegisterInteractionListener mListener;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    CustomerRegisterSlidesService service;

    public CityCustomerRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_city_customer_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinner = (Spinner) view.findViewById(R.id.spinnerCityCustomerRegister);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spinner.getSelectedItem().toString().trim().equals("Selecciona tu ciudad preferida")) {
                    notifyActivity(spinner.getSelectedItem().toString().trim());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void notifyActivity(String answer) {
        Bundle data = new Bundle();
        data.putString("answer", answer);
        mListener.onFragmentEvent(this, data);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentCustomerRegisterInteractionListener) {
            mListener = (OnFragmentCustomerRegisterInteractionListener) context;
            service = new CustomerRegisterSlidesService();
            adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, service.fillSpinner(8));
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        } else {
            throw new RuntimeException(context.toString()
                    + " should implements OnFragmentCustomerRegisterInteractionListener");
        }
    }
}