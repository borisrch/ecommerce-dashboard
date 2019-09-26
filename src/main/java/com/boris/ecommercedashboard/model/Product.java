package com.boris.ecommercedashboard.model;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Product {

	// This product schema should be identical to the Product model in ecommerce project.
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Product name is required.")
	@Basic(optional = false)
	private String name;

	private Double price;	
	private String pictureUrl;
	private String description;
	private String brand;
	private String category;
	private String colour;

	public Product(Long id, @NotNull(message = "Product name is required.") String name, Double price,
			String pictureUrl, String description, String brand, String category, String colour) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.pictureUrl = pictureUrl;
		this.description = description;
		this.brand = brand;
		this.category = category;
		this.colour = colour;
	}
	
	public Product() {
    }


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
	
}