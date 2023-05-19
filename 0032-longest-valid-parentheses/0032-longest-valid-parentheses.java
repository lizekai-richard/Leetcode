class Solution {
    public int longestValidParentheses(String s) {
        if (s.isBlank()) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int n = s.length();
        int ans = 0;
        stack.push(-1);
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    ans = Math.max(ans, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return ans;
    }
}