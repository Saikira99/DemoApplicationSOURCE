package com.example.datawarehouse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class IngestionJobDTO {

    private UUID jobId;
    private UUID sourceDatasetId;
    private UUID sinkDatasetId;

    public IngestionJobDTO(UUID jobId, UUID sourceDatasetId, UUID sinkDatasetId) {
        this.jobId = jobId;
        this.sourceDatasetId = sourceDatasetId;
        this.sinkDatasetId = sinkDatasetId;
    }
}
