package thk.admin;

import java.sql.Timestamp;

public class MemberDataBean {

	private int mem_num;
	private String mem_id;
	private String mem_pw;
	private String mem_name;
	private String mem_phone;
	private String mem_sms_yn;
	private String mem_email;
	private String mem_email_yn;
	private String mem_postnum;
	private String mem_addrs1;
	private String mem_addrs2;
	private String mem_rate;
	private int mem_sum;
	private int mem_point;
	private Timestamp reg_date;

	public void setMemNum (int mem_num){
		this.mem_num = mem_num;
	}
	public void setMemId (String mem_id){
		this.mem_id = mem_id;
	}
    public void setMemPw (String mem_pw){
		this.mem_pw = mem_pw;
	}
	public void setMemName (String mem_name){
		this.mem_name = mem_name;
	}
	public void setMemPhone (String mem_phone){
		this.mem_phone = mem_phone;
	}
	public void setMemSmsYn (String mem_sms_yn){
		this.mem_sms_yn = mem_sms_yn;
	}
	public void setMemEmail (String mem_email){
		this.mem_email = mem_email;
	}
	public void setMemEmailYn (String mem_email_yn){
		this.mem_email_yn = mem_email_yn;
	}
	public void setMemPostnum (String mem_postnum){
		this.mem_postnum = mem_postnum;
	}
	public void setMemAddrs1 (String mem_addrs1){
		this.mem_addrs1 = mem_addrs1;
	}
	public void setMemAddrs2 (String mem_addrs2){
		this.mem_addrs2 = mem_addrs2;
	}
	public void setMemRate (String mem_rate){
		this.mem_rate = mem_rate;
	}
	public void setMemSum (int mem_sum){
		this.mem_sum = mem_sum;
	}
	public void setMemPoint (int mem_point){
		this.mem_point = mem_point;
	}
	public void setReg_date (Timestamp reg_date){
		this.reg_date = reg_date;
	}
	
	////////////////////////////////////////////////
	
	public int getMemNum(){
 		return mem_num; 
 	}
	public String getMemId(){
 		return mem_id; 
 	}
	public String getMemPw(){
 		return mem_pw; 
 	}
	public String getMemName(){
 		return mem_name; 
 	}
	public String getMemPhone(){
 		return mem_phone; 
 	}
	public String getMemSmsYn(){
 		return mem_sms_yn; 
 	}
	public String getMemEmail(){
 		return mem_email; 
 	}
	public String getMemEmailYn(){
 		return mem_email_yn; 
 	}
	public String getMemPostnum(){
		return mem_postnum;
	}
	public String getMemAddrs1(){
 		return mem_addrs1; 
 	}
	public String getMemAddrs2(){
 		return mem_addrs2; 
 	}
	public String getMemRate(){
 		return mem_rate; 
 	}
	public int getMemSum(){
 		return mem_sum; 
 	}
	public int getMemPoint(){
 		return mem_point; 
 	}
	public Timestamp getReg_date(){
 		return reg_date; 
 	}
}