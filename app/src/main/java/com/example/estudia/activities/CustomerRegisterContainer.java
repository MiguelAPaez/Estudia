package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.CUSTOMER_REGISTER_QUESTIONS;

import android.content.Intent;
import android.os.Bundle;

import com.example.estudia.R;
import com.example.estudia.fragments.customer_register.CustomRegisterAdapter;
import com.example.estudia.fragments.customer_register.EigthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FifthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FirstCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FourthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.NineCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SecondCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SevenCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SixCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.ThirdCustomerRegisterFragment;
import com.example.estudia.interfaces.OnFragmentCustomerRegisterInteractionListener;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import com.example.estudia.databinding.ActivityCustomerRegisterContainerBinding;
import com.example.estudia.services.PreferencesEstudiaService;

import java.util.HashMap;
import java.util.Map;

public class CustomerRegisterContainer extends AppCompatActivity implements OnFragmentCustomerRegisterInteractionListener {

    private ActivityCustomerRegisterContainerBinding binding;

    public CustomerRegisterContainer() {
        super(R.layout.activity_customer_register_container);
    }

    private Map<Integer, String> answers = new HashMap<Integer, String>();
    private FragmentManager manager;
    private ViewPager mSlideViewPager;
    private CustomRegisterAdapter pagerAdapter;
    PreferencesEstudiaService preferencesEstudiaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCustomerRegisterContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mSlideViewPager = findViewById(R.id.view_pager_customer);

        pagerAdapter = new CustomRegisterAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new FirstCustomerRegisterFragment());

        mSlideViewPager.setAdapter(pagerAdapter);

        this.preferencesEstudiaService = new PreferencesEstudiaService(this);
    }

    @Override
    public void onFragmentEvent(Fragment fragment, Bundle data) {
        if (fragment instanceof FirstCustomerRegisterFragment) {
            if (data.getBoolean("answer")) {
                pagerAdapter.addFragment(new SecondCustomerRegisterFragment());
                pagerAdapter.notifyDataSetChanged();
            } else {
                pagerAdapter.addFragment(new ThirdCustomerRegisterFragment());
                pagerAdapter.notifyDataSetChanged();
            }
            saveAnswer(1, String.valueOf(data.getBoolean("answer")));
        } else if (fragment instanceof SecondCustomerRegisterFragment) {
            saveAnswer(2, data.getString("answer"));
            pagerAdapter.addFragment(new ThirdCustomerRegisterFragment());
            pagerAdapter.notifyDataSetChanged();
        } else if (fragment instanceof ThirdCustomerRegisterFragment) {
            if (data.getBoolean("answer")) {
                pagerAdapter.addFragment(new FourthCustomerRegisterFragment());
                pagerAdapter.notifyDataSetChanged();
            } else {
                pagerAdapter.addFragment(new FifthCustomerRegisterFragment());
                pagerAdapter.notifyDataSetChanged();
            }
            saveAnswer(3, String.valueOf(data.getBoolean("answer")));
        } else if (fragment instanceof FourthCustomerRegisterFragment) {
            saveAnswer(4, data.getString("answer"));
            pagerAdapter.addFragment(new FifthCustomerRegisterFragment());
            pagerAdapter.notifyDataSetChanged();
        } else if (fragment instanceof FifthCustomerRegisterFragment) {
            if (data.getBoolean("answer")) {
                pagerAdapter.addFragment(new SixCustomerRegisterFragment());
                pagerAdapter.notifyDataSetChanged();
            } else {
                pagerAdapter.addFragment(new EigthCustomerRegisterFragment());
                pagerAdapter.notifyDataSetChanged();
            }
            saveAnswer(5, String.valueOf(data.getBoolean("answer")));
        } else if (fragment instanceof SixCustomerRegisterFragment) {
            saveAnswer(6, data.getString("answer"));
            pagerAdapter.addFragment(new SevenCustomerRegisterFragment());
            pagerAdapter.notifyDataSetChanged();
        } else if (fragment instanceof SevenCustomerRegisterFragment) {
            saveAnswer(7, data.getString("answer"));
            pagerAdapter.addFragment(new EigthCustomerRegisterFragment());
            pagerAdapter.notifyDataSetChanged();
        } else if (fragment instanceof EigthCustomerRegisterFragment) {
            saveAnswer(8, data.getString("answer"));
            pagerAdapter.addFragment(new NineCustomerRegisterFragment());
            pagerAdapter.notifyDataSetChanged();
        } else if (fragment instanceof NineCustomerRegisterFragment) {
            saveAnswer(9, data.getString("answer"));
            saveInMemory();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }

        if (getItem(0) < pagerAdapter.getCount()) {
            int newPosition = mSlideViewPager.getCurrentItem() + 1;
            mSlideViewPager.setCurrentItem(newPosition, false);
        } else {
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            finish();
        }


        System.out.println("Answers!!");
        System.out.println(answers);
    }

    private void saveAnswer(int pos, String answer) {
        answers.put(pos, answer);
    }

    private int getItem(int i) {
        return mSlideViewPager.getCurrentItem() + i;
    }

    private void saveInMemory() {
        for (int i = 0; i < CUSTOMER_REGISTER_QUESTIONS.length; i++) {
            if (answers.containsKey(i)) {
                this.preferencesEstudiaService.writeAttribute(CUSTOMER_REGISTER_QUESTIONS[i], answers.get(i + 1));
            } else {
                this.preferencesEstudiaService.writeAttribute(CUSTOMER_REGISTER_QUESTIONS[i], "not apply");
            }
        }
        for (int j = 0; j < CUSTOMER_REGISTER_QUESTIONS.length; j++) {
            System.out.println("Entré a consultar los datos!!!");
            System.out.println(CUSTOMER_REGISTER_QUESTIONS[j] + ": " + this.preferencesEstudiaService.getAttribute(CUSTOMER_REGISTER_QUESTIONS[j]));
        }
    }
}