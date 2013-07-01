package org.motechproject.carereporting.exception;

import org.springframework.validation.ObjectError;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CareApiRuntimeException extends RuntimeException {

    private List<ObjectError> apiErrors;

    public Map<String, Object> getErrors() {
        Map<String, Object> map = new LinkedHashMap<>();

        for (ObjectError apiError : apiErrors) {
            map.put("objectName", apiError.getObjectName());
            map.put("code", apiError.getCodes()[0]);
            map.put("defaultMessage", apiError.getDefaultMessage());
        }

        return map;
    }

    public CareApiRuntimeException(Throwable cause) {
        super(cause);
    }

    public CareApiRuntimeException(List<ObjectError> apiErrors) {
        super(apiErrors.toString());
        this.apiErrors = apiErrors;
    }
}