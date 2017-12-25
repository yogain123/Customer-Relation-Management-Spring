package com.yogendra.CRM.POJO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Location_Finder")
public class LocationFinder {
	
@Id
@Column
@GeneratedValue(strategy=GenerationType.IDENTITY)
private int id;



@Column
private String city;

@Column
private String country;

@Column
private String countryCode;


@Column
private String query;

@Column
private String region;

@Column
private String regionName;

@Column
private String status;

@Column
private String timezone;

@Column
private String zip;


public LocationFinder() {
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public String getCity() {
	return city;
}


public void setCity(String city) {
	this.city = city;
}


public String getCountry() {
	return country;
}


public void setCountry(String country) {
	this.country = country;
}


public String getCountryCode() {
	return countryCode;
}


public void setCountryCode(String countryCode) {
	this.countryCode = countryCode;
}


public String getQuery() {
	return query;
}


public void setQuery(String query) {
	this.query = query;
}


public String getRegion() {
	return region;
}


public void setRegion(String region) {
	this.region = region;
}


public String getRegionName() {
	return regionName;
}


public void setRegionName(String regionName) {
	this.regionName = regionName;
}


public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


public String getTimezone() {
	return timezone;
}


public void setTimezone(String timezone) {
	this.timezone = timezone;
}


public String getZip() {
	return zip;
}


public void setZip(String zip) {
	this.zip = zip;
}


}