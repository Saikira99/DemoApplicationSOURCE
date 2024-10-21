package com.example.datawarehouse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DataSetDTO {

    private UUID datasetId; // Change to UUID
    private String datasetName;
    private String datasetType;
    private String databaseName;
    private String schemaName;
    private String sourceType;

    public DataSetDTO(String schemaName, UUID datasetId, String datasetName, String datasetType, String databaseName, String sourceType) {
        this.schemaName = schemaName;
        this.datasetId = datasetId;
        this.datasetName = datasetName;
        this.datasetType = datasetType;
        this.databaseName = databaseName;
        this.sourceType = sourceType;
    }
}
