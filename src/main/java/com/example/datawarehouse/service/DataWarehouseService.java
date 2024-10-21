package com.example.datawarehouse.service;

import com.example.datawarehouse.dto.DataSetDTO;
import com.example.datawarehouse.dto.IngestionJobDTO;
import com.example.datawarehouse.dto.SourceDestinationMappingDTO;
import com.example.datawarehouse.repository.DataSetRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DataWarehouseService {
    private final DataSetRepository dataSetRepository;

    public DataWarehouseService(DataSetRepository dataSetRepository) {
        this.dataSetRepository = dataSetRepository;
    }

    @Transactional
    public void saveSourceDataSets(List<DataSetDTO> dataSetDTOs) {
        Map<UUID, DataSetDTO> sourceDatasets = new HashMap<>();

        for (DataSetDTO dataSetDTO : dataSetDTOs) {
            UUID id = UUID.randomUUID();
            dataSetDTO.setDatasetId(id);
            dataSetRepository.saveDataSet(dataSetDTO);
            sourceDatasets.put(dataSetDTO.getDatasetId(), dataSetDTO);

            IngestionJobDTO jobDTO = new IngestionJobDTO(UUID.randomUUID(), dataSetDTO.getDatasetId(), null);
            dataSetRepository.createIngestionJob(jobDTO);
        }
    }

    @Transactional
    public void saveTargetDataSets(List<DataSetDTO> dataSetDTOs) {
        for (DataSetDTO dataSetDTO : dataSetDTOs) {
            UUID id = UUID.randomUUID();
            dataSetDTO.setDatasetId(id);
            dataSetRepository.saveDataSet(dataSetDTO);
            updateIngestionJobs(dataSetDTO);
        }
    }

    private void updateIngestionJobs(DataSetDTO targetDataSetDTO) {
        List<IngestionJobDTO> ingestionJobs = dataSetRepository.findIngestionJobsWithNullSink(targetDataSetDTO.getDatasetName());

        for (IngestionJobDTO job : ingestionJobs) {
            job.setSinkDatasetId(targetDataSetDTO.getDatasetId());
            dataSetRepository.updateIngestionJob(job);
            createColumnMapping(job.getSourceDatasetId(), job.getSinkDatasetId(), job.getJobId());
        }
    }

    private void createColumnMapping(UUID sourceDatasetId, UUID sinkDatasetId, UUID jobId) {
        UUID sourceFieldId = UUID.randomUUID(); // Placeholder for actual source field ID
        UUID sinkFieldId = UUID.randomUUID();   // Placeholder for actual sink field ID

        SourceDestinationMappingDTO mappingDTO = new SourceDestinationMappingDTO(jobId, sourceFieldId, sinkFieldId);
        dataSetRepository.createSourceDestinationMapping(mappingDTO);
        insertDataSetFields(sourceDatasetId);
    }

    private void insertDataSetFields(UUID datasetId) {
        dataSetRepository.insertDataSetFields(datasetId, "exampleFieldName", "string", 255, 0, 1);
    }
}
