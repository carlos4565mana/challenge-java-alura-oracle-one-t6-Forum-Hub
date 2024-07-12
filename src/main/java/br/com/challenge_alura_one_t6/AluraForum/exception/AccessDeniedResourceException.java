package br.com.challenge_alura_one_t6.AluraForum.exception;

public class AccessDeniedResourceException extends  RuntimeException{
    public AccessDeniedResourceException(String message){
        super("Acess Denied " + message);
    }
}
