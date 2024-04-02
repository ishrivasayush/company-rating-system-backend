package com.narainox.jobApplication.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordNotFoundException extends RuntimeException {
    private String record;
    private String type;
    private Object value;

    public RecordNotFoundException(String record, String type, Object value) {
        super(String.format("%s not found with %s=%s",record,type,value));
        this.record = record;
        this.type = type;
        this.value = value;
    }
}
