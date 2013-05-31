package cpu;

import java.util.Arrays;
import java.util.Random;

/**
 * @author sigito
 */
public class GuessNextStep {
    static class SplitNumber {
        int border;
        int lower;
        int higher;

        SplitNumber(int border) {
            this.border = border;
        }

        @Override
        public String toString() {
            return "SplitNumber{" +
                    "border=" + border +
                    ", lower=" + lower +
                    ", higher=" + higher +
                    '}';
        }
    }

    /**
     * Process iteration through {@code array} and return execution time
     *
     * @param array  array to iterate through
     * @param middle value to split array by
     * @return number of lower and higher then {@code middle} value
     */
    static SplitNumber getArrayDistribution(int[] array, int middle) {
        SplitNumber sn = new SplitNumber(middle);

        for (int i : array)
            if (i < middle)
                sn.lower++;
            else
                sn.higher++;

        return sn;
    }

    static int[] getRandomArray(int length, int maxRand) {
        Random rnd = new Random();
        int[] array = new int[length];

        for (int i = 0; i < array.length; i++)
            array[i] = rnd.nextInt(maxRand);

        return array;
    }

    /**
     * @see <a href="http://en.wikipedia.org/wiki/Branch_predictor"/>
     */
    public static void test() {
        int length = 10_000_000;
        int maxValue = 1_000;
        int middleValue = maxValue >> 1;

        int[] array = getRandomArray(length, maxValue);

        // Unsorted array
        long start = System.currentTimeMillis();
        SplitNumber sn = getArrayDistribution(array, middleValue);
        long end = System.currentTimeMillis();
        System.out.println("Unsorted array[" + length + "] (max value: " + maxValue + "): " + (end - start) + " ms");
        System.out.println(sn);

        // Sorted array
        Arrays.sort(array);

        start = System.currentTimeMillis();
        sn = getArrayDistribution(array, middleValue);
        end = System.currentTimeMillis();
        System.out.println("Sorted array[" + length + "] (max value: " + maxValue + "): " + (end - start) + " ms");
        System.out.println(sn);
    }
}
