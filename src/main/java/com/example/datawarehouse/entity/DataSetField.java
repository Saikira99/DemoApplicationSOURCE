package com.example.datawarehouse.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataSetField {
    private Long fieldId;
    private Long datasetId;
    private String fieldName;
    private String fieldType;
    private int fieldLength;
    private int piiData; // 0/1 flag
    private int isActive; // 0/1 flag

    // Getters and Setters
}
