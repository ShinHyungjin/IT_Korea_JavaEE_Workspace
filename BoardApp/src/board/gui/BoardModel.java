package board.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import board.model.Notice;

public class BoardModel extends AbstractTableModel{
	String [] column = {"Notice_ID","작성자","제목","등록일","조회수"};
	ArrayList <Notice> list = new ArrayList<Notice>();
			
	@Override
	public int getColumnCount() {
		return column.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}
	
	@Override
	public String getColumnName(int col) {
		return column[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Notice notice = list.get(row);
		String obj = null;
		if(col == 0) {
			obj = String.valueOf(notice.getNotice_id());
		} else if(col == 1) {
			obj = notice.getAuthor();
		}else if(col == 2) {
			obj = notice.getTitle();
		}else if(col == 3) {
			obj = notice.getRegdate();
		}else if(col == 4) {
			obj = String.valueOf(notice.getHit());
		}
		return obj;
	}
}
