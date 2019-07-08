package com.sram.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.sram.bean.User;
import com.sram.dao.UserDao;
import com.sram.impl.UserDaoImpl;

public class ManagerUI {
	JFrame frame = new JFrame("搜索页面");
	JPanel panel,paneltop,panelcenter,paneldown;
	JLabel l_id,l_username,l_tel;
	JTextField j_id,j_username,j_tel;
	JButton ok,cancel;
	DefaultTableModel tablemodel;
	JTable table;
	JScrollPane sp;
	List<User> lists;
	public JFrame getFrame(){
		return frame;
	}
	public ManagerUI(){
		init();
	}
	private void init() {
		frame.setSize(500,400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		panel = new JPanel(new BorderLayout());
		paneltop = new JPanel(new FlowLayout());
		l_id = new JLabel("按帐号：");
		j_id = new JTextField(6);
		l_username = new JLabel("按姓名：");
		j_username = new JTextField(6);
		l_tel = new JLabel("按电话：");
		j_tel = new JTextField(6);
		ok = new JButton("搜索");
		ok.addActionListener(new ActionListener() {
			UserDao userDao = new UserDaoImpl();
			@Override
			public void actionPerformed(ActionEvent e) {
				String username=j_username.getText();
				String id=j_id.getText();
				String tel=j_tel.getText();
				lists = userDao.findUsers("%"+id+"%", "%"+username+"%", "%"+tel+"%");
				if(lists.size()==0){
					JOptionPane.showMessageDialog(null,"查无此人");
				}else{
					//清空前一次查询记录；清空table
					//DefaultTableModel使用vector存储数据，
					//假如要一条一条去删除现有的行，只能按照倒叙去删
					//6    0,1,2,3,4,5
					for(int i =tablemodel.getRowCount()-1;i>=0;i--){
						tablemodel.removeRow(i);
						//removeRow从0开始算；0是查询结果的第一行
					}
					for(User user:lists){
						Object[] in={user.getId(),user.getName(), user.getPhone()};
						tablemodel.insertRow(0, in);
					}
				}
			}
		});
		paneltop.add(l_id);
		paneltop.add(j_id);
		paneltop.add(l_username);
		paneltop.add(j_username);
		paneltop.add(l_tel);
		paneltop.add(j_tel);
		paneltop.add(ok);
		panelcenter = new JPanel();
		tablemodel = new DefaultTableModel();
		String[] str ={"帐号","姓名","电话"};
		for(int i=0;i<str.length;i++){
			tablemodel.addColumn(str[i]);
		}
		table = new JTable();
		table.setModel(tablemodel);//给表格空间设置模板样式
		sp = new JScrollPane(table);//给表格空间设置滚动条
		sp.setPreferredSize(new Dimension(450, 280));//设置滚动条空间大小
		panelcenter.add(sp);
		paneldown = new JPanel();
		cancel = new JButton("删除");
		paneldown.add(cancel);
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				if(rowIndex < 0){
					JOptionPane.showMessageDialog(null, "未选中将要删除的用户");//弹出框
				}
				String idT = table.getValueAt(rowIndex, 0).toString();
				if(idT.equals("root")){
					JOptionPane.showMessageDialog(null, "这是管理员帐号，不可删除");
				} else{
					UserDao userDao = new UserDaoImpl();
					userDao.deleteUser(idT);

					String username=j_username.getText();
					String id=j_id.getText();
					String tel=j_tel.getText();
					lists = userDao.findUsers("%"+id+"%", "%"+username+"%", "%"+tel+"%");
					//清空前一次查询记录；清空table
					//DefaultTableModel使用vector存储数据，
					//假如要一条一条去删除现有的行，只能按照倒叙去删
					//6    0,1,2,3,4,5
					for(int i =tablemodel.getRowCount()-1;i>=0;i--){
						tablemodel.removeRow(i);
						//removeRow从0开始算；0是查询结果的第一行
					}
					for(User user:lists){
						Object[] in={user.getId(),user.getName(), user.getPhone()};
						tablemodel.insertRow(0, in);
					}
				}
			}
		});
		panel.add(paneltop,BorderLayout.NORTH);
		panel.add(panelcenter,BorderLayout.CENTER);
		panel.add(paneldown,BorderLayout.SOUTH);
		frame.add(panel);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(frame, "确定退出管理员界面，返回登录界面吗", "？", JOptionPane.YES_NO_OPTION);
				if(i == JOptionPane.YES_OPTION){
					frame.setVisible(false);
					Login l = new Login();
				} else {
				}
			}
		});
		frame.setVisible(true);
	}
}
