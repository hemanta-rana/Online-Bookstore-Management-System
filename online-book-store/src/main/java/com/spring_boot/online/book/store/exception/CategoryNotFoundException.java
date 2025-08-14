package com.spring_boot.online.book.store.exception;

public class CategoryNotFoundException extends  RuntimeException{

    public CategoryNotFoundException(String message){
        super(message);
    }
}
