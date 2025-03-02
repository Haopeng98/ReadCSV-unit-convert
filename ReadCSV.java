package Program;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadCSV {
    public static void main(String[] args) throws IOException {
        String csvFile = "C:\\input_measurements.csv";
        String outputFile = "C:\\temp\\output_measurements.csv";
        try (BufferedReader bf = new BufferedReader(new FileReader(csvFile))) { // reader
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile)); // writer
            String line;
            int i = 0;
            while ((line = bf.readLine()) != null) {
                String[] col = line.split(","); // splitting elements with comma to search for unit and value
                if (col.length >= 3 && i != 0) {
                    String unit = col[2]; // getting the unit from file
                    double value = Double.valueOf(col[3]); // getting the value to the corresponding unit
                    System.out.println("value: " + value);
                    System.out.println("unit: " + unit);
                    if (unit.equals("C")) { // found C
                        col[2] = "F"; // change C to F
                        value = (value * 9 / 5) + 32; // calculate F
                        col[3] = String.valueOf(value); // change the value
                    } else if (unit.equals("kg")) { // found kg
                        col[2] = "lbs"; // change kg to lbs
                        value = value * 2.20462; // calculate lbs
                        col[3] = String.valueOf(value); // change the value
                    } else if (unit.equals("mm")) { // found mm
                        col[2] = "in"; // change mm to in
                        value = value * 0.0393701; // calculate in
                        col[3] = String.valueOf(value); // change the value
                    }
                }
                i++;
                // using string builder to prepare for writing new file
                StringBuilder sb = new StringBuilder();
                sb.append(col[0]);
                sb.append(",");
                sb.append(col[1]);
                sb.append(",");
                sb.append(col[2]);
                sb.append(",");
                sb.append(col[3]);
                System.out.println(sb.toString());
                bw.write(sb.toString()); // write new file with altered content
                bw.newLine();
            }
            bw.close();
        }
    }
}

