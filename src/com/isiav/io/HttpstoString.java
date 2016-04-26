package com.isiav.io;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.*;
import javax.net.ssl.*;

public class HttpstoString {
    // We would never hardcode this literal in a real system,
    // this is only for this article.

    // Create an anonymous class to trust all certificates.
    // This is bad style, you should create a separate class.
    private X509TrustManager xtm = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
//                System.out.println("cert: " + chain[0].toString() + ", authType: " + authType);
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
    }; 

    // Create an class to trust all hosts
    private HostnameVerifier hnv = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
//            System.out.println("hostname: " + hostname);
            return true;
        }
    }; 

    // In this function we configure our system with a less stringent
    // hostname verifier and X509 trust manager.  This code is
    // executed once, and calls the static methods of HttpsURLConnection
    private HttpstoString() {
        // Initialize the TLS SSLContext with
        // our TrustManager
        SSLContext sslContext = null;

        try {
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager[] xtmArray = new X509TrustManager[] { xtm };
            sslContext.init(null, xtmArray, new java.security.SecureRandom());
        } catch(GeneralSecurityException gse) {
            // Print out some error message and deal with this exception
        }

        // Set the default SocketFactory and HostnameVerifier
        // for javax.net.ssl.HttpsURLConnection
        if(sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }

        HttpsURLConnection.setDefaultHostnameVerifier(hnv);
    }

    // This function is called periodically, the important thing
    // to note here is that there is no special code that needs to
    // be added to deal with a "HTTPS" URL.  All of the trust
    // management, verification, is handled by the HttpsURLConnection.
    public static String run(String rurl,String encode) {
    	new HttpstoString();
        try {
        	
		            URLConnection urlCon = (new URL(rurl)).openConnection();
		            urlCon.setReadTimeout(10000);
		            urlCon.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream(),encode));
		            String line;
		            String allStr="";
		
		            while((line = in.readLine()) != null) {
		                
		                allStr = allStr + line.trim()+"\n";
		            }
		            return allStr;
		        //  Whatever we want to do with these quotes
		        } catch(MalformedURLException mue) {
		            mue.printStackTrace();
		        } catch(IOException ioe) {
		            ioe.printStackTrace();
		        } catch(Exception e) {
		            e.printStackTrace();
		        }
        return null;
    }

//    public static void main(String[] args) {
//        System.out.println(run("https://www.taobao.com/","utf-8"));
//    }
} 