package com.example.structs;

import com.example.dto.CSVColumnWord;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.TreeMap;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PrefixTree {
    private final PrefixTreeNode root = new PrefixTreeNode('\u0000', new TreeMap<>());

    public void insertAll(List<CSVColumnWord> words) {
        for (CSVColumnWord word : words) {
            root.insert(new StringBuilder(word.value()), word.rowIndex());
        }
    }

    public void insert(CSVColumnWord word) {
        root.insert(new StringBuilder(word.value()), word.rowIndex());
    }

    public List<Integer> getRowsIndexesByPrefix(String prefix) {
        long startTime = System.currentTimeMillis();
        PrefixTreeNode prefixRoot = root.getRootByPrefix(new StringBuilder(prefix));
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Затрачено на поиск мл:" + duration);
        return prefixRoot.getRowIndexesFromTree().stream().toList();
    }

}
