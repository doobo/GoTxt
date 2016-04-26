package com.isiav.io;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ToHtml {
	
	public static Document getHtml(){
		Document doc=null;
		try {
			doc = Jsoup.connect("https://www.taobao.com").get();
			String title = doc.title();
			System.out.println(title);
			return doc;
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return doc;
	}
	
	
	
	
}
