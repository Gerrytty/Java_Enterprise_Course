public class ConsumerDismissal {
    private final static String QUEUE_1 = "dismiss";

    public static void main(String[] args) {
        Consumer consumer = new Consumer(QUEUE_1);
        consumer.create();
    }
}