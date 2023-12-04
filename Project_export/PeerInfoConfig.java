//DONE
package src;
import java.util.*;
import java.io.*;
public class PeerInfoConfig {
    private HashMap<String,RemotePeerInfo> peerMap;
    private ArrayList<String> peerList;

    public PeerInfoConfig(){
        this.peerMap = new HashMap<>();
        this.peerList = new ArrayList<>();
    }

    public void loadConfigFile()
    {
        String tempString;
        BufferedReader in;
        try {
            in = new BufferedReader(new FileReader("PeerInfo.cfg"));
            while((tempString = in.readLine()) != null) {
                String[] tokens = tempString.split("\\s+");
                this.peerMap.put(tokens[0],new RemotePeerInfo(tokens[0], tokens[1], tokens[2], tokens[3]));
                this.peerList.add(tokens[0]);
            }
            in.close();
        }
        catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }

    public RemotePeerInfo getPeerConfig(String peerID){
        return this.peerMap.get(peerID);
    }

    public HashMap<String, RemotePeerInfo> getPeerInfoMap(){
        return this.peerMap;
    }

    public ArrayList<String> getPeerList(){
        return this.peerList;
    }

}
