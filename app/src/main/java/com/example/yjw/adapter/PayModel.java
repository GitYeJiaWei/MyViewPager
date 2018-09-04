package com.example.yjw.adapter;


/**
 * @author YJW
 * @create 2018/8/10
 * @Describe
 */
public class PayModel extends ItemData{

    public String getEpc()
    {
        return epc;
    }

    public void setEpc(String epc)
    {
        this.epc = epc;
    }

    private String epc;

    @Override
    public String getUniqueKey()
    {
        return epc;
    }

    public Boolean isTake = false;

   /* public ClothData getClothData() {
        return clothData;
    }

    public void setClothData(ClothData clothData) {
        this.clothData = clothData;
    }

    private ClothData clothData;*/

    @Override
    public void takeData()
    {
     /* if (isTake != null && !isTake)
        {
            isTake = null;
            HashMap<String,String> hashMap =new HashMap<>();
            hashMap.put("epc",epc);
            hashMap.put("type","3");
            AppApplication.getApplication().getAppComponent().getApiService().getProductByEpc(hashMap).compose(RxHttpReponseCompat.<ClothData>compatResult())
                    .subscribe(new AdapterItemSubcriber<ClothData>(AppApplication.getApplication())
                    {
                        @Override
                        public void onNext(ClothData clothData) {
                            if(clothData!=null)
                            {
                                isTake = true;
                                setClothData(clothData);
                                notifyChanged();
                            }
                        }

                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                        }
                    });
        }*/
    }

}
