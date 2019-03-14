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

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/***
 * 验证码生成器。
 *
 * @author icefrog.su@qq.com
 */
public class ImageCodeBuilder {
    
    private static char codePools[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    
    /***
     * 生成验证码信息，返回map。 map中包含验证码文本内容和验证码image对象。
     * 通过key：image、strEnsure获得。
     * @param width 宽度
     * @param height 高度
     * @param os response.getOutputStream();
     * @return Map 对象。 包含两个key-value pairs
     */
    public static Map<String, Object> getImageCode(int width, int height, OutputStream os) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        if (width <= 0)
            width = 60;
        if (height <= 0)
            height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // 获取图形上下文
        Graphics g = image.getGraphics();
        //生成随机类
        Random random = new Random();
        // 设定背景色
        //g.setColor(buildRandomColor(200, 250));
        g.setColor(Color.white);
        g.fillRect(0, 0, width, height);
        //设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        // 随机产生30条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(buildRandomColor(160, 200));
        for (int i = 0; i < 30; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        //取随机产生的码
        String strEnsure = "";
        //4代表4位验证码,如果要生成更多位的认证码,则加大数值
        for (int i = 0; i < 4; ++i) {
            strEnsure += codePools[(int) (codePools.length * Math.random())];
            // 将认证码显示到图象中
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            //直接生成
            String str = strEnsure.substring(i, i + 1);
            g.drawString(str, 13 * i + 6, 16);
        }
        // 释放图形上下文
        g.dispose();
        returnMap.put("image", image);//image对象，
        returnMap.put("strEnsure", strEnsure);//生成的验证码文本
        return returnMap;
    }
    
    /***
     * 给定范围获得随机颜色
     */
    private static Color buildRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
