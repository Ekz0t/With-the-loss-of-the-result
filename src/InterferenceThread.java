import java.util.concurrent.atomic.AtomicInteger;

//Причина потери, в том, что i++ была неатомарна
public class InterferenceThread extends Thread {
    private final InterferenceExample checker;
    private static AtomicInteger i = new AtomicInteger();
//    private static int i;

    InterferenceThread(InterferenceExample checker) {
        this.checker = checker;
    }

    private void increment() {
        i.incrementAndGet();
    }//Неатомарное увелечение, можно переделать

    int getI() {
        return i.get();
    }

//    private static synchronized void increment() {
//        i++;
//    }
//
//    static synchronized int getI() {
//        return i;
//    }

    public void run() {
        while (!checker.stop()) {
            increment();
        }
    }
}
