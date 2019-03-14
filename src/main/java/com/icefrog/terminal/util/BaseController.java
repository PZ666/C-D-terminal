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

package com.icefrog.terminal.util;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 所有Controller的顶级父类
 *
 * @author icefrog.su@qq.com
 *
 */
public class BaseController extends BaseService{

	private static final org.slf4j.Logger Logger = LoggerFactory.getLogger(BaseController.class);
	
	/**
	 * 500错误页
	 */
	protected final String error500;
	
	/**
	 * 404错误页
	 */
	protected final String error404;
	
	/**
	 * 406错误页
	 */
	protected final String error406;
	
	/**
	 * 400错误页
	 */
	protected final String error400;
	
	/**
	 * 访问受限页
	 */
	protected final String noperimes;
	
	protected final List<String> imageMimeTypes;
	
	/***
	 * 初始化错误页
	 */
	public BaseController(){
		//初始化错误页
		error500 = Constans.ERROR_500;
		error404 = Constans.ERROR_404;
		error406 = Constans.ERROR_406;
		error400 = Constans.ERROR_400;
		noperimes = Constans.No_Permission;
		
		//初始化图片文件的Mimetype
		imageMimeTypes = new ArrayList<String>();
		imageMimeTypes.add("image/gif");
		imageMimeTypes.add("image/jpeg");
		imageMimeTypes.add("image/png");
		imageMimeTypes.add("image/x-icon");
		imageMimeTypes.add("image/x-ms-bmp");
		imageMimeTypes.add("image/svg+xml");
	}
	
	/**
	 * 发送一个http请求 默认使用GET请求方式
	 * @return
	 */
	protected String callApi(String url){
		return HttpClientUtil.doGet(url, null);
	}
	
	/**
	 * 发送一个http请求 默认使用GET请求方式 并指定请求参数
	 * @return
	 */
	protected String callApi(String url,final Map<String, String> params){
		return HttpClientUtil.doGet(url, params);
	}
	
	/**
	 * 发送一个http请求 并指定请求方式
	 * @return
	 */
	protected String callApi(String url,final Http requestMethod){
		if(requestMethod == Http.GET){
			return HttpClientUtil.doGet(url, null);
		}else if(requestMethod == Http.POST){
			return HttpClientUtil.doPost(url, null);
		}else{
			throw new RuntimeException("不支持的请求方式");
		}
	}
	
	/**
	 * 发送一个http请求 指定请求方式与参数信息
	 * @return
	 */
	protected String callApi(String url,final Http requestMethod, final Map<String, String> params) {
		if(params == null){
			throw new RuntimeException("参数map为null");
		}
		if(requestMethod == Http.GET){
			return HttpClientUtil.doGet(url, params);
		}else if(requestMethod == Http.POST){
			return HttpClientUtil.doPost(url, params);
		}else{
			throw new RuntimeException("不支持的请求方式");
		}
	}
	
	final static int EOF = -1;
	
	/**
	 * 文件下载<br>
	 * <code>
	 * response.setHeader("content-type", "application/octet-stream");
	 * response.setContentType("application/octet-stream");
	 * <code>
	 * @param response response响应对象
	 * @param file 文件
	 * @param filename 下载时显示的文件名(包括后缀)
	 * @param speed 下载速度,每次循环写出的字节数
	 */
	public void download(HttpServletResponse response, File file, String filename, int speed){
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		byte[] buff = new byte[speed];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(file));
			int i = bis.read(buff);
			while (i != EOF) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			String message = "【BaseController】-【公共文件下载方法】-出现IOException异常，异常信息:"+e.getMessage();
			Logger.error(message);
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					String message = "【BaseController公告】-【公共文件下载方法】-关闭流出现IOException异常，异常信息:"+e.getMessage();
					Logger.error(message);
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 文件下载<br>
	 * <code>
	 * response.setHeader("content-type", "application/octet-stream");
	 * response.setContentType("application/octet-stream");
	 * <code>
	 * @param response response响应对象
	 * @param filename 下载时显示的文件名(包括后缀)
	 * @param speed 下载速度,每次循环写出的字节数
	 */
	public void download(HttpServletResponse response, InputStream ins, String filename, int speed){
		byte[] buff = new byte[speed];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO8859-1"));
			os = response.getOutputStream();
			bis = new BufferedInputStream(ins);
			int i = bis.read(buff);
			while (i != EOF) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			String message = "【BaseController】-【公共文件下载方法】-出现IOException异常，异常信息:"+e.getMessage();
			Logger.error(message);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					String message = "【BaseController公告】-【公共文件下载方法】-关闭流出现IOException异常，异常信息:"+e.getMessage();
					Logger.error(message);
				}
			}
		}
	}
	
	
	public void downloadZip(HttpServletResponse response, Map<String, InputStream> inputStreams, String filename, int speed){
		BufferedInputStream bis = null;
		OutputStream os = null;
		ZipOutputStream  zip = null;
		try {
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(),"ISO8859-1"));
			os = response.getOutputStream();
			zip = new ZipOutputStream(os);
			for (Map.Entry<String, InputStream> entry : inputStreams.entrySet()) {
				 
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
				
				byte[] buffer = new byte[speed];
		        zip.putNextEntry(new ZipEntry(entry.getKey()));
		        int len = 0 ;
		        // 读取文件的内容,打包到zip文件
		        InputStream inputStream  = entry.getValue();
		        bis = new BufferedInputStream(inputStream);
		        while ((len = bis.read(buffer)) > 0) {
		        	zip.write(buffer, 0, len);
		        	zip.flush();
		        }
		        bis.close();
			}
			
		} catch (IOException e) {
			String message = "【BaseController】-【公共文件下载方法】-出现IOException异常，异常信息:"+e.getMessage();
			Logger.error(message);
		} finally {
			if (zip != null) {
				try {
					zip.close();
				} catch (IOException e) {
					String message = "【BaseController公告】-【公共文件下载方法】-关闭流出现IOException异常，异常信息:"+e.getMessage();
					Logger.error(message);
				}
			}
		}
	}
	
	/**
     * 下载模板信息
     * 适用于windows和linux
     * @param response
     * @param request
     * @param templeteName
     * @throws IOException
     */
    public void downloadTemplate(HttpServletResponse response, HttpServletRequest request, String templeteName) throws IOException {
        OutputStream outp = null;
        FileInputStream in = null;
        try {
            String fileName = templeteName; //要下载的模板文件
            String ctxPath = request.getSession().getServletContext().getRealPath(File.separator) + File.separator + "template" + File.separator;
            String filedownload = ctxPath + fileName;
            fileName = URLEncoder.encode(fileName, "UTF-8");
            // 要下载的模板所在的绝对路径
            response.reset();
            response.addHeader("Content-Disposition", "attachment; filename="+fileName);
            response.setContentType("application/octet-stream;charset=UTF-8");
            outp = response.getOutputStream();
            in = new FileInputStream(filedownload);
            byte[] b = new byte[1024];
            int i = 0;
            while ((i = in.read(b)) > 0) {
                outp.write(b, 0, i);
            }
            outp.flush();
        } catch (Exception e) {
            System.out.println("Error!");
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
                in = null;
            }
            if (outp != null) {
                outp.close();
                outp = null;
            }
        }
    }
}
