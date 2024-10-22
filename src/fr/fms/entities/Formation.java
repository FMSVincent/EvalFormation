package fr.fms.entities;

public class Formation {
	
	private String name;
	private String description;
	private int duration;
	private boolean isRemote;
	private double price;
	private int idCategory;
	
	public Formation(String name, String description, int duration, boolean isRemote, double price, int idCategory) {
		this.name = name;
		this.description = description;
		this.duration = duration;
		this.isRemote = isRemote;
		this.price = price;
		this.idCategory = idCategory;
	}

	@Override
	public String toString() {
		return "Formation [name=" + name + ", description=" + description + ", duration=" + duration + ", isRemote="
				+ isRemote + ", price=" + price + ", idCategory=" + idCategory + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public boolean isRemote() {
		return isRemote;
	}

	public void setRemote(boolean isRemote) {
		this.isRemote = isRemote;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	

}
