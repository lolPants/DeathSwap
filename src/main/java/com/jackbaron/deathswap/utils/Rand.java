package com.jackbaron.deathswap.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public final class Rand {
    private static Random random = new Random();

    public static int nextInt() {
        return random.nextInt();
    }

    public static int nextInt(int bound) {
        return random.nextInt(bound);
    }

    public static int nextInt(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static double nextDouble() {
        return random.nextDouble();
    }

    public static double nextDouble(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static <T> ArrayList<T> shuffle(ArrayList<T> list) {
        ArrayList<T> clone = new ArrayList<>(list.size());
        clone.addAll(list);

        HashMap<T, Integer> before = new HashMap<>();
        HashMap<T, Integer> after = new HashMap<>();

        for (T item : clone) {
            before.put(item, clone.indexOf(item));
        }

        Collections.shuffle(clone);

        for (T item : clone) {
            after.put(item, clone.indexOf(item));
        }

        boolean conflict = false;
        for (T item : clone) {
            Integer beforeIdx = before.get(item);
            Integer afterIdx = after.get(item);

            if (beforeIdx.equals(afterIdx)) {
                conflict = true;
                break;
            }
        }

        if (conflict) return shuffle(list);
        else return clone;
    }
}
