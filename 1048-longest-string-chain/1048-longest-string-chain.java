class Solution {
    private boolean isPredecessor(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        if (m - n != 1) {
            return false;
        }
        int numOfDifferent = 0;
        int p1 = 0, p2 = 0;
        while (p1 < n && p2 < m) {
            if (word1.charAt(p1) != word2.charAt(p2)) {
                if (numOfDifferent == 0) {
                    numOfDifferent++;
                } else {
                    return false;
                }
                p2++;
            } else {
                p1++;
                p2++;
            }
        }
        return true;
    }
    public int longestStrChain(String[] words) {
        int n = words.length;
        
        int[] dp = new int[n];
        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        
        Arrays.fill(dp, 1);
        int maxLen = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                String word1 = words[j];
                String word2 = words[i];
                if (isPredecessor(word1, word2)) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
}