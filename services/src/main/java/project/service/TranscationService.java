package project.service;

import org.springframework.stereotype.Service;
import project.model.internal.Transaction;
import project.utils.StringUtil;

import java.security.PrivateKey;
import java.security.PublicKey;

@Service
public class TranscationService {

    public byte[] generateSignature(PrivateKey privateKey, Transaction transaction) {
        String data = getStringFromKeys(transaction.getSender(), transaction.getReciepient(), transaction.getValue());
        byte[] signature = StringUtil.applyECDSASig(privateKey, data);
        return signature;
    }

    public boolean verifySignature(Transaction transaction) {
        String data = getStringFromKeys(transaction.getSender(), transaction.getReciepient(), transaction.getValue());
        return StringUtil.verifyECDSASig(transaction.getSender(), data, transaction.getSignature());
    }

    public String calulateHash(Transaction transaction) {
        String hash = StringUtil.applySha256(getStringFromKeys(transaction.getSender(),
                transaction.getReciepient(), transaction.getValue()) + System.currentTimeMillis());
        return hash;
    }

    private String getStringFromKeys(PublicKey sender, PublicKey reciepient, float value) {
        return StringUtil.getStringFromKey(sender) + StringUtil.getStringFromKey(reciepient) + Float.toString(value);
    }
}
