class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        
        for (int i = 0; i < n; ++i) {
            isPalindrome[i][i] = true;
        }
        
        int longestStart = 0, longestEnd = 0;
        for (int i = n - 2; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                if (i + 1 <= j - 1) {
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && isPalindrome[i + 1][j - 1];
                } else {
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j));
                }
                
                if (isPalindrome[i][j] && (j - i + 1) > (longestEnd - longestStart + 1)) {
                    longestStart = i;
                    longestEnd = j;
                }
            }
        }
        
        return s.substring(longestStart, longestEnd + 1);
    }
}