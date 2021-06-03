package com.javarush.task.task33.task3310.tests;

import com.javarush.task.task33.task3310.Helper;
import com.javarush.task.task33.task3310.Shortener;
import com.javarush.task.task33.task3310.strategy.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class SpeedTest {
    public long getTimeToGetIds(Shortener shortener, Set<String> strings, Set<Long> ids){
        Date start = new Date();
        for (String str : strings) {
            ids.add(shortener.getId(str));
        }
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }

    public long getTimeToGetStrings(Shortener shortener, Set<Long> ids, Set<String> strings){
        Date start = new Date();
        for (Long id : ids) {
            strings.add(shortener.getString(id));
        }
        Date finish = new Date();
        return finish.getTime() - start.getTime();
    }


    @Test
    public void testHashMapStorage(){
        Set<String>origStrings = new HashSet<>();
        Shortener shortener1 = new Shortener(new HashMapStorageStrategy());
        Shortener shortener2 = new Shortener(new HashBiMapStorageStrategy());

        for (int i = 0; i < 10000; i++) {
            origStrings.add(Helper.generateRandomString());
        }

        Set<Long> ids1 = new HashSet<>();
        Set<String> strings1 = new HashSet<>();
        long stringToId1 = getTimeToGetIds(shortener1, origStrings, ids1);
        long idsToStrings1 = getTimeToGetStrings(shortener1, ids1, strings1);

        Set<Long> ids2 = new HashSet<>();
        Set<String> strings2 = new HashSet<>();
        long stringToId2 = getTimeToGetIds(shortener2, origStrings, ids2);
        long idsToStrings2 = getTimeToGetStrings(shortener2, ids2, strings2);

        Assert.assertTrue(stringToId1 > stringToId2);
        Assert.assertEquals(idsToStrings1, idsToStrings2, 30);
    }
}
