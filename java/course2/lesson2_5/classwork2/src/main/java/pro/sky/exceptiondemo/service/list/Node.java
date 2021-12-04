package pro.sky.exceptiondemo.service.list;

import pro.sky.exceptiondemo.data.Person;

public class Node {
    Node next;
    Person item;

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Person getItem() {
        return item;
    }

    public void setItem(Person item) {
        this.item = item;
    }
}
