package com.fluffypets.exeptions;

public class DAOException extends RuntimeException {
    public  DAOException(String message){
        super(message);
    }
}
