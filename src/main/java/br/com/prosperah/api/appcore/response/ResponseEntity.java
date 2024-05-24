package br.com.prosperah.api.appcore.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseEntity<T> {
    private T data;
    private String message;
    private int statusCode;
}
