package com.sram.ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sram.bean.User;
import com.sram.dao.UserDao;
import com.sram.impl.UserDaoImpl;


public class AddVip {
	JFrame frame = new JFrame("添加");

	public AddVip(){
		init();
	}
	public void init(){
		frame.setSize(250, 210);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setResizable(false);

		JPanel panel = new JPanel(new BorderLayout());

		JPanel panel_top = new JPanel();
		JLabel title = new JLabel("VIP注册");
		title.setFont(new Font("宋体", Font.BOLD, 25));
		panel_top.add(title);

		JPanel panel_center = new JPanel(new GridLayout(3, 2));
		JPanel panel_center1 = new JPanel();
		JLabel l_username = new JLabel("请输入姓名：");
		final JTextField t_username = new JTextField(10);
		final JLabel nameMsg = new JLabel("*");
		nameMsg.setForeground(Color.red);
		panel_center1.add(l_username);
		panel_center1.add(t_username);
		panel_center1.add(nameMsg);
		JPanel panel_center2 = new JPanel();
		final JLabel l_password = new JLabel("请输入密码：");
		final JPasswordField t_password = new JPasswordField(10);
		final JLabel pwMsg = new JLabel("*");
		pwMsg.setForeground(Color.red);
		panel_center2.add(l_password);
		panel_center2.add(t_password);
		panel_center2.add(pwMsg);
		JPanel panel_center3 = new JPanel();
		JLabel l_tel = new JLabel("电话手机号：");
		final JTextField t_tel = new JTextField(10);
		final JLabel telMsg = new JLabel("*");
		telMsg.setForeground(Color.red);
		panel_center3.add(l_tel);
		panel_center3.add(t_tel);
		panel_center3.add(telMsg);
		panel_center.add(panel_center1);
		panel_center.add(panel_center2);
		panel_center.add(panel_center3);

		JPanel panel_down = new JPanel();
		JButton ok = new JButton("注册");
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(t_username.getText().equals("") || t_password.getText().equals("") || t_tel.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "带*号的项不能为空");
				}else{
					frame.setVisible(false);
					SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmmss");
					String curentDate = sdf.format(new Date());
					JOptionPane.showMessageDialog(frame, "恭喜您注册成功，帐号为：" + curentDate);
					User user = new User();
					user.setId(curentDate);
					user.setName(t_username.getText());
					user.setPassword(t_password.getText());
					user.setPhone(t_tel.getText());
					UserDao userDao = new UserDaoImpl();
					userDao.addUser(user);
					Login login = new Login(curentDate);
				}
			}
		});
		panel_down.add(ok);
		JButton reset = new JButton("重置");
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t_username.setText("");
				t_password.setText("");
				t_tel.setText("");
			}
		});
		panel_down.add(reset);

		panel.add(panel_top,BorderLayout.NORTH);
		panel.add(panel_center,BorderLayout.CENTER);
		panel.add(panel_down,BorderLayout.SOUTH);
		frame.add(panel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(frame, "确定退出注册界面，返回登录界面吗", "？", JOptionPane.YES_NO_OPTION);
				if(i == JOptionPane.YES_OPTION){
					frame.setVisible(false);
					Login login = new Login();
				} else {
				}
			}
		});
		frame.setVisible(true);
	}
}
