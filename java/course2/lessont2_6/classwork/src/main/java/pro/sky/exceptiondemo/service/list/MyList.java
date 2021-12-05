package pro.sky.exceptiondemo.service.list;

public class MyList<T> {
    Node<T> head;
    Node<T> tail;
    int size;

    public int size() {
        return size;
    }

    public boolean add(T item) {
        Node<T> newNode = new Node<T>();
        newNode.setItem(item);
        if (tail == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;
        size++;
        return true;
    }

    public T get(int index) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        Node<T> currentNode = getNode(index);
        return currentNode.getItem();
    }

    public T remove(int index) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException(index);
        }
        Node<T> deleteNode;
        if (index == 0) {
            deleteNode = head;
            head = head.getNext();
        } else {
            Node<T> prevNode = getNode(index - 1);
            deleteNode = prevNode.getNext();
            Node<T> nextNode = deleteNode.getNext();
            prevNode.setNext(nextNode);
            if (nextNode == null) {
                tail = prevNode;
            }
        }
        size--;
        return deleteNode.getItem();
    }

    private boolean checkIndex(int index) {
        return index < 0 || index >= size;
    }

    private Node<T> getNode(int index) {
        Node<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode;
    }
}
