package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.model.internal.Block;

import java.util.ArrayList;
import java.util.List;

import static project.service.BlockService.DIFFICULTY;

@Service
public class BlockChainService {

    @Autowired
    private static BlockService blockService;

    public static Boolean isChainValid(List<Block> blockChain) {
        Block currentBlock;
        Block previousBlock;
        String hashTarget = new String(new char[DIFFICULTY]).replace('\0', '0');

        for (int i = 1; i < blockChain.size(); i++) {
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i - 1);
            //compare registered hash and calculated hash:
            if (!currentBlock.getHash().equals(blockService.calculateHash(currentBlock))) {
                return false;
            }
            //compare previous hash and registered previous hash
            if (!previousBlock.getHash().equals(currentBlock.getPreviousHash())) {
                return false;
            }
            //check if hash is solved
            if (!currentBlock.getHash().substring(0, DIFFICULTY).equals(hashTarget)) {
                return false;
            }
        }
        return true;
    }
}