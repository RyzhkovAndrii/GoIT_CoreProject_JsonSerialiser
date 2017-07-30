package main.java.mapper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrii Ryzhkov on 12.07.2017.
 *
 */

public class MappersCash {

    private static volatile MappersCash ourInstance;
    private Map<String, IJsonMapper> cash;

    public static final String STRING_MAPPER_NAME = "stringMapper";
    public static final String NUMBER_MAPPER_NAME = "numberMapper";
    public static final String BOOLEAN_MAPPER_NAME = "booleanMapper";
    public static final String COLLECTION_MAPPER_NAME = "collectionMapper";
    public static final String MAP_MAPPER_NAME = "mapMapper";
    public static final String OBJECT_ARRAY_MAPPER_NAME = "objectArrayMapper";
    public static final String PRIMITIVE_ARRAY_MAPPER_NAME = "primitiveArrayMapper";
    public static final String CHARACTER_MAPPER_NAME = "characterMapper";

    private MappersCash() {
        cash = new HashMap<>();
        putAllStandardMappers();
    }

    private void putAllStandardMappers() {
        putMapper(STRING_MAPPER_NAME, new StringMapper());
        putMapper(CHARACTER_MAPPER_NAME, new CharacterMapper());
        putMapper(NUMBER_MAPPER_NAME, new NumberMapper());
        putMapper(BOOLEAN_MAPPER_NAME, new BooleanMapper());
        putMapper(COLLECTION_MAPPER_NAME, new CollectionMapper());
        putMapper(MAP_MAPPER_NAME, new MapMapper());
        putMapper(OBJECT_ARRAY_MAPPER_NAME, new ObjectArrayMapper());
        putMapper(PRIMITIVE_ARRAY_MAPPER_NAME, new PrimitiveArrayMapper());
    }

    public static MappersCash getInstance() {
        MappersCash localInstance = ourInstance;
        if (localInstance == null) {
            synchronized (MappersCash.class) {
                localInstance = ourInstance;
                if (localInstance == null) {
                    ourInstance = localInstance = new MappersCash();
                }
            }
        }
        return localInstance;
    }

    public void putMapper(String mapperName, IJsonMapper mapper) {
        cash.put(mapperName, mapper);
    }

    public IJsonMapper getMapper(String mapperName) {
        return cash.get(mapperName);
    }

}
