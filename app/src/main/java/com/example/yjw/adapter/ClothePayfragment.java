package com.example.yjw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import butterknife.BindView;

/**
 * @author YJW
 * @create 2018/8/8
 * @Describe
 */
 /*public class ClothePayfragment extends BaseFragment {
   @BindView(R.id.tv_top)
    LinearLayout tvTop;
    @BindView(R.id.tv_listshop)
    ListView tvListshop;
    @BindView(R.id.img_store)
    ImageView imgStore;
    @BindView(R.id.tv_bottom)
    RelativeLayout tvBottom;
    @BindView(R.id.tv_open)
    Button tvOpen;
    @BindView(R.id.tv_image)
    ImageView tvImage;
    @BindView(R.id.tv_panpay)
    RadioButton tvPanpay;
    @BindView(R.id.tv_weixin)
    RadioButton tvWeixin;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.tv_radio)
    RadioGroup tvRadio;
    MainActivity mainActivity;
    private  ClothAdapter clothAdapter;
    private LayoutInflater layoutInflater;
    private String mCurrentEpc;

    @Override
    public int setLayout() {
        return R.layout.fragment_clothes_shop;
    }

    @Override
    public void setupAcitivtyComponent(AppComponent appComponent) {

    }

    @Override
    public void init(View view) {
        layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        tvRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.tv_weixin){
                    tvImage.setImageResource(R.mipmap.weixin);
                }else {
                    tvImage.setImageResource(R.mipmap.zhifu);
                }
            }
        });
    }

    //扫到的EPC
    public void showlistepc(final ArrayList<String> epcs){
        if (epcs.size() == 0)
        {
            mainActivity.showFragment(MainActivity.SCAN);
            return;
        }

        if (clothAdapter == null)
        {
            //显示底部
            clothAdapter = new ClothAdapter(layoutInflater);
            tvListshop.setAdapter(clothAdapter);
        }
        ArrayList<PayModel> maps = new ArrayList<>();

        for (int i = 0; i < epcs.size(); i++)
        {
            String item = epcs.get(i);
            if (item == null || item.length() != 16)
            {
                continue;
            }
            PayModel data = clothAdapter.getData(item);//原本的数据就不去更新
            if (data == null)
            {
                data = new PayModel();
                data.setEpc(item);
            }
            maps.add(data);
            if (i == 0 && !isVisible())
            {
                mCurrentEpc = item;
            }
        }
        clothAdapter.reFresh(maps);
    }

    private class ClothAdapter extends EpcAdapter<PayModel> {
        private int defaultSelection = 0;

        public ClothAdapter(LayoutInflater layoutInflater)
        {
            super(layoutInflater);
        }
        @Override
        public void sortDataList(ArrayList<PayModel> dataList) {
            if (dataList.size() > 1)
            {
                Collections.sort(dataList, new Comparator<PayModel>()
                {
                    @Override
                    public int compare(PayModel o1, PayModel o2)
                    {
                        if (o1.getEpc().equals(mCurrentEpc))
                        {
                            return -1;
                        } else if (o2.getEpc().equals(mCurrentEpc))
                        {
                            return 1;
                        }
                        return 0;
                    }
                });
            }
        }

        public void setSelectPosition(int position)
        {
            if (!(position < 0 || position > getCount()))
            {
                defaultSelection = position;
                notifyDataSetChanged();
            }
        }

        @Override
        public ItemLayout<PayModel> getItemLayout(PayModel data) {
            return new ViewHolder();
        }

        @Override
        public void updateData(PayModel data) {
            super.updateData(data);
        }

        private class ViewHolder extends ItemLayout<PayModel>
        {
            View mainView;
            ImageView image;
            TextView message,price,num,total;

            @Override
            public View initView(LayoutInflater inflater, ViewGroup viewGroup)
            {
                mainView = inflater.inflate(R.layout.item_listshop, viewGroup, false);
                image = mainView.findViewById(R.id.item_image);
                message =mainView.findViewById(R.id.item_message);
                price =mainView.findViewById(R.id.item_price);
                num =mainView.findViewById(R.id.item_num);
                total =mainView.findViewById(R.id.item_total);
                return mainView;
            }

            @Override
            public void setDefaultView(int position, PayModel data)
            {
                image.setImageResource(R.drawable.bg_map_default);
            }

            @Override
            public void setView(int position, PayModel data)
            {
                PayModel item = getItem(position);
                ClothData cloth = item.getClothData();
                data.getClothData();

                if (cloth != null)
                {
                    message.setText(cloth.getListAttachment().get(0).getDescription());
                    num.setText("<1>");
                    total.setText("¥ "+cloth.getPrice());
                    price.setText("¥ "+cloth.getPrice());
                    Glide.with(mActivity)
                                .load(cloth.getListAttachment().get(0).getFileFullPath()).error(R.drawable.bg_map_default).placeholder(R.drawable.bg_map_default)
                                .into(image);

                }
            }

            @Override
            public void onClick(View v, PayModel data)
            {
            }
        }
    }


}*/
