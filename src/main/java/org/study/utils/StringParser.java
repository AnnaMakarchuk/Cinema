package org.study.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringParser {

    private static final StringParser STRING_PARSER = new StringParser();

    private StringParser() {
    }

    public static StringParser getInstance() {
        return STRING_PARSER;
    }

    /**
     * method check name and surname on correct input
     */
    public boolean checkNameSurname(String checkString) {
        String nameSurnameRegex = "[A-Z\\u0400-\\u04FF][a-z\\u0400-\\u04FF]+";
        return match(checkString, nameSurnameRegex);
    }

    /**
     * method check login and password on correct input
     */
    public boolean checkLoginPassword(String checkString) {
        String loginPasswordRegex = "[.\\S\\u0400-\\u04FF]+";
        return match(checkString, loginPasswordRegex);
    }

    /**
     * method check e-mail on correct input
     */
    public boolean checkEMail(String checkString) {
        String emailRegex = "[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}((net)|(com)|(org)|(ua))";
        return match(checkString, emailRegex);
    }

    /**
     * method check movie name and description on correct input
     */
    public boolean checkMovieNameDescription(String checkString) {
        String movieNameDescriptionRegex =
                "[A-Z\\d\\u0400-\\u04FF][\\u0400-\\u04FF\\w\\s\\u002e\\u002c\\u002d\\u003a\\u005f\\u0021\\u003f]+";
        return match(checkString, movieNameDescriptionRegex);
    }

    /**
     * method check movie duration on correct input
     */
    public boolean checkMovieDuration(String checkString) {
        String movieDurationRegex = "[1-2][\\d]{1,2}|[1-9][\\d]";
        return match(checkString, movieDurationRegex);
    }

    /**
     * method check movie age on correct input
     */
    public boolean checkMovieAge(String checkString) {
        String movieAgeRegex = "[0-9]{1}|[1][\\d]";
        return match(checkString, movieAgeRegex);
    }

    private boolean match(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }
}
