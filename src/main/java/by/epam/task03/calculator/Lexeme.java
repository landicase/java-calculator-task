package by.epam.task03.calculator;

import java.util.Objects;

class Lexeme {

    private LexemeType type;
    private String value;

    Lexeme(LexemeType type, char value) {
        this.type = type;
        this.value = Character.toString(value);
    }

    Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    LexemeType getType() {
        return type;
    }

    String getValue() {
        return value;
    }
}