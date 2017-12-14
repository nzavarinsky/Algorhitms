import java.io.*;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {

        String inputFileName = args.length >= 2 ? args[0] : "ijones.in";
        String outputFileName = args.length >= 2 ? args[1] : "ijones.out";

        DataIJonesInput inputData = readInput(inputFileName);
        DataIJonesOutput outputData = solve(inputData);
        writeOutput(outputFileName, outputData);

    }

    /* Зчитка файлу із введеними даними (ширина, висота, слова).
       Ширина - перша цифра першого рядка,
       довжина - ІІ цифра І рядка,
       слова - типу "плитки" (буквочки) з умови завдання */

    private static DataIJonesInput readInput(String inputFileName) throws IOException {
        File inputFile = new File(inputFileName);
        try (FileReader inputFileReader = new FileReader(inputFile)) {
            try (BufferedReader bufferedReader = new BufferedReader(inputFileReader)) {
                String[] theFirstLine = bufferedReader.readLine().split("\\s+");
                int width = Integer.valueOf(theFirstLine[0]);
                int height = Integer.valueOf(theFirstLine[1]);

                String[] words = new String[height];
                for (int i = 0; i < height; i++) {
                    words[i] = bufferedReader.readLine();
                }

                return new DataIJonesInput(width, height, words);
            }
        }
    }

    /* Вирахування кількості успішних способів пройти коридор.
    Інтеджер - об'єкт джави для роботи з цілими типами даних */

    private static DataIJonesOutput solve(DataIJonesInput inputData) {

        int width = inputData.getWidth();
        int height = inputData.getHeight();
        String[] words = inputData.getWords();

        BigInteger[] waysPrev = new BigInteger[height];
        BigInteger[] waysCurrent = new BigInteger[height];
        Map<Character, BigInteger> map = new HashMap<>();

        // Всі можливі букви алфавіту -> в мапу (бо 1 буква = 1 ключ)

        for(Character ch : "abcdefghijklmnopqrstuvwxyz".toCharArray()){
            map.put(ch, BigInteger.ZERO);
        }

        // Варіанти шляху по висоті і ширині
        // Суть завдання - можна рухатися тільки вправо на 1 або 2 плитки (букви)

        for(int i = 0; i < height; i++){
            waysPrev[i] = waysCurrent[i] = BigInteger.ZERO;
        }

        for(int i = 0; i < height; i++){
            waysPrev[i] = BigInteger.ONE;
            Character key  = words[i].charAt(0);
            map.put(key, map.get(key).add(BigInteger.ONE));
        }

        for(int j = 1; j < width; j++){
            for(int i = 0; i < height; i++){
                waysCurrent[i] = map.get(words[i].charAt(j));
                if(words[i].charAt(j) != words[i].charAt(j - 1)){
                    waysCurrent[i] = waysCurrent[i].add(waysPrev[i]);
                }
            }

            for(int i = 0; i < height; i++){
                Character key  = words[i].charAt(j);
                map.put(key, map.get(key).add(waysCurrent[i]));
            }

            BigInteger[] tmp = waysPrev;
            waysPrev = waysCurrent;
            waysCurrent = tmp;
        }

        // Вивід відповіді (к-сті способів пройти коридор)

        BigInteger countOfWays = height - 1 == 0? waysPrev[0]: waysPrev[0].add(waysPrev[height - 1]);
        return new DataIJonesOutput(countOfWays);
    }

    // Запис відповіді у вихідний файл

    private static void writeOutput(String outputFileName, DataIJonesOutput outputData) throws IOException {
        try (Writer outputFileWriter = new FileWriter(outputFileName)) {
            outputFileWriter.write(String.valueOf(outputData.getCountOfWays()));
        }
    }
}