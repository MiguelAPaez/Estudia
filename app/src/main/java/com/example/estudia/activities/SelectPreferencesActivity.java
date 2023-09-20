package com.example.estudia.activities;

import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_DESC_PREFERENCES;
import static com.example.estudia.enums.CustomConstants.EstudiaConstants.ARRAY_PREFERENCES;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.estudia.R;
import com.example.estudia.adapters.CustomPreferencesAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectPreferencesActivity extends AppCompatActivity {

    List<String> preferencesTitleList, prferencesDescriptionList;
    RecyclerView recyclerView;
    Button btnNext;
    CustomPreferencesAdapter customPreferencesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_preferences);

        preferencesTitleList = new ArrayList<>(Arrays.asList(ARRAY_PREFERENCES));
        prferencesDescriptionList = new ArrayList<>(Arrays.asList(ARRAY_DESC_PREFERENCES));

        recyclerView = findViewById(R.id.recyclerCustomPreferences);
        btnNext = (Button) findViewById(R.id.nextButtonPreferences);

        customPreferencesAdapter = new CustomPreferencesAdapter(getApplicationContext(), preferencesTitleList, prferencesDescriptionList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(customPreferencesAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback
            (ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.START | ItemTouchHelper.END, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder
                viewHolder, @NonNull RecyclerView.ViewHolder target) {
            int fromPosition = viewHolder.getAdapterPosition();
            int toPosition = target.getAdapterPosition();
            Collections.swap(preferencesTitleList, fromPosition, toPosition);
            recyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
            System.out.println("Soy la listaaaa!!");
            System.out.println(preferencesTitleList);
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }
    };

}