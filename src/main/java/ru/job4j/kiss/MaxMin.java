package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        List<T> list = this.sort(value, comparator);
        if (list != null && !list.isEmpty()) {
            int maxIndex = list.size() - 1;
            return list.get(maxIndex);
        }
        return null;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        List<T> list = this.sort(value, comparator);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    private <T> List<T> sort(List<T> value, Comparator<T> comparator) {
        boolean needIteration = true;
        while (needIteration) {
            needIteration = false;
            for (int i = 1; i < value.size(); i++) {
                if (comparator.compare(value.get(i), value.get(i - 1)) < 0) {
                    swap(value, i, i - 1);
                    needIteration = true;
                }
            }
        }
        return value;
    }

    private <T> void swap(List<T> array, int ind1, int ind2) {
        T tmp = array.get(ind1);
        array.set(ind1, array.get(ind2));
        array.set(ind2, tmp);
    }
}