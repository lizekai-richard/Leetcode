# Notes

The key to this problem is the equivalent expression below:

**Add $1$ to $n-1$ elements every time is the same as minus $1$ to a single element every time**.

If we are clear about this, then the code is very easy. It's obvious that if we think about decrement one element by $1$ every time, the minimum number of operations we need is the same as the number of operations we take to make every elements in the array equal to the minimal value. How many steps is that? Well, simply use the sum of the array $sum(are)$ minus $n \times min(are)$, and you get what you want.
