package com.example.yjw.OnRefreshList;

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
 * @author YJW
 * @create 2018/7/9
 * @Describe
 */
public class OnRefreshAdapter extends BaseAdapter{
    private List<OnRefreshModel> refreshModelList =new ArrayList<>();
    private LayoutInflater layoutInflater;
    private int hight=0;

    public OnRefreshAdapter(Context _context,int _hight){
        this.layoutInflater = LayoutInflater.from(_context);
        this.hight = _hight;
    }

    public void update(List<OnRefreshModel> list){
        if (list.size() ==0){
            return;
        }
        refreshModelList.clear();
        refreshModelList.addAll(list);
        list.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return refreshModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return refreshModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView listItemView = null;
        if (convertView == null) {
            //获取list_item布局文件的视图
            convertView = layoutInflater.inflate(R.layout.onrefresh_item, parent, false);
            //获取控件对象
            listItemView = new ListItemView();
            listItemView.time_tv =  convertView.findViewById(R.id.time_tv);
            listItemView.content_tv =  convertView.findViewById(R.id.content_tv);

            ViewGroup.LayoutParams layoutParams = convertView.getLayoutParams();
            layoutParams.height = hight / 3;
            convertView.setLayoutParams(layoutParams);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }

        final OnRefreshModel item = (OnRefreshModel) this.getItem(position);
        listItemView.time_tv.setText(item.getTime());
        listItemView.content_tv.setText("  " + item.getContent());

        return convertView;
    }


    /**
     * 使用一个类来保存Item中的元素
     * 自定义控件集合
     */
    public class ListItemView {
        TextView time_tv, content_tv;
    }

}
