package org.applicant.tracker.util;

import org.applicant.tracker.front.exceptions.InvalidSnilsException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicantUtilTest {

    @Autowired
    ApplicantUtil applicantUtil;

    @Test
    void initiallyFormattedSnilsShouldBeEqualToFormatted() {

        String formatted = "12312312367";


        String unFormatted = "123-123-123 67";

        assertEquals(formatted, applicantUtil.formatSnils(unFormatted));

    }

    @Test
    void invalidSnilsShouldThrewException() {

        assertThrows(InvalidSnilsException.class, () -> applicantUtil.checkAndFormatSnils("123-123-145 0"));

    }

    @Test
    void formattedSnilsWithLettersShouldBeEqualsInitialFormatted() {

        String formatted = "12312312367";


        String unFormatted = "ThIs &^!)@_#* is }{| test +++ 123cho cho-123-123!!! 67 abs";

        assertEquals(formatted, applicantUtil.formatSnils(unFormatted));

    }

}