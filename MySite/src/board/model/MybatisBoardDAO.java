package board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.config.MyBatisConfigManager;

public class MybatisBoardDAO {
	MyBatisConfigManager configManager =MyBatisConfigManager.getInstance();
	
	public int insert(Board board) {
		int result = 0;
		SqlSession sqlSession = configManager.getSession();
		result = sqlSession.insert("Board.insert", board);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	public List selectAll() {
		List list = null;
		SqlSession sqlSession = configManager.getSession();
		list = sqlSession.selectList("Board.selectAll");
		configManager.close(sqlSession);
		return list;
	}
	public Board select(int board_id) {
		Board board = null;
		SqlSession sqlSession = configManager.getSession();
		board = sqlSession.selectOne("Board.select");
		configManager.close(sqlSession);
		return board;
	}
	public int update(Board board) {
		int result = 0;
		SqlSession sqlSession = configManager.getSession();
		System.out.println(board.getBoard_id());
		System.out.println(board.getFilename());
		result = sqlSession.update("Board.update", board);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	public int delete(int board_id) {
		int result = 0;
		SqlSession sqlSession = configManager.getSession();
		result = sqlSession.delete("Board.delete", board_id);
		sqlSession.commit();
		configManager.close(sqlSession);
		return result;
	}
	
}
