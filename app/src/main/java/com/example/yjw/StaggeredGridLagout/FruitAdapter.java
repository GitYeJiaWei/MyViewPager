package com.example.yjw.StaggeredGridLagout;

/**
 * Created by YJW on 2018/1/16.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yjw.listview.Fruit;
import com.example.yjw.myviewpager.R;

import java.util.List;
/**
 * Created by YJW on 2018/1/15.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        View fruitview;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View view){
            super(view);
            fruitview =view;
            fruitImage =view.findViewById(R.id.fruit_image);
            fruitName = view.findViewById(R.id.fruit_name);
        }
    }

    public FruitAdapter(List<Fruit> fruitList){
        mFruitList =fruitList;
    }

    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item,
                parent,false);
        final ViewHolder viewHolder =new FruitAdapter.ViewHolder(view);
        viewHolder.fruitview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position =viewHolder.getAdapterPosition();
                Fruit fruit =mFruitList.get(position);
                Toast.makeText(v.getContext(),"fruitview",Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.fruitImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int postion = viewHolder.getAdapterPosition();
                Fruit fruit =mFruitList.get(postion);
                Toast.makeText(v.getContext(),"fruitImage",Toast.LENGTH_SHORT).show();
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Fruit fruit =mFruitList.get(position);
        holder.fruitImage.setImageResource(fruit.getImageId());
        holder.fruitName.setText(fruit.getName());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}

