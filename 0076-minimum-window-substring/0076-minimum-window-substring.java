class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();
        
        for (int i = 0; i < t.length(); ++i) {
            if (!mapT.containsKey(t.charAt(i))) {
                mapT.put(t.charAt(i), 1);
            } else {
                int v = mapT.get(t.charAt(i));
                mapT.put(t.charAt(i), v + 1);
            }
        }
        
        int numOfCharsInT = mapT.size();
        int numOfCharsInS = 0;
        
        int l = 0, r = 0;
        int windowLen = -1;
        int optimalL = 0, optimalR = 0;
        while (r < s.length()) {
            char c = s.charAt(r);
            int v = mapS.getOrDefault(c, 0);
            mapS.put(c, v + 1);
            
            if (mapT.containsKey(c) && mapT.get(c).intValue() == mapS.get(c).intValue()) {
                numOfCharsInS++;
            }
            
            while (l <= r && numOfCharsInS == numOfCharsInT) {
                if (windowLen == -1 || windowLen > r - l + 1) {
                    windowLen = r - l + 1;
                    optimalL = l;
                    optimalR = r;
                }
                
                char a = s.charAt(l);
                int p = mapS.get(a);
                mapS.put(a, p - 1);
                
                if (mapT.containsKey(a) && mapT.get(a).intValue() > mapS.get(a).intValue()) {
                    numOfCharsInS--;
                }
                l++;
            }
            
            r++;
        }
        
        if (windowLen == -1) {
            return "";
        } else {
            return s.substring(optimalL, optimalR + 1);
        }
    }
}
// class Solution {
//     public String minWindow(String s, String t) {

//         if (s.length() == 0 || t.length() == 0) {
//             return "";
//         }

//         // Dictionary which keeps a count of all the unique characters in t.
//         Map<Character, Integer> dictT = new HashMap<Character, Integer>();
//         for (int i = 0; i < t.length(); i++) {
//             int count = dictT.getOrDefault(t.charAt(i), 0);
//             dictT.put(t.charAt(i), count + 1);
//         }

//         // Number of unique characters in t, which need to be present in the desired window.
//         int required = dictT.size();

//         // Left and Right pointer
//         int l = 0, r = 0;

//         // formed is used to keep track of how many unique characters in t
//         // are present in the current window in its desired frequency.
//         // e.g. if t is "AABC" then the window must have two A's, one B and one C.
//         // Thus formed would be = 3 when all these conditions are met.
//         int formed = 0;

//         // Dictionary which keeps a count of all the unique characters in the current window.
//         Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

//         // ans list of the form (window length, left, right)
//         int[] ans = { -1, 0, 0 };

//         while (r < s.length()) {
//             // Add one character from the right to the window
//             char c = s.charAt(r);
//             int count = windowCounts.getOrDefault(c, 0);
//             windowCounts.put(c, count + 1);

//             // If the frequency of the current character added equals to the
//             // desired count in t then increment the formed count by 1.
//             if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
//                 formed++;
//             }

//             // Try and contract the window till the point where it ceases to be 'desirable'.
//             while (l <= r && formed == required) {
//                 c = s.charAt(l);
//                 // Save the smallest window until now.
//                 if (ans[0] == -1 || r - l + 1 < ans[0]) {
//                     ans[0] = r - l + 1;
//                     ans[1] = l;
//                     ans[2] = r;
//                 }

//                 // The character at the position pointed by the
//                 // `Left` pointer is no longer a part of the window.
//                 windowCounts.put(c, windowCounts.get(c) - 1);
//                 if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
//                     formed--;
//                 }

//                 // Move the left pointer ahead, this would help to look for a new window.
//                 l++;
//             }

//             // Keep expanding the window once we are done contracting.
//             r++;
//         }

//         return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
//     }
// }