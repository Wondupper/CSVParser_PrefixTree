package com.example;

import com.example.csv.scan.CSVColumnParser;
import com.example.csv.view.CSVRowsViewer;
import com.example.structs.PrefixTree;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Path pathToFile = Path.of("airports.dat");

    private static Scanner scanner = new Scanner(System.in);

    private static int columnIndex;

    private static CSVColumnParser parser = new CSVColumnParser(pathToFile);

    private static PrefixTree tree = new PrefixTree();

    private static CSVRowsViewer csvRowsViewer = new CSVRowsViewer(pathToFile);


    public static void main(String[] args) throws IOException {
        startProgramLoop();
    }

    private static void startProgramLoop() throws IOException {
        while (true) {
            System.out.print("Введите номер колонки для поиска");
            columnIndex = scanner.nextInt();
            parser.setColumnIndex(columnIndex);
            tree.insertAll(parser.getColumnElements());
            System.out.print("Введите префикс для поиска");
            List<Integer> indexes = tree.getRowsIndexesByPrefix(scanner.nextLine());
            csvRowsViewer.viewColumnsWithOrder(indexes);
            System.out.print("Для выхода введите: !quit");
            if (scanner.nextLine().equals("quit")) {
                break;
            }
        }
    }
}