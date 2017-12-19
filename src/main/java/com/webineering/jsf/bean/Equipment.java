package com.webineering.jsf.bean;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the equipment database table.
 * 
 */
@ManagedBean(name = "equipment")
@RequestScoped
@Entity
@NamedQuery(name = "Equipment.findAll", query = "SELECT e.equipmentId, e.equipmentName, e.equipmentDescription FROM Equipment e")
public class Equipment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "equipment_id")
	private String equipmentId;

	@Column(name = "equipment_name")
	private String equipmentName;

	@Lob
	@Column(name = "equipment_description", nullable = true)
	private String equipmentDescription;

	@Column(name = "ski_number", nullable = true)
	private String skiNumber;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_created")
	private Date dateCreated;

	@Temporal(TemporalType.DATE)
	@Column(name = "date_modified")
	private Date dateModified;

	@Column(name = "equipment2_id", nullable = true)
	private String equipment2Id;

	public Equipment() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(final Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateModified() {
		return this.dateModified;
	}

	public void setDateModified(final Date dateModified) {
		this.dateModified = dateModified;
	}

	public String getEquipmentDescription() {
		return this.equipmentDescription;
	}

	public void setEquipmentDescription(final String equipmentDescription) {
		this.equipmentDescription = equipmentDescription;
	}

	public String getEquipmentId() {
		return this.equipmentId;
	}

	public void setEquipmentId(final String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentName() {
		return this.equipmentName;
	}

	public void setEquipmentName(final String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipment2Id() {
		return this.equipment2Id;
	}

	public void setEquipment2Id(final String equipment2Id) {
		this.equipment2Id = equipment2Id;
	}

	public String getSkiNumber() {
		return this.skiNumber;
	}

	public void setSkiNumber(final String skiNumber) {
		this.skiNumber = skiNumber;
	}

}