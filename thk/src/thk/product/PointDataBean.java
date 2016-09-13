package thk.product;

public class PointDataBean {
	private int point_num;
	private int mem_num;
	private String point_add;
	private String point_reg_date;
	private String point_use_date;

	public void setPoint_num(int point_num) {
		this.point_num = point_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public void setPoint_add(String point_add) {
		this.point_add = point_add;
	}
	public void setPoint_reg_date(String point_reg_date) {
		this.point_reg_date = point_reg_date;
	}
	public void setPoint_use_date(String point_use_date) {
		this.point_use_date = point_use_date;
	}
		
	//get
	public int getPoint_num() {	
		return point_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public String getPoint_add() {
		return point_add;
	}
	public String getPoint_reg_date() {
		return point_reg_date;
	}
	public String getPoint_use_date() {
		return point_use_date;
	}
}
