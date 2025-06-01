package Game.util;

    public class MyLinkedList<T> {

        private Node<T> head;
        public MyLinkedList(){
            this.head=null;
        }

        public void add(T data) {
            Node<T> node = new Node<>(data);
            if (head == null) this.head = node;
            else {
                Node<T> current = this.head;
                while (current.next != null)
                    current = current.next;
                current.next = node;
            }
        }

        public void printAll() {
            Node<T> current = this.head;
            while (current != null) {
                System.out.println(current.data);
                current = current.next;
            }
        }

        public boolean remove(T data) {
            if (head == null) return false;

            if (this.head.data.equals(data)) {
                this.head = this.head.next;
                return true;
            }

            Node<T> current = this.head;
            while (current.next != null) {
                if (current.next.data.equals(data)) {
                    current.next = current.next.next;
                    return true;
                }
                current = current.next;
            }

            return false;
        }

        public void clear() {
            this.head = null;
        }

        public void addAll(MyLinkedList<T> other) {
            Node<T> current = other.head;
            while (current != null) {
                this.add(current.data);
                current = current.next;
            }
        }

        public Node<T> getHead() {
            return this.head;
        }

        public boolean isEmpty() {
            return this.head == null;
        }

        public int size() {
            int count = 0;
            Node<T> current = this.head;
            while (current != null) {
                count++;
                current = current.next;
            }
            return count;
        }
    }

