/*
 * Copyright 2019 版权所有
 *
 * @since 1.0
 * @author: icefrog.su@qq.com
 */

package com.icefrog.terminal.vo;

public class SiteImportDto {
    
    private String siteName;
    
    private String siteCode;
    
    private String siteType;
    
    public String getSiteName() {
        return siteName;
    }
    
    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }
    
    public String getSiteCode() {
        return siteCode;
    }
    
    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }
    
    public String getSiteType() {
        return siteType;
    }
    
    public void setSiteType(String siteType) {
        this.siteType = siteType;
    }
    
    @Override
    public String toString() {
        return "SiteImportDto{" + "siteName='" + siteName + '\'' + ", siteCode='" + siteCode + '\'' + ", siteType='" + siteType + '\'' + '}';
    }
}
