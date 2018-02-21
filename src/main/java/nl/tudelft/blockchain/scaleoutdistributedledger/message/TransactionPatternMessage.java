package nl.tudelft.blockchain.scaleoutdistributedledger.message;

import java.io.Serializable;
import java.util.logging.Level;

import nl.tudelft.blockchain.scaleoutdistributedledger.LocalStore;
import nl.tudelft.blockchain.scaleoutdistributedledger.simulation.transactionpattern.ITransactionPattern;
import nl.tudelft.blockchain.scaleoutdistributedledger.utils.Log;

/**
 * Message containing the transaction pattern that the receiver should use.
 */
public class TransactionPatternMessage extends Message implements Serializable {
	public static final int MESSAGE_ID = 6;
	private static final long serialVersionUID = 1L;
	
	private ITransactionPattern pattern;
	
	/**
	 * @param pattern - the pattern
	 */
	public TransactionPatternMessage(ITransactionPattern pattern) {
		this.pattern = pattern;
	}
	
	@Override
	public void handle(LocalStore localStore) {
		try {
			localStore.getApplication().setTransactionPattern(pattern);
		} catch (Exception ex) {
			Log.log(Level.SEVERE, "Unable to set transaction pattern on node " + localStore.getOwnNode().getId(), ex);
		}
	}
	
	@Override
	public int getMessageId() {
		return MESSAGE_ID;
	}

	@Override
	public String toString() {
		return "TransactionPatternMessage<" + pattern.getName() + ">";
	}
}
