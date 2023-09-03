package com.example.estudia.activities;

import android.os.Bundle;

import com.example.estudia.R;
import com.example.estudia.fragments.customer_register.EigthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FifthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FirstCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.FourthCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.NineCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SecondCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SevenCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.SixCustomerRegisterFragment;
import com.example.estudia.fragments.customer_register.ThirdCustomerRegisterFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.estudia.ui.main.SectionsPagerAdapter;
import com.example.estudia.databinding.ActivityCustomerRegisterContainerBinding;

import java.util.ArrayList;
import java.util.List;

public class CustomerRegisterContainer extends AppCompatActivity {

    private ActivityCustomerRegisterContainerBinding binding;

    public CustomerRegisterContainer() {
        super(R.layout.activity_customer_register_container);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*List<Fragment> list =new ArrayList<>();
        list.add(new FirstCustomerRegisterFragment());
        list.add(new SecondCustomerRegisterFragment());
        list.add(new ThirdCustomerRegisterFragment());
        list.add(new FourthCustomerRegisterFragment());
        list.add(new FifthCustomerRegisterFragment());
        list.add(new SixCustomerRegisterFragment());
        list.add(new SevenCustomerRegisterFragment());
        list.add(new EigthCustomerRegisterFragment());
        list.add(new NineCustomerRegisterFragment());*/

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.view_pager_customer, FirstCustomerRegisterFragment.class, null)
                    .add(R.id.view_pager_customer, SecondCustomerRegisterFragment.class, null)
                    .add(R.id.view_pager_customer, ThirdCustomerRegisterFragment.class, null)
                    .add(R.id.view_pager_customer, FourthCustomerRegisterFragment.class, null)
                    .add(R.id.view_pager_customer, FifthCustomerRegisterFragment.class, null)
                    .add(R.id.view_pager_customer, SevenCustomerRegisterFragment.class, null)
                    .add(R.id.view_pager_customer, EigthCustomerRegisterFragment.class, null)
                    .add(R.id.view_pager_customer, NineCustomerRegisterFragment.class, null)
                    .commit();
        }

        binding = ActivityCustomerRegisterContainerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager_customer);
        viewPager.setAdapter(sectionsPagerAdapter);
        /*TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = binding.fab;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
}