package com.example.yjw.UFH;

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
 * @create 2018/7/3
 * @Describe
 */
public class MainAdapter extends BaseAdapter{
    //定义需要包装的JSONArray对象
    public List<Main2Model> mymodelList=new ArrayList<>();
    private Context context=null;
    //视图容器
    private LayoutInflater layoutInflater;

    public MainAdapter(Context _context){
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
            convertView = layoutInflater.inflate(R.layout.main2_layout,null);
            //获取控件对象
            listItemView =new ListItemView();
            listItemView.address  = convertView.findViewById(R.id.tv_adress);
            listItemView.user =  convertView.findViewById(R.id.tv_user);
            listItemView.event = convertView.findViewById(R.id.tv_event);
            listItemView.remark = convertView.findViewById(R.id.tv_remark);
            listItemView.time = convertView.findViewById(R.id.tv_time);
            listItemView.message = convertView.findViewById(R.id.tv_message);
            listItemView.status =  convertView.findViewById(R.id.tv_status);
            listItemView.tx_id = convertView.findViewById(R.id.tv_tx_id);
            listItemView.nonce = convertView.findViewById(R.id.tv_nonce);
            listItemView.timestamp = convertView.findViewById(R.id.tv_timestamp);
            listItemView.sign = convertView.findViewById(R.id.tv_sign);
            //设置控件集到convertView
            convertView.setTag(listItemView);
        }
        else{
            listItemView = (ListItemView)convertView.getTag();
        }

        final Main2Model m1=(Main2Model) this.getItem(position);
        listItemView.address.setText("Address："+m1.getAddress()+"");
        listItemView.user.setText("User："+m1.getUser());
        listItemView.event.setText("Event："+m1.getEvent());
        listItemView.remark.setText("Remark："+m1.getRemark());
        listItemView.time.setText("Time："+m1.getTime());
        listItemView.message.setText("Message："+m1.getMessage());
        listItemView.status.setText("Status："+m1.getStatus());
        listItemView.tx_id.setText("Tx_id："+m1.getTx_id());
        listItemView.nonce.setText("Nonce："+m1.getNonce());
        listItemView.timestamp.setText("Timestamp："+m1.getTimestamp());
        listItemView.sign.setText("Sign："+m1.getSign());

        return convertView;
    }

    /**
     * 使用一个类来保存Item中的元素
     * 自定义控件集合
     */
    public final class ListItemView{
        TextView address,user,event,remark,time,message,status,tx_id,nonce,timestamp,sign;

    }

}
