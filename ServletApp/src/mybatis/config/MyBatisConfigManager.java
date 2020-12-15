package mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisConfigManager {
	String resource = "mybatis/config/config.xml";
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;
	private static MyBatisConfigManager instance;
	
	private MyBatisConfigManager() {
		try{
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public SqlSession getSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
	public void close(SqlSession sqlSession) {
		sqlSession.close();
	}
	
	public static MyBatisConfigManager getInstance() {
		if(instance == null) {
			instance = new MyBatisConfigManager();
		}
		return instance;
	}
	
}
