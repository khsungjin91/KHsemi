package thk.payment;

import java.sql.Timestamp;

public class PaymentDto {
	private long pay_code;
	private int mem_num;
	private String p_code;
	private String p_size;
	private int pay_amount;
	private int pay_price;
	private int p_point;
	private String pay_howpay;
	private Timestamp reg_date;
	private String p_name;
    private String year_mt;
    private String pay_sum;
	
    
    

	public String getYear_mt() {
		return year_mt;
	}
	public void setYear_mt(String year_mt) {
		this.year_mt = year_mt;
	}
	public String getPay_sum() {
		return pay_sum;
	}
	public void setPay_sum(String pay_sum) {
		this.pay_sum = pay_sum;
	}
	public long getPay_code() {
		return pay_code;
	}
	public void setPay_code(long pay_code) {
		this.pay_code = pay_code;
	}
	
	
	public void setMem_num(int mem_num) {this.mem_num=mem_num;}
	public void setP_code(String p_code) {this.p_code=p_code;}
	public void setP_size(String p_size) {this.p_size=p_size;}
	public void setPay_amount(int pay_amount) {this.pay_amount=pay_amount;}
	public void setPay_price(int pay_price) {this.pay_price=pay_price;}
	public void setP_point(int p_point) {this.p_point=p_point;}
	public void setPay_howpay(String pay_howpay) {this.pay_howpay=pay_howpay;}
	public void setReg_date(Timestamp reg_date) {this.reg_date = reg_date;}
	public void setP_name(String p_name) {this.p_name = p_name;}
	

	public int getMem_num() 	{		return mem_num;	}
	public String getP_code() 	{		return p_code;	}
	public String getP_size() 	{		return p_size;	}
	public int getPay_amount() 	{		return pay_amount;	}
	public int getPay_price() 	{		return pay_price;	}
	public int getP_point() 	{		return p_point;	}
	public String getPay_howpay() 	{		return pay_howpay;	}
	public Timestamp getReg_date() 	{		return reg_date;	}
	public String getP_name() 		{		return p_name;	}
	
	
}
	
	