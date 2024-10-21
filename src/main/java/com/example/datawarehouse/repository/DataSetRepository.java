package com.example.datawarehouse.repository;

import com.example.datawarehouse.dto.DataSetDTO;
import com.example.datawarehouse.dto.IngestionJobDTO;
import com.example.datawarehouse.dto.SourceDestinationMappingDTO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class DataSetRepository {
    private final JdbcTemplate jdbcTemplate;

    public DataSetRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveDataSet(DataSetDTO dataSetDTO) {
        String sql = "INSERT INTO DATASET (DATASET_ID, DATASET_NAME, DATASET_TYPE, DATABASE_NAME, SCHEMA_NAME, SOURCE_TYPE) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, dataSetDTO.getDatasetId(), dataSetDTO.getDatasetName(),
                dataSetDTO.getDatasetType(), dataSetDTO.getDatabaseName(),
                dataSetDTO.getSchemaName(), dataSetDTO.getSourceType());
    }

    public void createIngestionJob(IngestionJobDTO ingestionJobDTO) {
        String sql = "INSERT INTO INGESTION_JOB (JOB_ID, SOURCE_DATASET_ID, SINK_DATASET_ID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ingestionJobDTO.getJobId(), ingestionJobDTO.getSourceDatasetId(), ingestionJobDTO.getSinkDatasetId());
    }

    public List<IngestionJobDTO> findIngestionJobsWithNullSink(String datasetName) {
        String sql = "SELECT * FROM INGESTION_JOB WHERE SINK_DATASET_ID IS NULL AND SOURCE_DATASET_ID IN (SELECT DATASET_ID FROM DATASET WHERE DATASET_NAME = ?)";
        return jdbcTemplate.query(sql, new Object[]{datasetName}, (rs, rowNum) -> {
            UUID jobId = (UUID) rs.getObject("JOB_ID");
            UUID sourceDatasetId = (UUID) rs.getObject("SOURCE_DATASET_ID");
            UUID sinkDatasetId = (UUID) rs.getObject("SINK_DATASET_ID");
            return new IngestionJobDTO(jobId, sourceDatasetId, sinkDatasetId);
        });
    }

    public void updateIngestionJob(IngestionJobDTO ingestionJobDTO) {
        String sql = "UPDATE INGESTION_JOB SET SINK_DATASET_ID = ? WHERE JOB_ID = ?";
        jdbcTemplate.update(sql, ingestionJobDTO.getSinkDatasetId(), ingestionJobDTO.getJobId());
    }

    public void createSourceDestinationMapping(SourceDestinationMappingDTO mappingDTO) {
        String sql = "INSERT INTO SOURCE_DESTINATION_MAPPING (JOB_ID, SOURCE_FIELD_ID, SINK_FIELD_ID) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, mappingDTO.getJobId(), mappingDTO.getSourceFieldId(), mappingDTO.getSinkFieldId());
    }

    public void insertDataSetFields(UUID datasetId, String fieldName, String fieldType, int fieldLength, int piiData, int isActive) {
        String sql = "INSERT INTO DATASET_FIELDS (DATASET_ID, FIELD_NAME, FIELD_TYPE, FIELD_LENGTH, PII_DATA, IS_ACTIVE) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, datasetId, fieldName, fieldType, fieldLength, piiData, isActive);
    }
}
