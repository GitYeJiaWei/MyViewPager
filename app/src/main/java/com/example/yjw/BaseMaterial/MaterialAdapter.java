package com.example.yjw.BaseMaterial;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yjw.common.DialogUtils;
import com.example.yjw.myviewpager.R;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YJW
 * @create 2018/6/21
 * @Describe recyclerview.adapter 的公用类
 */
public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.ListItemView> {
    public List<MaterialModel> materialModelList=new ArrayList<>();
    private Context mcontext;
    private LayoutInflater layoutInflater;
    private OnItemClickListener mOnItemClickListener;

    public MaterialAdapter(Context context){
        this.mcontext=context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ListItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewitem =layoutInflater.inflate(R.layout.mylist_listitem,parent,false);
        ListItemView listItemView=new ListItemView(viewitem);
        return listItemView;
    }

    @Override
    public void onBindViewHolder(final ListItemView holder, final int position) {
        holder.name.setText(materialModelList.get(position).getName());
        holder.age.setText(materialModelList.get(position).getAge()+"");
        holder.sex.setText(materialModelList.get(position).getSex());
        holder.starttime.setText(materialModelList.get(position).getStarttime());
        holder.stoptime.setText(materialModelList.get(position).getEndtime());

        //对每一个item进行监听，接口的方式
        View itemView = ((LinearLayout) holder.itemView).getChildAt(0);
        if (mOnItemClickListener!=null){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, position);
                }
            });
        }

        //对每一个item中的控件进行监听
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.ShowToast(mcontext,"123466");
            }
        });

        holder.stoptime.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                DialogUtils.ShowToast(mcontext,"123456");
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return materialModelList.size();
    }

    /**
     * 使用一个类来保存Item中的元素
     * 自定义控件集合
     */
    class ListItemView extends RecyclerView.ViewHolder {
        TextView name,sex,age,starttime,stoptime;

        public ListItemView(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.sjh);
            age  = itemView.findViewById(R.id.money);
            sex =  itemView.findViewById(R.id.state);
            starttime = itemView.findViewById(R.id.starttime);
            stoptime = itemView.findViewById(R.id.stoptime);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
