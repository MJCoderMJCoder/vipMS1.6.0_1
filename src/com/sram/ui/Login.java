package com.sram.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sram.control.Control;

public class Login {
	public Login() {
		final JFrame frame = new JFrame("登录");
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null); //居中
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel(new GridLayout(4, 1));
		frame.add(panel);

		JPanel titlePanel = new JPanel();
		panel.add(titlePanel);
		final JLabel title = new JLabel("VIP登录");
		title.setFont(new Font("宋体", Font.BOLD, 25));
		titlePanel.add(title);

		JPanel idPanel = new JPanel();
		panel.add(idPanel);
		JLabel idLabel = new JLabel("请输入帐号：");
		final JTextField idText = new JTextField(10);
		final JLabel idMsg = new JLabel("  注 册 帐 号   ");
		idMsg.setForeground(Color.blue);
		idText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String idT = idText.getText();
				if(idText.getText().equals("")){
					idMsg.setText("  帐 号 为 空   ");
					idMsg.setForeground(Color.red);
				}else if(Control.checkID(idT)){
					idMsg.setText("  帐 号 正 确   ");
					idMsg.setForeground(Color.blue);
				}else{
					idMsg.setText("  帐 号 有 误   ");
					idMsg.setForeground(Color.red);
				}
			}
		});
		idMsg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				AddVip add = new AddVip();
			}
		});
		idPanel.add(idLabel);
		idPanel.add(idText);
		idPanel.add(idMsg);

		JPanel passwordPanel = new JPanel(); //密码面板
		panel.add(passwordPanel);
		JLabel passwordLabel = new JLabel("请输入密码：");
		final JPasswordField password = new JPasswordField(10);
		final JLabel passwordMsg = new JLabel("  找 回 密 码   ");
		passwordMsg.setForeground(Color.blue);
		passwordMsg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				FindPassword findPw = new FindPassword(idText.getText());
			}
		});
		password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pw = password.getText();
				String idT = idText.getText();
				if(password.getText().equals("")){
					passwordMsg.setText("  密 码 为 空   ");
					passwordMsg.setForeground(Color.red);
				}else if(Control.checkPW(idT, pw)){
					passwordMsg.setText("  密 码 正 确   ");
					passwordMsg.setForeground(Color.blue);
				}else {
					passwordMsg.setText("  密 码 有 误   ");
					passwordMsg.setForeground(Color.red);
				}
			}
		});
		passwordPanel.add(passwordLabel);
		passwordPanel.add(password);
		passwordPanel.add(passwordMsg);

		JPanel buttonPanel = new JPanel();
		panel.add(buttonPanel);
		JButton login = new JButton("登录");
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw = password.getText();
				String idT = idText.getText();
				if(idT.equals("root") && Control.checkPW(idT, pw)){
					frame.setVisible(false);
					ManagerUI manager = new ManagerUI();
				}else if(Control.checkPW(idT, pw) && Control.checkID(idT)){
					frame.setVisible(false);
					VIPInfomation vipInfo = new VIPInfomation(idT);
				}
			}
		});
		JButton reset = new JButton("重置");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				idText.setText("");
				password.setText("");
				passwordMsg.setText("  找 回 密 码   ");
				idMsg.setText("  注 册 帐 号   ");
				passwordMsg.setForeground(Color.blue);
				idMsg.setForeground(Color.blue);
			}
		});
		buttonPanel.add(login);
		buttonPanel.add(reset);
		frame.setVisible(true);
	}

	public Login(String curentDate) {
		final JFrame frame = new JFrame("登录");
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null); //居中
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel(new GridLayout(4, 1));
		frame.add(panel);

		JPanel titlePanel = new JPanel();
		panel.add(titlePanel);
		final JLabel title = new JLabel("VIP登录");
		title.setFont(new Font("宋体", Font.BOLD, 25));
		titlePanel.add(title);

		JPanel idPanel = new JPanel();
		panel.add(idPanel);
		JLabel idLabel = new JLabel("请输入帐号：");
		final JTextField idText = new JTextField(10);
		idText.setText(curentDate);
		final JLabel idMsg = new JLabel("  注 册 帐 号   ");
		idMsg.setForeground(Color.blue);
		idText.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String idT = idText.getText();
				if(idText.getText().equals("")){
					idMsg.setText("  帐 号 为 空   ");
					idMsg.setForeground(Color.red);
				}else if(Control.checkID(idT)){
					idMsg.setText("  帐 号 正 确   ");
					idMsg.setForeground(Color.blue);
				}else{
					idMsg.setText("  帐 号 有 误   ");
					idMsg.setForeground(Color.red);
				}
			}
		});
		idMsg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				AddVip add = new AddVip();
			}
		});
		idPanel.add(idLabel);
		idPanel.add(idText);
		idPanel.add(idMsg);

		JPanel passwordPanel = new JPanel(); //密码面板
		panel.add(passwordPanel);
		JLabel passwordLabel = new JLabel("请输入密码：");
		final JPasswordField password = new JPasswordField(10);
		final JLabel passwordMsg = new JLabel("  找 回 密 码   ");
		passwordMsg.setForeground(Color.blue);
		passwordMsg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
				FindPassword findPw = new FindPassword(idText.getText());
			}
		});
		password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pw = password.getText();
				String idT = idText.getText();
				if(password.getText().equals("")){
					passwordMsg.setText("  密 码 为 空   ");
					passwordMsg.setForeground(Color.red);
				}else if(Control.checkPW(idT, pw)){
					passwordMsg.setText("  密 码 正 确   ");
					passwordMsg.setForeground(Color.blue);
				}else {
					passwordMsg.setText("  密 码 有 误   ");
					passwordMsg.setForeground(Color.red);
				}
			}
		});
		passwordPanel.add(passwordLabel);
		passwordPanel.add(password);
		passwordPanel.add(passwordMsg);

		JPanel buttonPanel = new JPanel();
		panel.add(buttonPanel);
		JButton login = new JButton("登录");
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String pw = password.getText();
				String idT = idText.getText();
				if(idT.equals("root") && Control.checkPW(idT, pw)){
					frame.setVisible(false);
					ManagerUI manager = new ManagerUI();
				}else if(Control.checkPW(idT, pw) && Control.checkID(idT)){
					frame.setVisible(false);
					VIPInfomation vipInfo = new VIPInfomation(idT);
				}
			}
		});
		JButton reset = new JButton("重置");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				idText.setText("");
				password.setText("");
				passwordMsg.setText("  找 回 密 码   ");
				idMsg.setText("  注 册 帐 号   ");
				passwordMsg.setForeground(Color.blue);
				idMsg.setForeground(Color.blue);
			}
		});
		buttonPanel.add(login);
		buttonPanel.add(reset);
		frame.setVisible(true);
	}
}
