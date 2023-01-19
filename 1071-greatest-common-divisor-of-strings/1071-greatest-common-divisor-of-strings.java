class Solution {
    private boolean check(String s, String t) {
        int n = s.length();
        int m = t.length();
        if (n % m != 0) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n / m; ++i) {
            sb.append(t);
        }
        return s.equals(sb.toString());
    }
    public String gcdOfStrings(String str1, String str2) {
        int n = Math.min(str1.length(), str2.length());
        String ans = "";
        for (int i = 0; i < n; ++i) {
            String prefix = str1.substring(0, i + 1);
            boolean c1 = check(str1, prefix);
            boolean c2 = check(str2, prefix);
            if (c1 && c2) {
                ans = prefix;
            }
        }
        return ans;
    }
}