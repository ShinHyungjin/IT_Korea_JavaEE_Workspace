package gui;

import java.awt.Choice;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import blood.BloodManager;

public class BloodForm extends JFrame{
	Choice ch;
	JButton bt;
	BloodManager manager = new BloodManager();
	
	public BloodForm() {
		ch = new Choice();
		bt = new JButton("분석보기");
		
		ch.add("A");
		ch.add("B");
		ch.add("O");
		ch.add("AB");
		
		setLayout(new FlowLayout());
		add(ch);
		add(bt);
		
		setSize(400,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		bt.addActionListener((e) -> {
			showResult();
		});
		
	}
	public void showResult() {
		String msg = manager.getAdvisor(ch.getSelectedItem());
		JOptionPane.showMessageDialog(this, msg);
	}
	public static void main(String[] args) {
		new BloodForm();
	}
}
