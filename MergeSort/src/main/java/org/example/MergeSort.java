package org.example;

import org.w3c.dom.ls.LSOutput;

import javax.swing.plaf.basic.BasicSplitPaneUI;

public class MergeSort {
    //Algoritmo recursivo que divide el arreglo de a mitades y las va ordenando. Lo hace hasta tener
    //subarreglos de un elemento osea que no puede dividir mas.
    static int[] vec = {5,32,6,4,1,4,3,9};

    static void divideNsort(int[] vec, int beg, int end){
        if(beg<end){
            int mid = (beg+end)/2;
            divideNsort(vec, beg, mid);
            divideNsort(vec, mid+1, end);
            merge(vec, beg, mid, end);
        }
    }

    static void merge(int[] vec, int beg, int mid, int end){
        int n1 = mid - beg + 1; //Tamaño del primer subarreglo
        int n2 = end-mid; //Tamaño del segundo subarreglo
        //Voy a estar partiendo el array en mitades

        int[] left = new int[n1];
        int[] right = new int[n2];

        for(int i=0; i<n1; i++){
            left[i] = vec[beg+i];
        }
        for(int j=0;j<n2;j++){
            right[j] = vec[mid+1+j];
        }
        int i=0, j=0, k=beg;
        while (i < n1 && j < n2)
        {
            if(left[i] <= right[j])
            {
                vec[k] = left[i];
                i++;
            }
            else
            {
                vec[k] = right[j];
                j++;
            }
            k++;
        }
        while (i<n1)
        {
            vec[k] = left[i];
            i++;
            k++;
        }

        while (j<n2)
        {
            vec[k] = right[j];
            j++;
            k++;
        }

    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        printArray(vec);
        divideNsort(vec, 0, vec.length-1);
        printArray(vec);
    }
}
