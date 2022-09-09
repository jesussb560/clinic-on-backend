package com.app.clinicon.converter;

import javax.persistence.AttributeConverter;

public class TrimConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return attribute.trim();
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return dbData.trim();
    }
    
}
