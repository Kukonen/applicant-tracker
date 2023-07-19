package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.Applicant;
import org.applicant.tracker.dao.dto.Program;
import org.applicant.tracker.enums.EducationForm;
import org.applicant.tracker.enums.PaymentType;
import org.applicant.tracker.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProgramServiceTest {

    @Autowired
    ProgramService programService;

    Random random = new Random();

    private final Logger logger = LoggerFactory.getLogger(ProgramService.class);

    Program program1 = new Program(0L, "ГУАП", 100, "Прогинж", EducationForm.MIXED, PaymentType.CONTRACT);
    Program program2 = new Program(0L, "ЛЭТИ", 50, "Прогинж", EducationForm.EXTRAMURAL, PaymentType.BUDGET);
    Program program3 = new Program(0L, "ЧВК (челябинский военный колледж)", 3000, "убийца детей", EducationForm.FULL_TIME, PaymentType.TARGETED);

    @Test
    void insertAndGetLastShouldBeEqualInitialInsert() throws DatabaseException {

        Program program = program2;

        // Просто пробуем вставить запись в бд, и проверяем её присутствие в БД
        programService.insert(program);

        Program actual;

        try {
            actual = getLast();
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        logger.info("\ninitial: " + program.toString() + "\nactual: " + actual.toString());
        logger.info("Whole table: " + getAll().toString());
        assertEquals(program, actual);

        programService.deleteById(program.getId());

        logger.info("Whole table: " + getAll().toString());

    }

    @Test
    void deletedAnyEntityShouldNotBeInDB() throws DatabaseException {

        // Просто удаляем рандомную запись и смотрим, чтобы её не было в БД
        Program deleting;
        try {
            deleting = getRandom();
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        programService.deleteAll(deleting);

        ArrayList<Program> programs = getAll();

        assertFalse(programs.contains(deleting));


        logger.info("Whole table: " + getAll().toString());

        // После проверки добавляем назад, чтобы не остаться без записей
        programService.insert(deleting);

        logger.info("Whole table: " + getAll().toString());

    }

    @Test
    void deletingNonExistingShouldThrewException() throws DatabaseException {

        // При попытке удаления несуществующей записи по id должны получить DatabaseException
        assertThrows(DatabaseException.class, () -> programService.deleteById(-666L));

    }

    @Test
    void updatedAndGottenShouldBeEqualUpdated() throws DatabaseException {

        Program initial;
        try {
            initial = getRandom(); // Берём случайную запись
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        // Генерируем обновлённую на основе имеющейся (рандомим places)
        Program updated = new Program(initial.getId(), initial.getUniversity(), random.nextInt(1000), initial.getName(), initial.getForm(), initial.getType());

        logger.info("Whole table: " + getAll().toString());

        // Используем сервис и обновляем запись
        programService.update(updated);

        Program actual;
        try {
            actual = programService.getById(updated.getId());
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

    Program getLast() throws DatabaseException {
        ArrayList<Program> programs = getAll();
        if (programs.size() < 1) {
            throw new DatabaseException("Table is empty!");
        }
        return programs.get(programs.size() - 1);
    }


    ArrayList<Program> getAll() throws DatabaseException {
        return (ArrayList<Program>) programService.getAll();
    }

    Program getRandom() throws DatabaseException {
        ArrayList<Program> programs = getAll();
        if (programs.size() < 1) {
            throw new DatabaseException("Table is empty!");
        }
        return programs.get(random.nextInt(programs.size()));
    }


}