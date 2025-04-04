package com.example.structs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.TreeMap;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
class PrefixTreeNode {
    private final char value;
    private List<Integer> rowIndexes;
    @NonNull
    private TreeMap<Character, PrefixTreeNode> children;

    void insert(StringBuilder string, int rowIndex) {
        if (string.isEmpty()) return;
        if (!this.children.containsKey(string.charAt(0))) {
            char symbol = string.charAt(0);
            List<Integer> rowIndexes = new ArrayList<>();
            rowIndexes.add(rowIndex);
            PrefixTreeNode newNode = new PrefixTreeNode(symbol, rowIndexes, new TreeMap<>());
            this.children.put(string.charAt(0), newNode);
            newNode.insert(string.deleteCharAt(0), rowIndex);
        } else {
            PrefixTreeNode newNode = children.get(string.charAt(0));
            if (!newNode.rowIndexes.contains(rowIndex)) newNode.rowIndexes.add(rowIndex);
            newNode.insert(string.deleteCharAt(0), rowIndex);
        }
    }

    LinkedHashSet<Integer> getRowIndexesFromTree() {
        return new LinkedHashSet<>(this.rowIndexes);
    }

    PrefixTreeNode getRootByPrefix(StringBuilder prefix) {
        if (prefix.isEmpty()) return this;
        if (this.value == prefix.charAt(0)) {
            return this.getRootByPrefix(prefix.deleteCharAt(0));
        } else {
            if (this.children.containsKey(prefix.charAt(0))) {
                PrefixTreeNode newNode = this.children.get(prefix.charAt(0));
                return newNode.getRootByPrefix(prefix);
            } else {
                return null;
            }
        }
    }
}
