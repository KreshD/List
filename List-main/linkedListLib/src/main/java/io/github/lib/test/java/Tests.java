package io.github.lib;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


public class Tests {
   @Test
    public void delete() throws Exception {
      LinkedList<Integer> list = new LinkedList<>();

       list.addFirst(10);
       list.addFirst(20);
       list.addLast(30);
       list.addLast(40);
       list.removeLast();


       Assertions.assertThat(list.getLast()).isEqualTo(30);

       }

    @Test
    public void removeByInd() throws Exception {
        LinkedList<Integer> list = new LinkedList<>();

        list.addFirst(10);
        list.addFirst(20);
        list.addLast(30);
        list.addLast(40);
        list.removeByIndex(1);

        Assertions.assertThat(list.get(1)).isEqualTo(30);


    }


    @Test
    public void indexOf() throws CycleLinkedList.CycleLinkedListException {
        CycleLinkedList<Integer> cycleList = new CycleLinkedList<>();
        cycleList.add(20);
        cycleList.add(30);
        cycleList.add(40);
        cycleList.delete(30);

        Assertions.assertThat(cycleList.indexOf(20)).isEqualTo(0);
    }

 }


