// class Solution {
//     private int m;
//     private int n;
//     private char[][] board;
//     private boolean[][] visited;
    
//     private boolean dfs(String word, String target, int x, int y) {
//         if (word.equals(target)) {
//             return true;
//         }
//         if (x < 0 || x >= m || y < 0 || y >= n) {
//             return false;
//         }
//         if (word.length() >= target.length()) {
//             return false;
//         }
//         visited[x][y] = true;
//         String newWord = word + board[x][y];
//         if (newWord.equals(target)) {
//             return true;
//         }
//         boolean result1 = false;
//         boolean result2 = false;
//         boolean result3 = false;
//         boolean result4 = false;
//         if (x + 1 < m && !visited[x + 1][y]) {
//             result1 = dfs(newWord, target, x + 1, y);
//         }
//         if (x - 1 >= 0 && !visited[x - 1][y]) {
//             result2 = dfs(newWord, target, x - 1, y);
//         }
//         if (y + 1 < n && !visited[x][y + 1]) {
//             result3 = dfs(newWord, target, x, y + 1);
//         }
//         if (y - 1 >= 0 && !visited[x][y - 1]) {
//             result4 = dfs(newWord, target, x, y - 1);
//         }
//         visited[x][y] = false;
//         return result1 || result2 || result3 || result4;
//     }
    
//     public List<String> findWords(char[][] board, String[] words) {
//         List<String> ans = new ArrayList<>();
//         HashMap<Character, List<int[]>> coorOfChar = new HashMap<>();
//         this.m = board.length;
//         this.n = board[0].length;
//         this.board = board;
        
//         for (int i = 0; i < m; ++i) {
//             for (int j = 0; j < n; ++j) {
//                 char c = board[i][j];
//                 if (!coorOfChar.containsKey(c)) {
//                     coorOfChar.put(c, new ArrayList<int[]>());
//                 }
//                 coorOfChar.get(c).add(new int[]{i, j});
//             }
//         }
        
//         for (String word: words) {
//             boolean flag = true;
//             for (int i = 0; i < word.length(); ++i) {
//                 if (!coorOfChar.containsKey(word.charAt(i))) {
//                     flag = false;
//                 }
//             }
//             if (!flag) {
//                 continue;
//             }
//             char start = word.charAt(0);
//             List<int[]> coors = coorOfChar.get(start);
//             visited = new boolean[m][n];
//             for (int[] coor: coors) {
//                 if (dfs("", word, coor[0], coor[1])) {
//                     ans.add(word);
//                     break;
//                 }
//             }
//         }
//         return ans;
//     }
// }
class TrieNode {
  HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
  String word = null;
  public TrieNode() {}
}

class Solution {
  char[][] _board = null;
  ArrayList<String> _result = new ArrayList<String>();

  public List<String> findWords(char[][] board, String[] words) {

    // Step 1). Construct the Trie
    TrieNode root = new TrieNode();
    for (String word : words) {
      TrieNode node = root;

      for (Character letter : word.toCharArray()) {
        if (node.children.containsKey(letter)) {
          node = node.children.get(letter);
        } else {
          TrieNode newNode = new TrieNode();
          node.children.put(letter, newNode);
          node = newNode;
        }
      }
      node.word = word;  // store words in Trie
    }

    this._board = board;
    // Step 2). Backtracking starting for each cell in the board
    for (int row = 0; row < board.length; ++row) {
      for (int col = 0; col < board[row].length; ++col) {
        if (root.children.containsKey(board[row][col])) {
          backtracking(row, col, root);
        }
      }
    }

    return this._result;
  }
  
  private void backtracking(int row, int col, TrieNode parent) {
    Character letter = this._board[row][col];
    TrieNode currNode = parent.children.get(letter);

    // check if there is any match
    if (currNode.word != null) {
      this._result.add(currNode.word);
      currNode.word = null;
    }

    // mark the current letter before the EXPLORATION
    this._board[row][col] = '#';

    // explore neighbor cells in around-clock directions: up, right, down, left
    int[] rowOffset = {-1, 0, 1, 0};
    int[] colOffset = {0, 1, 0, -1};
    for (int i = 0; i < 4; ++i) {
      int newRow = row + rowOffset[i];
      int newCol = col + colOffset[i];
      if (newRow < 0 || newRow >= this._board.length || newCol < 0
          || newCol >= this._board[0].length) {
        continue;
      }
      if (currNode.children.containsKey(this._board[newRow][newCol])) {
        backtracking(newRow, newCol, currNode);
      }
    }

    // End of EXPLORATION, restore the original letter in the board.
    this._board[row][col] = letter;

    // Optimization: incrementally remove the leaf nodes
    if (currNode.children.isEmpty()) {
      parent.children.remove(letter);
    }
  }
}
