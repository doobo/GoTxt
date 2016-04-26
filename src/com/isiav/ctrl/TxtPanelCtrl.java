package com.isiav.ctrl;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class TxtPanelCtrl {
	
	public static void insert(String str,AttributeSet attrset,JTextPane textPane){
		Document docs=textPane.getDocument();//利用getDocument()方法取得JTextPane的Document instance.0
		str=str+"\n";
		try{
			docs.remove(0, docs.getLength());
		    docs.insertString(0,str,attrset); 
		    //设置光标开始位置为0
		    textPane.setCaretPosition(0);
		}catch(BadLocationException ble){
		     System.out.println("BadLocationException:"+ble);
		}
 }
	
	public SimpleAttributeSet getAttrset() {
		SimpleAttributeSet attrset=new SimpleAttributeSet();
		StyleConstants.setForeground(attrset,Color.black);
//		StyleConstants.setItalic(attrset,true);
		StyleConstants.setFontSize(attrset,16);
		return attrset;
	}
	
}
