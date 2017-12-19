package com.webineering.jsf.service_bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.webineering.jsf.bean.Equipment;
import com.webineering.jsf.service.EquipmentService;

@ManagedBean(name = "equipmentServiceBean")
@RequestScoped
public class EquipmentServiceBean {

	private EquipmentService equipmentService;

	@ManagedProperty(value = "#{emFactoryBean}")
	private EntityManagerFactoryBean factoryBean;

	@ManagedProperty(value = "#{equipment}")
	private Equipment equipment;

	private String errorMsg = null;

	public EquipmentService getEquipmentService() {
		return equipmentService;
	}

	public void setEquipmentService(final EquipmentService equipmentService) {
		this.equipmentService = equipmentService;
	}

	public EntityManagerFactoryBean getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(final EntityManagerFactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}

	@PostConstruct
	public void init() {
		equipmentService = new EquipmentService(factoryBean);
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(final Equipment equipment) {
		this.equipment = equipment;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String addEquipment() {
		System.out.println("the value of equipment name is " + equipment.getEquipmentName());
		final Equipment persistedEquipment = equipmentService.addEquipment(equipment);
		System.out.println("persisted data: " + persistedEquipment.getEquipmentName());
		// this.setEquipment(persistedEquipment);
		System.out.println("the value of equipment description is " + persistedEquipment.getEquipmentDescription());
		// return "listEquipment";
		return "showEquipment";
	}

	public List<Object[]> getEquipments() {
		return equipmentService.getAllEquipment();
	}
}
