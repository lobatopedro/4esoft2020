package forum4Bim;

import java.util.concurrent.atomic.AtomicBoolean;

public class JobProducer extends Thread {

    private final JobQueue jobs;
    private Boolean isRunning = new Boolean(true);

    public JobProducer(JobQueue jobs) {
        this.jobs = jobs;
    }

    @Override
    public void run() {
        try {
            System.out.println("Adicionando atividade, " + this );
            this.jobs.queueJob(1);
        } catch (Exception e) {
            System.out.println("Thread interrompida, " + this);
        }
        System.out.println(" Thread parada, " + this);
        this.interrupt();
    }
}
