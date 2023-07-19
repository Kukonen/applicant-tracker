package org.applicant.tracker.dao.mapper;

import org.applicant.tracker.dao.dto.IEntity;

import java.util.List;


public interface IMapper<Entity extends IEntity> {

    List<Entity> getAll();

    Entity getById(Long id);

    int insert(Entity entity);

    int deleteById(Long id);

    int delete(Entity entity);

    /** Обновляет данные в базе и возвращает число обновлённых записей */
    int update(Entity entity);

}
