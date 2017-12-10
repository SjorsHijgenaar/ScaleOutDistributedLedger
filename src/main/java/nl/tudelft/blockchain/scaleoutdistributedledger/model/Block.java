package nl.tudelft.blockchain.scaleoutdistributedledger.model;

import lombok.Getter;

import java.util.List;

/**
 * Block class.
 */
public class Block {

    @Getter
    private final int number;

    @Getter
    private Block previousBlock;

    @Getter
    private final Node owner;

    @Getter
    private final List<Transaction> transactions;

    /**
     * Constructor.
     * @param number - the number of this block.
     * @param owner - the owner of this block.
     * @param transactions - a list of transactions of this block.
     */
    public Block(int number, Node owner, List<Transaction> transactions) {
        this.number = number;
        this.owner = owner;
        this.transactions = transactions;
        this.previousBlock = null;
    }

    /**
     * Constructor.
     * @param number - the number of this block.
     * @param previousBlock - reference to the previous block in the chain of this block.
     * @param owner - the owner of this block.
     * @param transactions - a list of transactions of this block.
     */
    public Block(int number, Block previousBlock, Node owner, List<Transaction> transactions) {
        this.number = number;
        this.previousBlock = previousBlock;
        this.owner = owner;
        this.transactions = transactions;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + number;
        result = prime * result + owner.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Block)) return false;

        Block other = (Block) obj;
        if (this.number != other.number) return false;
        if (this.owner != other.owner) return false;

        if (this.previousBlock == null) {
            if (other.previousBlock != null) return false;
        } else if (!this.previousBlock.equals(other.previousBlock)) return false;

        return this.transactions.equals(other.transactions);
    }
}