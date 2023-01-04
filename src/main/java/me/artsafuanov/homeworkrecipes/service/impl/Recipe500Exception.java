package me.artsafuanov.homeworkrecipes.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class Recipe500Exception extends RuntimeException {
    public Recipe500Exception (String message) {
        super(StringUtils.strip(message));
    }
}