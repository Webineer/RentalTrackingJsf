package com.webineering.jsf.service_bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ManagedBean(name = "emFactoryBean", eager = true)
@ApplicationScoped
public class EntityManagerFactoryBean {

	private EntityManagerFactory entityManagerFactory;

	public EntityManagerFactoryBean() {
		entityManagerFactory = Persistence.createEntityManagerFactory("rental_tracking_jsf");
	}

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(final EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

}
