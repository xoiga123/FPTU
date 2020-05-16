/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 *
 * @author Hoai Bao
 */
public class Assignment2 {
    PriorityQueue<Node> pqueue = new PriorityQueue<>();
    Node root;
    Map<Character, String> table = new HashMap<>();
    Map<String, Character> decodeTable = new HashMap<>();
    
    String encode(String s){
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch: s.toCharArray()){
            if (!freq.containsKey(ch)){
                freq.put(ch, 1);
            }
            else freq.put(ch, freq.get(ch) + 1);
        }
        
        System.out.println("Character frequency: " + freq);
        
        for (char ch: freq.keySet()){
            pqueue.add(new Node(ch, new String(), freq.get(ch), null, null));
        }
        
        while (pqueue.size() > 1){
            Node l = pqueue.poll();
            Node r = pqueue.poll();
            
            addPrefixCode(l, "0");
            addPrefixCode(r, "1");
            Node sum = new Node('-', new String(), l.count + r.count, l, r);
            pqueue.add(sum);
        }
        root = pqueue.poll();
        
        traverseTree(root);
        
        System.out.println("Encode table: " + table);
        
        String result = new String();
        for (char ch: s.toCharArray()){
            result += table.get(ch);
        }
        
        for (Entry<Character, String> entry: table.entrySet()){
            decodeTable.put(entry.getValue(), entry.getKey());
        }
        
        System.out.println("Encoded: " + result);
        System.out.println("That is a total of " + result.length() + " bits.");
        return result;
    }
    
    void traverseTree(Node node){
        if (node.letter != '-') table.put(node.letter, node.code);
        if (node.left != null) traverseTree(node.left);
        if (node.right != null) traverseTree(node.right);
    }
    
    void addPrefixCode(Node node, String prefix){
        node.code = prefix + node.code;
        if (node.left !=  null) addPrefixCode(node.left, prefix);
        if (node.right !=  null) addPrefixCode(node.right, prefix);
    }
    
    String decode(String s){
        StringBuffer holder = new StringBuffer();
        String result = new String();
        int i = 0;
        while (i < s.length()){
            holder.append(s.charAt(i));
            if (decodeTable.containsKey(holder.toString())){
                result += decodeTable.get(holder.toString());
                holder.setLength(0);
            }
            i++;
        }
        System.out.println("Decoded: " + result);
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Assignment2 run = new Assignment2();
        System.out.println("Please enter a string to be encoded: ");
        String s = sc.nextLine();
        String encoded = run.encode(s);
        String decoded = run.decode(encoded);
    }
    
}
