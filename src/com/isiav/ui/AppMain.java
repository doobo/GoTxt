package com.isiav.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;

public class AppMain {

	private JFrame frmHtml;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppMain window = new AppMain();
					window.frmHtml.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppMain() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmHtml = new JFrame();
		frmHtml.setIconImage(Toolkit.getDefaultToolkit().getImage(AppMain.class.getResource("/com/sun/java/swing/plaf/motif/icons/DesktopIcon.gif")));
		frmHtml.setTitle("HTML\u5185\u5BB9\u63D0\u53D6\u5668");
		frmHtml.setBounds(150, 10, 800, 600);
		frmHtml.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHtml.setContentPane(new FirstPane());
	}

}
