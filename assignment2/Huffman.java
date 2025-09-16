package assignment2;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Huffman {
    BinaryTrie codeTrie;// This will store the optimal binary trie (Huffman tree) for encoding/decoding.
    Map<Character, String> codeTable;// This table maps characters to their binary Huffman code representations.

    //Constructor for the Huffman class
    // Takes an input string 's' and generates the optimal Huffman code for it.
    public Huffman(String s)
    {
        //Count the frequency of each character in the input string.
        Map<Character,Integer> freq = new HashMap<Character,Integer>();// A map to store the frequency of each character.
        for (char c : s.toCharArray()) {
            // For each character, increment its frequency count.
            freq.put(c, freq.getOrDefault(c, 0) + 1);       // count the frequencies of each character in s
        }
        //Create a list of BinaryTrie objects, one for each character in the string, using the frequency map.
        Collection<BinaryTrie> L = new LinkedList<BinaryTrie>();// A collection to hold the binary tries.

        // For each character and its frequency in the map, create a BinaryTrie and add it to the list.
        freq.forEach((c,f) -> {  L.add(new BinaryTrie(c,f)); } );       // create a binary trie for each character

        //Convert the list of BinaryTrie objects to a heap.
        // The heap will be used to construct the optimal Huffman tree
        HeapOfBinaryTries H = new HeapOfBinaryTries(L.toArray(new BinaryTrie[L.size()])); // store these tries in the heap data structure

        // `findOptimalCode` builds the optimal binary trie (Huffman tree).
        codeTrie = findOptimalCode(H);              //Find the optimal Huffman code by combining BinaryTries in the heap to form the codeTrie.

        //Create the code table that maps characters to their binary Huffman code.
        codeTable = codeTrie.createCodeTable(); // This maps each character to its Huffman code.
    }

    // TASK 3.B
    //Method to construct the optimal Huffman code from a heap of BinaryTries.
    private static BinaryTrie findOptimalCode(HeapOfBinaryTries H) {
        // While there is more than one BinaryTrie in the heap, we keep combining the two smallest tries.
        while (H.size() > 1) {
            //Extract the two minimum BinaryTries from the heap the ones with the smallest frequencies.
            BinaryTrie t1 = H.extractMin();
            BinaryTrie t2 = H.extractMin();
            //Combine the two extracted BinaryTries into a new combined BinaryTrie.
            BinaryTrie combined = new BinaryTrie(t1, t2);
            //Insert the combined BinaryTrie back into the heap.
            H.insert(combined); }
        //Return the final BinaryTrie, which represents the Huffman tree.
        return H.extractMin();
    }


    public void printCodeTable() {
        codeTable.forEach((c,b) -> {
            // Print each character and its binary Huffman code.
            System.out.println("'" + c + "' -> " + b);
        });
    }

    //Method to encode the input string using Huffman code table.
    public String encode(String s)
    {
        // StringBuilder to construct the encoded string.
        StringBuilder sb = new StringBuilder();

        // For each character in the input string, append its corresponding Huffman code to the result.
        for (char c : s.toCharArray()) {
            sb.append(codeTable.get(c));// Get the binary Huffman code for the character `c` and append it.
        }
        // Return the final encoded string.
        return sb.toString();
    }
    // Method to decode a binary string 's' back to its original string using the Huffman code trie.
    public String decode(String s)
    {
        BinaryTrie n = codeTrie;// Start decoding from the root of the Huffman tree.
        StringBuilder sb = new StringBuilder();// StringBuilder to build the decoded string.

        // For each bit in the input string 's', traverse the Huffman tree to find the corresponding character.
        for (char c : s.toCharArray()) {
            if (c == '0') {
                n = n.getLeft();// Move to the left child of the current node if the bit is '0'.
            } else if (c == '1') {
                n = n.getRight();// Move to the right child of the current node if the bit is '1'.
            }

            //if we reach a leaf node, append character to the decoded string
            if (n.getCharacter()!=null) {
                sb.append(n.getCharacter());// Append the character found at the leaf node.
                n = codeTrie;// Reset back to the root of the Huffman tree to start decoding the next character.
            }
        }
        // Return the fully decoded string.
        return sb.toString();
    }

    public static void main(String[] args) {
        //Define the input string:
        String s = "this is a small sentence to find the optimal binary code for";
        System.out.println(s);//print the original string
        //Create a Huffman object to generate the Huffman code for the input string:
        Huffman code = new Huffman(s);
        //Print the code table:
        code.printCodeTable();
        // Encode the string 's' using Huffman code and print the encoded binary string:
        String b = code.encode(s);
        System.out.println(b);//Print encoded string
        //Decode the encoded string 'b' back to the original string and print it:
        String t = code.decode(b);
        System.out.println(t);//Print decoded string(should be the same as original string)
    }
}
