package com.icefrog.terminal.model;

import java.util.Date;

public class TbProductType {
    private String id;

    private String createId;

    private String updateId;

    private String broadType;

    private String productType;

    private String generateBuyNo;

    private String generateSellNo;

    private Date createTime;

    private Date updateTime;

    private String remark;

    private Integer isdel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getBroadType() {
        return broadType;
    }

    public void setBroadType(String broadType) {
        this.broadType = broadType == null ? null : broadType.trim();
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType == null ? null : productType.trim();
    }

    public String getGenerateBuyNo() {
        return generateBuyNo;
    }

    public void setGenerateBuyNo(String generateBuyNo) {
        this.generateBuyNo = generateBuyNo == null ? null : generateBuyNo.trim();
    }

    public String getGenerateSellNo() {
        return generateSellNo;
    }

    public void setGenerateSellNo(String generateSellNo) {
        this.generateSellNo = generateSellNo == null ? null : generateSellNo.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }
}