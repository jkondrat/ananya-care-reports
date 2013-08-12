package org.motechproject.carereporting.service.impl;

import org.apache.commons.io.IOUtils;
import org.motechproject.carereporting.domain.IndicatorValueEntity;
import org.motechproject.carereporting.exception.CareNoValuesException;
import org.motechproject.carereporting.export.csv.CsvExportHelper;
import org.motechproject.carereporting.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CsvExportServiceImpl implements ExportService {

    @Autowired
    private CsvExportHelper csvExportHelper;

    @Override
    public byte[] convertIndicatorValuesToBytes(List<IndicatorValueEntity> indicatorValues) throws IOException {
        if(indicatorValues.size() == 0) {
            throw new CareNoValuesException();
        }

        List<String[]> lines = prepareCsvLines(indicatorValues);

        byte[] bytes;
        try(ByteArrayInputStream inputStream = csvExportHelper.convertToCsvFile(lines)) {
            bytes = IOUtils.toByteArray(inputStream);
        }

        return bytes;
    }

    private List<String[]> prepareCsvLines(List<IndicatorValueEntity> indicatorValues) {
        List<String[]> csvLines = new ArrayList<String[]>();
        for (IndicatorValueEntity indicatorValue : indicatorValues) {
            List<String> line = new ArrayList<String>();
            line.add(indicatorValue.getArea().getName());
            line.add(indicatorValue.getFrequency().getFrequencyName());
            line.add(indicatorValue.getValue().toString());
            csvLines.add(line.toArray(new String[line.size()]));
        }
        return csvLines;
    }

}