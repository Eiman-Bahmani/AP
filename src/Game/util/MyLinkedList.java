package Game.util;

    public class LinkList<T> {

        private Node<T> head;
        public LinkList(){
            this.head=null;
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

        public void printAll() {
            Node<T> current = head;
            while (current != null) {
                System.out.println(current.data);
                current = current.link;
            }
        }
        
        public boolean remove(T data) {
            if (head == null) return false;

            if (head.data.equals(data)) {
                head = head.next;
                return true;
            }

            Node<T> current = head;
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
            head = null;
        }

        public void addAll(MyLinkedList<T> other) {
            Node<T> current = other.head;
            while (current != null) {
                this.add(current.data);
                current = current.next;
            }
        }

        public Node<T> getHead() {
            return head;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public int size() {
            int count = 0;
            Node<T> current = head;
            while (current != null) {
                count++;
                current = current.next;
            }
            return count;
        }
    }

