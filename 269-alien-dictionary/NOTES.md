# Notes

This question is a very good practice for graph modeling and topological sort. Also, this question can teach us how to detect a cycle in a directed graph.

### Topological Search

Recall that what topological search does is to sort items with **constraints**. Now, in this question, we are decoding the alien dictionary while a dictionary is just a number of constraints! Think about the "smaller than" relationship between two characters $x$ and $y$. If we model this question using a graph, it means $x$ should always comes first comparing with $y$. That indicates a directed edge from $x$ to $y$. Based on this idea, we can use given words to construct a adjacency matrix or list to represent the alien alpahbet graph. And then, use topological search to get a valid ordering.

What I implemented is using adjacency matrix and post-order DFS. Below is a BFS(Khan's algorithm) version with adjacency list.

```java
public String alienOrder(String[] words) {
    
    // Step 0: Create data structures and find all unique letters.
    Map<Character, List<Character>> adjList = new HashMap<>();
    Map<Character, Integer> counts = new HashMap<>();
    for (String word : words) {
        for (char c : word.toCharArray()) {
            counts.put(c, 0);
            adjList.put(c, new ArrayList<>());
        }
    }
    
    // Step 1: Find all edges.
    for (int i = 0; i < words.length - 1; i++) {
        String word1 = words[i];
        String word2 = words[i + 1];
        // Check that word2 is not a prefix of word1.
      	// This is an edge case that is easy to forget about
        if (word1.length() > word2.length() && word1.startsWith(word2)) {
            return "";
        }
        // Find the first non match and insert the corresponding relation.
        for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
            if (word1.charAt(j) != word2.charAt(j)) {
                adjList.get(word1.charAt(j)).add(word2.charAt(j));
              	// the in-degree array
                counts.put(word2.charAt(j), counts.get(word2.charAt(j)) + 1);
                break;
            }
        }
    }
    
    // Step 2: Breadth-first search.
    StringBuilder sb = new StringBuilder();
    Queue<Character> queue = new LinkedList<>();
    for (Character c : counts.keySet()) {
        if (counts.get(c).equals(0)) {
            queue.add(c);
        }
    }
    while (!queue.isEmpty()) {
        Character c = queue.remove();
        sb.append(c);
        for (Character next : adjList.get(c)) {
            counts.put(next, counts.get(next) - 1);
            if (counts.get(next).equals(0)) {
                queue.add(next);
            }
        }
    }
    
    if (sb.length() < counts.size()) {
        return "";
    }
    return sb.toString();
}
```

### Detecting Cycles

If using Khan's algorithm, we don't need to consider the cycle problem specifically. However, when it comes to DFS, a detecting method will come in handy. While we know the difference between DFS and BFS is simply the data structure we use which results in the order we search. Now, when will there be a cycle? If we think about it, when more than one node $u$ appear in the stack at the same time, it means there will be a cycle. Let's assume the stack looks as follow:
$$
[u,\ a_{k+1},\ ...,\ a_{k+p+1},\ u,\ ...]
$$
Since we are expanding $u$ and somehow we go back to itself, then there must be a path from $u$ to $u$ which is a cycle. 

