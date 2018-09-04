package com.example.yjw.contentResolver;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yjw.myviewpager.R;

import java.util.List;

/**
 * Created by YJW on 2018/2/1.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private List<User> muserList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView phone;
        public ViewHolder(View view){
            super(view);
            name =view.findViewById(R.id.user_name);
            phone =view.findViewById(R.id.user_phone);
        }
    }

    public ContentAdapter(List<User> userList){
        muserList =userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view =LayoutInflater.from(parent.getContext()).inflate(R.layout.contentview,parent,false);
       ViewHolder viewHolder =new ViewHolder(view);
       return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user =muserList.get(position);
        holder.name.setText(user.getName());
        holder.phone.setText(user.getPhone());
    }

    @Override
    public int getItemCount() {
        return muserList.size();
    }
}
