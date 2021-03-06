package project.model.internal;

import java.util.List;

public class Block {

    private String hash;
    private String previousHash;
    private List<Transaction> transactions;
    private long timeStamp;
    private int nonce;

    public Block(String hash, String previousHash, List<Transaction> transactions, long timeStamp, int nonce) {
        this.hash = hash;
        this.previousHash = previousHash;
        this.transactions = transactions;
        this.timeStamp = timeStamp;
        this.nonce = nonce;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }
}