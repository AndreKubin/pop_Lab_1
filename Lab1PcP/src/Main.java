import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        BreakThread breakThread = new BreakThread();
        int numThreads = 5; // Змінюйте кількість потоків за потребою
        int step = 1; // Змінюйте крок за потребою

        ExecutorService executor = Executors.newFixedThreadPool(numThreads+1);
        executor.execute(breakThread);


        for (int i = 0; i < numThreads; i++) {
            SequenceSumThread thread = new SequenceSumThread(i + 1, step, breakThread);
            executor.execute(thread);
        }

        executor.shutdown();

        try {
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All threads have finished their work.");
    }
}