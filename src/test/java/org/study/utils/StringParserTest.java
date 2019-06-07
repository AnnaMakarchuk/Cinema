package org.study.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class StringParserTest {

    private StringParser stringParser = StringParser.getInstance();

    @Test
    public void shouldCheckNameSurname() {
        boolean test1 = stringParser.checkNameSurname("Anna");
        boolean test2 = stringParser.checkNameSurname("123");
        boolean test3 = stringParser.checkNameSurname("ggg");
        boolean test4 = stringParser.checkNameSurname("Annsss11a");
        boolean test5 = stringParser.checkNameSurname("Анна");


        assertTrue("name 'Anna' isn't correct", test1);
        assertFalse("name '123' is correct", test2);
        assertFalse("name 'ggg' is correct", test3);
        assertFalse("name 'Annsss11a' is correct", test4);
        assertTrue("name 'Анна' isn't correct", test5);
    }

    @Test
    public void checkLoginPassword() {
        boolean test1 = stringParser.checkLoginPassword("anna");
        boolean test2 = stringParser.checkLoginPassword("123");
        boolean test3 = stringParser.checkLoginPassword("Annss__s11a");
        boolean test4 = stringParser.checkLoginPassword("__aa 1A");
        boolean test5 = stringParser.checkLoginPassword("Аннfgtа");

        assertTrue("login and password 'anna' isn't correct", test1);
        assertTrue("login and password '123' isn't correct", test2);
        assertTrue("login and password 'Annss__s11a' isn't correct", test3);
        assertFalse("login and password '__aa 1A' is correct", test4);
        assertTrue("login and password 'Аннfgtа' isn't correct", test5);
    }

    @Test
    public void checkEMail() {
        boolean test1 = stringParser.checkEMail("anna123@gmail.com");
        boolean test2 = stringParser.checkEMail("__anna123@gmail.com");
        boolean test3 = stringParser.checkEMail("12an/_na123@gmail.com");
        boolean test4 = stringParser.checkEMail("Anna123@gmail.com");
        boolean test5 = stringParser.checkEMail("anna123@gmail.hgh");
        boolean test6 = stringParser.checkEMail("anna123@gmailcom");
        boolean test7 = stringParser.checkEMail("anna123@gmail._com");

        assertTrue("email 'anna123@gmail.com' isn't correct", test1);
        assertTrue("email '__anna123@gmail.com' isn't correct", test2);
        assertFalse("email '12an/_na123@gmail.com' is correct", test3);
        assertTrue("email 'Anna123@gmail.com' isn't correct", test4);
        assertFalse("email 'anna123@gmail.hgh' is correct", test5);
        assertFalse("email 'anna123@gmailcom' is correct", test6);
        assertFalse("email 'anna123@gmail._com' isn correct", test7);
    }

    @Test
    public void checkMovieNameDescription() {
        boolean test1 = stringParser.checkMovieNameDescription("Avengers");
        boolean test2 = stringParser.checkMovieNameDescription("100 dalm");
        boolean test3 = stringParser.checkMovieNameDescription("_Once s");
        boolean test4 = stringParser.checkMovieNameDescription("waw_");
        boolean test5 = stringParser.checkMovieNameDescription("U: aw");
        boolean test6 = stringParser.checkMovieNameDescription("aelita");
        boolean test7 = stringParser.checkMovieNameDescription("Космок12ssАФ");

        assertTrue("name 'Avengers' isn't correct", test1);
        assertTrue("name '100 dalm' isn't correct", test2);
        assertFalse("name '_Once s' is correct", test3);
        assertFalse("name 'waw_' is correct", test4);
        assertTrue("name 'U: aw' isn't correct", test5);
        assertFalse("name 'aelita' is correct", test6);
        assertTrue("name 'Космок12ssАФ' isn't correct", test7);
    }

    @Test
    public void checkMovieDuration() {
        boolean test1 = stringParser.checkMovieDuration("123");
        boolean test2 = stringParser.checkMovieDuration("5556");
        boolean test3 = stringParser.checkMovieDuration("89");
        boolean test4 = stringParser.checkMovieDuration("980");
        boolean test5 = stringParser.checkMovieDuration("05");

        assertTrue("duration '123' isn't correct", test1);
        assertFalse("duration '5556' is correct", test2);
        assertTrue("duration '89' isn't correct", test3);
        assertFalse("duration '980' is correct", test4);
        assertFalse("duration '05' is correct", test5);
    }

    @Test
    public void checkMovieAge() {
        boolean test1 = stringParser.checkMovieAge("123");
        boolean test2 = stringParser.checkMovieAge("0");
        boolean test3 = stringParser.checkMovieAge("16");
        boolean test4 = stringParser.checkMovieAge("25");

        assertFalse("age '123' is correct", test1);
        assertTrue("age '0' isn't correct", test2);
        assertTrue("age '16' isn't correct", test3);
        assertFalse("age '25' is correct", test4);
    }
}
