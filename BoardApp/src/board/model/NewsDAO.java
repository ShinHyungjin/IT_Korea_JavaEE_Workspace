package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class NewsDAO {
	DBManager dbManager = new DBManager();
	
	//insert
	public int insert(News news) {
		PreparedStatement pstmt = null;
		Connection con = null;
		int result = 0;
		String sql = "insert into news(title, writer, content) values(?,?,?)";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		return result;
	}
	
	//update
	public int update(News news) {
		PreparedStatement pstmt = null;
		Connection con = null;
		int result = 0;
		
		String sql = "update news set title=?, writer=?, content=? where news_id=?";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, news.getTitle());
			pstmt.setString(2, news.getWriter());
			pstmt.setString(3, news.getContent());
			pstmt.setInt(4, news.getNews_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		return result;
	}
	
	//select
	public News select(int news_id) {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		News news = null;
		String sql = "select * from news where news_id=?";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				news.setContent(rs.getString("content"));
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return news;
	}
	
	//selectAll
	public List<News> selectAll() {
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		ArrayList<News> list = new ArrayList<News>();
		
		//join문으로 해당 뉴스글의 댓글수를 뿌려줌
		StringBuilder sb = new StringBuilder();
		sb.append("select  n.news_id as news_id, writer, title , regdate, hit,count(comments_id) as cnt ");
		sb.append("from news n left outer join comments c "); // left, right를 기준으로 해당 테이블의 컬럼은 무조건 가져와라
		sb.append("on n.news_id = c.news_id "); 
		sb.append("group by n.news_id,writer, title , regdate, hit order by n.news_id desc");
		
		//String sql = "select * from news";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				News news = new News();
				news.setNews_id(rs.getInt("news_id"));
				news.setTitle(rs.getString("title"));
				news.setWriter(rs.getString("writer"));
				//news.setContent(rs.getString("content")); //기사 리스트에선 content는 필요없다
				news.setRegdate(rs.getString("regdate"));
				news.setHit(rs.getInt("hit"));
				news.setCnt(rs.getInt("cnt"));
				list.add(news);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	//delete
	public int delete(int news_id) {
		PreparedStatement pstmt = null;
		Connection con = null;
		int result = 0;
		String sql = "delete from news where news_id=?";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	//자식코맨드 존재로 인한 삭제된 게시물 처리
	public int replace(int news_id) {
		PreparedStatement pstmt = null;
		Connection con = null;
		int result = 0;
		
		String sql = "update news set title='원글이 삭제된 게시물입니다', writer='', content='' where news_id=?";
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, news_id);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
}
