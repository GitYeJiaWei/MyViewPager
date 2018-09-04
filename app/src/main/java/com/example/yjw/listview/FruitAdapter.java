package com.example.yjw.listview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yjw.myviewpager.R;

import java.util.List;

/**
 * Created by YJW on 2018/1/12.
 */

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resource;

    public FruitAdapter(@NonNull Context context, int resource, @NonNull List<Fruit> objects) {
        super(context, resource, objects);
        this.resource =resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Fruit fruit =getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view  = LayoutInflater.from(getContext()).inflate(resource,parent,false);
            viewHolder =new ViewHolder();
            viewHolder.fruitImage = view.findViewById(R.id.fruit_image);
            viewHolder.fruitName = view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);//将viewHolder存储在view 中
        }else{
            view =convertView;
            viewHolder =(ViewHolder) view.getTag();//重新获取viewholder
        }

        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }

    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
}
