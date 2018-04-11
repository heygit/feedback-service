package project.model.internal;

import project.model.external.Feedback;

import java.security.PublicKey;

public class Transaction {

    private String hash;
    private PublicKey user;
    private long productId;
    private Feedback value;
    private byte[] signature;

    public Transaction(String hash, PublicKey user, long productId, Feedback value, byte[] signature) {
        this.hash = hash;
        this.user = user;
        this.productId = productId;
        this.value = value;
        this.signature = signature;
    }

    public Transaction(PublicKey user, long productId, Feedback value) {
        this.user = user;
        this.productId = productId;
        this.value = value;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public PublicKey getUser() {
        return user;
    }

    public void setUser(PublicKey user) {
        this.user = user;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Feedback getValue() {
        return value;
    }

    public void setValue(Feedback value) {
        this.value = value;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
}