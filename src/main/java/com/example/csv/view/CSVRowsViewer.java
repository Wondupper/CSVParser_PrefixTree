package com.example.csv.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class CSVRowsViewer {
    @NonNull
    private Path path;

    public void viewColumnsWithOrder(List<Integer> rowIndexes) {
        String[] rows = new String[rowIndexes.size()];
        int max = Collections.max(rowIndexes);
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            int currentRowIndex = 1;
            while ((line = br.readLine()) != null && currentRowIndex <= max) {
                if (rowIndexes.contains(currentRowIndex)) {
                    rows[rowIndexes.indexOf(currentRowIndex)] = line;
                }
                currentRowIndex++;
            }
            for (String row : rows) {
                System.out.println(row);
            }
            System.out.println("Найдено строк:" + rowIndexes.size());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
