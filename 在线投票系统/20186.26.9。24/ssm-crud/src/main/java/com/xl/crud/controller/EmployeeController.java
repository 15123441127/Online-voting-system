package com.xl.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xl.crud.bean.Employee;
import com.xl.crud.bean.Msg;
import com.xl.crud.service.EmployeeService;

/**
 * ����ѡ��CRUD����
 * @author Administrator
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * ��������ѡ����Ϣ
	 */
	
	@RequestMapping("/empsp")
	@ResponseBody
	public Msg getEmps() {
		List<Employee> list=employeeService.getEmps();
		return Msg.success().add("empsp", list);
	}
	
	/**
	 * ������������һ
	 * ������1-2-3
	 * ����ɾ����1
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	public Msg deleteEmp(@PathVariable("ids")String ids) {
		//����ɾ��
		if(ids.contains("-")) {
			List<Integer> del_ids=new ArrayList<Integer>();
			String[] str_ids=ids.split("-");
			//��װid����
			for (String string : str_ids) {
				del_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteBatch(del_ids);
		}else {
			Integer id=Integer.parseInt(ids);
			employeeService.deleteEmp(id);
			
		}
		return Msg.success();
	
		
	}
	
	
	/**
	 * ���ֱ�ӷ���ajax=PUT��ʽ������
	 * ��װ����
	 * Employee [empId=4022, empName=null, gender=null, email=null, dId=null, department=null]
	 * 
	 * ����
	 * ������������
	 * ����Employee�����װ����
	 * update tbl_emp where emp_id=1009
	 * 
	 * ԭ��
	 * Tomcat ��
	 * 	1�����������е����ݣ���װ��һ��map
	 * 2��request.getParameter("empName"")�ͻ�����mapȡֵ
	 * 3��SpringMVC��װPOJO�����ʱ��
	 * 				���POJO�е�ÿ�����Ե�ֵ��request.getParemeter("email")
	 * AJAX����PUT����������Ѫ����
	 * 			PUT�����������е����ݣ�request.getParameter("empName")�ò���
	 * 			Tomcatһ����PUT�����װ�����嵱�е�����Ϊmap��ֻ��POST��ʽ������Ż��װ������
	 * 
	 * �������
	 * ����Ҫ��֧��ֱ�ӷ���PUT֮�������Ҫ��װ�������е�����
	 * 1��������HttpPutFormContentFilter
	 *2���������ã��������������ݽ�����װ��һ��map
	 *3��request�����°�װ��request.getParameter()����д���ͻ���Լ���װ��map��ȡ����
	 * 
	 * ѡ�ָ��·���
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg saveEmp(Employee employee) {
		System.out.println("��Ҫ���µ�ѡ������"+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
		
	}
	
	/**
	 * ����id��ѯѡ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id) {
		Employee employee=employeeService.getEmp(id);
		return Msg.success().add("emp",employee);
		
	}
	
	/**
	 * ����û����Ƿ����
	 * @param empName
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkuser")
	public Msg checkuser(@RequestParam("empName")String empName) {
		//���ж��û����Ƿ��ǺϷ��ı��ʽ��
		String regx="(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regx)) {
			return Msg.fail().add("va_msg", "�û���������6-12λ���ֺ���ĸ����ϻ���2-5Ϊ����");
		}
		//���ݿ��û����ظ�У��
		boolean b=employeeService.checkuser(empName);
		if(b) {
			return Msg.success();
		}else {
			return Msg.fail().add("va_msg", "�û���������");
		}
	}
	
	/**
	 * Ա������
	 * 1.֧��jsr303У��
	 * ����hibernate-Validator
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		if(result.hasErrors()) {
			//У��ʧ�ܣ�Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵ���Ϣ
			Map<String, Object> map=new HashMap<String, Object>();
			List<FieldError> errors=result.getFieldErrors();
			for (FieldError fieldError : errors) {
				System.out.println("������ֶ�����"+fieldError.getField());
				System.out.println("������Ϣ��"+fieldError.getDefaultMessage());
				map.put(fieldError.getField(), fieldError.getDefaultMessage());
				
			}
			return Msg.fail().add("errorFields", map);
		}else {
			
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	/**
	 *ѡ�е�ѡ�ֲ��뵽Voting����
	 */
	
	/**
	 * ����Jackson��
	 * @param pn
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody//�ѷ��صĶ���תΪjson�ַ���
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn) {
		//�ⲻ��һ����ҳ��ѯ
				//����pagehelper��ҳ���
				//�ڲ�ѯ֮ǰֻ��Ҫ���ã�����ҳ�룬�Լ���ҳÿһҳ�Ĵ�С
				PageHelper.startPage(pn, 6);
				//startPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
				List<Employee> emps=employeeService.getAll();
				
				//ʹ��pageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ�������
				//��װ����ϸ�ķ�ҳ��Ϣ�����������ǲ�ѯ���������ݣ�����������ʾ��ҳ��
				PageInfo page=new PageInfo(emps,5);
				return Msg.success().add("pageInfo",page);
	}
	/**
	 * ��ѯѡ�����ݣ���ҳ��ѯ��
	 * @return
	 */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,
			Model model) {
		//�ⲻ��һ����ҳ��ѯ
		//����pagehelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ���ã�����ҳ�룬�Լ���ҳÿһҳ�Ĵ�С
		PageHelper.startPage(pn, 6);
		//startPage��������Ĳ�ѯ���Ƿ�ҳ��ѯ
		List<Employee> emps=employeeService.getAll();
		
		//ʹ��pageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ�������
		//��װ����ϸ�ķ�ҳ��Ϣ�����������ǲ�ѯ���������ݣ�����������ʾ��ҳ��
		PageInfo page=new PageInfo(emps,5);
		model.addAttribute("pageInfo", page);
		
		return "list";
	}

}
