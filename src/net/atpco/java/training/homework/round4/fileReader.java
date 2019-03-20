package net.atpco.java.training.homework.round4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class fileReader {
    public static void main(String[] args) {
        readFileByLines("D:\\GitHub\\atpco_training\\src\\net\\atpco\\java\\training\\homework\\round4\\fileReader.java");
    }

    public static void readFileByLines(String fileName) {
        try {
            int rowNum = 0;
            int codingRows = 0;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String lineContents = null;
            while ((lineContents = reader.readLine()) != null) {
//                if (!lineContents.isEmpty()) {
                    if (!lineContents.contains("/") && !lineContents.contentEquals("import") && !lineContents.contentEquals("package") && !lineContents.isEmpty()) {
                        codingRows++;
                    }
//                }
                rowNum++;
//                System.out.println(lineContents);
            }
            System.out.println(codingRows);
            System.out.println(rowNum);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
