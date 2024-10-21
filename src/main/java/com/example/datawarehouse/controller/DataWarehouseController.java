package com.example.datawarehouse.controller;

import com.example.datawarehouse.dto.DataSetDTO;
import com.example.datawarehouse.dto.DataSetType;
import com.example.datawarehouse.dto.SaveDataSetsRequest;
import com.example.datawarehouse.service.DataWarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/datasets")
public class DataWarehouseController {

    private final DataWarehouseService dataWarehouseService;

    public DataWarehouseController(DataWarehouseService dataWarehouseService) {
        this.dataWarehouseService = dataWarehouseService;
    }

    @PostMapping
    public ResponseEntity<Void> saveDataSets(@Valid @RequestBody SaveDataSetsRequest request) {
        List<DataSetDTO> dataSetDTOs = request.getDataSetDTOs();

        // Set the dataset type for each DTO
        for (DataSetDTO dto : dataSetDTOs) {
            dto.setDatasetType(request.getType().name());
        }

        if (request.getType() == DataSetType.SOURCE) {
            dataWarehouseService.saveSourceDataSets(dataSetDTOs);
        } else if (request.getType() == DataSetType.TARGET) {
            dataWarehouseService.saveTargetDataSets(dataSetDTOs);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
