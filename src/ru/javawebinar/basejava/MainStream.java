package ru.javawebinar.basejava;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {
    public static void main(String[] args) {
        // 123
        System.out.println(minValue(new int[]{3, 2, 3, 3, 1, 3}));
        // 89
        System.out.println(minValue(new int[]{9, 8, 9}));
        // sum = 6 -> 1, 3
        System.out.println(oddOrEven(Arrays.asList(1, 2, 3)));
        // sum = 7 -> 2, 4
        System.out.println(oddOrEven(Arrays.asList(1, 2, 4)));
    }

    public static int minValue(int[] values) {
        return Arrays.stream(values)
                .distinct()
                .sorted()
                .reduce(0, (left, right) -> left * 10 + right);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(Integer::intValue).sum();
        return integers.stream()
                .filter(item -> (sum % 2 == 0) == (item % 2 != 0))
                .collect(Collectors.toList());
    }
}
