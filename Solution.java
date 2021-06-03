package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    private static long elementsNumber = 10000;
    private static long elementsNumber1 = 100;
    public static void main(String[] args) {
        Solution.testStrategy(new HashMapStorageStrategy(), elementsNumber);
        System.out.println();
        Solution.testStrategy(new OurHashMapStorageStrategy(), elementsNumber);
        System.out.println();
        Solution.testStrategy(new FileStorageStrategy(), elementsNumber1);
        System.out.println();
        Solution.testStrategy(new OurHashBiMapStorageStrategy(), elementsNumber);
        System.out.println();
        Solution.testStrategy(new HashBiMapStorageStrategy(), elementsNumber);
        System.out.println();
        Solution.testStrategy(new DualHashBidiMapStorageStrategy(), elementsNumber);
        System.out.println();
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());

        Set<String> testSet = new HashSet<>();

        for (int i = 0; i < elementsNumber; i++) {
            testSet.add(Helper.generateRandomString());
        }

        Shortener shortener = new Shortener(strategy);

        Date start1 = new Date();
        Date finish1 = new Date();
        Set<Long>keys = getIds(shortener, testSet);

        start1.setTime(System.currentTimeMillis());
        for (String str : testSet) {
            shortener.getId(str);
        }
        finish1.setTime(System.currentTimeMillis());
        Helper.printMessage("Время получения идентификаторов для " + elementsNumber + " строк: " + (finish1.getTime() - start1.getTime()) + "мс" );

        Date start2 = new Date();
        Date finish2 = new Date();
        Set<String>strings = getStrings(shortener, keys);

        start2.setTime(System.currentTimeMillis());
        for (Long key : keys) {
            shortener.getString(key);
        }
        finish2.setTime(System.currentTimeMillis());
        Helper.printMessage("Время получения строк для " + elementsNumber + " идентификаторов: " + (finish2.getTime() - start2.getTime()) + "мс" );

        if (testSet.size() == strings.size()){
            Helper.printMessage("Тест пройден.");
        }
        else {
            Helper.printMessage("Тест не пройден.");
        }
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        Set <Long> setId = new HashSet<>();

        for (String s : strings) {
            setId.add(shortener.getId(s));
        }
        return setId;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        Set <String> stringSet = new HashSet<>();

        for (Long k : keys) {
            stringSet.add(shortener.getString(k));
        }
        return stringSet;
    }

}
