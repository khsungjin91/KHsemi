package thk.admin;

import java.sql.Timestamp;

public class ProductDto {

	private String p_code;
	private String p_name;
	private String p_material;
	private String p_thumbnail;
	private String p_img;
	private int p_price;
	private int p_price_dc;
	private int p_point;
	private String p_category1;
	private String p_category2;
	private String p_category3;
	private Timestamp p_sale_date;
	private int readcount; 
	
	
	
	
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public void setP_code(String p_code){  this.p_code = p_code;  }
	public void setP_name(String p_name){  this.p_name = p_name;  }
	public void setP_material(String p_material){  this.p_material = p_material;  }
	public void setP_thumbnail(String p_thumbnail){  this.p_thumbnail = p_thumbnail;  }
	public void setP_img(String p_img){  this.p_img = p_img;  }
	public void setP_price(int p_price){  this.p_price = p_price;  }
	public void setP_price_dc(int p_price_dc){  this.p_price_dc = p_price_dc;  }
	public void setP_point(int p_point){  this.p_point = p_point;  }
	public void setP_category1(String p_category1){  this.p_category1 = p_category1;  }
	public void setP_category2(String p_category2){  this.p_category2 = p_category2;  }
	public void setP_category3(String p_category3){  this.p_category3 = p_category3;  }
	public void setP_sale_date(Timestamp p_sale_date){   this.p_sale_date = p_sale_date;  }
	
	public String getP_code(){  return p_code;  }
	public String getP_name(){  return p_name;  }
	public String getP_material(){  return p_material;  }
	public String getP_thumbnail(){  return p_thumbnail;  }
	public String getP_img(){  return p_img;  }
	public int getP_price(){  return p_price;  }
	public int getP_price_dc(){  return p_price_dc;  }
	public int getP_point(){  return p_point;  }
	public String getP_category1(){  return p_category1;  }
	public String getP_category2(){  return p_category2;  }
	public String getP_category3(){  return p_category3;  }
	public Timestamp getP_sale_date(){  return p_sale_date; }
	
}
