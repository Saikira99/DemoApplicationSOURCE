package com.example.datawarehouse.dto;





import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class SaveDataSetsRequest {
    @NotNull
    private DataSetType type;

    @NotNull
    @Valid
    private List<DataSetDTO> dataSetDTOs;

    public DataSetType getType() {
        return type;
    }

    public void setType(DataSetType type) {
        this.type = type;
    }

    public List<DataSetDTO> getDataSetDTOs() {
        return dataSetDTOs;
    }

    public void setDataSetDTOs(List<DataSetDTO> dataSetDTOs) {
        this.dataSetDTOs = dataSetDTOs;
    }
}
