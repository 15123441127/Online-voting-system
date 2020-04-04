package login;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionTemplate;

import login.ILogin;


public class Login implements ILogin{
	private SqlSessionTemplate sqlSession;
	public boolean execute(User user) {
		boolean ret=false;
		//			inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		//			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
					// 2.�õ�ִ��sql�Ķ���
		//			SqlSession sqlSession = sqlSessionFactory.openSession();
					// 3.ִ��sql���
					int userResult = sqlSession.insert("login",user);
					// 4.������
					if (userResult!=0) {
						ret=true;
					}
					// 5.�ر����ݿ�
//					sqlSession.close();
		return ret;
	}
	public SqlSessionTemplate getSqlSession() {
		return sqlSession;
	}
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}


}
