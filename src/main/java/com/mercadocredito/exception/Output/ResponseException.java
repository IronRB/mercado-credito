package com.mercadocredito.exception.Output;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseException {
    private int status;
    private String title;
    private String message;
}
