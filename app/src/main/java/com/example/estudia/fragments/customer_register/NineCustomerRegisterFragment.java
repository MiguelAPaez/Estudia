package com.example.estudia.fragments.customer_register;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.estudia.R;
import com.example.estudia.interfaces.OnFragmentCustomerRegisterInteractionListener;
import com.example.estudia.services.CustomerRegisterSlidesService;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NineCustomerRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NineCustomerRegisterFragment extends Fragment {

    private OnFragmentCustomerRegisterInteractionListener mListener;
    EditText words;
    Button btnNext;
    String answer;

    public NineCustomerRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nine_customer_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        words = (EditText) view.findViewById(R.id.editTextNineCustomerRegister);
        btnNext = (Button) view.findViewById(R.id.nextButtonNineCustomerRegister);
        words.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                answer = words.getText().toString().trim();
                btnNext.setVisibility(View.VISIBLE);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyActivity();
            }
        });
    }

    private void notifyActivity() {
        Bundle data = new Bundle();
        data.putString("answer", answer);
        mListener.onFragmentEvent(this, data);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentCustomerRegisterInteractionListener) {
            mListener = (OnFragmentCustomerRegisterInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " should implements OnFragmentCustomerRegisterInteractionListener");
        }
    }
}