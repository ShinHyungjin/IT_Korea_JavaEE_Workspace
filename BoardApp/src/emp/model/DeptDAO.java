package emp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import db.MybatisManager;

public class DeptDAO {
	MybatisManager manager = new MybatisManager();
	SqlSessionFactory factory;
	
	public DeptDAO() {
		factory = manager.getSqlSessionFactory();
	}
	
	public List selectAll() {
		SqlSession session = factory.openSession(); // 孽府巩 荐青按眉 积己
		return session.selectList("mybatis.config.Dept.selectAll");
	}
	
}
