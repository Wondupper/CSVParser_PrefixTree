package com.example.csv.scan;

import com.example.dto.CSVColumnWord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CSVColumnParser {
    @NonNull
    private Path path;
    private int columnIndex;

    public List<CSVColumnWord> getColumnElements() throws IOException {
        List<CSVColumnWord> columnElements = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            int currentRowIndex = 1;
            while ((line = br.readLine()) != null) {
                String columnElement = Arrays.asList(line.split(", ")).get(columnIndex - 1);
                columnElement = columnElement.replaceAll("\"", "");
                columnElements.add(new CSVColumnWord(columnElement, currentRowIndex));
                currentRowIndex++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return columnElements;
    }
}
