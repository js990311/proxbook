package com.proxbook.finder.csv.importer.extractor;

import com.proxbook.finder.csv.importer.CsvEntityType;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CsvExtractorManager {
    private final List<AbstractCsvExtractor> csvExtractors;

    public Optional<CsvExtractResult> extract(CsvEntityType type, Resource resource) {
        for(AbstractCsvExtractor csvExtractor : csvExtractors){
            if(csvExtractor.isSupport(type)){
                return Optional.of(csvExtractor.extract(resource));
            }
        }
        return Optional.empty();
    }
}
