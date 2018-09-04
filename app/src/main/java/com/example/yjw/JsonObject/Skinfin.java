package com.example.yjw.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author YJW
 * @create 2018/8/1
 * @Describe
 */
public class Skinfin implements Serializable {
    public SkuQty skuQty = new SkuQty();//sap返回的结构体

    public String epc;
    public String boxNum;//所属箱号
    public double realCount;//实际数量
    public String style;//款式
    public String color;//颜色
    public String size;//尺寸
    public String shelfPosition;//仓库位置
    public HashMap<String,Boolean> _EPC_LIST_MAP = new HashMap<>();//计划数量
    public boolean isMatch = true;//是否匹配
    public boolean isEdit = false;//是否正在编辑

    public ArrayList<Check> get_EPC_LIST = new ArrayList<>();
}
