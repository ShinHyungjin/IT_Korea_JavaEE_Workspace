package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class CommentsDAO {
	DBManager dbManager = new DBManager();
	//insert
		public int insert(Comments comments) {
			PreparedStatement pstmt = null;
			Connection con = null;
			int result = 0;
			String sql = "insert into comments(news_id, author, msg) values(?,?,?)";
			
			con = dbManager.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, comments.getNews_id());
				pstmt.setString(2, comments.getAuthor());
				pstmt.setString(3, comments.getMsg());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con,pstmt);
			}
			return result;
		}
		
		//update
		public int update(Comments comments) {
			PreparedStatement pstmt = null;
			Connection con = null;
			int result = 0;
			
			String sql = "update news set author=?, msg=? where comments_id=? and news_id=?";
			
			con = dbManager.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, comments.getAuthor());
				pstmt.setString(2, comments.getMsg());
				pstmt.setInt(3, comments.getComments_id());
				pstmt.setInt(4, comments.getNews_id());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con,pstmt);
			}
			return result;
		}
		
		//select
		public Comments select(int comments_id, int news_id) {
			PreparedStatement pstmt = null;
			Connection con = null;
			ResultSet rs = null;
			Comments comments = null;
			String sql = "select * =  from comments where comments_id=? and news_id=?";
			
			con = dbManager.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, comments_id);
				pstmt.setInt(2, news_id);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					comments = new Comments();
					comments.setComments_id(rs.getInt("comments_id"));
					comments.setNews_id(rs.getInt("news_id"));
					comments.setAuthor(rs.getString("author"));
					comments.setCdate(rs.getString("cdate"));
					comments.setMsg(rs.getString("msg"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt, rs);
			}
			return comments;
		}
		
		//selectAll
		public List<Comments> selectAll(int news_id) {
			PreparedStatement pstmt = null;
			Connection con = null;
			ResultSet rs = null;
			ArrayList<Comments> list = new ArrayList<Comments>();
			
			String sql = "select * from comments where news_id=?";
			
			con = dbManager.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, news_id);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					Comments comments = new Comments();
					comments.setComments_id(rs.getInt("comments_id"));
					comments.setNews_id(rs.getInt("news_id"));
					comments.setAuthor(rs.getString("author"));
					comments.setCdate(rs.getString("cdate"));
					comments.setMsg(rs.getString("msg"));
					list.add(comments);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt, rs);
			}
			return list;
		}
		
		//delete
		public int delete(int comments_id) {
			PreparedStatement pstmt = null;
			Connection con = null;
			int result = 0;
			String sql = "delete from comments where comments_id=?";
			
			con = dbManager.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, comments_id);
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				dbManager.release(con, pstmt);
			}
			return result;
		}

}
