package com.example.yjw.JsonObject;

/**
 * @author YJW
 * @create 2018/7/27
 * @Describe
 */

public class Park
{
    /**
     * Id : 1
     * OrderDetailId : 2d04e055-3f4d-45e5-ba37-90ae6e0cf4bc
     * BoxId : 1
     * WhLocationId : 444444
     * StoreId : 12
     * SaleTime : null
     * CreateDate : null
     * CreateUser : null
     * Style : TKIMK28007S
     * Color : 蓝
     * Size : XL
     * packingDetailInfos: null
     */

    private String Id;
    private String OrderDetailId;
    private String BoxId;
    private String WhLocationId;
    private String StoreId;
    private Object SaleTime;
    private Object CreateDate;
    private Object CreateUser;
    private String Style;
    private String Color;
    private String Size;
    private String packingDetailInfos;

    public String getPackingDetailInfos() {
        return packingDetailInfos;
    }

    public void setPackingDetailInfos(String packingDetailInfos) {
        this.packingDetailInfos = packingDetailInfos;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getOrderDetailId() {
        return OrderDetailId;
    }

    public void setOrderDetailId(String OrderDetailId) {
        this.OrderDetailId = OrderDetailId;
    }

    public String getBoxId() {
        return BoxId;
    }

    public void setBoxId(String BoxId) {
        this.BoxId = BoxId;
    }

    public String getWhLocationId() {
        return WhLocationId;
    }

    public void setWhLocationId(String WhLocationId) {
        this.WhLocationId = WhLocationId;
    }

    public String getStoreId() {
        return StoreId;
    }

    public void setStoreId(String StoreId) {
        this.StoreId = StoreId;
    }

    public Object getSaleTime() {
        return SaleTime;
    }

    public void setSaleTime(Object SaleTime) {
        this.SaleTime = SaleTime;
    }

    public Object getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(Object CreateDate) {
        this.CreateDate = CreateDate;
    }

    public Object getCreateUser() {
        return CreateUser;
    }

    public void setCreateUser(Object CreateUser) {
        this.CreateUser = CreateUser;
    }

    public String getStyle() {
        return Style;
    }

    public void setStyle(String Style) {
        this.Style = Style;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String Color) {
        this.Color = Color;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

}

