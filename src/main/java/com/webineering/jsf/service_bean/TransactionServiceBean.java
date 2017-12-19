package com.webineering.jsf.service_bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.webineering.jsf.bean.Transaction;
import com.webineering.jsf.service.TransactionService;

@ManagedBean(name = "transactionServiceBean")
@RequestScoped
public class TransactionServiceBean {

	private TransactionService transactionService;

	@ManagedProperty(value = "#{emFactoryBean}")
	private EntityManagerFactoryBean factoryBean;

	@ManagedProperty(value = "#{transaction}")
	private Transaction transaction;

	private Integer rentedCount;

	private String errorMsg = null;

	public TransactionService getTransactionService() {
		return transactionService;
	}

	public void setTransactionService(final TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	public EntityManagerFactoryBean getFactoryBean() {
		return factoryBean;
	}

	public void setFactoryBean(final EntityManagerFactoryBean factoryBean) {
		this.factoryBean = factoryBean;
	}

	@PostConstruct
	public void init() {
		transactionService = new TransactionService(factoryBean);
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(final Transaction transaction) {
		this.transaction = transaction;
	}

	public Integer getRentedCount() {
		return rentedCount;
	}

	public void setRentedCount(final Integer rentedCount) {
		this.rentedCount = rentedCount;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(final String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String addTransaction() {
		final Transaction persistedTransaction = transactionService.addTransaction(transaction);
		return "showTransaction";
	}

	public String addRental() {
		transaction.setTransactionType("out");
		final String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		transaction.setTransactionDate(timeStamp);
		System.out.println("equipment id is " + transaction.getEquipment1Id());
		final String rentStatus = transactionService.rentCheck(transaction.getEquipment1Id(),
				timeStamp);
		if (rentStatus == null) {
			System.out.println("rent status is null");
		} else {
			System.out.println("rent status is " + rentStatus);
		}

		if (rentStatus == null || rentStatus.equals("in")) {
			System.out.println("about to enter rental into table");
			final Transaction persistedTransaction = transactionService.addTransaction(transaction);
		} else {
			System.out.println("already out");
			this.setErrorMsg("This equipment already out.  NOT ENTERED!");
		}

		this.setRentedCount(this.nonReturnsCount());

		return "rentalDone";
	}

	public String addReturn() {
		transaction.setTransactionType("in");
		final String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		transaction.setTransactionDate(timeStamp);
		System.out.println("equipment id is " + transaction.getEquipment1Id());
		final String rentStatus = transactionService.rentCheck(transaction.getEquipment1Id(),
				timeStamp);
		if (rentStatus == null) {
			System.out.println("return status is null");
		} else {
			System.out.println("return status is " + rentStatus);
		}

		if (rentStatus == null) {
			System.out.println("not already out");
			this.setErrorMsg("This equipment not rented out already.  NOT ENTERED!");
		} else if (rentStatus.equals("out")) {
			System.out.println("about to enter return into table");
			final Transaction persistedTransaction = transactionService.addTransaction(transaction);
		} else {
			System.out.println("not already out");
			this.setErrorMsg("This equipment already returned previously.  NOT ENTERED!");
		}

		this.setRentedCount(this.nonReturnsCount());

		return "returnDone";
	}

	public List<Object[]> getTransactions() {
		return transactionService.getAllTransactions();
	}

	public String checkRental() {
		final String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		return transactionService.rentCheck(this.getTransaction().getEquipment1Id(), timeStamp);
	}

	public List<Object[]> nonReturns() {
		final String timeStamp = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
		// final String timeStamp = "12/15/2017";
		final List<Object[]> origResults = transactionService.nonReturns(timeStamp);
		final List<Object[]> finalResults = new ArrayList<Object[]>();
		String tempString = "none";
		if (origResults == null) {
			System.out.println("service bean error msg set");
			// this.setErrorMsg("No data at this time!");
		} else {
			for (final Object[] obj : origResults) {
				final Object[] tempArray = new Object[3];
				if (String.valueOf(obj[1]).equals(tempString)) {
					// do nothing
				} else {
					if (String.valueOf(obj[2]).equals("out")) {
						System.out
								.println(String.valueOf(obj[0]) + "," + String.valueOf(obj[1]) + ","
										+ String.valueOf(obj[2]));
						tempArray[0] = obj[0];
						tempArray[1] = obj[1];
						tempArray[2] = obj[2];
						finalResults.add(tempArray);
					}
					tempString = String.valueOf(obj[1]);
				}
			}
		}
		// return transactionService.nonReturns(timeStamp);
		return finalResults;
	}

	public Integer nonReturnsCount() {
		return this.nonReturns().size();
	}

}
