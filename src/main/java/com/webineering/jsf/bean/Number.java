package com.webineering.jsf.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the numbers database table.
 * 
 */
@Entity
@Table(name = "numbers")
@NamedQuery(name = "Number.findAll", query = "SELECT n FROM Number n")
public class Number implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name = "date_created")
	private String dateCreated;

	@Column(name = "number_series")
	private String numberSeries;

	@Column(name = "number_value")
	private String numberValue;

	public Number() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(final String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getNumberSeries() {
		return this.numberSeries;
	}

	public void setNumberSeries(final String numberSeries) {
		this.numberSeries = numberSeries;
	}

	public String getNumberValue() {
		return this.numberValue;
	}

	public void setNumberValue(final String numberValue) {
		this.numberValue = numberValue;
	}

}