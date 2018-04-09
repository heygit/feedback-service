package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.internal.Account;
import project.model.internal.Transaction;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

@Service
public class AccountService {

    @Autowired
    private TranscationService transcationService;

    public Account createAccount() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
            // Initialize the key generator and generate a KeyPair
            keyGen.initialize(ecSpec, random); //256
            KeyPair keyPair = keyGen.generateKeyPair();
            // Set the public and private keys from the keyPair
            return new Account(keyPair.getPrivate(), keyPair.getPublic());
        }catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Transaction sendData(Account source, PublicKey recipient, float value) {
        Transaction transaction = new Transaction(source.getPublicKey(), recipient, value);
        byte[] signature = transcationService.generateSignature(source.getPrivateKey(), transaction);
        transaction.setSignature(signature);

        return transaction;
    }

}