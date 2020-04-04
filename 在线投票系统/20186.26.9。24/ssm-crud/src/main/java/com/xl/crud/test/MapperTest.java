package com.xl.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xl.crud.bean.Department;
import com.xl.crud.bean.Employee;
import com.xl.crud.dao.DepartmentMapper;
import com.xl.crud.dao.EmployeeMapper;

/**
 * ����dao�㹤��
 * @author Administrator
 * �Ƽ�spring����Ŀ�Ϳ���ʹ��SPring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 * 1������SpringTestģ��
 * 2��@ContextConfigurationָ�������ļ���λ��
 * 3��ֱ��autowiredҪʹ�õ��������
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	/**
	 * ����departmentMapper
	 */
	@Test
	public void testCRUD() {
//		//1.����SpringIOC����
//		ApplicationContext ioc=new ClassPathXmlApplicationContext("applicationContext.xml");
//		//�������л�ȡmapper
//		DepartmentMapper bean=ioc.getBean(DepartmentMapper.class);
		System.out.println(departmentMapper);
		
		//1.���뼸������
//		new Department();
//		departmentMapper.insertSelective(new Department(1,"����Ӱ"));
//		departmentMapper.insertSelective(new Department(2,"����"));
		
		//2������Ա�����ݣ�����Ա������
		//employeeMapper.insertSelective(new Employee(null, "jerry", "F", "jerry@xl.com", 1));
		//3.����������Ա��,������ʹ�ÿ���ִ������������sqlsession
//		for() {
//			employeeMapper.insertSelective(new Employee(null, "jerry", "F", "jerry@xl.com", 1));
//		}
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++) {
			String uid=UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null, uid, "M", uid+"@xl.com", 1));
			
		}
		System.out.println("�������");
//		
	}

}
