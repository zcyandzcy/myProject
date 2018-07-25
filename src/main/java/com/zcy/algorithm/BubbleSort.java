package com.zcy.algorithm;

import java.util.Random;

/**
 * @author zcy
 * @apiNote 交换排序之一  --> 冒泡排序算法
 * 对冒泡排序常见的改进方法是加入标志性变量exchange，用于标志某一趟排序过程中是否有数据交换。
 */
public class BubbleSort {

    public void bubbleSort(int[] list) {
        int temp = 0; // 用来交换的临时数
        // 要遍历的次数
        for (int i = 0; i < list.length - 1; i++) {
            // 从后向前依次的比较相邻两个数的大小，遍历一次后，把数组中第i小的数放在第i个位置上
            for (int j = list.length - 1; j > i; j--) {
                // 比较相邻的元素，如果前面的数大于后面的数，则交换
                if (list[j - 1] > list[j]) {
                    temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                }
            }

            System.out.format("第 %d 趟：	", i);
            printAll(list);
        }
    }

    // 对 bubbleSort 的优化算法
    public void bubbleSort_2(int[] list) {
        int temp = 0; // 用来交换的临时数
        boolean bChange = false; // 交换标志

        // 要遍历的次数
        for (int i = 0; i < list.length - 1; i++) {
            bChange = false;
            // 从后向前依次的比较相邻两个数的大小，遍历一次后，把数组中第i小的数放在第i个位置上
            for (int j = list.length - 1; j > i; j--) {
                // 比较相邻的元素，如果前面的数大于后面的数，则交换
                if (list[j - 1] > list[j]) {
                    temp = list[j - 1];
                    list[j - 1] = list[j];
                    list[j] = temp;
                    bChange = true;
                }
            }

            // 如果标志为false，说明本轮遍历没有交换，已经是有序数列，可以结束排序
            if (false == bChange)
                break;

            System.out.format("第 %d 趟：	", i);
            printAll(list);
        }
    }


    public void bubbleSort_3(int[] array) {
        int tmp = 0;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        //无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    //把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
            System.out.format("第 %d 趟：	", i);
            printAll(array);
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
        // 初始化一个随机序列
        final int MAX_SIZE = 10;
        int[] array = new int[MAX_SIZE];
        Random random = new Random();
        for (int i = 0; i < MAX_SIZE; i++) {
            array[i] = random.nextInt(MAX_SIZE);
        }

        // 调用冒泡排序方法
        BubbleSort bubble = new BubbleSort();
        bubble.printAll(array);
        System.out.println("排序前:	"+System.currentTimeMillis());
//         bubble.bubbleSort(array);
//        bubble.bubbleSort_2(array);
        bubble.bubbleSort_3(array);
        System.out.println("排序后:	"+System.currentTimeMillis());
        bubble.printAll(array);
    }
}
