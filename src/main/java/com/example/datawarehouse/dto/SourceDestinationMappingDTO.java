package com.example.datawarehouse.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SourceDestinationMappingDTO {

    private UUID jobId;
    private UUID sourceFieldId;
    private UUID sinkFieldId;

    public SourceDestinationMappingDTO(UUID jobId, UUID sourceFieldId, UUID sinkFieldId) {
        this.jobId = jobId;
        this.sourceFieldId = sourceFieldId;
        this.sinkFieldId = sinkFieldId;
    }
}
