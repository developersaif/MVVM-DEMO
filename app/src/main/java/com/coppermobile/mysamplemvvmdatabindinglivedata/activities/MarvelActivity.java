package com.coppermobile.mysamplemvvmdatabindinglivedata.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import com.coppermobile.mysamplemvvmdatabindinglivedata.MarvelViewModelFactory;
import com.coppermobile.mysamplemvvmdatabindinglivedata.R;
import com.coppermobile.mysamplemvvmdatabindinglivedata.adapters.DishAdapter;
import com.coppermobile.mysamplemvvmdatabindinglivedata.adapters.MarvelAdapter;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Dish;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Marvel;
import com.coppermobile.mysamplemvvmdatabindinglivedata.databinding.ActivityMarvelBinding;
import com.coppermobile.mysamplemvvmdatabindinglivedata.utils.MarvelClickListener;
import com.coppermobile.mysamplemvvmdatabindinglivedata.viewmodels.MarvelViewModel;

import java.util.List;

public class MarvelActivity extends AppCompatActivity implements MarvelClickListener {

    private ActivityMarvelBinding binding;
    private MarvelAdapter marvelAdapter;
    MarvelViewModel marvelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_marvel);
        setUpRecyclerView();
        marvelViewModel = obtainViewModel(this);
        observeViewModel(marvelViewModel);
    }

    private void setUpRecyclerView() {
        marvelAdapter = new MarvelAdapter(this);
        RecyclerView rvDish = binding.rvMarvel;
        rvDish.setHasFixedSize(true);
        rvDish.setAdapter(marvelAdapter);
    }

    public MarvelViewModel obtainViewModel(MarvelActivity activity){

        MarvelViewModelFactory factory = MarvelViewModelFactory.getInstance(activity.getApplication());
        return ViewModelProviders.of(activity,factory).get(MarvelViewModel.class);

    }

    private  void  observeViewModel(MarvelViewModel marvelViewModel){

        marvelViewModel.getAllMarvel().observe(this, new Observer<List<Marvel>>() {
            @Override
            public void onChanged(@Nullable List<Marvel> marvelList) {
                if (marvelList != null)
                    marvelAdapter.addData(marvelList);
            }
        });
    }

    @Override
    public void onItemPressed(Marvel marvel) {

        if (marvel != null) {
            Intent i = new Intent(this, MainActivity.class);
          // i.putExtra("dish", dish);
            startActivity(i);
        }
    }
}