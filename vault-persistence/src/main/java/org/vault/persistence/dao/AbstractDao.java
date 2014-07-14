package org.vault.persistence.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.vault.base.domain.Identifiable;
import org.vault.base.reflection.VReflection;
import org.vault.base.spring.beans.VaultBeanT1;
import org.vault.persistence.managed.entities.EntityManagerContext;
import org.vault.persistence.utilities.EntityManagers;
import org.vault.persistence.utilities.Queries;
import org.vault.persistence.utilities.QueryQualifier;

public class AbstractDao<T extends Identifiable> extends VaultBeanT1<T> implements Dao<T> {
	@Autowired
	private EntityManagerContext context;

	@Override
	public T create() {
		return VReflection.construct(this.getEntityClassInternal());
	}

	@Override
	public T find(Long id) {
		return getEntityManager().find(this.getEntityClassInternal(), id);
	}

	@Override
	public List<T> findAll() {
		return Queries.select(this.getEntityClassInternal(), getEntityManager()).getResults();
	}

	@Override
	public List<T> findAll(QueryQualifier qualifier) {
		return Queries.select(this.getEntityClassInternal(), qualifier, getEntityManager()).getResults();
	}

	@Override
	public T save(T entity) {
		return getEntityManager().merge(entity);
	}

	@Override
	public void delete(T entity) {
		getEntityManager().remove(entity);
	}

	protected EntityManager getEntityManager() {
		return EntityManagers.findEmForClass(context.getAllEntityManagers(), this.getEntityClassInternal());
	}

	@SuppressWarnings("unchecked")
	private Class<T> getEntityClassInternal() {
		return (Class<T>) this.getEntityClass();
	}

	protected Class<?> getEntityClass() {
		return this.runtimeType1;
	}
}