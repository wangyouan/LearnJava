package LeetCode;

import java.util.Arrays;

/**
 * Created by warn on 4/4/16.
 * <p>
 * Use to test code
 */
public class LeetCodeTest {
    private static void testDFS() {
        TagDFS test;
        test = new TagDFS();
//        System.out.println(test.partition("baabc").toString());
        int nCourse = 2;
        int[][] prerequisites = {{1, 0}};
        int[] result = test.findOrder(nCourse, prerequisites);
        System.out.println(Arrays.toString(result));
    }

    private static void testMath() {
        TagMath test = new TagMath();
        System.out.print(test.calculate("(((((8 - 12))))) +3"));
    }

    private static void testSort() {
//        int[] num = new int[100];
//        Random random = new Random();
//        for (int i = 0; i < 100; i++) {
//            num[i] = random.nextInt(100);
//        }
        int[] num = {4, 5, 5, 6};
        TagSort test = new TagSort();
        test.wiggleSort(num);
        System.out.println(Arrays.toString(num));
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) testSort();
        long runningTime = System.currentTimeMillis() - startTime;
        System.out.println("Running time is " + runningTime / 10);
    }
}
