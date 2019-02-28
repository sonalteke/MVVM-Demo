package com.melayer.firstimagedemo;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.melayer.firstimagedemo.adapter.RecyclerAdapter;
import com.melayer.firstimagedemo.models.NicePlaces;
import com.melayer.firstimagedemo.viewModels.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;
    private ProgressBar progressBar;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.init();

        mainActivityViewModel.getNicePlaces().observe(this, new Observer<List<NicePlaces>>() {
            @Override
            public void onChanged(@Nullable List<NicePlaces> nicePlaces) {
                recyclerAdapter.notifyDataSetChanged();
            }
        });

        mainActivityViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean){
                    showProgressBar();
                }
                else {
                    hideProgressBar();
                    recyclerView.smoothScrollToPosition(mainActivityViewModel.getNicePlaces().getValue().size());
                }
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivityViewModel.addNewValue(
                        new NicePlaces("Washington","https://i.imgur.com/ZcLLrkY.jpg")
                );
            }
        });

        initRecyclerView();
    }

    private void initRecyclerView(){
        recyclerAdapter = new RecyclerAdapter(this, mainActivityViewModel.getNicePlaces().getValue());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void showProgressBar(){
        progressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        progressBar.setVisibility(View.INVISIBLE);
    }
}
