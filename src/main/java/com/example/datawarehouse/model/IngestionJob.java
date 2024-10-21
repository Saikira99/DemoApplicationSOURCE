package com.example.datawarehouse.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngestionJob {
    private Long jobId;
    private Long sourceDataSetId;
    private Long targetDataSetId;
}
