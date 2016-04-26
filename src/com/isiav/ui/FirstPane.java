package com.isiav.ui;

import javax.swing.JPanel;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FirstPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5395167130307748551L;
	private JTextField txt_add;
	private JComboBox cb_encode = null;
	private JTextPane txt_Pane = null;
	/**
	 * Create the panel.
	 */
	public FirstPane() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lb_encode = new JLabel("\u7F16\u7801:  ");
		lb_encode.setFont(new Font("宋体", Font.BOLD, 16));
		GridBagConstraints gbc_lb_encode = new GridBagConstraints();
		gbc_lb_encode.anchor = GridBagConstraints.EAST;
		gbc_lb_encode.ipady = 20;
		gbc_lb_encode.insets = new Insets(10, 30, 5, 0);
		gbc_lb_encode.gridx = 0;
		gbc_lb_encode.gridy = 0;
		add(lb_encode, gbc_lb_encode);
		
		 cb_encode = new JComboBox();
		cb_encode.setFont(new Font("宋体", Font.BOLD, 16));
		cb_encode.setModel(new DefaultComboBoxModel(new String[] {"UTF-8", "GBK", "GB2312", "ISO-8859-1"}));
		GridBagConstraints gbc_cb_encode = new GridBagConstraints();
		gbc_cb_encode.anchor = GridBagConstraints.WEST;
		gbc_cb_encode.ipady = 6;
		gbc_cb_encode.insets = new Insets(10, 0, 5, 10);
		gbc_cb_encode.gridx = 1;
		gbc_cb_encode.gridy = 0;
		add(cb_encode, gbc_cb_encode);
		
		JLabel lb_add = new JLabel("\u7F51\u7EDC\u5730\u5740:  ");
		lb_add.setFont(new Font("宋体", Font.BOLD, 16));
		GridBagConstraints gbc_lb_add = new GridBagConstraints();
		gbc_lb_add.ipady = 20;
		gbc_lb_add.insets = new Insets(10, 0, 5, 0);
		gbc_lb_add.gridx = 2;
		gbc_lb_add.gridy = 0;
		add(lb_add, gbc_lb_add);
		
		txt_add = new JTextField();
		txt_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txt_add.setText("");
				if(com.isiav.ctrl.PasteKit.displayTextFromClipboard()!=null){
					txt_add.setText(com.isiav.ctrl.PasteKit.displayTextFromClipboard());
				}
			}
		});
		txt_add.setFont(new Font("宋体", Font.BOLD, 16));
		GridBagConstraints gbc_txt_add = new GridBagConstraints();
		gbc_txt_add.gridwidth = 2;
		gbc_txt_add.fill = GridBagConstraints.HORIZONTAL;
		gbc_txt_add.ipady = 12;
		gbc_txt_add.insets = new Insets(10, 0, 5, 0);
		gbc_txt_add.gridx = 3;
		gbc_txt_add.gridy = 0;
		add(txt_add, gbc_txt_add);
		txt_add.setColumns(10);
		
		JButton btn_sub = new JButton("\u89E3\u6790");
		btn_sub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toText();
			}
		});
		btn_sub.setFont(new Font("宋体", Font.BOLD, 16));
		GridBagConstraints gbc_btn_sub = new GridBagConstraints();
		gbc_btn_sub.ipady = 5;
		gbc_btn_sub.insets = new Insets(10, 10, 5, 100);
		gbc_btn_sub.anchor = GridBagConstraints.WEST;
		gbc_btn_sub.gridx = 5;
		gbc_btn_sub.gridy = 0;
		add(btn_sub, gbc_btn_sub);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 6;
		gbc_scrollPane.insets = new Insets(10, 10, 20, 10);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		txt_Pane = new JTextPane();
		scrollPane.setViewportView(txt_Pane);

	}
	
	
	
	private void toText(){
		String encode = cb_encode.getSelectedItem().toString();
		String turl = txt_add.getText();
		String result_str = com.isiav.io.HtmltoString.getHtmlContent(turl, encode);
		result_str = com.isiav.io.CheckString.getHtml2Text(result_str);
		com.isiav.io.CheckString.writeFile(result_str);
		result_str = com.isiav.io.CheckString.getFileLine("temp.txt");
		
		SimpleAttributeSet attrset = getAttrset();
		com.isiav.ctrl.TxtPanelCtrl.insert(result_str,attrset,txt_Pane); 
		
	}



	public SimpleAttributeSet getAttrset() {
		SimpleAttributeSet attrset=new SimpleAttributeSet();
		StyleConstants.setForeground(attrset,Color.black);
//		StyleConstants.setItalic(attrset,true);
		StyleConstants.setFontSize(attrset,16);
		return attrset;
	}
}
