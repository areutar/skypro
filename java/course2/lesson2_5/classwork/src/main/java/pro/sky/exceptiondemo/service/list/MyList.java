package pro.sky.exceptiondemo.service.list;

import pro.sky.exceptiondemo.data.Person;

public class MyList {
    Node head;
    Node tail;
    int size;

    public int size() {
        return size;
    }

    public boolean add(Person person) {
        Node newNode = new Node();
        newNode.setItem(person);
        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        return true;
    }

    public Person get(int index) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        Node currentNode = getNode(index);
        return currentNode.getItem();
    }

    public Person remove(int index) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException(index);
        }
        Node deleteNode;
        if (index == 0) {
            deleteNode = head;
            head = head.getNext();
        } else {
            Node prevNode = getNode(index - 1);
            deleteNode = prevNode.getNext();
            Node nextNode = deleteNode.getNext();
            prevNode.setNext(nextNode);
            if (nextNode == null) {
                tail = prevNode;
            }
        }
        size--;
        return deleteNode   .getItem();
    }

    private boolean checkIndex(int index) {
        return index < 0 || index >= size;
    }

    private Node getNode(int index) {
        Node currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }
}
