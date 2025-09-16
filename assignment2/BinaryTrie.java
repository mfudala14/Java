package assignment2;

import java.util.HashMap;
import java.util.Map;

// The BinaryTrie class represents a node in a binary trie for Huffman encoding.
public class BinaryTrie {
    private int freq = 0;// Frequency of the character in the trie node.
    private Character c = null;// Character stored in this node, or null if it's an internal node.
    private BinaryTrie left = null;//Left child of the node
    private BinaryTrie right = null;//Right child of the node

    // create a new leaf node for a Huffman code trie
    public BinaryTrie(char c, int freq)
    {
        this.freq = freq;// Initialize frequency with the provided value.
        this.c = c; // Initialize character with the provided character.
    }

    // create a new inner node for a Huffman code trie
    public BinaryTrie(BinaryTrie left, BinaryTrie right)
    {
        freq = left.freq + right.freq;// The frequency of the internal node is the sum of its children's frequencies.
        this.left = left;//Set the left child
        this.right = right;//Set the right child
    }

    // compare two nodes of a Huffman code trie
    public Boolean compare(BinaryTrie T)
    {
        return freq<T.freq;// Compares the frequency of the current node with the provided node.
    }

    // create a code table for binary encoding
    public Map<Character, String> createCodeTable()
    {
        // Create a new empty HashMap for the code table:
        Map<Character, String> codeTable = new HashMap<Character, String>();
        // If this node is a leaf node add it to the code table with an empty string.
        if (c!=null) {
            codeTable.put(c,"");// Assign the empty string as the code for the character in the leaf node.
        }
        else {
            // If this is not a leaf node, recursively generate the code table for the left and right subtrees.
            if (left != null) {
                //create the code table for the left subtree, prepending "0" to each code.
                left.createCodeTable().forEach((c, b) -> {
                    codeTable.put(c, "0" + b);// Prepend "0" to each code from the left child.
                });
            }
            if (right != null) {
                //create the code table for the right subtree, prepending "1" to each code.
                right.createCodeTable().forEach((c, b) -> {
                    codeTable.put(c, "1" + b);// Prepend "1" to each code from the right child.
                });
            }
        }
        return codeTable;// Return the completed code table.
    }

    // Getter method to retrieve the left child of the current node.
    public BinaryTrie getLeft()
    {
        return left;
    }//return left child

    // Getter method to retrieve the right child of the current node.
    public BinaryTrie getRight()
    {
        return right;
    }//return the right child

    // Getter method to retrieve the character stored in this node (for leaf nodes).
    public Character getCharacter()
    {
        return c;
    }// Return the character stored in this node
}
