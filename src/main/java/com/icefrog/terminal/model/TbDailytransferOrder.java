package com.icefrog.terminal.model;

public class TbDailytransferOrder {
    private String id;

    private String siteId;

    private String productTypeId;
    
    private String dailytransferId;// dailytransfer_id

    private String buySellType;

    private String money;

    private Integer buyCount;// buy_count
    
    private Integer sellCount;// sell_count

    private String res1;

    private String res2;

    private String res3;
    
    private String res4;

    private Integer isDel;
    
    public String getRes4() {
        return res4;
    }
    
    public void setRes4(String res4) {
        this.res4 = res4;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getSiteId() {
        return siteId;
    }
    
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }
    
    public String getProductTypeId() {
        return productTypeId;
    }
    
    public void setProductTypeId(String productTypeId) {
        this.productTypeId = productTypeId;
    }
    
    public String getDailytransferId() {
        return dailytransferId;
    }
    
    public void setDailytransferId(String dailytransferId) {
        this.dailytransferId = dailytransferId;
    }
    
    public String getBuySellType() {
        return buySellType;
    }
    
    public void setBuySellType(String buySellType) {
        this.buySellType = buySellType;
    }
    
    public String getMoney() {
        return money;
    }
    
    public void setMoney(String money) {
        this.money = money;
    }
    
    public Integer getBuyCount() {
        return buyCount;
    }
    
    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }
    
    public Integer getSellCount() {
        return sellCount;
    }
    
    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }
    
    public String getRes1() {
        return res1;
    }
    
    public void setRes1(String res1) {
        this.res1 = res1;
    }
    
    public String getRes2() {
        return res2;
    }
    
    public void setRes2(String res2) {
        this.res2 = res2;
    }
    
    public String getRes3() {
        return res3;
    }
    
    public void setRes3(String res3) {
        this.res3 = res3;
    }
    
    public Integer getIsDel() {
        return isDel;
    }
    
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }
}