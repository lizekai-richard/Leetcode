# Notes

This question asks for how many pairs of consecutive numbers there are that can add up to a number $n$.

So, we have: $n\ =\ i +(i+1)+\dots+j$.

However, if we write it in this way, it's quite hard to discover anything useful, or say the brute force following this equation is $O(N^3)$. 

Let's change modify the equation a little bit: $n\ =\ (x + 1) + (x + 2) + \dots + (x + k)$. In this way, we are actually thinking about finding a start point $x$ and the length of the consecutive sequence $k$. 

Then, $n\ =\ k \cdot x + (1+2+\dots+k)\ =\ k \cdot x + \frac{k(k+1)}{2}$

So, $x\ =\ \frac{n}{k} - \frac{k+1}{2}$. Since $k$ represents the length of the consecutive sequence, we can enumerate $k$. Since $x$ should be a positive integer, we have $\frac{n}{k}-\frac{k+1}{2} \ge 0$, which gives us $k \le \sqrt{2n+\frac{1}{4}}-\frac{1}{2}$.

Finally, we have the solution: enumerate $k$ from $1$ to $\sqrt{2n+\frac{1}{4}}-\frac{1}{2}$, if the current $k$ satisfies that $x\ =\ \frac{n}{k} - \frac{k+1}{2} $ is an integer, we add one to the counter.

