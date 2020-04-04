package login;

import javax.validation.constraints.AssertTrue;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class User {
	@NotEmpty()
	@Length(max=12,min=1)
	String username;//�û���
	@Length(max=12,min=1)
	String password;//����
	String password2;//ȷ������
	String sex;//�Ա�
	String work;//ְҵ
	String favorite;//���˰���
	String note;//˵��
/*	@AssertTrue()
	public boolean isSamePass() {
	if (password!=null&&password.equals(username)) {return false;} else {return true;}
	}*/
	@AssertTrue()
	public boolean isSamePassword() {
	if (password!=null&&password.equals(password2)) {return true;} else {return false;}
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getWork() {
		return work;
	}
	public void setWork(String work) {
		this.work = work;
	}
	public String getFavorite() {
		return favorite;
	}
	public void setFavorite(String favorite) {
		this.favorite = favorite;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
