class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String prefix = "";
        for(int i = 0; i < strs[0].length(); ++i) {
            String tempPrefix = prefix + strs[0].charAt(i);
            boolean allOk = true;
            for(int j = 1; j < strs.length; ++j) {
                if(!strs[j].startsWith(tempPrefix)) {
                    allOk = false;
                    break;
                }
            }
            if(!allOk) {
                break;
            } else {
                prefix = tempPrefix;
            }
        }
        return prefix;
    }
}