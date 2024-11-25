package com.ikoinaris.matchbet.exception;

public class InvalidSpecifierException extends  RuntimeException{

    public InvalidSpecifierException() {
        System.out.println("A specifier must be have of the following values: x, 1, 2");
    }
}
