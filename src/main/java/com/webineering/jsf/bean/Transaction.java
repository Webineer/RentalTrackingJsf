package com.webineering.jsf.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the transactions database table.
 * 
 */
@ManagedBean(name = "transaction")
@RequestScoped
@Entity
@Table(name = "transactions")
@NamedQueries({
		@NamedQuery(name = "Transaction.findAll", query = "SELECT t FROM Transaction t"),
		@NamedQuery(name = "Transaction.rentCheck", query = "SELECT t.transactionType FROM Transaction t WHERE t.equipment1Id = :equipId AND t.transactionDate = :transDate ORDER BY t.id DESC"),
		@NamedQuery(name = "Transaction.nonReturns", query = "select t.id, t.equipment1Id, t.transactionType from Transaction t where t.equipment1Id in (select distinct t.equipment1Id from Transaction t) and t.transactionDate = :transDate order by t.id DESC")
})
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "agreement_id", nullable = true)
	private String agreementId;

	@Column(name = "equipment1_id", nullable = true)
	private String equipment1Id;

	@Column(name = "equipment2_id", nullable = true)
	private String equipment2Id;

	@Column(name = "transaction_date")
	private String transactionDate;

	@Column(name = "transaction_type")
	private String transactionType;

	public Transaction() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getAgreementId() {
		return this.agreementId;
	}

	public void setAgreementId(final String agreementId) {
		this.agreementId = agreementId;
	}

	public String getEquipment1Id() {
		return this.equipment1Id;
	}

	public void setEquipment1Id(final String equipment1Id) {
		this.equipment1Id = equipment1Id;
	}

	public String getEquipment2Id() {
		return this.equipment2Id;
	}

	public void setEquipment2Id(final String equipment2Id) {
		this.equipment2Id = equipment2Id;
	}

	public String getTransactionDate() {
		return this.transactionDate;
	}

	public void setTransactionDate(final String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return this.transactionType;
	}

	public void setTransactionType(final String transactionType) {
		this.transactionType = transactionType;
	}

}