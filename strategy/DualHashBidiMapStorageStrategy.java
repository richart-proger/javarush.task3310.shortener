package com.javarush.task.task33.task3310.strategy;

import org.apache.commons.collections4.bidimap.DualHashBidiMap;

public class DualHashBidiMapStorageStrategy implements StorageStrategy {
    private DualHashBidiMap data = new DualHashBidiMap();

    /**
     * 3. Метод containsKey() должен проверять содержится ли ключ в data.
     * 4. Метод containsValue() должен проверять содержится ли значение в data.
     * 5. Метод put() должен добавлять пару (key, value) в data.
     * 6. Метод getValue() должен возвращать значение полученное из data.
     * 7. Метод getKey() должен возвращать ключ полученный из data.getKey().
     * @param key
     * @return
     */
    @Override
    public boolean containsKey(Long key) {
        return data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        return (Long) data.getKey(value);
    }

    @Override
    public String getValue(Long key) {
        return (String) data.get(key);
    }
}
