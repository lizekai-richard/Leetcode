class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Boolean> appeared = new HashMap<>();
        int i = 0, j = 0;
        int n = s.length();
        int maxLen = 0;
        while (i < n) {
            while (j < n && appeared.getOrDefault(s.charAt(j), false) == false) {
                appeared.put(s.charAt(j), true);
                j++;
            }
            maxLen = Math.max(maxLen, j - i);
            appeared.remove(s.charAt(i));
            i++;
        }
        return maxLen;
    }
}