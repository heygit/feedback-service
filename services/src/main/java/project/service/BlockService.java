package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.internal.Block;
import project.model.internal.Transaction;
import project.utils.StringUtil;

import java.util.Date;

@Service
public class BlockService {

    public static int DIFFICULTY = 3;

    @Autowired
    private TranscationService transcationService;

    public Block createBlock(String previousHash) {
        long timeStamp = new Date().getTime();
        int nonce = mineBlock(previousHash, timeStamp);
        String hash = calculateHash(previousHash, timeStamp, nonce);
        return new Block(hash, previousHash, timeStamp);
    }

    public String calculateHash(Block block) {
        return calculateHash(block.getPreviousHash(), block.getTimeStamp(), block.getNonce());
    }

    public String calculateHash(String previousHash, long timeStamp, int nonce) {
        String calculatedHash = StringUtil.applySha256(previousHash
                + Long.toString(timeStamp) + Integer.toString(nonce));
        return calculatedHash;
    }

    public int mineBlock(Block block) {
        return mineBlock(block.getPreviousHash(), block.getTimeStamp());
    }

    //Increases nonce value until hash target is reached.
    public int mineBlock(String previousHash, long timeStamp) {
        int nonce = 0;
        String hash = calculateHash(previousHash, timeStamp, nonce);
        String target = StringUtil.getDificultyString(DIFFICULTY); //Create a string with difficulty * "0"
        while(!hash.substring(0, DIFFICULTY).equals(target)) {
            nonce++;
            hash = calculateHash(previousHash, timeStamp, nonce);
        }
        return nonce;
    }

    //Add transactions to this block
    public boolean addTransaction(Block block, Transaction transaction) {
        if (!transcationService.verifySignature(transaction)) {
            return false;
        }

        block.getTransactions().add(transaction);
        System.out.println("Transaction Successfully added to Block");
        return true;
    }
}
