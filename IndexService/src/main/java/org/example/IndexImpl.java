
package org.example;

public class IndexImpl implements eda.IndexService {
    int [] vec;
    @Override
    public void initialize(int[] elements) {
        if(elements == null){
            throw new IllegalArgumentException("elements is null");
        }else
            vec = elements;
    }

    @Override
    public boolean search(int key) {
        boolean a = false   ;
        while(a = false){

        }

    }

    @Override
    public void insert(int key) {

    }

    @Override
    public void delete(int key) {

    }

    @Override
    public int occurrences(int key) {
        return 0;
    }

    @Override
    public int occurrencesRec(int key) {
        return 0;
    }
}
