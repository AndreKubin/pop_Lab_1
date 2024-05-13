class SequenceSumThread implements Runnable {
    private int sequenceNumber = 0;
    private int threadNumber;
    private int step;
    private int sum;
    private int elementsCount;
    private final BreakThread breakThread;

    public SequenceSumThread(int threadNumber, int step, BreakThread breakThread) {
        this.threadNumber = threadNumber;
        this.step = step;
        this.sum = 0;
        this.elementsCount = 0;
        this.breakThread = breakThread;
    }

    @Override
    public void run() {
        do {
            sum += sequenceNumber;
            elementsCount++;
            sequenceNumber += step;
            try {
                Thread.sleep(100); // Пауза для імітації обчислень
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Повторне встановлення прапорця перерви
            }
        }while(!breakThread.isCanBreak());
        System.out.println("Thread " + threadNumber + ": Sum = " + sum + ", Elements Count = " + elementsCount);
    }
}
