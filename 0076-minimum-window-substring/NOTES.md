# Notes

Based on this question, I'll make a detailed summary of the sliding window algorithm. The core steps are as follows:

1. Initialize two pointers `lp, rp` to simulate a window. We want elements inside the window to be target elements.
2. Use `rp` to expand the window. Keep adding elements in until the window staisfies the condition.
3. Use `lp` to trim down the window. Keep deleting elements until the window doesn't satisfy the condition any longer.
4. Update the answer

The essence of "sliding window" can be summarized as: "Use right pointer to expand the window and left pointer to shrink the window". We can write a template code as follows:

```python
def sliding_window(nums, cond):
    l, r = 0, 0
    n = len(nums)
    
    while r < n:
        e = nums[r]
        # some operation here
        ...
        # trim down the window until the condition doesn't hold anymore
        while l <= r and cond:
            
            # update answer
            ans = min(ans, r - l + 1)
            # remove nums[l] and update corresponding things
            ...
            # shrink the window
            l += 1
            
            
        # expand the window
        r += 1
```

