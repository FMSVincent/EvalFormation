package fr.fms.entities;

public class OrderItem {
	private int idOrderItem;

	private int idFormation;
	private double unitaryPrice;
	private int idOrder;

	public OrderItem(int idOrderItem, int idFormation, double unitaryPrice, int idOrder) {
		this.idOrderItem = idOrderItem;
		this.idFormation = idFormation;
		this.unitaryPrice = unitaryPrice;
		this.idOrder = idOrder;
	}

	public int getIdOrderItem() {
		return idOrderItem;
	}

	public void setIdOrderItem(int idOrderItem) {
		this.idOrderItem = idOrderItem;
	}

	public int getIdFormation() {
		return idFormation;
	}

	public void setIdArticle(int idFormation) {
		this.idFormation = idFormation;
	}

	public double getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}
}
