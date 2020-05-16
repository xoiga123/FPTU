/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)============================================
import java.io.*;
import java.util.*;
//-------------------------------------------------------------------------------
public class Graph {
   int count = 0;
  int [][] a; int n;
  char v[];
  int deg[];
  Graph() {
    v = "ABCDEFGHIJKLMNOP".toCharArray();
    deg = new int[20];
    a = new int[20][20];
    n = 0;
   }

  void loadData(int k) {  //do not edit this function
    RandomAccessFile f;int i,j,x;
    String s;StringTokenizer t;
    a = new int[20][20];
    try {
     f = new RandomAccessFile("data.txt","r");
     for(i=0;i<k;i++) f.readLine();
     s = f.readLine();s = s.trim();
     n = Integer.parseInt(s);
     for(i=0;i<n;i++) {
       s = f.readLine();s = s.trim();
       t = new StringTokenizer(s);
       for(j=0;j<n;j++) { 
         x = Integer.parseInt(t.nextToken().trim());
         a[i][j] = x;
        }
       }
     f.close();
     }
    catch(Exception e) {}

   }

  void dispAdj() {
    int i,j;
    for(i=0;i<n;i++) {
      System.out.println();
      for(j=0;j<n;j++)
        System.out.printf("%4d",a[i][j]);
     }
   }

  void fvisit(int i, RandomAccessFile f) throws Exception {
    f.writeBytes("  "+v[i]);
   }

 void fdispAdj(RandomAccessFile f) throws Exception { 
    int i,j;
    f.writeBytes("n = "+n+"\r\n");
    for(i=0;i<n;i++) {
      f.writeBytes("\r\n");
      for(j=0;j<n;j++)  f.writeBytes("  " + a[i][j]);
     }
    f.writeBytes("\r\n");
   }

  void breadth(boolean [] en, int i, RandomAccessFile f) throws Exception {
    Queue q = new Queue();
    int r,j;
    q.enqueue(i); en[i]=true;
    while(!q.isEmpty()) {
      r = q.dequeue();
      fvisit(r,f);
      for(j=0;j<n;j++) {
        if(!en[j] && a[r][j]>0) {
         q.enqueue(j);en[j]=true;
        }
       }
     }
   }

  void breadth(int  k, RandomAccessFile f) throws Exception {
    boolean [] en = new boolean[20];
    int i;
    for(i=0;i<n;i++) en[i]=false;
    breadth(en,k,f);
    for(i=0;i<n;i++) 
      if(!en[i]) breadth(en,i,f);
   }

 void depth(boolean [] visited,int k, RandomAccessFile f) throws Exception {
    fvisit(k,f);visited[k]=true;
    for(int i=0;i<n;i++) {
      if(!visited[i] && a[k][i]>0) depth(visited,i,f);
     }
   }
  void depth(int k, RandomAccessFile f) throws Exception {
    boolean [] visited = new boolean[20];
    int i;
    for(i=0;i<n;i++) visited[i]=false;
    depth(visited,k,f);
    for(i=0;i<n;i++) 
       if(!visited[i]) depth(visited,i,f);
   }

//===========================================================================
//(2)===YOU CAN EDIT OR EVEN ADD NEW FUNCTIONS IN THE FOLLOWING PART========
//===========================================================================
//=================================================================
  void depth2(boolean [] visited,int k, RandomAccessFile f) throws Exception {
    if (count < 7){
        fvisit(k,f);
        count++;
        System.out.println(count);
    }
    visited[k]=true;
    for(int i=0;i<n;i++) {
      if(!visited[i] && a[k][i]>0) depth2(visited,i,f);
     }
   }
  void depth2(int k, RandomAccessFile f) throws Exception {
    boolean [] visited = new boolean[20];
    int i;
    for(i=0;i<n;i++) visited[i]=false;
    depth2(visited,k,f);
    for(i=0;i<n;i++) 
       if(!visited[i]) depth2(visited,i,f);
   }
  void f1() throws Exception {
    loadData(1);
    String fname = "f1.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    depth(2,f);
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
     depth2(2, f);

    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }

//=================================================================
  void dijkstra(boolean[] selected, Stack S, int[] dist, int[] path, int p, int q) {
        int i, j, t, k, curr, step;// curr: current vertex, which is to be added to the S set
        //Khoi tao
        for (i = 0; i < n; i++) {
            selected[i] = false;
            dist[i] = a[p][i]; // At the first step distance is a direct distance  
            path[i] = p;       // and the vertex before vertex i is the vertex  p
        }
        int[] sele = new int[50];
        int nSele = 0;
        selected[p] = true;
        S.push(p);
        sele[nSele++] = p;
        curr = p;// add the vertex p the the S set

        while (curr != q)//Thuc hien vong lap cho den khi gap nut q
        {
            t = 999;
            k = -1;
            for (i = 0; i < n; i++) {
                if (i == p || selected[i]) {
                    continue;
                }
                //Tim dinh gan nhat de dua vao S = tap da xet)
                if (dist[i] < t) {
                    t = dist[i];
                    k = i;
                }
            }

            if (t == 999) {
                System.out.println("\nKhong co duong di");
                return;
            }
            //Dua  dinh k vao tap S da xet
            selected[k] = true;
            S.push(k);
            curr = k;
            sele[nSele++] = k;
            //Tinh lai khoang cach
            for (i = 0; i < n; i++) {
                if (i == p || selected[i]) {
                    continue;
                }
                if (dist[i] > dist[k] + a[k][i]) {
                    dist[i] = dist[k] + a[k][i];
                    path[i] = k;
                }
            }
        }
    }

    String pathDijkstra(int[] dist, int[] path, int p, int q) {
        Stack s = new Stack();
        String text = "";
        int i, j;
        i = q;
        s.push(i);
        do {
            i = path[i];
            s.push(i);
        } while (i != p);
        while (!s.isEmpty()) {
            i = s.pop();
            text += v[i];
            if (i != q) {
                text += "  ";
            }
        }
        text += "\n";
        return text;
    }
  void f2() throws Exception {
    loadData(12);
    String fname = "f2.txt";
    File g123 = new File(fname);
    if(g123.exists()) g123.delete();
    RandomAccessFile  f = new RandomAccessFile(fname, "rw"); 
    f.writeBytes("\r\n");
    //-------------------------------------------------------------------------------------
     /*You must keep statements pre-given in this function.
       Your task is to insert statements here, just after this comment,
       to complete the question in the exam paper.*/
      // You can use the statement fvisit(i,f); i = 0, 1, 2,...,n-1 to display the vertex i to file f2.txt 
      //  and statement f.writeBytes(" " + k); to write  variable k to the file f2.txt  
    int p = 0;
        int q = 6;
        boolean[] selected = new boolean[n];
        Stack S = new Stack();
        int[] dist = new int[n];
        int[] path = new int[n];
        dijkstra(selected, S, dist, path, p, q);
        String text = pathDijkstra(dist, path, p, q);
        String temp = "";
        for (int count = 1; count <= 4; count++) {
            int vertice = S.pop();
            temp = v[vertice] + "-" + dist[vertice]+ temp;
            if (count != 4) {
                temp = " " + temp;
            }
        }
        text += temp;
        f.writeBytes(text);



    //-------------------------------------------------------------------------------------
    f.writeBytes("\r\n");
    f.close();
   }

}
