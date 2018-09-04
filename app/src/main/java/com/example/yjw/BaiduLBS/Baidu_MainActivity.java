package com.example.yjw.BaiduLBS;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomControls;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.example.yjw.common.BaseActivity;
import com.example.yjw.common.DialogUtils;
import com.example.yjw.map.utils.MapServices;
import com.example.yjw.myviewpager.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Baidu_MainActivity extends BaseActivity {
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.gohome)
    Button gohome;
    private MapServices mapServices = null;
    private MapView mapView;
    private LatLng point;
    private BDLocation _location;
    /**
     * 当前地点击点
     */
    private LatLng currentPt;
    private GeoCoder mSearch = null; // 搜索模块，也可去掉地图模块独立使用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_baidu__main);
        ButterKnife.bind(this);

        //初始化mapview
        mapView = findViewById(R.id.bmapView);
        //获取MapServices 实例
        mapServices = new MapServices(this, mapView.getMap());
        //实时定位
        mapServices.SetOnLocationCallback(new MapServices.LocationCallbackInterface() {
            @Override
            public void getLoaction(BDLocation location) {
                _location = location;
                //Toast.makeText(Baidu_MainActivity.this, location.getLatitude() +"==="+location.getLongitude(), Toast.LENGTH_LONG).show();
            }
        });

        // 初始化搜索模块，注册事件监听
        mSearch = GeoCoder.newInstance();
        initSearch();


        initview();
        initdata();
        initClick();

    }

    /**
     * 发起搜索
     *
     * @param v
     */
    public void searchButtonProcess(View v) {

        if (v.getId() == R.id.reversegeocode) {
            //点搜索
            int version = 0;
            EditText lat = (EditText) findViewById(R.id.lat);
            EditText lon = (EditText) findViewById(R.id.lon);
            LatLng ptCenter = new LatLng((Float.valueOf(lat.getText()
                    .toString())), (Float.valueOf(lon.getText().toString())));
            // 反Geo搜索
            //是否返回新数据
            version = 1;

            mSearch.reverseGeoCode(new ReverseGeoCodeOption()
                    .location(ptCenter).newVersion(version));
        } else if (v.getId() == R.id.geocode) {
            //名称搜索
            EditText editCity = (EditText) findViewById(R.id.city);
            EditText editGeoCodeKey = (EditText) findViewById(R.id.geocodekey);
            // Geo搜索
            mSearch.geocode(new GeoCodeOption().city(
                    editCity.getText().toString()).address(editGeoCodeKey.getText().toString()));
        }
    }

    private void initSearch() {
        mSearch.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(Baidu_MainActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                mapView.getMap().clear();
                mapView.getMap().addOverlay(new MarkerOptions().position(result.getLocation())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.mipmap.point)));
                mapView.getMap().setMapStatus(MapStatusUpdateFactory.newLatLng(result
                        .getLocation()));
                String strInfo = String.format("纬度：%f 经度：%f",
                        result.getLocation().latitude, result.getLocation().longitude);
                Toast.makeText(Baidu_MainActivity.this, strInfo, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(Baidu_MainActivity.this, "抱歉，未能找到结果", Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                mapView.getMap().clear();
                mapView.getMap().addOverlay(new MarkerOptions().position(result.getLocation())
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.mipmap.point)));
                mapView.getMap().setMapStatus(MapStatusUpdateFactory.newLatLng(result
                        .getLocation()));
                Toast.makeText(Baidu_MainActivity.this, result.getAddress() + " adcode: " + result.getAdcode(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initview() {
        // 隐藏百度logo
        View child = mapView.getChildAt(1);
        if (child != null && (child instanceof ImageView || child instanceof ZoomControls)) {
            child.setVisibility(View.INVISIBLE);
        }
        //隐藏地图上比例尺
        mapView.showScaleControl(false);
        //隐藏缩放控件
        mapView.showZoomControls(false);

    }

    /**
     * 添加一个自定义Marker 点
     */
    private void initdate() {
        //自定义Maker坐标点
        point = new LatLng(24.6128, 118.0576);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.point);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //构建文字Option对象，用于在地图上添加文字
        OverlayOptions textOption = new TextOptions()
                .bgColor(0xAAFFFF00)
                .fontSize(16)
                .fontColor(0xFFFF00FF)
                .text("自定义点")
                .rotate(0)
                .position(point);
        //在地图上添加Marker，并显示
        mapView.getMap().addOverlay(option);
        mapView.getMap().addOverlay(textOption);

    }

    /**
     * 批量添加自定义Marker 点
     */
    private void initdata() {
        //创建OverlayOptions的集合
        List<OverlayOptions> options = new ArrayList<OverlayOptions>();
        //设置坐标点
        LatLng point1 = new LatLng(24.6, 118.0);
        LatLng point2 = new LatLng(24.6, 118.1);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.car1);
        BitmapDescriptor bitmap1 = BitmapDescriptorFactory
                .fromResource(R.mipmap.car2);
        //创建OverlayOptions属性
        OverlayOptions option1 = new MarkerOptions()
                .position(point1)
                .icon(bitmap);
        OverlayOptions option2 = new MarkerOptions()
                .position(point2)
                .icon(bitmap1);
        //将OverlayOptions添加到list
        options.add(option1);
        options.add(option2);
        //在地图上批量添加
        mapView.getMap().addOverlays(options);

    }

    private void initClick() {
        //地图单击事件监听接口：
        mapView.getMap().setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            /**
             * 地图单击事件回调函数
             * @param point 点击的地理坐标
             */
            public void onMapClick(LatLng point) {
                currentPt = point;
                updateMapState();
                DialogUtils.ShowToast(Baidu_MainActivity.this, "地图单击事件:" + point.latitude);
            }

            /**
             * 地图内 Poi 单击事件回调函数
             * @param poi 点击的 poi 信息
             */
            public boolean onMapPoiClick(MapPoi poi) {
                currentPt = poi.getPosition();
                updateMapState();
                return true;
            }
        });


        //地图定位图标点击事件监听接口：
        mapView.getMap().setOnMyLocationClickListener(new BaiduMap.OnMyLocationClickListener() {
            /**
             * 地图定位图标点击事件监听函数
             */
            public boolean onMyLocationClick() {
                DialogUtils.ShowToast(Baidu_MainActivity.this, "地图定位图标点击事件监听:" + point.latitude);
                return true;
            }
        });

        //长点击事件
        mapView.getMap().setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng point) {
                currentPt = point;
                updateMapState();
                DialogUtils.ShowToast(Baidu_MainActivity.this, "长点击事件:" + _location.getLocationDescribe());
            }
        });

        //双击事件
        mapView.getMap().setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            @Override
            public void onMapDoubleClick(LatLng point) {
                currentPt = point;
                updateMapState();
                DialogUtils.ShowToast(Baidu_MainActivity.this, "双击事件:" + _location.getLocationDescribe());
            }
        });

        //地图 Marker 覆盖物点击事件监听接口：
        mapView.getMap().setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            /**
             * 地图 Marker 覆盖物点击事件监听函数
             * @param marker 被点击的 marker
             */
            public boolean onMarkerClick(Marker marker) {
                DialogUtils.ShowToast(Baidu_MainActivity.this, "地图 Marker 覆盖物点击事件监听:" + _location.getLocationDescribe());
                return true;
            }
        });

    }

    /**
     * 更新地图状态显示面板
     */
    private void updateMapState() {
        if (text == null) {
            return;
        }
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.point);

        String state = "";
        if (currentPt == null) {
            state = "点击、长按、双击地图以获取经纬度和地图状态";
        } else {
            state = String.format("当前经度： %f 当前纬度：%f",
                    currentPt.longitude, currentPt.latitude);
            MarkerOptions ooA = new MarkerOptions().position(currentPt).icon(bitmap);
            mapView.getMap().clear();
            mapView.getMap().addOverlay(ooA);
        }
        state += "\n";
        MapStatus ms = mapView.getMap().getMapStatus();
        state += String.format(
                "zoom=%.1f rotate=%d overlook=%d",
                ms.zoom, (int) ms.rotate, (int) ms.overlook);
        text.setText(state);

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        mapServices.Start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSearch.destroy();
        mapServices.Stop();
    }

    @OnClick(R.id.gohome)
    public void onViewClicked() {
        //把定位点再次显现出来
        LatLng latLng =new LatLng(_location.getLatitude(),_location.getLongitude());
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latLng);
        mapView.getMap().animateMapStatus(mapStatusUpdate);
    }
}
