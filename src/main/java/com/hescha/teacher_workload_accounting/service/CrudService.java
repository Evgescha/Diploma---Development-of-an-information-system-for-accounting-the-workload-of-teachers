package com.hescha.teacher_workload_accounting.service;

import java.util.List;

/**
 * Класс основных операций с бд
 * @param <Entity> Сущность (таблиа), с которой будут проводить операции
 */
public interface CrudService<Entity> {

	boolean create(Entity entity);

	Entity read(long id);

	List<Entity> readAll();

	boolean update(Entity entity);

	boolean delete(long id);
}