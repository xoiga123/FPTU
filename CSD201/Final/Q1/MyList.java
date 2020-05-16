/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

public class MyList
 {Node head,tail;
  MyList() {head=tail=null;}
  boolean isEmpty()
   {return(head==null);
   }
  void clear() {head=tail=null;}

  void fvisit(Node p, RandomAccessFile f) throws Exception
   {if(p != null) f.writeBytes(p.info + " ");
   }

  void ftraverse(RandomAccessFile f) throws Exception
   {Node p = head;
    while(p!=null)
      {fvisit(p,f); // You will use this statement to write information of the node p to the file
       p=p.next;
      }
    f.writeBytes("\r\n");
   }

  void loadData(int k)  //do not edit this function
   {String [] a = Lib.readLineToStrArray("data.txt", k);
    int [] b = Lib.readLineToIntArray("data.txt", k+1);
    int [] c = Lib.readLineToIntArray("data.txt", k+2);
    int n = a.length;
    for(int i=0;i<n;i++) addLast(a[i],b[i],c[i]);
   }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
//==================================================================
  void addLast(String xCode, int xType, int xPrice)
   {//You should write here appropriate statements to complete this function.
       if (xCode.charAt(0) == 'A') return;
       if (isEmpty()){ 
           head = tail = new Node(new Corn(xCode, xType, xPrice));
           return;
       }
       tail.next = new Node(new Corn(xCode, xType, xPrice));
       tail = tail.next;

   }

  void f1() throws Exception
    {/* You do not need to edit this function. Your task is to complete the addLast  function
        above only.
     */
     clear();
     loadData(2);
     String fname = "f1.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     ftraverse(f);
     f.close();
    }  

//==================================================================
  public void insertPositionK(Corn x, int position) {
        if (isEmpty()) {
            head = tail = new Node(x);
        }
        int count = 1;
        Node p = head;
        while (p != null && count < position) {
            p = p.next;
            count++;
        }
        Node temp = p.next;
        p.next = new Node(x, temp);
    }
  
  void f2() throws Exception
    {clear();
     loadData(6);
     String fname = "f2.txt";
     File g123 = new File(fname);
     if(g123.exists()) g123.delete();
     RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
     ftraverse(f);
     Corn x, y;
     x = new Corn("X",1,2);
     y = new Corn("Y",3,4);
     //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
     insertPositionK(y, 2);
     insertPositionK(x, 3);


    //------------------------------------------------------------------------------------
     ftraverse(f);
     f.close();
    }  

//==================================================================
  public void deleteFirstCondition() {
      int min = getMin(head);
      System.out.println(min);
        Node p = head;
        while (p != null) {
            if (p.info.price == min) {
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }
    }
  public void dele(Node q) {
        Node f, p;
        f = null;
        p = head;
        while (p != null) {
            if (p == q) {
                break;
            }
            f = p;
            p = p.next;
        }
        if (p == null) {
            return;
        }
        if (f == null) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }
        f.next = p.next;
        if (f.next == null) {
            tail = f;
        }
    }
  
  int getMin(Node root){
      int min = 99999;
      Node running = root;
      while (running != null){
          if (running.info.price < min){
              min = running.info.price;
          }
          running = running.next;
      }
      return min;
  }
  void f3() throws Exception
   {clear();
    loadData(10);
    String fname = "f3.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    ftraverse(f);
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
     deleteFirstCondition();


    //------------------------------------------------------------------------------------
    ftraverse(f);
    f.close();
   }

//==================================================================
  public void sortEdited() {
        Node pi, pj;
        Corn x;
        pi = head;
        int count = 0;
        while (pi != null) {
            count++;
            pj = pi.next;
            int count1 = 0;
            while (pj != null) {
                count1++;
                if (pj.info.price > pi.info.price) {
                    x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
                pj = pj.next;
                if (count1 == 5 - count) {
                    break;
                }
            }
            pi = pi.next;
            if (count == 4) {
                break;
            }
        }
        System.out.println("we are counting to = " + count);
    }
  void f4() throws Exception
   {clear();
    loadData(14);
    String fname = "f4.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    ftraverse(f);
    //------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
     sortEdited();


    //------------------------------------------------------------------------------------
    ftraverse(f);
    f.close();
   }

 }
