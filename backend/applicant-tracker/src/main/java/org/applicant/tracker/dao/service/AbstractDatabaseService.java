package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.IEntity;
import org.applicant.tracker.dao.exceptions.DuplicatedRecordsDatabaseException;
import org.applicant.tracker.dao.exceptions.InternalDatabaseException;
import org.applicant.tracker.dao.mapper.IMapper;
import org.applicant.tracker.dao.exceptions.DatabaseException;
import org.applicant.tracker.dao.exceptions.NoContentDatabaseException;

import java.util.List;


/** CRUD-full сервис с основными методами и их реализациями на generic-types */
public abstract class AbstractDatabaseService<Mapper extends IMapper<Entity>, Entity extends IEntity> {

    protected Mapper mapper;

    public List<Entity> getAll() {
        return mapper.getAll();
    }

    public Entity getById(Long id) throws DatabaseException {
        Entity entity = mapper.getById(id);

        if (entity == null) {
            throw new NoContentDatabaseException("В таблице отсутствует запись с таким id!");
        }

        return entity;
    }

    public void insert(Entity entity) throws DatabaseException {
        try {
            // Если мы добавили 0 записей, то кидаем исключение
            if (mapper.insert(entity) == 0) {
                throw new InternalDatabaseException("Запись не была добавлена в таблицу по внутренней ошибке");
            }
        } catch (org.springframework.dao.DuplicateKeyException e) {
            throw new DuplicatedRecordsDatabaseException("Запись не была добавлена в таблицу по причине дублирования ключей, отмеченных как уникальные: " + e);
        }
    }

    public void deleteById(Long id) throws DatabaseException {
        // Если мы удалили 0 записей, то кидаем исключение
        if (mapper.deleteById(id) == 0) {
            throw new NoContentDatabaseException("Запись не была удалена по причине отсутствия данных с таким id!");
        }
    }

    public void deleteAll(Entity entity) throws DatabaseException {
        // Если мы удалили 0 записей, то кидаем исключение
        if (mapper.delete(entity) == 0) {
            throw new NoContentDatabaseException("Запись не была удалена по причине отсутствия таких данных!");
        }
    }

    public void update(Entity entity) throws DatabaseException {
        // Если мы обновили 0 записей, то кидаем исключение
        if (mapper.update(entity) == 0) {
            throw new NoContentDatabaseException("Запись не была обновлена по причине отсутствия таких данных!");
        }
    }

}
