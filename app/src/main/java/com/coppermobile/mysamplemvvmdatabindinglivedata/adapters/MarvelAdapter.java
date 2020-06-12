package com.coppermobile.mysamplemvvmdatabindinglivedata.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.coppermobile.mysamplemvvmdatabindinglivedata.R;
import com.coppermobile.mysamplemvvmdatabindinglivedata.activities.MainActivity;
import com.coppermobile.mysamplemvvmdatabindinglivedata.activities.MarvelActivity;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Dish;
import com.coppermobile.mysamplemvvmdatabindinglivedata.data.Marvel;
import com.coppermobile.mysamplemvvmdatabindinglivedata.databinding.ListDishBinding;
import com.coppermobile.mysamplemvvmdatabindinglivedata.databinding.ListMarvelBinding;

import java.util.List;

public class MarvelAdapter extends RecyclerView.Adapter<MarvelAdapter.MyViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Marvel> marvelList;
    private Context context;

    public MarvelAdapter(Context context) {
        this.context = context;
    }

    public void addData(List<Marvel> marvelList) {
        this.marvelList = marvelList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        ListMarvelBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_marvel, viewGroup, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Marvel dish = marvelList.get(i);
        if (dish != null) {
            myViewHolder.binding.setDish(dish);
            myViewHolder.binding.setMain((MarvelActivity) context);
        }
    }

    @Override
    public int getItemCount() {
        return marvelList != null && !marvelList.isEmpty() ? marvelList.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ListMarvelBinding binding;

        MyViewHolder(ListMarvelBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
