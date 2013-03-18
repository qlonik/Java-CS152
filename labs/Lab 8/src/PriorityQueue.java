
public class PriorityQueue {

    public final static int LOW_PRIORITY = 0;
    public final static int MEDIUM_PRIORITY = 1;
    public final static int HIGH_PRIORITY = 2;
    private QueueNode head;

    public PriorityQueue() {
        head = null;
    }

    public void add(Data data, int priority) {
        QueueNode toAddNode = new QueueNode(data, priority);

        if (head == null) {
            head = toAddNode;
        } else {
            QueueNode last = head;
            while (last.getNext() != null
                    && priority <= last.getNext().getPriority()) {
                last = last.getNext();
            }

            if (priority > last.getPriority()) {
                toAddNode.setNext(last);

                last.setPrevious(toAddNode);
                head = toAddNode;
            } else {
                toAddNode.setPrevious(last);
                toAddNode.setNext(last.getNext());

                if (last.getNext() != null) {
                    last.getNext().setPrevious(toAddNode);
                }
                last.setNext(toAddNode);
            }
        }
    }

    public String toString() {
        String result = "";

        QueueNode copy = head;
        while (copy != null) {
            String priorityString = "";
            switch (copy.getPriority()) {
                case PriorityQueue.LOW_PRIORITY:
                    priorityString = "T";
                    break;
                case PriorityQueue.MEDIUM_PRIORITY:
                    priorityString = "N";
                    break;
                case PriorityQueue.HIGH_PRIORITY:
                    priorityString = "S";
            }
            result += priorityString + copy.getData() + ", ";
            copy = copy.getNext();
        }

        result = result.substring(0, result.length() - 2);

        return result;
    }

    private class QueueNode {

        private Data data;
        private int priority;
        private QueueNode next, previous;

        public QueueNode(Data data, int priority) {
            this.data = data;
            this.priority = priority;

            next = null;
            previous = null;
        }

        public Data getData() {
            return data;
        }

        public int getPriority() {
            return priority;
        }

        public QueueNode getNext() {
            return next;
        }

        public void setNext(QueueNode next) {
            this.next = next;
        }

        public QueueNode getPrevious() {
            return previous;
        }

        public void setPrevious(QueueNode previous) {
            this.previous = previous;
        }
    }
}
