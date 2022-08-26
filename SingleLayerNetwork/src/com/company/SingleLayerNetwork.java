package com.company;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SingleLayerNetwork {
    public SingleLayerNetwork() throws IOException {
        Double totalAttempts = (double) 0;
        Double successfulPredictions = (double) 0;

        //Importing files
        String[] englishTrainFiles = {
                "src/com/company/lang/English/1.txt",
                "src/com/company/lang/English/2.txt",
                "src/com/company/lang/English/3.txt",
                "src/com/company/lang/English/4.txt",
                "src/com/company/lang/English/5.txt",
                "src/com/company/lang/English/6.txt",
                "src/com/company/lang/English/7.txt",
                "src/com/company/lang/English/8.txt",
                "src/com/company/lang/English/9.txt",
                "src/com/company/lang/English/10.txt"
        };

        String[] polishTrainFiles = {
                "src/com/company/lang/Polish/1.txt",
                "src/com/company/lang/Polish/2.txt",
                "src/com/company/lang/Polish/3.txt",
                "src/com/company/lang/Polish/4.txt",
                "src/com/company/lang/Polish/5.txt",
                "src/com/company/lang/Polish/6.txt",
                "src/com/company/lang/Polish/7.txt",
                "src/com/company/lang/Polish/8.txt",
                "src/com/company/lang/Polish/9.txt",
                "src/com/company/lang/Polish/10.txt"
        };

        String[] germanTrainFiles = {
                "src/com/company/lang/German/1.txt",
                "src/com/company/lang/German/2.txt",
                "src/com/company/lang/German/3.txt",
                "src/com/company/lang/German/4.txt",
                "src/com/company/lang/German/5.txt",
                "src/com/company/lang/German/6.txt",
                "src/com/company/lang/German/7.txt",
                "src/com/company/lang/German/8.txt",
                "src/com/company/lang/German/9.txt",
                "src/com/company/lang/German/10.txt"
        };

        String[] testFiles = {
                "src/com/company/lang.test/English/1.txt",
                "src/com/company/lang.test/English/2.txt",
                "src/com/company/lang.test/English/3.txt",
                "src/com/company/lang.test/Polish/1.txt",
                "src/com/company/lang.test/Polish/2.txt",
                "src/com/company/lang.test/Polish/3.txt",
                "src/com/company/lang.test/German/1.txt",
                "src/com/company/lang.test/German/2.txt",
                "src/com/company/lang.test/German/3.txt",

        };
        File file;

        List<Vector> vectorList = new ArrayList<>();
        List<Vector> testVectorList = new ArrayList<>();
        for(int i = 0; i < englishTrainFiles.length; i++){
            file = new File(englishTrainFiles[i]);
            Vector v = makeVectorFromFile(file, "English");
            vectorList.add(v);
        }
        for(int i = 0; i < polishTrainFiles.length; i++){
            file = new File(polishTrainFiles[i]);
            Vector v = makeVectorFromFile(file, "Polish");
            vectorList.add(v);
        }
        for(int i = 0; i < germanTrainFiles.length; i++){
            file = new File(germanTrainFiles[i]);
            Vector v = makeVectorFromFile(file, "German");
            vectorList.add(v);
        }
        //Create test vectors
        for(int i = 0; i < 3; i++){
            file = new File(testFiles[i]);
            Vector v = makeVectorFromFile(file, "English");
            testVectorList.add(v);
        }
        for(int i = 3; i < 6; i++){
            file = new File(testFiles[i]);
            Vector v = makeVectorFromFile(file, "Polish");
            testVectorList.add(v);
        }
        for(int i = 6; i < 9; i++){
            file = new File(testFiles[i]);
            Vector v = makeVectorFromFile(file, "German");
            testVectorList.add(v);
        }

        //Perceptron time
        totalAttempts += (double) testVectorList.size();
        Perceptron pEng = new Perceptron(vectorList, "English");
        successfulPredictions += pEng.predict(testVectorList);

        totalAttempts += (double) testVectorList.size();
        Perceptron pPl = new Perceptron(vectorList, "Polish");
        successfulPredictions += pPl.predict(testVectorList);

        totalAttempts += (double) testVectorList.size();
        Perceptron pGer = new Perceptron(vectorList, "German");
        successfulPredictions += pGer.predict(testVectorList);

        System.out.println(successfulPredictions);
        System.out.println(totalAttempts);
        System.out.println(successfulPredictions/totalAttempts);

    }

    public Vector makeVectorFromFile(File file, String vClass) throws IOException {
        System.out.println("now reading: " + file.getPath());
        BufferedReader br = new BufferedReader(new FileReader(file));
        Map<Character, Double> map = new HashMap<>();
        for(int i = 97; i <= 122; i++){
            char c = (char) i;
            map.put(c, (double) 0);
        }
        String st;

        while((st = br.readLine()) != null){
            st = st.toLowerCase();
            char[] cArray = st.toCharArray();
            for (int i = 0; i < cArray.length; i++){
                if((int) cArray[i] >= 97 && (int) cArray[i] <= 122){
                    map.put(cArray[i], (map.get(cArray[i]) + 1));
                }
            }
        }

        return new Vector(map, vClass);
    }
}
