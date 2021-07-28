package ru.job4j.kiss;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxMin {
    private <T> Map<Boolean, T> maxMin(List<T> value, Comparator<T> comparator) {
        if (!value.isEmpty()) {
            T tempMax = value.get(0);
            T tempMin = value.get(0);
            Map<Boolean, T> mapIsMax = new HashMap<>();
            for (var item : value) {
                if (comparator.compare(tempMax, item) < 0) {
                    tempMax = item;
                }
                if (comparator.compare(tempMin, item) > 0) {
                    tempMax = item;
                }
            }
            mapIsMax.put(true, tempMax);
            mapIsMax.put(false, tempMin);
            return mapIsMax;
        }
        return null;
    }

    public <T> T max(List<T> value, Comparator<T> comparator) {
        T max = maxMin(value, comparator).get(true);
        return max;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        T min = maxMin( value, comparator).get(false);
        return min;
    }
}