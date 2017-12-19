package com.webineering.jsf.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;

import com.webineering.jsf.bean.Equipment;
import com.webineering.jsf.service_bean.EntityManagerFactoryBean;

public class EquipmentService {

	private EntityManagerFactory factory;

	public EquipmentService(final EntityManagerFactoryBean factoryBean) {
		this.factory = factoryBean.getEntityManagerFactory();
	}

	public List<Object[]> getAllEquipment() {
		final EntityManager em = factory.createEntityManager();
		final CriteriaBuilder cb = em.getCriteriaBuilder();
		final List<Object[]> result = em.createNamedQuery("Equipment.findAll").getResultList();

		if (result != null && result.size() > 0) {
			// System.out.println("equip id in service is " + result.get(0).getEquipmentId());
			// em.close();
			return result;
		} else {
			System.out.println("null return");
			return null;
		}

	}

	public Equipment addEquipment(final Equipment equipment) {
		System.out.println("inside equipmentService: " + equipment.getEquipmentName());
		final EntityManager em = factory.createEntityManager();
		if (em == null) {
			System.out.println("entity manager is null");
			throw new IllegalArgumentException();
		}
		final EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.persist(equipment);
		txn.commit();
		return equipment;
	}

	public Equipment updateEquipment(final Equipment equipment) {
		final EntityManager em = factory.createEntityManager();
		final EntityTransaction txn = em.getTransaction();
		txn.begin();
		em.merge(equipment);
		txn.commit();
		return equipment;
	}

	public void deleteEquipment(final Equipment equipment) {
		final EntityManager em = factory.createEntityManager();
		final EntityTransaction txn = em.getTransaction();
		txn.begin();
		final Equipment foundEquipment = em.find(Equipment.class, equipment.getId());
		em.remove(foundEquipment);
		txn.commit();
	}

	public Equipment getEquipment(final int id) {
		final EntityManager em = factory.createEntityManager();
		return em.find(Equipment.class, id);
	}

}
