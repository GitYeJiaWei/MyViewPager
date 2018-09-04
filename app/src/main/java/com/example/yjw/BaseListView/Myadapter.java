package com.example.yjw.BaseListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YJW on 2018/4/11.
 */

public class Myadapter extends BaseAdapter {
    //定义需要包装的JSONArray对象
    public List<Mymodel> mymodelList=new ArrayList<>();
    private Context context=null;
    //视图容器
    private LayoutInflater layoutInflater;

    public Myadapter(Context _context){
        this.context=_context;
        //创建视图容器并设置上下文
        this.layoutInflater=LayoutInflater.from(_context);
    }
    @Override
    public int getCount() {
        return this.mymodelList.size();
    }

    @Override
    public Object getItem(int position) {
        if (getCount()>0){
            return this.mymodelList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView listItemView =null;
        if (convertView ==null){
            //获取list_item布局文件的视图
            convertView = layoutInflater.inflate(R.layout.mylist_listitem,null);
            //获取控件对象
            listItemView =new ListItemView();
            listItemView.name = convertView.findViewById(R.id.sjh);
            listItemView.age  = convertView.findViewById(R.id.money);
            listItemView.sex =  convertView.findViewById(R.id.state);
            listItemView.starttime = convertView.findViewById(R.id.starttime);
            listItemView.stoptime = convertView.findViewById(R.id.stoptime);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }
        else{
            listItemView = (Myadapter.ListItemView)convertView.getTag();
        }

        final Mymodel m1=(Mymodel) this.getItem(position);
        listItemView.name.setText(m1.getName());
        listItemView.age.setText(m1.getAge()+"");
        listItemView.sex.setText(m1.getSex());
        listItemView.starttime.setText(m1.getStarttime());
        listItemView.stoptime.setText(m1.getEndtime());
        return convertView;
    }

    /**
     * 使用一个类来保存Item中的元素
     * 自定义控件集合
     */
    public final class ListItemView{
        TextView name,sex,age,starttime,stoptime;
    }
}
