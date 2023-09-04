package com.example.estudia.fragments.customer_register;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.estudia.R;
import com.example.estudia.interfaces.OnFragmentCustomerRegisterInteractionListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FifthCustomerRegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FifthCustomerRegisterFragment extends Fragment {

    private OnFragmentCustomerRegisterInteractionListener mListener;
    Button yesButton, noButton;

    public FifthCustomerRegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fifth_customer_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        yesButton = view.findViewById(R.id.yesButtonFifthFragment);
        noButton = view.findViewById(R.id.noButtonFifthFragment);

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesButton.setSelected(true);
                noButton.setSelected(false);
                notifyActivity(true);
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noButton.setSelected(true);
                yesButton.setSelected(false);
                notifyActivity(false);
            }
        });
    }

    private void notifyActivity(boolean answer) {
        Bundle data = new Bundle();
        data.putBoolean("answer", answer);
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