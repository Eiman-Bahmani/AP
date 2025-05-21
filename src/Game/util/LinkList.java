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
    }

