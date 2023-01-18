# Notes

First of all, we can easily come up with a brute force solution with $O(N^2)$ time complexity.

```java
for (int i = 1; i <= n; ++i) {
    for (int j = i; j <= n; ++j) {
        if (sum[j] - sum[i - 1] == k) {
            count++;
        }
    }
}
```

Now, let's think about how to optimize. The main issue here is that we need to have a two-layer loop to enumerate all the subarrays. The left end and right end are $i$ and $j$, respectively. Then we can check if the subarray sum $sum[j] - sum[i - 1]$ equals to $k$. 

If we look at this question from another angle, we realize for every right end $j$, we just need to know how many $i$ there are such that $sum[i - 1] = sum[j] - k$. 

Therefore, we can use a hash table. The key is prefix sum and the value is the number of existance. Then, for every $j$, we check if $sum[j]-k$ is in the table. If it's in the table, we add the value to the final answer. After that, we update the table and insert the current prefix sum.

```java
for (int i = 1; i <= n; ++i) {
	int key = sum[i] - k;
    if (hash.containsKey(key)) {
        count += hash.get(key);
    }
    hash.put(key, hash.getOrDefault(key, 0) + 1);
}
```

