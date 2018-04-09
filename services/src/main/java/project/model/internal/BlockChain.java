package project.model.internal;

import java.util.ArrayList;
import java.util.List;

public class BlockChain {

    private List<Block> blocks;

    public BlockChain() {
        blocks = new ArrayList<>();
    }

    public BlockChain(List<Block> blocks) {
        this.blocks = blocks;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}