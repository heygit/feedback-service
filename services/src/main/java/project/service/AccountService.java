package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.entity.AccountEntity;
import project.model.internal.Account;
import project.repository.AccountRepository;
import project.utils.StringUtil;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.spec.ECGenParameterSpec;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional(readOnly = true)
    public Account getAccount(long userId) {
        AccountEntity accountEntity = accountRepository.findOne(userId);
        return new Account(accountEntity.getPrivateKey(), accountEntity.getPublicKey());
    }

    @Transactional(readOnly = true)
    public long authorize(String username, String password) {
        AccountEntity accountEntity = accountRepository.findByUsername(username);
        if (accountEntity.getHashedPassword().equals(StringUtil.applySha256(password))) {
            return accountEntity.getId();
        }
        throw new RuntimeException("Invalid credentials");
    }
}