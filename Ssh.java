//DONE
import java.io.*;
public class Ssh {
    public static void main(String[] args) {
        String username = "brookescoglio";	//Login UserID
        String projPath = "/cise/homes/brookescoglio/CNT/project"; // path of the project
        // is
        String pubKey = "rsakey"; // location of key
        try {
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-00.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1001 ");
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-01.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1002 ");
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-02.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1003 ");
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-03.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1004 ");
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-04.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1005 ");
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-05.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1006 ");
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-06.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1007 ");
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-08.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1008 ");
            Runtime.getRuntime().exec("ssh -i " + pubKey + " " + username +
                    "@lin114-07.cise.ufl.edu cd " + projPath
                    + " ; java PeerProcess 1009 ");
        } catch (Exception e) {
        }
    }
}
