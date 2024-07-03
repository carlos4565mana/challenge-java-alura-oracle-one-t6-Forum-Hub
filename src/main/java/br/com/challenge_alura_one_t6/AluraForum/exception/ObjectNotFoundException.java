package br.com.challenge_alura_one_t6.AluraForum.exception;

public class ObjectNotFoundException extends  RuntimeException{

    public ObjectNotFoundException(String objectName, String id) {
        super("Could not find " + objectName + " with Id " + id + " :(");
    }

    public ObjectNotFoundException(String objectName, Long id) {
        super("Could not find " + objectName + " with Id " + id + " :(");
    }
}
