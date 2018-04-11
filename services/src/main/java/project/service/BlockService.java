package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.converter.BlockConverter;
import project.converter.FeedbackConverter;
import project.converter.TransactionConverter;
import project.entity.BlockEntity;
import project.entity.FeedbackEntity;
import project.entity.TransactionEntity;
import project.model.internal.Block;
import project.model.internal.Transaction;
import project.repository.BlockRepository;
import project.repository.FeedbackRepository;
import project.repository.TransactionRepository;
import project.utils.StringUtil;

import java.util.Date;
import java.util.List;

@Service
public class BlockService {

    public static int DIFFICULTY = 3;

    @Autowired
    private TranscationService transcationService;

    @Autowired
    private BlockRepository blockRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Transactional(readOnly = true)
    public String calculateHash(Block block) {
        return calculateHash(block.getPreviousHash(), block.getTimeStamp(), block.getNonce());
    }

    @Transactional(readOnly = true)
    public String calculateHash(String previousHash, long timeStamp, int nonce) {
        String calculatedHash = StringUtil.applySha256(previousHash
                + Long.toString(timeStamp) + Integer.toString(nonce));
        return calculatedHash;
    }

    @Transactional(readOnly = true)
    public int mineBlock(String previousHash, long timeStamp) {
        int nonce = 0;
        String hash = calculateHash(previousHash, timeStamp, nonce);
        String target = StringUtil.getDificultyString(DIFFICULTY); // Create a string with difficulty * "0"
        while(!hash.substring(0, DIFFICULTY).equals(target)) {
            nonce++;
            hash = calculateHash(previousHash, timeStamp, nonce);
        }
        return nonce;
    }

    @Transactional
    public void addTransaction(Transaction transaction) {
        addTransactionToBlock(getOrCreateLastBlock(), transaction);
    }

    @Transactional
    public Block saveBlock(BlockEntity blockEntity) {
        blockEntity = blockRepository.save(blockEntity);
        return BlockConverter.createBlockFromEntity(blockEntity);
    }

    private void addTransactionToBlock(BlockEntity blockEntity, Transaction transaction) {
        if (!transcationService.verifySignature(transaction)) {
            throw new RuntimeException("Could not verify transaction signature");
        }
        FeedbackEntity feedbackEntity = FeedbackConverter.createFeedbackEntity(transaction.getValue());
        feedbackEntity = feedbackRepository.save(feedbackEntity);
        TransactionEntity transactionEntity = TransactionConverter.createTransactionEntity(transaction, feedbackEntity,
                blockEntity);
        transactionRepository.save(transactionEntity);
    }

    private BlockEntity getOrCreateLastBlock() {
        List<BlockEntity> allBlocks = blockRepository.findAll();
        if (allBlocks.isEmpty()) {
            return blockRepository.save(createGenesisBlock());
        }
        return allBlocks.get(allBlocks.size() - 1);
    }

    private BlockEntity createGenesisBlock() {
        return createBlock(null);
    }

    private BlockEntity createBlock(String previousHash) {
        long timeStamp = new Date().getTime();
        int nonce = mineBlock(previousHash, timeStamp);
        String hash = calculateHash(previousHash, timeStamp, nonce);
        return new BlockEntity(hash, previousHash, timeStamp, nonce);
    }
}
