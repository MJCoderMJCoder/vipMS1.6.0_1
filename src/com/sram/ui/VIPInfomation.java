package com.sram.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
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

public class VIPInfomation implements ActionListener{
	JFrame frame = new JFrame();
	JPanel panel,content,panel1,panel2,panel3, panel4, panel5, topPanel;
	JPasswordField pw;
	JLabel label ,label2,label3,label4,label5;
	String ImageName="";
	JTextField t1, t2, t3;
	JButton bt0, bt1, bt2;

	public VIPInfomation(final String idT){
		frame=new JFrame("个人信息详情");
		frame.setSize(500,300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		UserDao userDao = new UserDaoImpl();
		User user = userDao.load(idT);
		panel=new JPanel(new BorderLayout());
		
		topPanel = new JPanel();
		final JLabel title = new JLabel("VIP个人信息详情");
		title.setFont(new Font("宋体", Font.BOLD, 25));
		topPanel.add(title);

		//setBorder() 是使用Border 边框 ，Border 是特殊的Swing组件，为Swing组件提供不同的边框修饰
		//EtchedBorder该类实现简单的浮雕化边框
		content = new JPanel(new GridLayout(5, 1));
		content.setBorder(BorderFactory.createTitledBorder("基本信息"));
		panel1 = new JPanel();	
		label = new JLabel("帐号：");
		panel1.add(label);
		t1 = new JTextField(12);
		t1.setText(idT);
		t1.setEditable(false);
		panel1.add(t1);
		panel2 = new JPanel();	
		label2 = new JLabel("姓名：");
		panel2.add(label2);
		t2 = new JTextField(12);
		t2.setText(user.getName());
		t2.setEditable(false);
		panel2.add(t2);
		panel3 = new JPanel();
		label3 = new JLabel("手机：");
		panel3.add(label3);
		t3 = new JTextField(12);
		t3.setText(user.getPhone());
		t3.setEditable(false);
		panel3.add(t3);

		panel5 = new JPanel();
		label5 = new JLabel("密码：");
		panel5.add(label5);
		pw = new JPasswordField(12);
		pw.setText(user.getPassword());
		pw.setEditable(false);
		panel5.add(pw);
		
		panel4 = new JPanel();
		bt0 = new JButton("修改信息");
		bt0.addActionListener(this);
		bt0.setVisible(true);
		bt2 = new JButton("提交修改");
		bt2.addActionListener(this);
		bt2.setVisible(false);
		bt1 = new JButton("注销VIP");
		bt1.addActionListener(this);
		bt1.setVisible(true);
		panel4.add(bt0);
		panel4.add(bt1);
		panel4.add(bt2);
		content.add(panel1);
		content.add(panel2);
		content.add(panel3);
		content.add(panel5);
		content.add(panel4);
		
		JPanel emptyPanel = new JPanel(new GridLayout(5, 1));
		emptyPanel.setSize(100, 200);
		JLabel emptyLabe1 = new JLabel("**************VIP特权**************");
		emptyLabe1.setFont(new Font("宋体", Font.BOLD, 13));
		emptyPanel.add(emptyLabe1);
		JLabel emptyLabe2 = new JLabel("  * 全场消费打五折");
		emptyLabe2.setFont(new Font("宋体", Font.PLAIN, 19));
		emptyPanel.add(emptyLabe2);
		JLabel emptyLabe3 = new JLabel("  * 节假日赠送惊喜礼品");
		emptyLabe3.setFont(new Font("宋体", Font.PLAIN, 19));
		emptyPanel.add(emptyLabe3);
		JLabel emptyLabe4 = new JLabel("  * 享受VIP贵宾休息区");
		emptyLabe4.setFont(new Font("宋体", Font.PLAIN, 19));
		emptyPanel.add(emptyLabe4);
		JLabel emptyLabe5 = new JLabel("  * 每月三次（巨额）抽奖机会");
		emptyLabe5.setFont(new Font("宋体", Font.PLAIN, 19));
		emptyPanel.add(emptyLabe5);

		panel.add(emptyPanel, BorderLayout.WEST);
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(content,BorderLayout.EAST);

		frame.add(panel);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(frame, "确定退出个人信息界面，返回登陆界面吗", "？", JOptionPane.YES_NO_OPTION);
				if(i == JOptionPane.YES_OPTION){
					frame.setVisible(false);
					Login l = new Login();
				} else {
				}
			}
		});
	}
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();//控件
		if(obj==bt0){
			t2.setEditable(true);
			t3.setEditable(true);
			pw.setEditable(true);
			bt0.setVisible(false);
			bt1.setVisible(false);
			bt2.setVisible(true);
		}else if(obj==bt1){
			int i = JOptionPane.showConfirmDialog(frame, "您确定要从该系统中删除您的所有信息（将会注销帐号）么？", "？", JOptionPane.YES_NO_OPTION);
			if(i == JOptionPane.YES_OPTION){
				UserDao userDao = new UserDaoImpl();
				userDao.deleteUser(t1.getText());
				JOptionPane.showMessageDialog(frame, "注销成功");
				frame.setVisible(false);
				Login login = new Login();
			} else {
			}
			
		}else if(obj==bt2){
			User user = new User();
			user.setId(t1.getText());
			user.setName(t2.getText());
			user.setPassword(pw.getText());
			user.setPhone(t3.getText());
			UserDao userDao = new UserDaoImpl();
			userDao.updateUser(user);
			JOptionPane.showMessageDialog(frame, "修改提交成功");
			t2.setEditable(false);
			t2.setText(user.getName());
			t3.setEditable(false);
			t3.setText(user.getPhone());
			pw.setEditable(false);
			pw.setText(user.getPassword());
			bt0.setVisible(true);
			bt1.setVisible(true);
			bt2.setVisible(false);
		}
	}
}
