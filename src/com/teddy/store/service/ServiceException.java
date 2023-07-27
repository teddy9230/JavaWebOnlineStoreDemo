package com.teddy.store.service;

public class ServiceException extends Exception{

    public ServiceException(String meg){
        super(meg);
    }

    public ServiceException(String meg, Throwable ex){
        super(meg, ex);
    }
}
