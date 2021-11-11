package by.epam.task03.calculator;

import java.util.List;

public class Calculator {

    public double calculate(String expression) {
        List<Lexeme> lexemes = LexemeList.getLexemes(expression);
        LexemeList lexemeList = new LexemeList(lexemes);
        return getExpression(lexemeList);
    }

    private static double getExpression(LexemeList lexemes) {
        Lexeme lexeme = lexemes.getNext();
        if (lexeme.getType() == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.goBack();
            return getPlusMinus(lexemes);
        }
    }

    private static double number(LexemeList lexemes) {
        Lexeme lexeme = lexemes.getNext();
        switch (lexeme.getType()) {
            case NUMBER:
                return Integer.parseInt(lexeme.getValue());
            case LEFT_BRACKET:
                double value = getPlusMinus(lexemes);
                lexeme = lexemes.getNext();
                if (lexeme.getType() != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getIndex());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                        + " at position: " + lexemes.getIndex());
        }
    }

    private static double getPlusMinus(LexemeList lexemes) {
        double value = getMulDiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.getNext();
            switch (lexeme.getType()) {
                case PLUS:
                    value += getMulDiv(lexemes);
                    break;
                case MINUS:
                    value -= getMulDiv(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                    lexemes.goBack();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getIndex());
            }
        }
    }
    private static double getMulDiv(LexemeList lexemes) {
        double value = number(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.getNext();
            switch (lexeme.getType()) {
                case MUL:
                    value *= number(lexemes);
                    break;
                case DIV:
                    value /= number(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case PLUS:
                case MINUS:
                    lexemes.goBack();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getIndex());
            }
        }
    }
}