package com.ikoinaris.matchbet.exception;

public class InvalidMatchIdException extends RuntimeException{

    public InvalidMatchIdException(int matchId) {
        System.out.println(String.format("No match found with id %d", matchId));
    }
}
