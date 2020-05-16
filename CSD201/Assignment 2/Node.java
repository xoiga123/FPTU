/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment2;

/**
 *
 * @author Hoai Bao
 */
public class Node implements Comparable<Node> {
    char letter;
    String code;
    int count;
    Node left;
    Node right;

    public Node(char letter, String code, int count, Node left, Node right) {
        this.letter = letter;
        this.code = code;
        this.count = count;
        this.left = left;
        this.right = right;
    }

    
    
    @Override
    public int compareTo(Node o) {
        return this.count - o.count;
    }
}
