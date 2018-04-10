package project.service;

import org.springframework.stereotype.Service;
import project.model.external.Feedback;
import project.model.internal.Account;
import project.model.internal.Transaction;
import project.utils.StringUtil;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collections;
import java.util.List;

@Service
public class TranscationService {

    public Transaction processTransaction(Account account, long productId, Feedback value) {
        Transaction transaction = new Transaction(account.getPublicKey(), productId, value);
        byte[] signature = generateSignature(account.getPrivateKey(), transaction);
        String hash = calulateHash(transaction);
        transaction.setSignature(signature);
        transaction.setHash(hash);
        return transaction;
    }

    public List<Transaction> getTransactions() {
        return Collections.emptyList();
    }

    public byte[] generateSignature(PrivateKey privateKey, Transaction transaction) {
        String data = getStringFromKeys(transaction.getUser(), transaction.getProductId(), transaction.getValue());
        byte[] signature = StringUtil.applyECDSASig(privateKey, data);
        return signature;
    }

    public boolean verifySignature(Transaction transaction) {
        String data = getStringFromKeys(transaction.getUser(), transaction.getProductId(), transaction.getValue());
        return StringUtil.verifyECDSASig(transaction.getUser(), data, transaction.getSignature());
    }

    public String calulateHash(Transaction transaction) {
        String hash = StringUtil.applySha256(getStringFromKeys(transaction.getUser(),
                transaction.getProductId(), transaction.getValue()) + System.currentTimeMillis());
        return hash;
    }

    private String getStringFromKeys(PublicKey user, long productId, Feedback value) {
        return StringUtil.getStringFromKey(user) + productId + value;
    }
}
