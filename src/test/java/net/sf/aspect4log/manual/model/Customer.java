package net.sf.aspect4log.manual.model;

public class Customer {
	int id;
	String name;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
	
}
