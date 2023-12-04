//CHANGE
package Project_export;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class PeerServer implements Runnable {
    private String peerID;
    private ServerSocket listener;
    private PeerAdmin peerAdmin;
    private boolean terminate;

    public PeerServer(String peerID, ServerSocket listener, PeerAdmin admin) {
        this.peerID = peerID;
        this.listener = listener;
        this.peerAdmin = admin;
        this.terminate = false;
    }

    public void run() {
        while (!this.terminate) {
            try {
                Socket neighbour = this.listener.accept();
                PeerHandler neighbourHandler = new PeerHandler(neighbour, this.peerAdmin);
                new Thread(neighbourHandler).start();
                String addr = neighbour.getInetAddress().toString();
                int port = neighbour.getPort();
            }
            catch (SocketException e) {
                break;
            }
            catch (Exception e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
