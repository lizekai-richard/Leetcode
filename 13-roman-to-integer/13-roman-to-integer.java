class Solution {
    public void fillRomanToIntegerDict(HashMap<Character, Integer> dict) {
        dict.put('I', 1);
        dict.put('V', 5);
        dict.put('X', 10);
        dict.put('L', 50);
        dict.put('C', 100);
        dict.put('D', 500);
        dict.put('M', 1000);
    }
    public int romanToInt(String s) {
        HashMap<Character, Integer> romanDict = new HashMap<>();
        fillRomanToIntegerDict(romanDict);
        int result = 0;
        for(int i = 0; i < s.length(); ++i) {
            char curRoman = s.charAt(i); 
            if(i + 1 < s.length()) {
                char nextRoman = s.charAt(i + 1);
                if(romanDict.get(curRoman) < romanDict.get(nextRoman)) {
                    result = result - romanDict.get(curRoman);
                } else {
                    result = result + romanDict.get(curRoman);
                }
            } else {
                result = result + romanDict.get(curRoman);
            }
        }
        return result;
    }
}