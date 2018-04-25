package com.utn.TP4Monitor;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This Class will be thrown when some object wont be found in a repository
 */
@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFounException extends RuntimeException {
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceNotFounException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("No existe %s con %s : '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
