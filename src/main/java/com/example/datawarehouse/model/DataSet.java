package com.example.datawarehouse.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DataSet {
    private Long datasetId;
    private String datasetName;
    private String datasetType;
    private String databaseName;
    private String schemaName;
    private String sourceType;
}
