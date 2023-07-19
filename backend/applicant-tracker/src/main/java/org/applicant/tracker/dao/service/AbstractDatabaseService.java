package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.IEntity;
import org.applicant.tracker.dao.mapper.IMapper;
import org.applicant.tracker.exceptions.DatabaseException;

import java.util.List;


/** CRUD-full сервис с основными методами и их реализациями на generic-types */
public abstract class AbstractDatabaseService<Mapper extends IMapper<Entity>, Entity extends IEntity> {

    protected Mapper mapper;

    List<Entity> getAll() {
        return mapper.getAll();
    }

    Entity getById(Long id) throws DatabaseException {
        Entity entity = mapper.getById(id);

        if (entity == null) {
            throw new DatabaseException("В таблице отсутствует запись с таким id!");
        }

        return entity;
    }

    void insert(Entity entity) throws DatabaseException {
        try {
            // Если мы добавили 0 записей, то кидаем исключение
            if (mapper.insert(entity) == 0) {
                throw new DatabaseException("Запись не была добавлена в таблицу по внутренней ошибке");
            }
        } catch (org.springframework.dao.DuplicateKeyException e) {
            throw new DatabaseException("Запись не была добавлена в таблицу по причине дублирования ключей: " + e);
        }
    }

    void deleteById(Long id) throws DatabaseException {
        // Если мы удалили 0 записей, то кидаем исключение
        if (mapper.deleteById(id) == 0) {
            throw new DatabaseException("Запись с таким id не была удалена!");
        }
    }

    void deleteAll(Entity entity) throws DatabaseException {
        // Если мы удалили 0 записей, то кидаем исключение
        if (mapper.delete(entity) == 0) {
            throw new DatabaseException("Запись не была удалена!");
        }
    }

    void update(Entity entity) throws DatabaseException {
        // Если мы обновили 0 записей, то кидаем исключение
        if (mapper.update(entity) == 0) {
            throw new DatabaseException("Запись не была обновлена!");
        }
    }

}
