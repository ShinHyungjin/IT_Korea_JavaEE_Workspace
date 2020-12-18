package com.model2.mybatis.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfigManager {
	private static MybatisConfigManager instance = null;
	String resource;
	InputStream inputStream;
	SqlSessionFactory sqlSessionFactory;

	private MybatisConfigManager() {
		resource = "com/model2/mybatis/config/config.xml";
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static MybatisConfigManager getInstance() {
		if (instance == null)
			instance = new MybatisConfigManager();
		return instance;
	}

	public SqlSession getSqlSession() {
		SqlSession sqlSession = null;
		sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}

	public void close(SqlSession sqlSession) {
		sqlSession.close();
	}
}
