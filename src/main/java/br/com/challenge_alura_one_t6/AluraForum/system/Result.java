package br.com.challenge_alura_one_t6.AluraForum.system;

import br.com.challenge_alura_one_t6.AluraForum.entities.Course;
import lombok.Data;

@Data
public class Result {
    private boolean flag;
    private Integer code;
    private String message;
    private Object data;

    public Result(boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }
}
