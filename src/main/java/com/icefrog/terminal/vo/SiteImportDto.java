/*
 * Copyright 2019 icefrog.su@qq.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
