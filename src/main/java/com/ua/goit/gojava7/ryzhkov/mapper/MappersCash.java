package com.ua.goit.gojava7.ryzhkov.mapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MappersCash {

    private static MappersCash instanceMapperCash = new MappersCash();
    private Map<Class<?>, AbstractMapper> cash;

    private MappersCash() {
        cash = new HashMap<>();
        putAllStandardMappers();
    }

    private void putAllStandardMappers() {
        putMapper(String.class, new StringMapper());
        putMapper(Character.class, new CharacterMapper());
        putMapper(Number.class, new NumberMapper());
        putMapper(Boolean.class, new BooleanMapper());
        putMapper(Collection.class, new CollectionMapper());
        putMapper(Map.class, new MapMapper());
        putMapper(Object[].class, new ArrayMapper());
    }

    public static MappersCash getInstance() {
        return instanceMapperCash;
    }

    public void putMapper(Class<?> clazz, AbstractMapper mapper) {
        cash.put(clazz, mapper);
    }

    public AbstractMapper getMapper(Class<?> clazz) {
        return cash.get(clazz);
    }

}
