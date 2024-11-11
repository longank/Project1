package com.javaweb.repository.entity;

public class BuildingEntity {
	private String name;
	private String street;
	private String ward;
	private Long id;
	private Long districtid;
	private Long floorarea;
	private Long rentprice;
	private String servicefee;
	private Long brokeragefee;
	private String managername;
	private String managerphonenumber;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDistrictid() {
		return districtid;
	}
	public void setDistrictid(Long districtid) {
		this.districtid = districtid;
	}
	public Long getFloorarea() {
		return floorarea;
	}
	public void setFloorarea(Long floorarea) {
		this.floorarea = floorarea;
	}
	public Long getRentprice() {
		return rentprice;
	}
	public void setRentprice(Long rentprice) {
		this.rentprice = rentprice;
	}
	public String getServicefee() {
		return servicefee;
	}
	public void setServicefee(String servicefee) {
		this.servicefee = servicefee;
	}
	public Long getBrokeragefee() {
		return brokeragefee;
	}
	public void setBrokeragefee(Long brokeragefee) {
		this.brokeragefee = brokeragefee;
	}
	public String getManagername() {
		return managername;
	}
	public void setManagername(String managername) {
		this.managername = managername;
	}
	public String getManagerphonenumber() {
		return managerphonenumber;
	}
	public void setManagerphonenumber(String managerphonenumber) {
		this.managerphonenumber = managerphonenumber;
	}
	
}
