public class Driver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        
        queue.add(new Data("2", "N2"), PriorityQueue.MEDIUM_PRIORITY);
        System.out.println(queue);
        queue.add(new Data("1", "T1"), PriorityQueue.LOW_PRIORITY);
        System.out.println(queue);
        queue.add(new Data("3", "S3"), PriorityQueue.HIGH_PRIORITY);
        System.out.println(queue);
        queue.add(new Data("1", "S1"), PriorityQueue.HIGH_PRIORITY);
        System.out.println(queue);
        queue.add(new Data("1", "N1"), PriorityQueue.MEDIUM_PRIORITY);
        System.out.println(queue);
        queue.add(new Data("3", "N3"), PriorityQueue.MEDIUM_PRIORITY);
        System.out.println(queue);
        queue.add(new Data("2", "T2"), PriorityQueue.LOW_PRIORITY);
        System.out.println(queue);
        queue.add(new Data("3", "T3"), PriorityQueue.LOW_PRIORITY);
        System.out.println(queue);
        queue.add(new Data("2", "S2"), PriorityQueue.HIGH_PRIORITY);
        System.out.println(queue);
        
    }
}
