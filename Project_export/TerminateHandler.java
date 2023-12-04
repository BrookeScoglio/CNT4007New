//DONE
package src;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.lang.*;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TerminateHandler implements Runnable {
    private int interv;
    private PeerAdmin peerAdmin;
    private Random randNum = new Random();
    private ScheduledFuture<?> job = null;
    private ScheduledExecutorService scheduler = null;

    TerminateHandler(PeerAdmin padmin) {
        this.peerAdmin = padmin;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void startJob(int timeinterval) {
        this.interv = timeinterval*2;
        this.job = scheduler.scheduleAtFixedRate(this, 30, this.interv, TimeUnit.SECONDS);
    }

    public void run() {
        try {
            if(this.peerAdmin.checkIfDone()) {
                this.peerAdmin.closeHandlers();
                this.cancelJob();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelJob() {
        this.scheduler.shutdownNow();
    }
}
