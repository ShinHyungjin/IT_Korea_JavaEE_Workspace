package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class ImageBoardDAO {
	DBManager dbManager = new DBManager(); 
	
	//create(insert)
	public int insert(ImageBoard imageboard) {
		PreparedStatement pstmt=null;
		Connection con = null;
	
		int result = 0;
		String sql = "insert into imageboard(author, title, content, filename) ";
		sql += "values(?,?,?,?)";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, imageboard.getAuthor());
			pstmt.setString(2, imageboard.getTitle());
			pstmt.setString(3, imageboard.getContent());
			pstmt.setString(4, imageboard.getFilename());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		return result;
	}
	
	//selectAll
	public ArrayList<ImageBoard> selectAll() {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		String sql = "select * from imageboard order by board_id desc";
		ArrayList<ImageBoard> list = new ArrayList<ImageBoard>();
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				ImageBoard imageBoard = new ImageBoard();
				imageBoard.setBoard_id(rs.getInt("board_id"));
				imageBoard.setAuthor(rs.getString("author"));
				imageBoard.setTitle(rs.getString("title"));
				imageBoard.setContent(rs.getString("content"));
				imageBoard.setRegdate(rs.getString("regdate"));
				imageBoard.setHit(rs.getInt("hit"));
				imageBoard.setFilename(rs.getString("filename"));
				list.add(imageBoard);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	//select
	public ImageBoard select(int board_id) {
		PreparedStatement pstmt=null;
		Connection con = null;
		ResultSet rs = null;
		ImageBoard imageBoard = null;
		String sql = "select * from imageboard where board_id=?";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				imageBoard = new ImageBoard();
				imageBoard.setBoard_id(rs.getInt("board_id"));
				imageBoard.setAuthor(rs.getString("author"));
				imageBoard.setTitle(rs.getString("title"));
				imageBoard.setContent(rs.getString("content"));
				imageBoard.setRegdate(rs.getString("regdate"));
				imageBoard.setHit(rs.getInt("hit"));
				imageBoard.setFilename(rs.getString("filename"));
			}
			sql = "update imageboard set hit=hit+1 where board_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt, rs);
		}
		return imageBoard;
	}
	
	//update
	public int update(ImageBoard imageBoard) {
		PreparedStatement pstmt =  null;
		Connection con = null;
		int result = 0;
		String sql = "update imageboard set author=?, title=?, content=?, filename=? where board_id=?";
		
		con =dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,imageBoard.getAuthor());
			pstmt.setString(2,imageBoard.getTitle());
			pstmt.setString(3,imageBoard.getContent());
			pstmt.setString(4,imageBoard.getFilename());			
			pstmt.setInt(5, imageBoard.getBoard_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	//delete
	public int delete(ImageBoard imageBoard) {
		PreparedStatement pstmt =  null;
		Connection con = null;
		String sql = "delete from imageboard where board_id=?";
		int result = 0;
		
		con =dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, imageBoard.getBoard_id());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}

}
