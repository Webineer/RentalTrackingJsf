package com.webineering.jsf.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import com.webineering.jsf.bean.Transaction;
import com.webineering.jsf.service_bean.EntityManagerFactoryBean;

public class TransactionService {

	private EntityManagerFactory factory;

	public TransactionService(final EntityManagerFactoryBean factoryBean) {
		this.factory = factoryBean.getEntityManagerFactory();
	}

	public List<Object[]> getAllTransactions() {
		final EntityManager em = factory.createEntityManager();
		final List<Object[]> result = em.createNamedQuery("Transaction.findAll").getResultList();

		if (result != null && result.size() > 0) {
			// System.out.println("equip id in service is " + result.get(0).getEquipmentId());
			// em.close();
			return result;
		} else {
			System.out.println("null return");
			return null;
		}

	}

	public Transaction addTransaction(final Transaction transaction) {
		final EntityManager em = factory.createEntityManager();
		if (em == null) {
			System.out.println("entity manager is null");
			throw new IllegalArgumentException();
		}
		final EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(transaction);
		txn.commit();
		return transaction;
	}

	public Transaction updateTransaction(final Transaction transaction) {
		final EntityManager em = factory.createEntityManager();
		final EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.merge(transaction);
		txn.commit();
		return transaction;
	}

	public void deleteTransaction(final Transaction transaction) {
		final EntityManager em = factory.createEntityManager();
		final EntityTransaction txn = em.getTransaction();
		txn.begin();
		final Transaction foundTransaction = em.find(Transaction.class, transaction.getId());
		em.remove(foundTransaction);
		txn.commit();
	}

	public Transaction getTransaction(final int id) {
		final EntityManager em = factory.createEntityManager();
		return em.find(Transaction.class, id);
	}

	public String rentCheck(final String equipId, final String transDate) {
		final EntityManager em = factory.createEntityManager();

		final List<Object[]> result = em.createNamedQuery("Transaction.rentCheck")
				.setParameter("equipId", equipId)
				.setParameter("transDate", transDate)
				.getResultList();

		if (result != null && result.size() > 0) {
			System.out.println("rent check value in service is " + String.valueOf(result.get(0)));
			return String.valueOf(result.get(0));
		} else {
			System.out.println("rent check is null");
			return null;
		}
	}

	public List<Object[]> nonReturns(final String transDate) {
		final EntityManager em = factory.createEntityManager();

		final List<Object[]> result = em.createNamedQuery("Transaction.nonReturns")
				.setParameter("transDate", transDate)
				.getResultList();

		if (result != null && result.size() > 0) {
			System.out.println("result in service is " + String.valueOf(result.get(0)));
			return result;
		} else {
			System.out.println("null return");
			return null;
		}
	}
}
