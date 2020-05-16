/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.io.*;
import java.util.*;

public class BSTree {
   Node root;
   int count;
   Boolean deleted = false;
   BSTree() {root=null;}
   boolean isEmpty() {
       return(root==null);
      }
   void clear() {
       root=null;
      }
   void visit(Node p) {
      System.out.print("p.info: ");
      if(p != null) System.out.println(p.info + " ");
     }
   void fvisit(Node p, RandomAccessFile f) throws Exception {
      if(p != null) f.writeBytes(p.info + " ");
     }
   void preOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      fvisit(p,f);
      preOrder(p.left,f);
      preOrder(p.right,f);
     }
   void inOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      inOrder(p.left,f);
      fvisit(p,f);
      inOrder(p.right,f);
     }
   void postOrder(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      postOrder(p.left,f);
      postOrder(p.right,f);
      fvisit(p,f);
     }
  void breadth(Node p, RandomAccessFile f) throws Exception {
     if(p==null) return;
     Queue q = new Queue();
     q.enqueue(p);Node r;
     while(!q.isEmpty()) {
        r = q.dequeue();
        fvisit(r,f);
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
    }
   void loadData(int k) { //do not edit this function
      String [] a = Lib.readLineToStrArray("data.txt", k);
      int [] b = Lib.readLineToIntArray("data.txt", k+1);
      int [] c = Lib.readLineToIntArray("data.txt", k+2);
      int n = a.length;
      for(int i=0;i<n;i++) insert(a[i],b[i],c[i]);
     }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================

    void insert(Corn x) {
        Node q = new Node(x);
        if (isEmpty()) {
            root = q;
            return;
        }
        Node f, p;
        f = null;
        p = root;
        while (p != null) {
            if (p.info.type == x.type) {
                System.out.println("The key " + x.type + " already exists, no insertion");
                return;
            }
            f = p;
            if (x.type < p.info.type) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (x.type < f.info.type) {
            f.left = q;
        } else {
            f.right = q;
        }
    }
  void insert(String xCode, int xType, int xPrice) {
    //You should insert here statements to complete this function
    if (xCode.charAt(0) != 'B') {
            insert(new Corn(xCode, xType, xPrice));
        }

   }

//Do not edit this function. Your task is to complete insert function above only.
  void f1() throws Exception {
    clear();
    loadData(2);
    String fname = "f1.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    inOrder(root,f);
    f.writeBytes("\r\n");
    f.close();
   }  
  
//=============================================================
  void inOrder2(Node p, RandomAccessFile f) throws Exception {
      if(p==null) return;
      inOrder2(p.left,f);
      if (p.info.price < 5) fvisit(p,f);
      inOrder2(p.right,f);
     }
  void f2() throws Exception {
    clear();
    loadData(6);
    String fname = "f2.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    inOrder(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
    inOrder2(root, f);


    //------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }  

//=============================================================
  void deleByCopy(int xPrice) {
        if (root == null) {
            System.out.println(" The tree is empty, no deletion");
            return;
        }
        Node f, p; // f will be the father of p
        p = root;
        f = null;
        while (p != null) {
            if (p.info.type == xPrice) {
                break;//Found key x
            }
            if (xPrice < p.info.type) {
                f = p;
                p = p.left;
            } else {
                f = p;
                p = p.right;
            }
        }
        if (p == null) {
            System.out.println(" The key " + xPrice + " does not exist, no deletion");
            return;
        }
        if (p.left == null && p.right == null) // p is a leaf node
        {
            if (f == null) // The tree is one node
            {
                root = null;
            } else {
                if (f.left == p) {
                    f.left = null;
                } else {
                    f.right = null;
                }
            }
            return;
        }

        if (p.left != null && p.right == null) // p has only left child
        {
            if (f == null) // p is a root
            {
                root = p.left;
            } else {
                if (f.left == p) // p is a left child
                {
                    f.left = p.left;
                } else {
                    f.right = p.left;
                }
            }
            return;
        }

        if (p.left == null && p.right != null) // p has only right child
        {
            if (f == null) // p is a root
            {
                root = p.right;
            } else {
                if (f.left == p) // p is aleft child
                {
                    f.left = p.right;
                } else {
                    f.right = p.right;
                }
            }
            return;
        }

        if (p.left != null && p.right != null) // p has both left and right children
        {
            Node q, fr, rp; // p's key will be replaced by rp's one
            fr = null;
            q = p.left;
            rp = q;
            while (rp.right != null) {
                fr = rp;
                rp = rp.right; // Find the right most node on the left sub-tree
            }
            p.info = rp.info;
            if (fr == null) // rp is just a left son of p 
            {
                p.left = rp.left;
            } else {
                fr.right = rp.left;
            }
        }

    }
  void delef3(Node running){
      if (running.info.type < 10 && deleted == false){
            deleByCopy(running.info.type);
            deleted = true;
            return;
        }
        if (running.left != null && deleted == false) delef3(running.left);
        if (running.right != null && deleted == false) delef3(running.right);
    }
  void f3() throws Exception {
    clear();
    loadData(10);
    String fname = "f3.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    preOrder(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
    delef3(root);

    //------------------------------------------------------------------------------------
    preOrder(root,f);
    f.writeBytes("\r\n");
    f.close();
   }  

//=============================================================
  Node preOrder2(Node p, RandomAccessFile f) throws Exception {
      if (p != null && p.info.type == 5) return p;
      preOrder2(p.left,f);
      preOrder2(p.right,f);
      return null;
     }
  
  Node breadth2(Node p, RandomAccessFile f) throws Exception {
     Queue q = new Queue();
     q.enqueue(p);Node r;
     Node found = null;
     while(!q.isEmpty()) {
        r = q.dequeue();
        if (r.info.type == 5){
            found = r;
            return found;
        }
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
     return null;
    }
  int breadth3(Node p, RandomAccessFile f) throws Exception {
     if(p==null) return 0;
     Queue q = new Queue();
     q.enqueue(p);Node r;
     int count = 0;
     while(!q.isEmpty()) {
        r = q.dequeue();
        count++;
        if(r.left!=null) q.enqueue(r.left);
        if(r.right!=null) q.enqueue(r.right);
       }
     return count;
    }
  void f4() throws Exception {
    clear();
    loadData(14);;
    String fname = "f4.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    breadth(root,f);
    f.writeBytes("\r\n");
    //------------------------------------------------------------------------------------
    /*You must keep statements pre-given in this function.
      Your task is to insert statements here, just after this comment,
      to complete the question in the exam paper.*/
    Node chosen = breadth2(root, f);
    
    chosen.info.price = breadth3(chosen, f);
    

     
    //------------------------------------------------------------------------------------
    breadth(root,f);
    f.writeBytes("\r\n");
    f.close();
   }  

 }
