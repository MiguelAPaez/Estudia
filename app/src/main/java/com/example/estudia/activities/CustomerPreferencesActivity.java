package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.CUSTOMER_REGISTER_PREFERENCES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.CUSTOMER_REGISTER_QUESTIONS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.example.estudia.R;
import com.example.estudia.databinding.ActivityCustomerPreferencesBinding;
import com.example.estudia.fragments.customer_register.CityCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.CustomRegisterAdapter;
import com.example.estudia.fragments.customer_register.MobilityCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.ProgramModalityCustomerRegistryFragment;
import com.example.estudia.fragments.customer_register.ProgramTypeCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SecondCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.StudyAreaCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.ThirdCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.TimeAvailabilityCustomerRegisterFragment;
import com.example.estudia.interfaces.OnFragmentCustomerRegisterInteractionListener;
import com.example.estudia.services.PreferencesEstudiaService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CustomerPreferencesActivity extends AppCompatActivity implements OnFragmentCustomerRegisterInteractionListener {

    private ActivityCustomerPreferencesBinding binding;

    public CustomerPreferencesActivity() {
        super(R.layout.activity_customer_preferences);
    }

    private Map<Integer, String> answers = new HashMap<Integer, String>();

    private ViewPager mSlideViewPager;
    private CustomRegisterAdapter pagerAdapter;
    PreferencesEstudiaService preferencesEstudiaService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustomerPreferencesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mSlideViewPager = findViewById(R.id.view_pager_customer_preferences);

        pagerAdapter = new CustomRegisterAdapter(getSupportFragmentManager());

        pagerAdapter.addFragment(new ProgramTypeCustomerRegisterFragment());

        mSlideViewPager.setAdapter(pagerAdapter);

        this.preferencesEstudiaService = new PreferencesEstudiaService(this);
    }

    @Override
    public void onFragmentEvent(Fragment fragment, Bundle data) {
        if (fragment instanceof ProgramTypeCustomerRegisterFragment) {
            saveAnswer(0, String.valueOf(data.getString("answer")));
            addFragmentInContainer(new StudyAreaCustomerRegisterFragment());
        } else if (fragment instanceof StudyAreaCustomerRegisterFragment) {
            saveAnswer(1, String.valueOf(data.getString("answer")));
            addFragmentInContainer(new ProgramModalityCustomerRegistryFragment());
        } else if (fragment instanceof ProgramModalityCustomerRegistryFragment) {
            saveAnswer(2, String.valueOf(data.getString("answer")));
            addFragmentFromValue(data, new TimeAvailabilityCustomerRegisterFragment(), new MobilityCustomerRegisterFragment());
        } else if (fragment instanceof MobilityCustomerRegisterFragment) {
            saveAnswer(3, String.valueOf(data.getBoolean("answer")));
            addFragmentFromBoolean(data, new CityCustomerRegisterFragment(), new TimeAvailabilityCustomerRegisterFragment());
        } else if (fragment instanceof CityCustomerRegisterFragment) {
            saveAnswer(4, String.valueOf(data.getString("answer")));
            addFragmentInContainer(new TimeAvailabilityCustomerRegisterFragment());
        } else if (fragment instanceof TimeAvailabilityCustomerRegisterFragment) {
            saveAnswer(5, String.valueOf(data.getString("answer")));
            saveInMemory();
            // Go to Choose principal preferences
            // addFragmentInContainer(new TimeAvailabilityCustomerRegisterFragment());
            Intent i = new Intent(getApplicationContext(), SelectPreferencesActivity.class);
            startActivity(i);
            finish();
        }

        if (getItem(0) < pagerAdapter.getCount()) {
            int newPosition = mSlideViewPager.getCurrentItem() + 1;
            mSlideViewPager.setCurrentItem(newPosition, false);
        } else {
            Intent i = new Intent(getApplicationContext(), SelectPreferencesActivity.class);
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
        for (int i = 0; i < CUSTOMER_REGISTER_PREFERENCES.length; i++) {
            if (answers.get(i) != null) {
                this.preferencesEstudiaService.writeAttribute(CUSTOMER_REGISTER_PREFERENCES[i], answers.get(i));
            } else {
                this.preferencesEstudiaService.writeAttribute(CUSTOMER_REGISTER_PREFERENCES[i], "not apply");
            }
        }
        System.out.println("Entré a consultar los datos!!!");
        for (int j = 0; j < CUSTOMER_REGISTER_PREFERENCES.length; j++) {
            System.out.println(CUSTOMER_REGISTER_PREFERENCES[j] + ": " + this.preferencesEstudiaService.getAttribute(CUSTOMER_REGISTER_PREFERENCES[j]));
        }
    }

    private void addFragmentFromValue(Bundle data, Fragment redirectToIf, Fragment redirectToElse) {
        if (Objects.equals(data.getString("answer"), "Virtual")) {
            addFragmentInContainer(redirectToIf);
        } else {
            addFragmentInContainer(redirectToElse);
        }
    }

    private void addFragmentFromBoolean(Bundle data, Fragment redirectToIf, Fragment redirectToElse) {
        if (data.getBoolean("answer")) {
            addFragmentInContainer(redirectToIf);
        } else {
            addFragmentInContainer(redirectToElse);
        }
    }

    private void addFragmentInContainer(Fragment fragment) {
        List<Fragment> fragments = pagerAdapter.getFragments();
        if (!fragments.contains(fragment)) {
            pagerAdapter.addFragment(fragment);
            pagerAdapter.notifyDataSetChanged();
        }
    }
}