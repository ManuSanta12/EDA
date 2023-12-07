package org.example;

import java.util.Arrays;

public class IndexWithDuplicates implements IndexService {
    int chunck = 5;
    int[] v = new int[chunck];

    int dim=0;
    @Override
    public void initialize(int[] elements) {
        v = elements;
        dim = elements.length;
    }

    @Override
    public boolean search(int key) {
       int n = searchIndex(key);
       return (n==-1)? false:true;
    }
    @Override
    public int searchIndex(int key){
        int l = 0;
        int r = v.length-1;
        while(l<=r){
            int m = l + (r-l)/2;
            if(v[m]==key){
                return m;
            }else if(v[m] > key){
               r=m-1;
            }else{
                l=m+1;
            }
        }
        return -1;
    }
    @Override
    public void insert(int key) {
        if (dim >= v.length - 1) {
            v = Arrays.copyOf(v, dim + chunck);
        }
        int n = -1;
        for (int i = 0; i < dim; i++) {
            if (v[i] > key) {
                n = i;
                break;
            }
        }
        if (n == -1) {
            n = dim;
        }
        for (int i = dim-1; i >= n; i--) {
            v[i+1] = v[i];
        }
        v[n] = key;
        dim++;
        for (int i = 0; i < dim; i++) {
            System.out.printf("%d ",v[i]);
        }
        System.out.println();
    }

    @Override
    public void delete(int key) {
        int n = searchIndex(key);
        if(n==-1)
            return;
        if(n==dim-1){
            dim--;
            return;
        }
        for(int i = n; i<dim;i++){
            v[i-1]=v[i];
        }
    }

    @Override
    public int occurrences(int key) {
        int n = searchIndex(key);
        if(n==-1)
            return 0;
        int i=1;
        int cont=1;
        while (v[n + i] == key || v[n-i]==key) {
            if(v[n+i]==key)
                cont++;
            if(v[n-i]==key){
                cont++;
            }
            i++;
        }
        return cont;
    }
    public static void main(String[] args) {
        IndexService myIndex= new IndexWithDuplicates();
        /*myIndex.initialize(new int[]{10, 20, 30});
        myIndex.insert(20);
        myIndex.insert(15);
        System.out.println(myIndex.occurrences(20));*/
        System.out.println (myIndex.occurrences( 10 ) ); // se obtiene 0
        myIndex.delete( 10 ); // ignora
        System.out.println (myIndex.search( 10 ) ); // se obtiene false
        myIndex.insert( 80 ); // almacena [80]
        myIndex.insert( 20 ); // almacena [20, 80]
        myIndex.insert( 80 ); // almacena [20, 80, 80]
        myIndex.initialize(new int[]{30,30,50,50,80,100,100,100});
        // el índice posee [30, 30, 50, 50, 80, 100, 100, 100]
        System.out.println( myIndex.search( 20 )); // se obtiene false
        System.out.println( myIndex.search( 80 )); // se obtiene true
        System.out.println (myIndex.occurrences( 50 ) ); // se obtiene 2
        myIndex.delete( 50 );
        System.out.println (myIndex.occurrences( 50 ) ); // se obtiene 1
    }
}
