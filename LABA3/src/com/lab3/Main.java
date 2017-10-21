package com.lab3;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

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

    public void calculate() throws IOException {
        double storona_kv = 0;
        storona_kv =(int) Math.sqrt(n * a * b);
        double suma_storin_by_h = 0,
                sum_storin_by_w = 0,
                count_rectangles_by_h = 0,
                count_recktagles_by_w = 0;

        while(true){
            while (storona_kv - suma_storin_by_h > a) {
                count_rectangles_by_h += 1;
                suma_storin_by_h += a;
            }
            while (storona_kv - sum_storin_by_w > b) {
                count_recktagles_by_w += 1;
                sum_storin_by_w += b;
            }
            double rectangles_count = count_rectangles_by_h * count_recktagles_by_w;
            if (rectangles_count >= n) {
                break;
            }
            if (storona_kv - sum_storin_by_w < storona_kv - sum_storin_by_w) {
                storona_kv += b - (storona_kv - sum_storin_by_w);
            } else {
                storona_kv += a - (storona_kv - suma_storin_by_h);
            }
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

