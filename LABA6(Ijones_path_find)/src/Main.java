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
        Map<Character, BigInteger> gridElements = new HashMap<>();

        // Всі можливі букви алфавіту -> в мапу (бо 1 буква = 1 ключ)

        for(Character ch : "abcdefghijklmnopqrstuvwxyz".toCharArray()){
            gridElements.put(ch, BigInteger.ZERO);
        }

        // Варіанти шляху по висоті і ширині
        // Суть завдання - можна рухатися тільки вправо на 1 або 2 плитки (букви)

        //ставим дефолтне значення на початку шляху == 0
        for(int i = 0; i < height; i++){
            waysPrev[i] = waysCurrent[i] = BigInteger.ZERO;
        }

        //прохід по плитках - визначення відповідності ключа до елементів на плитках.
        //підрахунок пройдених плиток по висоті по к-сті вхідних даних ( для коридору )
        //WaysPrev - к-сть шляхів (a-b-c) - типу 1 шлях
        for(int i = 0; i < height; i++){
            waysPrev[i] = BigInteger.ONE;
            Character key  = words[i].charAt(0);
            gridElements.put(key, gridElements.get(key).add(BigInteger.ONE));
        }

        //підрахунок шляху по ширині
        //якщо не співпадає можна перестрибувати
        for(int j = 1; j < width; j++){
            for(int i = 0; i < height; i++){
                waysCurrent[i] = gridElements.get(words[i].charAt(j));
                if(words[i].charAt(j) != words[i].charAt(j - 1)){//якщо не співпадає з елеменами пройдених по висоті
                    waysCurrent[i] = waysCurrent[i].add(waysPrev[i]); //підрах по ширині
                }
            }

            //обрахунок вихідного шляху
            for(int i = 0; i < height; i++){
                Character key  = words[i].charAt(j);
                gridElements.put(key, gridElements.get(key).add(waysCurrent[i]));
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