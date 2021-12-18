package main.java.trie;


import java.util.HashMap;
import java.util.Map;

/**
 * 字典树 -- Trie
 *
 * @author LinJn
 * @since 2021/11/24 21:56
 */
public class Trie {

    private static class Node {

        int count;

        final Map<Character, Node> child;

        public Node() {
            this.count = 0;
            child = new HashMap<>();
        }
    }

    private final Node root;

    public Trie() {
        root = new Node();
    }

    public void insert(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            final char c = word.charAt(i);
            if (!curr.child.containsKey(c)) {
                curr.child.put(c, new Node());
            }
            curr = curr.child.get(c);
        }
        curr.count = curr.count + 1;
    }

    public boolean search(String word) {
        final Node node = this.find(word);
        if (node == null) {
            return false;
        }
        return node.count > 0;
    }

    public boolean startsWith(String prefix) {
        final Node node = this.find(prefix);
        return node != null;
    }

    private Node find(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            final char c = word.charAt(i);
            if (!curr.child.containsKey(c)) {
                return null;
            }
            curr = curr.child.get(c);
        }
        return curr;
    }
}
