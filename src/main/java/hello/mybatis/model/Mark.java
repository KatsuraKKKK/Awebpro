package hello.mybatis.model;

import java.util.Date;

public class Mark {
	private int id;
	//private int item_id;
	private Item item;
	private double money;
	private Date time;
	private String discr;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	/*
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	*/
	
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDiscr() {
		return discr;
	}
	public void setDiscr(String discr) {
		this.discr = discr;
	}
	
	
	
}
