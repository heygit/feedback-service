package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.internal.Block;
import project.model.internal.BlockChain;

import static project.service.BlockService.DIFFICULTY;

@Service
public class BlockChainService {

    @Autowired
    private static BlockService blockService;

//    создаем транзакцию

//    genesisTransaction = new Transaction(coinbase.publicKey, walletA.publicKey, 100f);

//    подписываем транзакцию
//    genesisTransaction.generateSignature(coinbase.privateKey);

//    проставляем айди транзакции
//    genesisTransaction.transactionId = "0"; //manually set the transaction id

//    добавляем транзакцию в блок
//    genesis.addTransaction(genesisTransaction);

//    добавляем блок в цепочку
//    addBlock(genesis);

    public static Boolean isChainValid(BlockChain blockChain) {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[DIFFICULTY]).replace('\0', '0');

        for (int i = 1; i < blockChain.getBlocks().size(); i++) {
            currentBlock = blockChain.getBlocks().get(i);
            previousBlock = blockChain.getBlocks().get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.getHash().equals(blockService.calculateHash(currentBlock))) {
                System.out.println("#Current Hashes not equal");
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                System.out.println("#Previous Hashes not equal");
                return false;
            }
            //check if hash is solved
            if (!currentBlock.getHash().substring(0, DIFFICULTY).equals(hashTarget)) {
                System.out.println("#This block hasn't been mined");
                return false;
            }
        }
        System.out.println("Blockchain is valid");
        return true;
    }

    public void addBlock(BlockChain blockChain, Block block) {
        int nonce = blockService.mineBlock(block);
        block.setNonce(nonce);
        blockChain.getBlocks().add(block);
    }
}