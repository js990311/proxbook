package com.proxbook.finder.csv.importer.extractor;

import com.proxbook.finder.csv.importer.CsvEntityType;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RequiredArgsConstructor
public abstract class AbstractCsvExtractor {

    public abstract boolean isSupport(CsvEntityType type);
    public abstract boolean isValid(String[] row);

    public CsvParser getCsvParser(){
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        return new CsvParser(settings);
    }

    public CsvExtractResult extract(Resource resource){
        CsvParser parser = getCsvParser();
        CsvExtractResult ret = new CsvExtractResult();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))){
            for(String[] row : parser.iterate(reader)){
                if(isValid(row)){
                    ret.addValidRecords(row);
                }else {
                    ret.addInValidRecords(row);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    /* Utils */

    protected boolean isBlank(String record){
        return record != null && !record.isBlank();
    }

    protected boolean isInteger(String record){
        try {
            int number = Integer.parseInt(record);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    protected boolean isLong(String record){
        try {
            Long number = Long.parseLong(record);
        }catch (NumberFormatException e){
            return false;
        }
        return true;

    }

    protected boolean isPositiveInteger(String record){
        try{
            int number = Integer.parseInt(record);
            if(number<0){
                return false;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}
