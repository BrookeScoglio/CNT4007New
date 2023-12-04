# Peer-to-Peer File Sharing Project
This project involves the implementation of a Peer-to-Peer (P2P) file-sharing software in Java, akin to BitTorrent, a widely-used P2P protocol for file distribution. A notable feature of BitTorrent, the choking-unchoking mechanism, has been integrated into the modified protocol described in the accompanying PDF document.

## Prerequisites and Setup:
To run the project, follow these steps:
1. **Copy and Compile Code:**
    - Log in to the remote machine and copy the source code.
    - Compile the code using the command: `javac PeerProcess.java`.

2. **Run Wrapper:**
    - A wrapper has been provided to streamline the process of starting PeerProcesses on remote machines.
    - Execute the wrapper to initiate all processes on the remote machines.

3. **Generate SSH Keys:**
    - Run `ssh-keygen` to generate SSH keys. When prompted for a filename and passphrase, opt not to use a passphrase.
    - Use `ssh-copy-id -i <your_key> <username>@<remote_machine>` to copy the key to a remote machine.
    - Log in to each remote machine at least once using `ssh -i <your_key> <username>@<remote_machine>` to avoid fingerprint prompts when running the wrapper.

4. **Directory Setup:**
    - Copy the `Common.config` file, `PeerInfo.config` file, and the file to be transferred to the corresponding peer directories within the "project/" folder.

5. **Configure Wrapper:**
    - Edit the Ssh wrapper file, updating the following parameters:
        - Change the username to your own.
        - Set `projPath` to the directory where you run the program.
        - Set 'pubKey' to your generated RSA key.

6. **Start Wrapper:**
    - Compile the Ssh.java program using `javac Ssh.java`.
    - Run the wrapper using `java Ssh`.

## Functionality Requirements Met:
1. **Initializing PeerProcess:**
   - Reading of 'Common.cfg' file and set related variables – readCommon() in util/Config.java 
   - Reading of 'PeerInfo.cfg' file and set related variables – readpeerInfo() in util/Config.java 
   - Establishing a TCP connection between two peer processes – 
   - Peer.java starts socket/PeerClient.java and socket/PeerServer.java; PeerServer listens for TCP connection and Peer Client initiates a TCP Connection with PeerServer 
   - Peer connects, sends handshake messages, starts exchanging pieces and terminates after completion of downloading of file by itself and other peers
     - sendHandshake() and recvHandshake() in message/HandshakeMessage.java 
     - receiveMessages() in message/Messenger.java handles logic for different types of messages. 
     - util/MessageUtil.java handles the different types of messages and checks for termination condition.

2. **Post Connecting:**
   - After connection each peer process sends 'handshake' message to each other before sending other messages –  sendHandshake() and recvHandshake() in message/HandshakeMessage.java
   - Send/Receiving of 'bitfield' message – This is handled by message/BitfieldMessage.java and the logic is handled in message/Messenger.java
   - Send 'interested'/'not interested' message – InterestedMessage.java and NotInterestedMessage.java; logic handled in message/Messenger.java
   - Send k unchoke/choke messages every p seconds – handled in timertask/PreferredNeighborsTask.java
   - Set Optimistically unchoke neighbor every m seconds – handled in timertask/OptimisticUnchokingTask.java

3. **File Exchange:**
   - Send 'request', 'have', 'not interested', 'interested', 'piece' – These are in the relevant classes inside the message folder
   - Receive 'have' message and update related bitfield – have message is received in message/Messenger.java and bitfield is updated in handleHaveMessage() message/MessageUtil.java

4. **Terminate Service:**
   - After all peers have received the file, checkTermination() in util/MessageUtil.java checks if bitfield of all the peers is full and accordingly terminates the program.


# Issue: Towards the due date we were running into compilation errors, but during the demonstration we displayed the project that was compiling completely.
## List of work from each member:
1. **Hannah Hardy:**
   - Setup starter files and created the GitHub repository for the group.
   - Created Ssh.java file for connection.
   - Created TerminateHandler.java file to handle termination of service.
   - Created PeerInfoConfig.java file initializing and storing the information of the peers.
   - Created PeerAdmin.java file containing functions used in peer initialization and connection.
2. **Brooke Scoglio:**
   - Fixed issues with the compilation of the repository when setting up files.
   - Created PeerSever.java and PeerLogger.java used within the peer connection process.
   - Created PeerProcess.java file needed for peer connection and initialization.
   - Created various java message files needed for message handling. (ActualMessage.java, ChokeHandler.java, HandshakeMessage.java, OptimisticUnchokeHandler.java)
3. **Hannah Hardy and Brooke Scoglio:**
   - Recorded video together showing when project successfully compiled.