package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.Applicant;
import org.applicant.tracker.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;
import  org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicantServiceTest {

    @Autowired
    ApplicantService applicantService;

    Random random = new Random();

    private final Logger logger = LoggerFactory.getLogger(ApplicantService.class);

    Applicant applicant1 = new Applicant(0L, "123.123.123 12", "Valer Valerovich");
    Applicant applicant2 = new Applicant(0L, "123.123.125 12", "Volodya");
    Applicant applicant3 = new Applicant(0L, "166.777.777 11", null);

    @Test
    void insertAndGetLastShouldBeEqualInitialInsert() throws DatabaseException {

        Applicant applicant = new Applicant(0L, "122.567.999 56", "EvGENIY");

        // Просто пробуем вставить запись в бд, и проверяем её присутствие в БД
        applicantService.insert(applicant);

        Applicant actual;

        try {
            actual = getLast();
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        logger.info("\ninitial: " + applicant.toString() + "\nactual: " + actual.toString());
        logger.info("Whole table: " + getAll().toString());
        assertEquals(applicant, actual);

        applicantService.deleteById(applicant.getId());

        logger.info("Whole table: " + getAll().toString());

    }

    @Test
    void deletedAnyEntityShouldNotBeInDB() throws DatabaseException {

        // Просто удаляем рандомную запись и смотрим, чтобы её не было в БД
        Applicant deleting;
        try {
            deleting = getRandom();
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        applicantService.deleteAll(deleting);

        ArrayList<Applicant> applicants = getAll();

        assertFalse(applicants.contains(deleting));


        logger.info("Whole table: " + getAll().toString());

        // После проверки добавляем назад, чтобы не остаться без записей
        applicantService.insert(deleting);

        logger.info("Whole table: " + getAll().toString());

    }

    @Test
    void deletingNonExistingShouldThrewException() throws DatabaseException {

        // Несуществующая в БД запись!
        Applicant deleting = new Applicant(-1L, "its non existing men!", "noname");

        // При попытке удаления несуществующей записи должны получить DatabaseException
        assertThrows(DatabaseException.class, () -> applicantService.deleteAll(deleting));

        // При попытке удаления несуществующей записи по id должны получить DatabaseException
        assertThrows(DatabaseException.class, () -> applicantService.deleteById(-666L));

    }
    
    @Test
    void updatedAndGottenShouldBeEqualUpdated() throws DatabaseException {

        Applicant initial;
        try {
            initial = getRandom(); // Берём случайную запись
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        // Генерируем обновлённую на основе имеющейся (рандомим snils)
        Applicant updated = new Applicant(initial.getId(), String.valueOf(random.nextInt(100)), initial.getName());

        logger.info("Whole table: " + getAll().toString());

        // Используем сервис и обновляем запись
        applicantService.update(updated);

        Applicant actual;
        try {
            actual = applicantService.getById(updated.getId());
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        // Проверка будет успешна, если запись обновилась в таблице
        assertEquals(updated, actual);
        // И старой записи нет в таблице
        try {
            assertFalse(getAll().contains(initial));
        } catch (DatabaseException e) {
            throw new RuntimeException(e);
        }

        logger.info("Whole table: " + getAll().toString());

    }

    Applicant getLast() throws DatabaseException {
        ArrayList<Applicant> applicants = getAll();
        if (applicants.size() < 1) {
            throw new DatabaseException("Table is empty!");
        }
        return applicants.get(applicants.size() - 1);
    }


    ArrayList<Applicant> getAll() throws DatabaseException {
        return (ArrayList<Applicant>) applicantService.getAll();
    }

    Applicant getRandom() throws DatabaseException {
        ArrayList<Applicant> applicants = getAll();
        if (applicants.size() < 1) {
            throw new DatabaseException("Table is empty!");
        }
        return applicants.get(random.nextInt(applicants.size()));
    }

}