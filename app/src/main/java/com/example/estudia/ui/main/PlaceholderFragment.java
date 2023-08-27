package com.example.estudia.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.estudia.R;
import com.example.estudia.databinding.FragmentCustomerRegisterContainerBinding;
import com.example.estudia.fragments.customer_register.EigthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FifthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FirstCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FourthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.NineCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SecondCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SevenCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SixCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.ThirdCustomerRegisterFragment;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;
    private FragmentCustomerRegisterContainerBinding binding;

    public static Fragment newInstance(int index) {
        Fragment fragment = null;
        switch (index) {
            case 1:
                fragment = new FirstCustomerRegisterFragment();
                break;
            case 2:
                fragment = new SecondCustomerRegisterFragment();
                break;
            case 3:
                fragment = new ThirdCustomerRegisterFragment();
                break;
            case 4:
                fragment = new FourthCustomerRegisterFragment();
                break;
            case 5:
                fragment = new FifthCustomerRegisterFragment();
                break;
            case 6:
                fragment = new SixCustomerRegisterFragment();
                break;
            case 7:
                fragment = new SevenCustomerRegisterFragment();
                break;
            case 8:
                fragment = new EigthCustomerRegisterFragment();
                break;
            case 9:
                fragment = new NineCustomerRegisterFragment();
                break;
        }
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = new ViewModelProvider(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentCustomerRegisterContainerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.sectionLabel;
        pageViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}