package me.artsafuanov.homeworkrecipes.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RecipeException extends RuntimeException {
    public RecipeException (String message) {
        super(StringUtils.strip(message));
    }
}
