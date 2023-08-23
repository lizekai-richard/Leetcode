class FileSystem {
    HashMap<String, ArrayList<String>> directories;
    HashMap<String, String> files;

    public FileSystem() {
        this.directories = new HashMap<>();
        this.files = new HashMap<>();
        
        this.directories.put("/", new ArrayList<>());
    }
    
    public List<String> ls(String path) {
        if (files.containsKey(path)) {
            String[] names = path.split("/", -1);
            ArrayList<String> res = new ArrayList<>();
            res.add(names[names.length - 1]);
            return res;
        }
        ArrayList<String> entries = directories.get(path);
        Collections.sort(entries);
        return entries;
    }
    
    public void mkdir(String path) {
        ArrayList<String> newEntry = new ArrayList<>();
        directories.put(path, newEntry);
        
        String[] entries = path.split("/", -1);
        String[] parent = Arrays.copyOfRange(entries, 1, entries.length - 1);
        
        String currentPath = "/";
        String prevPath = "/";
        for (String s: parent) {
            currentPath += s;
            if (!directories.containsKey(currentPath)) {
                ArrayList<String> e = new ArrayList<>();
                directories.put(currentPath, e);
                
                ArrayList<String> prevEntry = directories.get(prevPath);
                prevEntry.add(s);
                directories.put(prevPath, prevEntry);
            }
            prevPath = currentPath;
            currentPath += "/";
        }

        ArrayList<String> parentEntry = directories.get(prevPath);
        parentEntry.add(entries[entries.length - 1]);
        directories.put(prevPath, parentEntry);
    }
    
    public void addContentToFile(String filePath, String content) {
        if (!directories.containsKey(filePath)) {
            this.mkdir(filePath);
        }
        if (!files.containsKey(filePath)) {
            files.put(filePath, content);
        } else {
            String oriContent = files.get(filePath);
            String newContent = oriContent + content;
            files.put(filePath, newContent);
        }
    }
    
    public String readContentFromFile(String filePath) {
        String content = files.get(filePath);
        return content;
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */