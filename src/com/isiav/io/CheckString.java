package com.isiav.io;

	import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

	public class CheckString {

		//得到html源文件中的TXT内容
		public static String getHtml2Text(String inputString){
		     String htmlStr = inputString; //含html标签的字符串
		     String textStr ="";
		     java.util.regex.Pattern p_script;
		     java.util.regex.Matcher m_script;
		     java.util.regex.Pattern p_style;
		     java.util.regex.Matcher m_style;
		     java.util.regex.Pattern p_html;
		     java.util.regex.Matcher m_html;

		    try{
		          String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
		          String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
		          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
//		          String regEx_cat =""; //{"cat_name"  }

		          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
		          m_script = p_script.matcher(htmlStr);
		          htmlStr = m_script.replaceAll(""); //过滤script标签

		          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
		          m_style = p_style.matcher(htmlStr);
		          htmlStr = m_style.replaceAll(""); //过滤style标签

		          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
		          m_html = p_html.matcher(htmlStr);
		          htmlStr = m_html.replaceAll(""); //过滤html标签
		          
//		          p_html = Pattern.compile(regEx_cat);
//		          m_html = p_html.matcher(htmlStr);
//		          htmlStr = m_html.replaceAll(""); //{"cat_name" }

		          textStr = htmlStr;
		     }catch(Exception e){
		         e.printStackTrace();
		     }
		    
		     return removeKong(textStr.replace('\t', '\n'));//返回文本字符串
		 }   
		
		//替换多个字符串为一个字符
		public static String removeKong(String textStr){
		    String w="";
		    //替换多个空字符串
		    Pattern p = Pattern.compile("\\s+");
		    Matcher m = p.matcher(textStr);
		    w= m.replaceAll(" ");
		    
		    //除去html的常用标签
		    Pattern p4 = Pattern.compile("(&nbsp;)|(&quot;)|(&raquo;)|(&bull;)|(&lt;)|(&gt;)|(&ldquo;)");
		    Matcher m4 = p4.matcher(w);
		    w= m4.replaceAll("");
		    
		    //替换；和。号为换行
		    Pattern p2 = Pattern.compile(";");
		    Matcher m2 = p2.matcher(w);
		    w= m2.replaceAll(";\n");
		    
		    Pattern p3 = Pattern.compile("。");
		    Matcher m3 = p3.matcher(w);
		    w= m3.replaceAll("。\n");
		    
		    Pattern p5 = Pattern.compile(">");
		    Matcher m5 = p5.matcher(w);
		    w= m5.replaceAll(">\n");
		    
			return w;
		}
		
		
		//将String写入文件
		 public static void writeFile(String content){
		        try{
		        File f = new File("temp.txt");
		        f.createNewFile();
		        BufferedWriter output = new BufferedWriter(new FileWriter(f));
		        output.write(content);
		        output.close();
		        }catch(IOException e){
		          e.printStackTrace();
		        }
		    }
		 //追加字符串追加到文件
		 public  void writeFiletoEnd(String url,String filename){
		       //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
		        try{
		            FileWriter writer = new FileWriter(filename, true);
		            writer.write(url);
//		            System.out.println("写文件成功...");
		            writer.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		 
		 public static String getFileLine(String url){
		        InputStream is=null;  
		        BufferedReader br = null;  
		        FileWriter writer=null; 
		        String tmp,str="";  
			 try {  
				 	is=new BufferedInputStream(new FileInputStream(url));  
		            br = new BufferedReader(new InputStreamReader(is));  
		            writer = new FileWriter("htmlText.txt", true);  
		            while((tmp=br.readLine())!=null){  
		                if(tmp.equals(""));  
			                else{  
			                	if(tmp.length()>1){
			                    writer.write(tmp.trim()+"\n");  
			                    str = str+tmp.trim()+"\n"; 
			                	}
		                }  
		            }  
		            writer.close();  
		            is.close();  
		            return str;
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        }  
			return null;
		 }
		 

	}
