package com.hust.soict.hieu.helper;

public class SelectionSort implements NumberSort {
    
    @Override
    public void sort(int[] arr) {
        int n = arr.length;
        
        for (int i = 0; i < n - 1; ++i) {
            int min_idx = i;
            
            for (int j = i + 1; j < n; ++j) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            
            ArrayUtils.swapTwoElements(arr, min_idx, i);
        }
    }
}
