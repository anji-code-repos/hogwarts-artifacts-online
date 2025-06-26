package com.practice.hard.hogwarts_artifacts_online.system.exception;

public class ObjectNotFoundException extends RuntimeException{

    public ObjectNotFoundException(String object, Integer id) {
        super("Could not find " + object + " with Id " + id);
    }

    public ObjectNotFoundException(String object, String id) {
        super("Could not find " + object + " with Id " + id);
    }
}
