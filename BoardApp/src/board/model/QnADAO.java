package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBManager;

public class QnADAO {
	DBManager dbManager=new DBManager();
	
	//insert : 원글 등록
	public int insert(QnA qna) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int result=0;
		
		String sql="insert into qna(writer, title ,content) values(?,?,?)";
		try {
			con=dbManager.getConnection();
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			result=pstmt.executeUpdate();//실행
			
			//team을 방금 들어간 레코드에 의해 발생한 pk 값으로 업데이트!!!
			sql="update qna set team=(select last_insert_id()) where qna_id=(select last_insert_id())";
			pstmt=con.prepareStatement(sql); //쿼리문 1:1 대응하게!!
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	/*	
	1.기존에 내가본글보다 rank가 큰 글의 rank는 모두 1씩 증가되시오!! (공간확보 )
    update  qna  rank=rank+1 where rank > 내본글 rank and 
    team=내본team
	2.빈 공간을 내가 차지!!(답변)
	   insert  qna(~team, rank, depth) values(내본team,내본rank+1,내본depth+1)
	 */   
	public int reply(QnA qna) {
		int result=0;
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = "update qna set rank=rank+1 where team=? and rank > ?";
		
		try {
			con = dbManager.getConnection();
			con.setAutoCommit(false); //java의 con은 자동으로 commit 하기 때문에 트랜잭션 커밋을 사용자가 직접 조작하기 위함
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, qna.getTeam());
			pstmt.setInt(2, qna.getRank());
			
			result = pstmt.executeUpdate();
			
			sql = "insert into qna(writer, title, content, team, rank, depth) ";
			sql += "values(?,?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getTeam());
			pstmt.setInt(5, qna.getRank()+1); // 내본글 다음 rank
			pstmt.setInt(6, qna.getDepth()+1); // 내본글 다음 depth
			
			result = pstmt.executeUpdate();
			
			con.commit();	// update와 insert의 트랜잭션이 문제없이 수행될 경우 커밋함
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				con.rollback(); // catch는 문제가 생긴경우이기 때문에 DB의 상태를 이전 지점으로 되돌아감
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			dbManager.release(con, pstmt);
			try {
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//selectAll
	public List selectAll() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		ArrayList<QnA> list = new ArrayList<QnA>();
		
		String sql="select * from qna order by team desc, rank asc";
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				QnA qna = new QnA(); //레코드만큼 vo 생성해야 함!!
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
				
				list.add(qna); //리스트에 추가하기!!
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	//select
	public QnA select(int qna_id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		QnA qna = null;
		String sql="select * from qna where qna_id=?";
		con=dbManager.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, qna_id);
			rs=pstmt.executeQuery();
		
			while(rs.next()) {
				qna = new QnA(); //레코드만큼 vo 생성해야 함!!
				qna.setQna_id(rs.getInt("qna_id"));
				qna.setWriter(rs.getString("writer"));
				qna.setTitle(rs.getString("title"));
				qna.setContent(rs.getString("content"));
				qna.setRegdate(rs.getString("regdate"));
				qna.setHit(rs.getInt("hit"));
				qna.setTeam(rs.getInt("team"));
				qna.setRank(rs.getInt("rank"));
				qna.setDepth(rs.getInt("depth"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return qna;
	}
	
	//update
	public int update(QnA qna) {
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = "update qna set writer=?, title=?, content=? where qna_id=?";
		int result=0;
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qna.getWriter());
			pstmt.setString(2, qna.getTitle());
			pstmt.setString(3, qna.getContent());
			pstmt.setInt(4, qna.getQna_id());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		return result;
	}
	
	//delete
	public int delete(QnA qna) {
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = "delete from qna where qna_id=?";
		int result=0;
		
		con = dbManager.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna.getQna_id());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con,pstmt);
		}
		return result;
	}
	
}
