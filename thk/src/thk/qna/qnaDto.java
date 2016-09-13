package thk.qna;

import java.sql.Timestamp;

public class qnaDto {


	private String writer,pw,email,phone,sms_yn,secret,category1,category2,category3,subject,content;
	private int num,ref,re_step,re_level;
	private Timestamp reg_date;
	//글번호, 작성자, 패스워드, 이메일, 폰번호, 답글sms수신여부, 비밀글여부, 카테고리1차, 2차, 3차, 제목, 내용, 글그룹, 글정렬, 글정렬레벨, 등록일

	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setSms_yn(String sms_yn) {
		this.sms_yn = sms_yn;
	}
	public void setSecret(String secret) {
		this.secret = secret;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	public void setCategory3(String category3) {
		this.category3 = category3;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	
	
	

	public String getWriter() {return writer;}
	public String getPw() {return pw;}
	public String getEmail() {return email;}
	public String getPhone() {return phone;}
	public String getSms_yn() {return sms_yn;}
	public String getSecret() {return secret;}
	public String getCategory1() {return category1;}
	public String getCategory2() {return category2;}
	public String getCategory3() {return category3;}
	public String getSubject() {return subject;}
	public Timestamp getReg_date() {return reg_date;}
	public String getContent() {return content;}
	public int getNum() {return num;}
	public int getRef() {return ref;}
	public int getRe_step() {return re_step;}
	public int getRe_level() {return re_level;}
	
	
}
