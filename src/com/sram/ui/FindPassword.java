package com.sram.ui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.sram.control.Control;

public class FindPassword {
	public FindPassword(String id){
		final JFrame frame = new JFrame("找回密码");
		frame.setSize(280, 180);
		frame.setLocationRelativeTo(null); //居中
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 1));
		frame.setResizable(false);
		
		JPanel p_id = new JPanel();
		frame.add(p_id);
		JLabel l_id = new JLabel("请输入帐号：");
		p_id.add(l_id);
		final JTextField t_id = new JTextField(10);
		t_id.setText(id);
		p_id.add(t_id);
		JLabel idMsg = new JLabel("*");
		idMsg.setForeground(Color.red);
		p_id.add(idMsg);
		
		JPanel p_tel = new JPanel();
		frame.add(p_tel);
		JLabel l_tel = new JLabel("输入手机号：");
		p_tel.add(l_tel);
		final JTextField t_tel = new JTextField(10);
		p_tel.add(t_tel);
		JLabel telMsg = new JLabel("*");
		telMsg.setForeground(Color.red);
		p_tel.add(telMsg);
		
		JPanel p_initPw = new JPanel();
		frame.add(p_initPw);
		JLabel l_initPw = new JLabel("当前密码为：");
		p_initPw.add(l_initPw);
		final JTextField t_initPassword = new JTextField(10);
		t_initPassword.setEditable(false);
		p_initPw.add(t_initPassword);
		JLabel initMsg = new JLabel(" ");
		p_initPw.add(initMsg);
		
		JPanel buttonPanel = new JPanel();
		frame.add(buttonPanel);
		JButton login = new JButton("返回登录");
		login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Login login = new Login(t_id.getText());
			}
		});
		
		JButton reset = new JButton("确定提交");
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String idT = t_id.getText();
				String tel = t_tel.getText();
				if(t_id.getText().equals("") || t_tel.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "带*号的项不能为空");
				}else if(Control.checkPhone(idT, tel) && Control.checkID(idT)){
					t_initPassword.setText(Control.findPw(idT));
				}else{
					JOptionPane.showMessageDialog(frame, "请重新核对您的帐号和手机号是否一致");
				}
			}
		});
		buttonPanel.add(login);
		buttonPanel.add(reset);
		
		frame.setVisible(true);
	}
	
}
