package board.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import board.model.Notice;
import board.model.NoticeDAO;

public class BoardList extends Page{
	JTable table;
	JScrollPane scroll;
	JButton bt;
	BoardModel model;
	NoticeDAO noticeDAO;
	ArrayList<Notice> boardList;
	public BoardList(BoardMain boardMain) {
		super(boardMain);
		
		noticeDAO = new NoticeDAO();
		table = new JTable(model = new BoardModel()); // jtable�� �𵨰�ü���� ����
		scroll = new JScrollPane(table);
		bt = new JButton("�۾���");
		
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth(), 600));
		
		add(scroll);
		add(bt);
		
		getList();
		table.updateUI();
		
		bt.addActionListener((e)-> {
			boardMain.showPage(Pages.valueOf("BoardWrite").ordinal());
		});
		table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				int row = table.getSelectedRow();
				Notice notice = boardList.get(row);
				
				BoardContent boardContent = (BoardContent)boardMain.pageList[Pages.valueOf("BoardContent").ordinal()];
				boardContent.setData(notice);
				boardMain.showPage(Pages.valueOf("BoardContent").ordinal());
			}
		});
		
	}
	public void getList() {
		boardList = noticeDAO.selectAll();
		model.list = noticeDAO.selectAll();
	}

}
