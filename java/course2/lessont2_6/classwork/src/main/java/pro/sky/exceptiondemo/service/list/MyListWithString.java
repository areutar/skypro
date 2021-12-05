package pro.sky.exceptiondemo.service.list;

public class MyListWithString {
    NodeWithString head;
    NodeWithString tail;
    int size;

    public int size() {
        return size;
    }

    public boolean add(String item) {
        NodeWithString newNodeWithString = new NodeWithString();
        newNodeWithString.setItem(item);
        if (tail == null) {
            head = newNodeWithString;
        } else {
            tail.setNext(newNodeWithString);
        }
        tail = newNodeWithString;
        size++;
        return true;
    }

    public String get(int index) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
        NodeWithString currentNodeWithString = getNodeWithString(index);
        return currentNodeWithString.getItem();
    }

    public String remove(int index) {
        if (checkIndex(index)) {
            throw new IndexOutOfBoundsException(index);
        }
        NodeWithString deleteNodeWithString;
        if (index == 0) {
            deleteNodeWithString = head;
            head = head.getNext();
        } else {
            NodeWithString prevNodeWithString = getNodeWithString(index - 1);
            deleteNodeWithString = prevNodeWithString.getNext();
            NodeWithString nextNodeWithString = deleteNodeWithString.getNext();
            prevNodeWithString.setNext(nextNodeWithString);
            if (nextNodeWithString == null) {
                tail = prevNodeWithString;
            }
        }
        size--;
        return deleteNodeWithString.getItem();
    }

    private boolean checkIndex(int index) {
        return index < 0 || index >= size;
    }

    private NodeWithString getNodeWithString(int index) {
        NodeWithString currentNodeWithString = head;
        for (int i = 0; i < index; i++) {
            currentNodeWithString = currentNodeWithString.getNext();
        }
        return currentNodeWithString;
    }
}
