package util;

import java.util.HashMap;
import java.util.Map;

public class MapBuilderUtil<KeyType, ValueType> {
    private Map<KeyType, ValueType> map = new HashMap<>();

    public <K extends KeyType, V extends ValueType> MapBuilderUtil<KeyType, ValueType> put(K key, V value) {
        this.map.put(key, value);
        return this;
    }

    public Map<KeyType, ValueType> build() {
        return this.map;
    }
}
