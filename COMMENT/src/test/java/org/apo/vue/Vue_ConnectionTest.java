package org.apo.vue;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.ListIterator;

import static org.junit.jupiter.api.Assertions.*;

class Vue_ConnectionTest {


    @Test
    public void Test_Vue_Connection ()
    {
        ArrayList<String> list = new ArrayList<>();

        list.add("text1");
        list.add("text2");

        Vue_Connection a = new Vue_Connection(list);
        a.Affiche();
        System.out.println("3");
        while (!a.isHas_choose()); // bloquer ici je sais pas pourquoi même si le handleur est appelé et haschose mis à un
        System.out.println("4");
        list=a.Info();
        ListIterator<String> li = list.listIterator();

        while (li.hasNext()){
            System.out.println(li.next());
        }

    }


}