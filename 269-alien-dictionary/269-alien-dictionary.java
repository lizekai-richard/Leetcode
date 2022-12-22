public class AlienDictionary {
    public boolean dfs(int node, int n, int[][] adjMatrix, HashMap<Integer, Boolean> visited, StringBuilder s,
                    HashMap<Integer, Character> indexToChar) {
        if (visited.containsKey(node)) {
            return visited.get(node);
        }
        visited.put(node, false);
        for (int adj = 0; adj < n; ++adj) {
            if (adjMatrix[node][adj] == 1) {
                boolean result = dfs(adj, n, adjMatrix, visited, s, indexToChar);
                if (!result) return false;
            }
        }
        visited.put(node, true);
        s.append(indexToChar.get(node));
        return true;
    }

    public String topoSort(int[][] adjMatrix, HashMap<Integer, Character> indexToChar,
                           int n) {
        StringBuilder result = new StringBuilder();
        HashMap<Integer, Boolean> visited = new HashMap<>();
        for (int node = 0; node < n; ++node) {
            boolean noCycle = dfs(node, n, adjMatrix, visited, result, indexToChar);
            if (!noCycle) {
                return "";
            }
        }
        result.reverse();
        return result.toString();
    }
    public String alienOrder(String[] words) {
        HashMap<Character, Integer> charToIndex = new HashMap<>();
        HashMap<Integer, Character> indexToChar = new HashMap<>();

        for (String s: words) {
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (!charToIndex.containsKey(c)) {
                    int index = charToIndex.size();
                    charToIndex.put(c, index);
                    indexToChar.put(index, c);
                }
            }
        }

        int n = charToIndex.size();
        int numEdges = 0;
        int[][] adjMatrix = new int[n][n];

        for (int i = 0; i < words.length; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                String s1 = words[i];
                String s2 = words[j];
                if (s1.length() > s2.length() && s1.startsWith(s2)) {
                    return "";
                }
                int p1 = 0, p2 = 0;
                while (p1 < s1.length() && p2 < s2.length()) {
                    char c1 = s1.charAt(p1);
                    char c2 = s2.charAt(p2);
                    int index1 = charToIndex.get(c1);
                    int index2 = charToIndex.get(c2);
                    if (c1 != c2) {
                        adjMatrix[index1][index2] = 1;
                        numEdges++;
                        break;
                    }
                    p1++;
                    p2++;
                }
            }
        }
        
        return topoSort(adjMatrix, indexToChar, n);
    }
}