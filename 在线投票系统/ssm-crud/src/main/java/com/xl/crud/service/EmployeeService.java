package com.xl.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl.crud.bean.Department;
import com.xl.crud.bean.Employee;
import com.xl.crud.bean.EmployeeExample;
import com.xl.crud.bean.EmployeeExample.Criteria;
import com.xl.crud.dao.EmployeeMapper;

@Service//ҵ���߼�����
public class EmployeeService {
	//�Զ�װ��services��ҵ���߼�����
	@Autowired
	EmployeeMapper employeeMapper;

	/**
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getAll() {
		//������
		return employeeMapper.selectByExampleWithDept(null);
	}
	/**
	 * Ա������
	 * @param employee
	 */

	public void saveEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.insertSelective(employee);
		
	}
	/**
	 * �����û����Ƿ����
	 * @param empName
	 * @return true������ǰ�������� false��������
	 */
	public boolean checkuser(String empName) {
		// TODO Auto-generated method stub
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count=employeeMapper.countByExample(example);
		return count==0;
	}
	/**
	 * ����ѡ��id��ѯѡ��
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		Employee employee=employeeMapper.selectByPrimaryKey(id);
		
		return employee;
	}
	/**
	 * ѡ�ָ���
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}
	/**
	 * ѡ��ɾ��
	 * @param id
	 */
	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
		
	}
	public void deleteBatch(List<Integer> ids) {
		// TODO Auto-generated method stub
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		//delete form xxx where emp_id in(1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);
		
	}
	
	/**
	 * ��ȡ����ѡ�ַ���
	 * @return
	 */
	public List<Employee> getEmps() {
		// TODO Auto-generated method stub
		List<Employee> list=employeeMapper.selectByExample(null);
		return list;
		
	}
	/*public List<Department> getDepts() {
		// TODO Auto-generated method stub
		List<Department> list=departmentMapper.selectByExample(null);
		return list;
	}*/

}
