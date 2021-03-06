package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class NoticeDAO {
	DBManager dbManager = new DBManager();
	
	public int del(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt =  null;
		String sql = "delete from notice where notice_id=?";
		int result = 0;
		
		con =dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice.getNotice_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	public int edit(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt =  null;
		String sql = "update notice set author=?, title=?, content=? where notice_id=?";
		int result = 0;
		
		con =dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,notice.getAuthor());
			pstmt.setString(2,notice.getTitle());
			pstmt.setString(3,notice.getContent());
			pstmt.setInt(4, notice.getNotice_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	public int regist(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt =  null;
		String sql = "insert into notice(author, title, content) values(?,?,?)";
		int result = 0;
		
		con =dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,notice.getAuthor());
			pstmt.setString(2,notice.getTitle());
			pstmt.setString(3,notice.getContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	public ArrayList<Notice> selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList<Notice>();
		String sql = "select * from notice order by notice_id desc";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Notice notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				list.add(notice);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	// 게시물 1건 가져오기(상세보기)
	public Notice select(int notice_id) {
		PreparedStatement pstmt=null;
		Connection con = null;
		ResultSet rs = null;
		Notice notice = null;
		String sql = "select * from notice where notice_id=?";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				notice = new Notice();
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
			sql = "update notice set hit=hit+1 where notice_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, notice_id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return notice;
	}
}
