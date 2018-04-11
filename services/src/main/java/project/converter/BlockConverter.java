package project.converter;

import project.entity.BlockEntity;
import project.model.internal.Block;
import project.model.internal.Transaction;
import project.utils.ConverterHelper;

import java.util.List;

public class BlockConverter {

    public static Block createBlockFromEntity(BlockEntity source) {
        List<Transaction> transactions = ConverterHelper.convert(source.getTransactions(),
                TransactionConverter::createTransactionFromEntity);

        return new Block(source.getHash(), source.getPreviousHash(), transactions, source.getTimeStamp(), source.getNonce());
    }
}
