package com.yogendra.CRM.POJO;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="Image")
public class Image {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Id")
	private int id;
	
	@Column(name="Name")
	private String name;
	
	@Lob
	@Column
	private byte[] contentbyte;
	
	@Lob
	@Column
	@Transient
	private String content;
	
	
	
	public int getId() {
		return id;
	}





	public void setId(int id) {
		this.id = id;
	}





	public String getName() {
		return name;
	}





	public void setName(String name) {
		this.name = name;
	}





	public byte[] getContentbyte() {
		return contentbyte;
	}





	public void setContentbyte(byte[] contentbyte) {
		this.contentbyte = contentbyte;
	}





	


	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	@Override
	public String toString() {
		return String.format("Image [contentbyte=%s]", Arrays.toString(contentbyte));
	}





	public Image()
	{
		
	}
	
	

}
