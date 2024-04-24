package com.luka.r18.util;

import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CustomUtil {

    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    public static String md5(String key) {
        if (key == null || key.isEmpty()) {
            return null;
        }
        return DigestUtils.md5Hex(key.getBytes());
    }

    public static String toJson(Integer code, String msg, Object object) {
        Gson gson = new Gson();
        Map<String, Object> json = new HashMap<>();
        json.put("code", code);
        json.put("msg", msg);
        json.put("data", object);
        return gson.toJson(json);
    }

    public static String toJson(Integer code) {
        return toJson(code, null, null);
    }

    public static String toJson(Integer code, String msg) {
        return toJson(code, msg, null);
    }

    public static byte[] getFile(String path) {
        try {
            // path是指想要下载的文件的路径
            File file = new File(path);
            // 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            int read = fis.read(buffer);
            fis.close();
            return buffer;
        } catch (Exception e) {
            return null;
        }
    }

    public static void setFileResponse(HttpServletResponse response, String fileName, byte[] buffer) {
        try {
            // 清空response
            response.reset();
            // 设置response的Header
            response.setCharacterEncoding("UTF-8");
            //Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
            //attachment表示以附件方式下载   inline表示在线打开   "Content-Disposition: inline; filename=文件名.mp3"
            // filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
            // 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + buffer.length);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods", "*");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setContentType("application/octet-stream");
        } catch (Exception ignored) {
        }
    }
}
