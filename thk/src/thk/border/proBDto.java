package thk.border;

import java.sql.Timestamp;

public class proBDto {
	
	private String writer,subject,content,pw,email,ip;
	private int num,ref,re_step,re_level,readcount;
	private Timestamp reg_date;
	
	
	
	public void setNum(int num){this.num = num;}
	public void setWriter(String writer){this.writer = writer;}
	public void setPw(String pw) {this.pw = pw;}
	public void setSubject(String subject){this.subject = subject;}
	public void setContent(String content){this.content = content;}
	public void setReadcount(int readcount) {this.readcount = readcount;}
	public void setRef(int ref){this.ref = ref;}
	public void setRe_step(int re_step){this.re_step = re_step;}
	public void setRe_level(int re_level){this.re_level = re_level;}
	public void setReg_date(Timestamp reg_date){this.reg_date = reg_date;}
	public void setEmail(String email) {this.email = email;}
	public void setIp(String ip) {this.ip = ip;}
	
	
	
	
	
	public int getNum(){return num;}
	public String getWriter(){return writer;}
	public String getPw() {return pw;}
	public String getSubject(){return subject;}
	public String getContent(){return content;}
	public int getReadcount() {return readcount;}
	public int getRef() {return ref;}
	public int getRe_step() {return re_step;}
	public int getRe_level() {return re_level;}
	public Timestamp getReg_date() {return reg_date;}
	public String getEmail() {return email;}
	public String getIp() {return ip;}
	
	
	
	
	
	
	

}