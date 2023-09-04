package com.example.estudia.fragments.customer_register;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomRegisterAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public CustomRegisterAdapter(@NonNull FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public void addFragment(Fragment fragment) {
        fragments.add(fragment);
    }

    public List<Fragment> getFragments() {
        return fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
