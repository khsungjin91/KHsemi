package thk.ship;

public class ShipDto {
	private long pay_code;
	private int mem_num;
	private String p_code;
	private String p_size;
	private int pay_amount; 
	private String get_name;
	private String get_phone;
	private String get_postnum;
	private String get_addrs1;
	private String get_addrs2;
	private String get_memo;
	private String shipnum;
	private String shipcheck;
	
	
	
	
	public long getPay_code() {
		return pay_code;
	}
	public void setPay_code(long pay_code) {
		this.pay_code = pay_code;
	}
	public String getShipcheck() {
		return shipcheck;
	}
	public void setShipcheck(String shipcheck) {
		this.shipcheck = shipcheck;
	}

	public void setMem_num(int mem_num) {this.mem_num=mem_num;}
	public void setP_code(String p_code) {this.p_code=p_code;}
	public void setP_size(String p_size) {this.p_size=p_size;}
	public void setPay_amount(int pay_amount) {this.pay_amount=pay_amount;}
	public void setGet_name(String get_name) {	this.get_name = get_name;	}
	public void setGet_phone(String get_phone) {		this.get_phone = get_phone;	}
	public void setGet_postnum(String get_postnum) {		this.get_postnum = get_postnum;	}
	public void setGet_addrs1(String get_addrs1) {		this.get_addrs1 = get_addrs1;	}
	public void setGet_addrs2(String get_addrs2) {		this.get_addrs2 = get_addrs2;	}
	public void setGet_memo(String get_memo) {		this.get_memo = get_memo;	}
	public void setShipnum(String shipnum) {		this.shipnum = shipnum;	}


	public int getMem_num() { return mem_num; }
	public String getP_code() {		return p_code;	}
	public String getP_size() {		return p_size;	}
	public int getPay_amount() {		return pay_amount;}
	public String getGet_name() {		return get_name;}
	public String getGet_phone() {		return get_phone;}
	public String getGet_postnum() {		return get_postnum;	}
	public String getGet_addrs1() {		return get_addrs1;	}
	public String getGet_addrs2() {		return get_addrs2;	}
	public String getGet_memo() {		return get_memo;	}
	public String getShipnum() {		return shipnum;	}
	
	
}
