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
        String host = "https://idcert.market.alicloudapi.com"; //�����ַ
        String path = "/idcard";//��׺
        String appcode = "ba6bb17924cd48a1ae6054bebfa5ae6b";//�����AppCode
        try {
            String urlSend = host + path + "?idCard=" + idnumber + "&name=" + URLEncoder.encode(realname, "UTF-8");//ƴ����������
            URL url = new URL(urlSend);
            HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
            httpURLCon.setRequestProperty("Authorization", "APPCODE " + appcode);
            int httpCode = httpURLCon.getResponseCode();
            if (httpCode == 200) {
                String json = read(httpURLCon.getInputStream());
                int colonIndex = json.indexOf(":");
                int commaIndex = json.indexOf(",");
                //��ȡjson�е�code
                String code = json.substring(colonIndex + 2, commaIndex - 1);
                System.out.println(code);
                if (code.equals("01")) {
                    result = Result.success(ResultCode.SUCCESS, "ʵ����֤ͨ��!");
                } else if (code.equals("02")) {
                    result = Result.error(ResultCode.ERROR, "ʵ����֤ʧ��!");
                } else if (code.equals("202")) {
                    result = Result.error(ResultCode.ERROR, "�޷���֤!");
                } else if (code.equals("203")) {
                    result = Result.error(ResultCode.ERROR, "�쳣���!");
                } else if (code.equals("204")) {
                    result = Result.error(ResultCode.ERROR, "������ʽ����ȷ!");
                } else if (code.equals("205")) {
                    result = Result.error(ResultCode.ERROR, "���֤��ʽ����ȷ!");
                } else if (code.equals("9999")) {
                    result = Result.error(ResultCode.ERROR, "ϵͳά��!");
                }
            } else {
                Map<String, List<String>> map = httpURLCon.getHeaderFields();
                String error = map.get("X-Ca-Error-Message").get(0);
                if (httpCode == 400 && error.equals("Invalid AppCode `not exists`")) {
                    result = Result.error(ResultCode.ERROR, "AppCode���� ");
                } else if (httpCode == 400 && error.equals("Invalid Url")) {
                    result = Result.error(ResultCode.ERROR, "����� Method��Path ���߻�������");
                } else if (httpCode == 400 && error.equals("Invalid Param Location")) {
                    result = Result.error(ResultCode.ERROR, "��������");
                } else if (httpCode == 403 && error.equals("Unauthorized")) {
                    result = Result.error(ResultCode.ERROR, "����δ����Ȩ����URL��Path����ȷ��");
                } else if (httpCode == 403 && error.equals("Quota Exhausted")) {
                    result = Result.error(ResultCode.ERROR, "�ײͰ���������");
                } else if (httpCode == 403 && error.equals("Api Market Subscription quota exhausted")) {
                    result = Result.error(ResultCode.ERROR, "�ײͰ��������꣬�������ײ�");
                } else {
                    result = Result.error(ResultCode.ERROR, "���������� �� ��������");
                }
            }
        } catch (MalformedURLException e) {
            result = Result.error(ResultCode.ERROR, "URL��ʽ����");
        } catch (UnknownHostException e) {
            result = Result.error(ResultCode.ERROR, "URL��ַ����");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /*
     * ��ȡ���ؽ��
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
����'�޷���֤'ʱ����ʾ�����ޡ���ԭ�����£�
    (1) ���۾��ˣ������۲���2��ľ��ˣ�һ��Ϊ2�꣩�����ⲿ����Ա��
    (2) �����ʵ����ѧ������Ǩ�ƣ�
    (3) ����Ǩ������û�����µ�Ǩ���Ǩ�룻
    (4) ����Ǩ����Ǩ��أ����ع���ϵͳδ�ϱ������������ϱ�ʱ���е�����죩��
    (5) �������������ع���ϵͳδ�ϱ������������ϱ�ʱ���е�����죩��
    (6) �����ʵ����������δ����
    (7) �����ʵ��δ�����������֤��
    (8) �����������
    (9) ���֤��ȷʵ�����ڡ�
 */