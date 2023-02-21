package com.giacomini.estimateshub.exception;

public class ProductNotFoundException extends EntityNotFoundException{

    public ProductNotFoundException(String message){
        super(message);
    }
}
