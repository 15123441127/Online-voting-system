package login;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.InputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
 @Controller
public class UploadController {
	 @RequestMapping("/upload1")
	 public ModelAndView testFileUpload(String filename,MultipartFile file,HttpSession session) throws IOException{
	 	System.out.println("��ע: " + filename);
	 	filename=file.getOriginalFilename();
	 	System.out.println("ԭ�ļ���: " + filename);
	 	String path = session.getServletContext().getRealPath("/")+"files\\"+filename;
	     System.out.println(path);
	 	File f=new File(path);
	 	file.transferTo(f);
	 	String uripath="/SpringMVC/files/"+filename;
	 	ModelAndView modelAndView = new ModelAndView("download");
	 	modelAndView.addObject("filename", filename);
	 	modelAndView.addObject("uripath", uripath);
	 	return modelAndView;
	 }
	 @RequestMapping("/download")
	 public ResponseEntity<byte[]> downloadFlie (String filename,HttpSession session) throws IOException{
	 //�õ�Ҫ�����ļ��ֽ���
	 	ServletContext servletContext = session.getServletContext();
	 	java.io.InputStream in = servletContext.getResourceAsStream("/files/"+filename);
	 //���ֽ����ļ�ת��Ϊ�ֽ�����body����������ҳ�������
	 	byte [] body = new byte[in.available()];
	 	in.read(body);
	 //��������ҳ��ͷ
	 	HttpHeaders headers = new HttpHeaders();
	 	headers.add("Content-Disposition", "attachment;filename="+filename);
	 //��������ҳ���״̬	
	 HttpStatus statusCode = HttpStatus.OK;
	 //����ҳ�����ݡ�ҳ��ͷ��ҳ��״̬��������ת����ҳ��(ʵ�ʷ��ص��������ļ�����)
	 	ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
	 	return response;
	 }


}