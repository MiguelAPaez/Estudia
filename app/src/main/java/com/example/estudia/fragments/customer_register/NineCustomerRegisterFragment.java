package com.example.estudia.fragments.customer_register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.estudia.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NineCustomerRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NineCustomerRegisterFragment extends Fragment {

    public NineCustomerRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nine_customer_register, container, false);
    }
}