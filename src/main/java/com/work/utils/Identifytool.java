package com.work.utils;

import com.work.common.ResultCode;
import com.work.common.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

public class Identifytool {

    public static Result identify(String realname, String idnumber) {
        Result result = new Result();
        String host = "https://idcert.market.alicloudapi.com"; //请求地址
        String path = "/idcard";//后缀
        String appcode = "ba6bb17924cd48a1ae6054bebfa5ae6b";//购买的AppCode
        try {
            String urlSend = host + path + "?idCard=" + idnumber + "&name=" + URLEncoder.encode(realname, "UTF-8");//拼接请求链接
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                String json = read(httpURLCon.getInputStream());
                int colonIndex = json.indexOf(":");
                int commaIndex = json.indexOf(",");
                //获取json中的code
                String code = json.substring(colonIndex + 2, commaIndex - 1);
                System.out.println(code);
                if (code.equals("01")) {
                    result = Result.success(ResultCode.SUCCESS, "实名认证通过!");
                } else if (code.equals("02")) {
                    result = Result.error(ResultCode.ERROR, "实名认证失败!");
                } else if (code.equals("202")) {
                    result = Result.error(ResultCode.ERROR, "无法认证!");
                } else if (code.equals("203")) {
                    result = Result.error(ResultCode.ERROR, "异常情况!");
                } else if (code.equals("204")) {
                    result = Result.error(ResultCode.ERROR, "姓名格式不正确!");
                } else if (code.equals("205")) {
                    result = Result.error(ResultCode.ERROR, "身份证格式不正确!");
                } else if (code.equals("9999")) {
                    result = Result.error(ResultCode.ERROR, "系统维护!");
                }
            } else {
                Map<String, List<String>> map = httpURLCon.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
                    result = Result.error(ResultCode.ERROR, "AppCode错误 ");
                } else if (httpCode == 400 && error.equals("Invalid Url")) {
                    result = Result.error(ResultCode.ERROR, "请求的 Method、Path 或者环境错误");
                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                    result = Result.error(ResultCode.ERROR, "参数错误");
                } else if (httpCode == 403 && error.equals("Unauthorized")) {
                    result = Result.error(ResultCode.ERROR, "服务未被授权（或URL和Path不正确）");
                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                    result = Result.error(ResultCode.ERROR, "套餐包次数用完");
                } else if (httpCode == 403 && error.equals("Api Market Subscription quota exhausted")) {
                    result = Result.error(ResultCode.ERROR, "套餐包次数用完，请续购套餐");
                } else {
                    result = Result.error(ResultCode.ERROR, "参数名错误 或 其他错误");
                }
            }
        } catch (MalformedURLException e) {
            result = Result.error(ResultCode.ERROR, "URL格式错误");
        } catch (UnknownHostException e) {
            result = Result.error(ResultCode.ERROR, "URL地址错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * 读取返回结果
     */
    private static String read(InputStream is) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while ((line = br.readLine()) != null) {
            line = new String(line.getBytes(), "utf-8");
            sb.append(line);
        }
        br.close();
        return sb.toString();
    }
}
/*
出现'无法验证'时，表示‘库无’，原因如下：
    (1) 现役军人，刚退役不到2年的军人（一般为2年）、特殊部门人员；
    (2) 身份真实，大学生户口迁移；
    (3) 户口迁出，且没有在新的迁入地迁入；
    (4) 户口迁入新迁入地，当地公安系统未上报到公安部（上报时间有地域差异）；
    (5) 更改姓名，当地公安系统未上报到公安部（上报时间有地域差异）；
    (6) 身份真实，但是逾期未办理；
    (7) 身份真实，未更换二代身份证；
    (8) 移民和死亡；
    (9) 身份证号确实不存在。
 */