package forum4Bim;

import java.util.concurrent.atomic.AtomicBoolean;

public class JobConsumer extends Thread {

    private JobQueue jobs;
    private Integer assignedJob = null;
    private AtomicBoolean isRunning = new AtomicBoolean(true);

    public JobConsumer(JobQueue jobs) {
        this.jobs = jobs;
    }

    @Override
    public void run() {
        while (isRunning.get()) {
            if (assignedJob == null || assignedJob == 0) {
                try {
                    assignedJob = jobs.getNextJob();
                    System.out.println("peguei um");
                    if (assignedJob == null) {
                        System.out.println("Nothing to do... " + System.currentTimeMillis() + " " + this);
                        this.sleep(5000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                jobs.assignJob();
                System.out.println("I'm working. Job size " + assignedJob + ", " + System.currentTimeMillis() + ": " + this);
                try {
                    this.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                jobs.concludeJob();
                assignedJob = null;
            }
        }
        this.interrupt();
    }

    public void stopConsumer() {
        isRunning.set(false);
    }
}
