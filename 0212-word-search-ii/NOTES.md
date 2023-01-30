# Notes

The most direct way of thinking is that for every word in the word list, we do a DFS search with backtracking to determine where it exists in the board. However, this solution is meant to be TLE because the asymptotic running time is $O(s\cdot 3^{L})$ where $s$ is the size of word list ($3 \times 10^4$) and $L$ is the maximum length of a word ($10$). What's the bottleneck here? Obviously, it's the size of the word list. But can we search without enumerating each word?

This is when the most powerful data strcuture for strings comes in handy, which is out **Trie**. For each word to be searched, we store it in the Trie. Then, instead of considering each word, we consider each cell on the board. For each cell, if there exists some word starting with it, then we do a backtracking search. How to check if some word starts with a character? Normally, we would go through all words and check, but that makes us back to origin. Now we have Trie, it helps us do that easily. Just check if any child of the current node contains that character.

<img src="NOTES.assets/Screenshot 2023-01-30 at 7.04.50 PM.png" alt="Screenshot 2023-01-30 at 7.04.50 PM" style="zoom:50%;" />

Using Trie reduces the time complexity to $O(nm\cdot3^{L})$ which is acceptable.

```java
// definition of a Trie node
class TrieNode {
    HashMap<Character, TrieNode> children = new HashMap<>();
    String word = null;
    public TrieNode() {}
}

// construct the Trie
TrieNode root = new TrieNode();
for (String word: words) {
    TrieNode node = root;
    for (char c: word.toCharArray()) {
        if (node.children.containsKey(c)) {
            node = node.children.get(c);
        } else {
            node.children.put(c, new TrieNode());
            node = node.children.get(c);
        }
    }
    node.word = word;
}
```

