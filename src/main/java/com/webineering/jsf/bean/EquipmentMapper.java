package com.webineering.jsf.bean;

import java.util.List;

public class EquipmentMapper {

	private static final long serialVersionUID = 1L;

	private Integer equipmentCount;
	private List<Object[]> equipmentList;

	public Integer getEquipmentCount() {
		return equipmentCount;
	}

	public void setEquipmentCount(final Integer equipmentCount) {
		this.equipmentCount = equipmentCount;
	}

	public List<Object[]> getEquipmentList() {
		return equipmentList;
	}

	public void setEquipmentList(final List<Object[]> equipmentList) {
		this.equipmentList = equipmentList;
	}

}
