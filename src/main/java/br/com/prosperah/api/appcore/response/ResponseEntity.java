package br.com.prosperah.api.appcore.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseEntity<T> {
    private T data;
    private String message;
    private int statusCode;

    public ResponseEntity(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }
}
