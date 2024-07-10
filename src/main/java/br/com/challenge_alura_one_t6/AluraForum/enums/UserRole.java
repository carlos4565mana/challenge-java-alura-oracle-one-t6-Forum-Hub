package br.com.challenge_alura_one_t6.AluraForum.enums;

public enum UserRole {

    ADMIN("admin"),
    USER("user");

    private String role;

    UserRole (String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
