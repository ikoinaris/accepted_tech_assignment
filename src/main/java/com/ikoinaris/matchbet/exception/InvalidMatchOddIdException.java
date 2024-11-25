package com.ikoinaris.matchbet.exception;

public class InvalidMatchOddIdException extends RuntimeException{

    public InvalidMatchOddIdException(int matchOddId) {
        System.out.println(String.format("No Match Odd found with id %d", matchOddId));
    }
}
