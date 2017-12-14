package com.lab3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Reading file and
 * making arrays for
 * integers
 */
public class Main {
    public int a =0,b=0,n=0;
    public void read() throws IOException {
        Path filePath = Paths.get("input.txt");
        Scanner scanner = new Scanner(filePath);
        int[] data = new int[3];
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                for(int i = 0; i < 3; i++){
                    data[i] = scanner.nextInt();
                }
            } else {
                scanner.next();
            }

        }
        n = data[0];
        b = data[1];
        a = data[2];
    }

    /**
     * Calculating minimal
     * size of square
     */
    public void calculate() throws IOException {
        double storona_kv = 0; //init side size of square
        storona_kv =(int) Math.sqrt(n * a * b); //starting minimal side size
        double suma_storin_by_h = 0, // sum of rectangle side sizes by height
                sum_storin_by_w = 0, // sum of rectangle side size by width
                count_rectangles_by_h = 0, // counter to count rectangles by their height
                count_recktagles_by_w = 0; // counter to count rectangles by their width

        while(true){

            /**
             * Count number of
             *  rectangles by
             *  their heigth
             *  */
            while (storona_kv - suma_storin_by_h > a) {
                count_rectangles_by_h += 1;
                suma_storin_by_h += a;
            }

            /**
             * Count number of
             * rectangles by
             * their width
             */
            while (storona_kv - sum_storin_by_w > b) {
                count_recktagles_by_w += 1;
                sum_storin_by_w += b;
            }

            /**
             * Calculating available
             * counted number of
             *rectangles
             */
            double rectangles_count = count_rectangles_by_h * count_recktagles_by_w;

            /**
             * Checking available counted
             * rectangles with given
             * in data file
             */
            if (rectangles_count >= n) {
                break;
            }

            /**
             * Adding that size that
             * needs to put number
             * of rectangles to get minimal
             * size of square
             */
            if (storona_kv - sum_storin_by_w < storona_kv - sum_storin_by_w) {
                storona_kv += b - (storona_kv - sum_storin_by_w);
            } else {
                storona_kv += a - (storona_kv - suma_storin_by_h);
            }

            /**
             * Write output
             * to file
             */
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
            writer.write(String.valueOf(storona_kv));
            writer.close();
            break;
        }
    }

    public static void main(String[] args) throws IOException {
        Main mn = new Main();
        mn.read();
        mn.calculate();
     }
    }

