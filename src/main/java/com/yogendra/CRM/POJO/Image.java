package com.yogendra.CRM.POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name="Image")
public class Image {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	@Column(name="Image_Name")
	private String name;
	
	@Lob
	@Column(name="Photo")
	private byte[] photo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image()
	{
		
	}
	
	

}
