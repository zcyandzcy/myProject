package com.zcy.algorithm;


/**
 * @author zcy
 * <p>
 * 堆的概念
 * 堆是一棵顺序存储的完全二叉树。
 * <p>
 * 其中每个结点的关键字都不大于其孩子结点的关键字，这样的堆称为小根堆。
 * <p>
 * 其中每个结点的关键字都不小于其孩子结点的关键字，这样的堆称为大根堆。
 * <p>
 * 要点
 * （1）根据初始数组去构造初始堆（构建一个完全二叉树，保证所有的父结点都比它的孩子结点数值大）。
 * <p>
 * （2）每次交换第一个和最后一个元素，输出最后一个元素（最大值），然后把剩下元素重新调整为大根堆。
 * <p>
 * 当输出完最后一个元素后，这个数组已经是按照从小到大的顺序排列了。
 * <p>
 * 算法稳定性
 * <p>
 * 堆排序是一种不稳定的排序方法。
 */
public class HeapSort {

    public void HeapAdjust(int[] array, int parent, int length) {
        int temp = array[parent]; // temp保存当前父节点
        int child = 2 * parent + 1; // 先获得左孩子

        while (child < length) {
            // 如果有右孩子结点，并且右孩子结点的值大于左孩子结点，则选取右孩子结点
            if (child + 1 < length && array[child] < array[child + 1]) {
                child++;
            }

            // 如果父结点的值已经大于孩子结点的值，则直接结束
            if (temp >= array[child])
                break;

            // 把孩子结点的值赋给父结点
            array[parent] = array[child];

            // 选取孩子结点的左孩子结点,继续向下筛选
            parent = child;
            child = 2 * child + 1;
        }

        array[parent] = temp;
    }

    public void heapSort(int[] list) {
        // 循环建立初始堆
        for (int i = list.length / 2; i >= 0; i--) {
            HeapAdjust(list, i, list.length);
        }

        // 进行n-1次循环，完成排序
        for (int i = list.length - 1; i > 0; i--) {
            // 最后一个元素和第一元素进行交换
            int temp = list[i];
            list[i] = list[0];
            list[0] = temp;

            // 筛选 R[0] 结点，得到i-1个结点的堆
            HeapAdjust(list, 0, i);
            System.out.format("第 %d 趟: 	", list.length - i);
            printPart(list, 0, list.length - 1);
        }
    }

    // 打印序列
    public void printPart(int[] list, int begin, int end) {
        for (int i = 0; i < begin; i++) {
            System.out.print("	");
        }
        for (int i = begin; i <= end; i++) {
            System.out.print(list[i] + "	");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 初始化一个序列
        int[] array = {
                1, 3, 4, 5, 2, 6, 9, 7, 8, 0
        };

        // 调用快速排序方法
        HeapSort heap = new HeapSort();
        System.out.print("排序前:	");
        heap.printPart(array, 0, array.length - 1);
        heap.heapSort(array);
        System.out.print("排序后:	");
        heap.printPart(array, 0, array.length - 1);
    }
}
