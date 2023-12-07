package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        BTree<Integer> st = new BTree<>(1);
        st.add(3);
        st.add(18);
        st.add(23);
        st.add(35);
        st.add(15);
        st.add(5);
        st.add(89);
        st.add(1);
        st.add(10);
        st.add(28);


        System.out.println("-------------");
        System.out.println(st.toString());

        st.remove(23);
        st.remove(5);
        st.remove(35);
        st.remove(15);


        System.out.println("-------------");
        System.out.println(st.toString());
    }
}