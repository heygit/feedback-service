package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.TransactionConverter;
import project.entity.TransactionEntity;
import project.model.external.Feedback;
import project.model.internal.Account;
import project.model.internal.Transaction;
import project.repository.TransactionRepository;
import project.utils.ConverterHelper;
import project.utils.StringUtil;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collections;
import java.util.List;

@Service
public class TranscationService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional(readOnly = true)
    public Transaction processTransaction(Account account, long productId, Feedback value) {
        Transaction transaction = new Transaction(account.getPublicKey(), productId, value);
        byte[] signature = generateSignature(account.getPrivateKey(), transaction);
        String hash = calulateHash(transaction);
        transaction.setSignature(signature);
        transaction.setHash(hash);
        return transaction;
    }

    @Transactional(readOnly = true)
    public List<Transaction> getTransactions() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        return ConverterHelper.convert(transactions, TransactionConverter::createTransactionFromEntity);
    }

    @Transactional(readOnly = true)
    public byte[] generateSignature(PrivateKey privateKey, Transaction transaction) {
        String data = getStringFromKeys(transaction.getUser(), transaction.getProductId(), transaction.getValue());
        byte[] signature = StringUtil.applyECDSASig(privateKey, data);
        return signature;
    }

    @Transactional(readOnly = true)
    public boolean verifySignature(Transaction transaction) {
        String data = getStringFromKeys(transaction.getUser(), transaction.getProductId(), transaction.getValue());
        return StringUtil.verifyECDSASig(transaction.getUser(), data, transaction.getSignature());
    }

    @Transactional(readOnly = true)
    public String calulateHash(Transaction transaction) {
        String hash = StringUtil.applySha256(getStringFromKeys(transaction.getUser(),
                transaction.getProductId(), transaction.getValue()) + System.currentTimeMillis());
        return hash;
    }

    private String getStringFromKeys(PublicKey user, long productId, Feedback value) {
        return StringUtil.getStringFromKey(user) + productId + value;
    }
}
