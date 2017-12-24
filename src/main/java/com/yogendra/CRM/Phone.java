package com.yogendra.CRM;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Phone")
public class Phone {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="Extension_Number")
	private int extensionNumber;
	
	@Column(name="Main_Number")
	private int mainNumber;
	
	
	public int getExtensionNumber() {
		return extensionNumber;
	}
	public void setExtensionNumber(int extensionNumber) {
		this.extensionNumber = extensionNumber;
	}
	public int getMainNumber() {
		return mainNumber;
	}
	public void setMainNumber(int mainNumber) {
		this.mainNumber = mainNumber;
	}

}
