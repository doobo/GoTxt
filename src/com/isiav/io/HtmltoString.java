package com.isiav.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmltoString {

	
	public static String getHtmlContent(URL url, String encode) {  
        StringBuffer contentBuffer = new StringBuffer();  
  
        int responseCode = -1;  
        HttpURLConnection con = null;  
        try {  
            con = (HttpURLConnection) url.openConnection();  
            con.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");// IE�����������  
            con.setConnectTimeout(60000);  
            con.setReadTimeout(60000);  
            // �����ҳ������Ϣ��  
            responseCode = con.getResponseCode();  
            if (responseCode == -1) {  
                System.out.println(url.toString() + " : connection is failure...");  
                con.disconnect();  
                return null;  
            }  
            if (responseCode >= 400) // ����ʧ��  
            {  
                System.out.println("����ʧ��:get response code: " + responseCode);  
                con.disconnect();  
                return null;  
            }  
  
            InputStream inStr = con.getInputStream();  
            InputStreamReader istreamReader = new InputStreamReader(inStr, encode);  
            BufferedReader buffStr = new BufferedReader(istreamReader);  
  
            String str = null;  
            while ((str = buffStr.readLine()) != null)  
                contentBuffer.append(str);  
            inStr.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
            contentBuffer = null;  
            System.out.println("error: " + url.toString());  
        } finally {  
            con.disconnect();  
        }  
        return contentBuffer.toString();  
    }  
	
	 public static String getHtmlContent(String url, String encode) {  
	        if (!url.toLowerCase().startsWith("http://")&&!url.toLowerCase().startsWith("https://")) {  
	            url = "http://" + url;  
	        }  
	        try {  
	        	if(url.toLowerCase().startsWith("http://")){
	            URL rUrl = new URL(url);  
	            return getHtmlContent(rUrl, encode);  
	        	}else{
	        		
		            return com.isiav.io.HttpstoString.run(url, encode);
	        	}
	        } catch (Exception e) {  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
}
