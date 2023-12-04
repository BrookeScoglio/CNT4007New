//DONE
package src;
import java.util.*;
import java.io.*;
import java.nio.*;

//Class for actual messages including size, the type, and the payload
public class ActualMessage {

    //actual message variables
    private int sizeMessage;
    private char messageType;
    private byte[] payMessage;

    public ActualMessage() {
    }

    //constructor setting defaults
    public ActualMessage(char messageType) {
        this.payMessage = new byte[0];
        this.messageType = messageType;
        this.sizeMessage = 1;
    }

    //Constructor
    public ActualMessage(char messageType, byte[] payMessage) {
        this.payMessage = payMessage;
        this.messageType = messageType;
        this.sizeMessage = this.payMessage.length+1;
    }

    //Builds message
    public byte[] buildActualMessage() {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            byte[] bytes = ByteBuffer.allocate(4).putInt(this.sizeMessage).array();
            stream.write(bytes);
            stream.write((byte) this.messageType);
            stream.write(this.payMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stream.toByteArray();
    }

    //Reads message
    public void readActualMessage(int len, byte[] message) {
        this.sizeMessage = len;
        this.payMessage = extractPayload(message, 1);
        this.messageType = extractMessageType(message, 0);
    }

    //Returns int from an array of bytes
    public int extractIntFromByteArray(byte[] message, int start) {
        byte[] len = new byte[4];
        for (int i = 0; i < 4; i++) {
            len[i] = message[i + start];
        }
        ByteBuffer bb = ByteBuffer.wrap(len);
        return bb.getInt();
    }

    //Returns message type given an index
    public char extractMessageType(byte[] message, int index) {
        return (char) message[index];
    }

    //Returns the payload of the message
    public byte[] extractPayload(byte[] message, int index) {
        byte[] resp = new byte[this.sizeMessage - 1];
        System.arraycopy(message, index, resp, 0, this.sizeMessage - 1);
        return resp;
    }

    //returns the bits in the message
    public BitSet getBitFieldMessage() {
        BitSet bits = new BitSet();
        bits = BitSet.valueOf(this.payMessage);
        return bits;
    }

    //Returns index of piece
    public int getPieceIndexFromPayload() {
        return extractIntFromByteArray(this.payMessage, 0);
    }

    //Returns piece
    public byte[] getPieceFromPayload() {
        int size = this.sizeMessage - 5;
        byte[] piece = new byte[size];
        for (int i = 0; i < size; i++) {
            piece[i] = this.payMessage[i + 4];
        }
        return piece;
    }

    //Returns type of message
    public char getMessageType() {
        return this.messageType;
    }

}
