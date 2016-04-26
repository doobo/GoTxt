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

		//�õ�htmlԴ�ļ��е�TXT����
		public static String getHtml2Text(String inputString){
		     String htmlStr = inputString; //��html��ǩ���ַ���
		     String textStr ="";
		     java.util.regex.Pattern p_script;
		     java.util.regex.Matcher m_script;
		     java.util.regex.Pattern p_style;
		     java.util.regex.Matcher m_style;
		     java.util.regex.Pattern p_html;
		     java.util.regex.Matcher m_html;

		    try{
		          String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //����script��������ʽ{��<script[^>]*?>[\\s\\S]*?<\\/script> }
		          String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //����style��������ʽ{��<style[^>]*?>[\\s\\S]*?<\\/style> }
		          String regEx_html = "<[^>]+>"; //����HTML��ǩ��������ʽ
//		          String regEx_cat =""; //{"cat_name"  }

		          p_script = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
		          m_script = p_script.matcher(htmlStr);
		          htmlStr = m_script.replaceAll(""); //����script��ǩ

		          p_style = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
		          m_style = p_style.matcher(htmlStr);
		          htmlStr = m_style.replaceAll(""); //����style��ǩ

		          p_html = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
		          m_html = p_html.matcher(htmlStr);
		          htmlStr = m_html.replaceAll(""); //����html��ǩ
		          
//		          p_html = Pattern.compile(regEx_cat);
//		          m_html = p_html.matcher(htmlStr);
//		          htmlStr = m_html.replaceAll(""); //{"cat_name" }

		          textStr = htmlStr;
		     }catch(Exception e){
		         e.printStackTrace();
		     }
		    
		     return removeKong(textStr.replace('\t', '\n'));//�����ı��ַ���
		 }   
		
		//�滻����ַ���Ϊһ���ַ�
		public static String removeKong(String textStr){
		    String w="";
		    //�滻������ַ���
		    Pattern p = Pattern.compile("\\s+");
		    Matcher m = p.matcher(textStr);
		    w= m.replaceAll(" ");
		    
		    //��ȥhtml�ĳ��ñ�ǩ
		    Pattern p4 = Pattern.compile("(&nbsp;)|(&quot;)|(&raquo;)|(&bull;)|(&lt;)|(&gt;)|(&ldquo;)");
		    Matcher m4 = p4.matcher(w);
		    w= m4.replaceAll("");
		    
		    //�滻���͡���Ϊ����
		    Pattern p2 = Pattern.compile(";");
		    Matcher m2 = p2.matcher(w);
		    w= m2.replaceAll(";\n");
		    
		    Pattern p3 = Pattern.compile("��");
		    Matcher m3 = p3.matcher(w);
		    w= m3.replaceAll("��\n");
		    
		    Pattern p5 = Pattern.compile(">");
		    Matcher m5 = p5.matcher(w);
		    w= m5.replaceAll(">\n");
		    
			return w;
		}
		
		
		//��Stringд���ļ�
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
		 //׷���ַ���׷�ӵ��ļ�
		 public  void writeFiletoEnd(String url,String filename){
		       //��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
		        try{
		            FileWriter writer = new FileWriter(filename, true);
		            writer.write(url);
//		            System.out.println("д�ļ��ɹ�...");
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
