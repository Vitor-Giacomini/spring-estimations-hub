package com.giacomini.estimateshub.exception;

public class ProductAlreadyExistsException extends EntityAlreadyExistsException{

    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
