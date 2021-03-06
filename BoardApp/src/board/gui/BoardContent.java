package board.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.model.Notice;
import board.model.NoticeDAO;

public class BoardContent extends Page {
	JTextField t_title, t_author;
	JTextArea area;
	JScrollPane scroll;
	JButton bt_list, bt_edit, bt_del;
	Notice notice;
	NoticeDAO noticeDAO;

	public BoardContent(BoardMain boardMain) {
		super(boardMain);

		t_author = new JTextField();
		t_title = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_list = new JButton("목록으로");
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		notice = new Notice();
		noticeDAO = new NoticeDAO();

		t_author.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth() - 10, 25));
		t_title.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth() - 10, 25));
		scroll.setPreferredSize(new Dimension((int) this.getPreferredSize().getWidth() - 10, 500));

		add(t_author);
		add(t_title);
		add(scroll);
		add(bt_list);
		add(bt_edit);
		add(bt_del);

		bt_list.addActionListener((e) -> {
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());
		});

		bt_edit.addActionListener((e) -> {

			if (JOptionPane.showConfirmDialog(BoardContent.this, "수정하실래여?") == JOptionPane.OK_OPTION) {
				notice.setAuthor(t_author.getText());
				notice.setTitle(t_title.getText());
				notice.setContent(area.getText());

				int result = noticeDAO.edit(notice);

				if (result == 0) {
					JOptionPane.showMessageDialog(BoardContent.this, "수정실패");
				} else {
					JOptionPane.showMessageDialog(BoardContent.this, "수정성공");
					BoardList boardList = (BoardList) boardMain.pageList[Pages.valueOf("BoardList").ordinal()];
					boardList.getList();
					boardList.table.updateUI();
					boardMain.showPage(Pages.valueOf("BoardList").ordinal());
				}
			}
		});

		bt_del.addActionListener((e) -> {
			
			if (JOptionPane.showConfirmDialog(BoardContent.this, "삭제하실래여?") == JOptionPane.OK_OPTION) {

				int result = noticeDAO.del(notice);

				if (result == 0) {
					JOptionPane.showMessageDialog(BoardContent.this, "삭제실패");
				} else {
					JOptionPane.showMessageDialog(BoardContent.this, "삭제성공");
					BoardList boardList = (BoardList) boardMain.pageList[Pages.valueOf("BoardList").ordinal()];
					boardList.getList();
					boardList.table.updateUI();
					boardMain.showPage(Pages.valueOf("BoardList").ordinal());
				}
			}

		});

	}

	public void setData(Notice notice) {
		this.notice = notice; // 나중에 써먹을것을 대비하여 보관
		t_author.setText(notice.getAuthor());
		t_title.setText(notice.getTitle());
		area.setText(notice.getContent());
	}
}
