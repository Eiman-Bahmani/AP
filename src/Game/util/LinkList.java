package Game.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkList<T> implements Iterable<T> {
    private Node<T> head;

    public LinkList() {
        this.head = null;
    }

    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) head = node;
        else {
            Node<T> current = head;
            while (current.link != null)
                current = current.link;
            current.link = node;
        }
    }

    public boolean contains(T target) {
        for (T item : this) {
            if (item.equals(target)) return true;
        }
        return false;
    }

    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.link;
        }
        return count;
    }

    public T get(int index) {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            if (count == index) return current.data;
            count++;
            current = current.link;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;

            public boolean hasNext() {
                return current != null;
            }

            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.link;
                return data;
            }
        };
    }

    public void printAll() {
        for (T item : this) {
            System.out.println(item);
        }
    }
}
