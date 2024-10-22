package fr.fms.entities;

public class Formation {
	
	private int idFormation;
	private String name;
	private int duration;
	private String description;
	private boolean isRemote;
	private float unitaryPrice;
	private int idCategory;
	
	public Formation(int idFormation, String name, int duration, String description, boolean isRemote,
			float unitaryPrice, int idCategory) {
		this.idFormation = idFormation;
		this.name = name;
		this.duration = duration;
		this.description = description;
		this.isRemote = isRemote;
		this.unitaryPrice = unitaryPrice;
		this.idCategory = idCategory;
	}

	@Override
	public String toString() {
		return "Formation [idFormation=" + idFormation + ", name=" + name + ", duration=" + duration + ", description="
				+ description + ", isRemote=" + isRemote + ", unitaryPrice=" + unitaryPrice + ", idCategory="
				+ idCategory + "]";
	}

	public int getIdFormation() {
		return idFormation;
	}

	public void setIdFormation(int idFormation) {
		this.idFormation = idFormation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsRemote() {
		return isRemote;
	}

	public void setRemote(boolean isRemote) {
		this.isRemote = isRemote;
	}

	public float getUnitaryPrice() {
		return unitaryPrice;
	}

	public void setUnitaryPrice(float unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}
	
	
	
}
