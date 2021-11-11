package by.epam.task03.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class LexemeList {
    private List<Lexeme> lexemes;
    private int index = 0;

    LexemeList(List<Lexeme> expression) {
        this.lexemes = expression;
    }

    List<Lexeme> getLexemesList() {
        return lexemes;
    }

    int getIndex() {
        return index;
    }

    Lexeme getNext() {
        return lexemes.get(index++);
    }

    void goBack() {
        if (index > 0)
            index--;
    }

    static List<Lexeme> getLexemes(String expression) {
        List<Lexeme> lexemes = new ArrayList<>();
        int position = 0;
        while (position < expression.length()) {
            char character = expression.charAt(position);
            switch (character) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, character));
                    position++;
                    break;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, character));
                    position++;
                    break;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.PLUS, character));
                    position++;
                    break;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.MINUS, character));
                    position++;
                    break;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.MUL, character));
                    position++;
                    break;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.DIV, character));
                    position++;
                    break;
                default:
                    if (character >= '0' && character <= '9') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(character);
                            position++;
                            if (position >= expression.length()) {
                                break;
                            }
                            character = expression.charAt(position);
                        } while (character >= '0' && character <= '9');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (character != ' ') {
                            throw new RuntimeException("Unexpected character: " + character);
                        }
                        position++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }
}