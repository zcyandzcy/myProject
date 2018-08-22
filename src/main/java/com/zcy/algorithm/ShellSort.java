package com.zcy.algorithm;


/**
 * @author zcy
 * <p>
 * 希尔(Shell)排序又称为缩小增量排序，它是一种插入排序。它是直接插入排序算法的一种威力加强版。
 * <p>
 * 希尔排序的基本思想是：
 * <p>
 * 把记录按步长 gap 分组，对每组记录采用直接插入排序方法进行排序。
 * <p>
 * 随着步长逐渐减小，所分成的组包含的记录越来越多，当步长的值减小到 1 时，整个数据合成为一组，构成一组有序记录，则完成排序。
 */
public class ShellSort {
    public void shellSort(int[] list) {
        //设置步长
        int gap = list.length / 2;

        while (1 <= gap) {
            // 把距离为 gap 的元素编为一个组，扫描所有组
            for (int i = gap; i < list.length; i++) {
                int j = 0;
                int temp = list[i];

                // 对距离为 gap 的元素组进行排序
                for (j = i - gap; j >= 0 && temp < list[j]; j = j - gap) {
                    list[j + gap] = list[j];
                }
                list[j + gap] = temp;
            }

            System.out.format("gap = %d:	", gap);
            printAll(list);
            gap = gap / 2; // 减小增量
        }
    }

    // 打印完整序列
    public void printAll(int[] list) {
        for (int value : list) {
            System.out.print(value + "	");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] array = {
                9, 1, 2, 5, 7, 4, 8, 6, 3, 5
        };

        // 调用希尔排序方法
        ShellSort shell = new ShellSort();
        System.out.print("排序前:		");
        shell.printAll(array);
        shell.shellSort(array);
        System.out.print("排序后:		");
        shell.printAll(array);
    }
}
