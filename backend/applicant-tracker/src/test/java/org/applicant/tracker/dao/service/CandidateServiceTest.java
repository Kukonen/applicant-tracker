package org.applicant.tracker.dao.service;

import org.applicant.tracker.dao.dto.Candidate;
import org.applicant.tracker.dao.exceptions.DatabaseException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CandidateServiceTest {

    @Autowired
    CandidateService candidateService;

    Random random = new Random();

    private final Logger logger = LoggerFactory.getLogger(ProgramService.class);

    Candidate candidate1 = new Candidate(0L, 9L, 22L, 255, null, false);

    @Test
    void insertAndGetLastShouldBeEqualInitialInsert() throws DatabaseException {

        Candidate candidate = candidate1;

        // Просто пробуем вставить запись в бд, и проверяем её присутствие в БД
        candidateService.insert(candidate);

        Candidate actual;

        try {
            actual = getLast();
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        logger.info("\ninitial: " + candidate.toString() + "\nactual: " + actual.toString());
        logger.info("Whole table: " + getAll().toString());
        assertEquals(candidate, actual);

        candidateService.deleteById(candidate.getId());

        logger.info("Whole table: " + getAll().toString());

    }

    @Test
    void getByNonExistingSnilsShouldThrewException() {

        String snils = "haha this snils can not exist!";

        assertThrows(DatabaseException.class, () -> candidateService.getBySnils(snils));

    }

    @Test
    void deletedAnyEntityShouldNotBeInDB() throws DatabaseException {

        // Просто удаляем рандомную запись и смотрим, чтобы её не было в БД
        Candidate deleting;
        try {
            deleting = getRandom();
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        candidateService.deleteAll(deleting);

        ArrayList<Candidate> candidates = getAll();

        assertFalse(candidates.contains(deleting));


        logger.info("Whole table: " + getAll().toString());

        // После проверки добавляем назад, чтобы не остаться без записей
        candidateService.insert(deleting);

        logger.info("Whole table: " + getAll().toString());

    }

    @Test
    void deletingNonExistingShouldThrewException() throws DatabaseException {

        // При попытке удаления несуществующей записи по id должны получить DatabaseException
        assertThrows(DatabaseException.class, () -> candidateService.deleteById(-666L));

    }

    @Test
    void updatedAndGottenShouldBeEqualUpdated() throws DatabaseException {

        Candidate initial;
        try {
            initial = getRandom(); // Берём случайную запись
        } catch (DatabaseException e) {
            fail(e);
            return;
        }

        // Генерируем обновлённую на основе имеющейся (рандомим places)
        Candidate updated = new Candidate(initial.getId(), initial.getApplicantId(), initial.getProgramId(), random.nextInt(400), random.nextInt(5), initial.isCertificateSubmitted());

        logger.info("Whole table: " + getAll().toString());

        // Используем сервис и обновляем запись
        candidateService.update(updated);

        Candidate actual;
        try {
            actual = candidateService.getById(updated.getId());
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

    Candidate getLast() throws DatabaseException {
        ArrayList<Candidate> candidates = getAll();
        if (candidates.size() < 1) {
            throw new DatabaseException("Table is empty!");
        }
        return candidates.get(candidates.size() - 1);
    }


    ArrayList<Candidate> getAll() throws DatabaseException {
        return (ArrayList<Candidate>) candidateService.getAll();
    }

    Candidate getRandom() throws DatabaseException {
        ArrayList<Candidate> candidates = getAll();
        if (candidates.size() < 1) {
            throw new DatabaseException("Table is empty!");
        }
        return candidates.get(random.nextInt(candidates.size()));
    }



}