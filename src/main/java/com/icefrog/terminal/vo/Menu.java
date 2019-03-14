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

public class Menu{

	private String id;

    private String fid;

    private String menuname;

    private String url;

    private String iconurl;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getFid() {
		return fid;
	}
	
	public void setFid(String fid) {
		this.fid = fid;
	}
	
	public String getMenuname() {
		return menuname;
	}
	
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getIconurl() {
		return iconurl;
	}
	
	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}
}