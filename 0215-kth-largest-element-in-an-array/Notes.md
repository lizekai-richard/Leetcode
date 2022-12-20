# Note

Below is the template for partition

```java
public void partition(nums, left, right, pIndex) {
    int pivot = nums[pIndex];
    swap(nums, pIndex, right);
    
    int storeIndex = left;
    for (int i = left; i <= right; ++i) {
        if (nums[i] < pivot) {
            swap(nums, storeIndex, i);
            storeIndex++;
        }
    }
    swap(nums, storeIndex, right);
    return storeIndex;
}
```

`storeIndex` represents the smallest index that satisfies `nums[index] > pivot`. Every time we encounter an element that is smaller than the pivot, we swap that index with `storeIndex` and increase the `storeIndex` by 1. By doing so, we can move all elements that are smaller than the pivot to the left.



3-way partition is dividing the aray into three parts:

- Elements smaller than the low value.
- Elements within the range of [low, high].
- Elements larger than the high value.

```java
public static void
    threeWayPartition(int[] arr, int lowVal, int highVal)
    {
 
        int n = arr.length;
 
        // Initialize next available positions for
        // smaller (than range) and greater elements
        int start = 0, end = n - 1;
 
        // Traverse elements from left
        for (int i = 0; i < n; i++) {
 
            // If current element is smaller than
            // range, put it on next available smaller
            // position.
            if (arr[i] < lowVal) {
                swap(arr, i, start);
                start++;
            }
 
            // If current element is greater than
            // range, put it on next available greater
            // position.
            else if (arr[i] > highVal) {
                swap(arr, i, end);
                end--;
            }
        }
    }
```

