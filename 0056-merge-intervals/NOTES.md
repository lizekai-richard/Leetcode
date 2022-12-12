# Notes

For this question, there is only one thing that you should be careful with: **The sorting order and merging order must be consistent**.

If you sort by the left boundary, then you should merge from left to right. Vice versa. The reason is as follows: 

Assume we sort by right boundary but merge from left to right, then a typical counter-example would be something like this:

<img src="Picture.png" alt="Picture" style="zoom:22%;" />

We can see that the first four intervals cannot be merged but the last one can include all intervals. However, by our implementation, only the last two intervals will be merged.