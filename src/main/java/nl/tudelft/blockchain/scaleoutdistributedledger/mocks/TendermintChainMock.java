package nl.tudelft.blockchain.scaleoutdistributedledger.mocks;

import java.io.IOException;
import java.util.logging.Level;

import nl.tudelft.blockchain.scaleoutdistributedledger.model.Block;
import nl.tudelft.blockchain.scaleoutdistributedledger.model.BlockAbstract;
import nl.tudelft.blockchain.scaleoutdistributedledger.model.Sha256Hash;
import nl.tudelft.blockchain.scaleoutdistributedledger.model.mainchain.MainChain;
import nl.tudelft.blockchain.scaleoutdistributedledger.utils.Log;
import nl.tudelft.blockchain.scaleoutdistributedledger.utils.SDLByteArrayOutputStream;

/**
 * Mock for TendermintChain.
 */
public class TendermintChainMock implements MainChain {
	@Override
	public void init() {}
	
	@Override
	public Sha256Hash commitAbstract(BlockAbstract abs) {
		// Generate a deterministic hash, just in case
		try (SDLByteArrayOutputStream stream = new SDLByteArrayOutputStream(8)) {
			stream.writeInt(abs.getBlockNumber());
			stream.writeInt(abs.getOwnerNodeId());
			byte[] hash = stream.getByteArray();
			
			// Update abstract
			abs.setAbstractHash(Sha256Hash.withHash(hash));
			return Sha256Hash.withHash(hash);
		} catch (IOException ex) {
			Log.log(Level.SEVERE, "Unexpected error while making hash of abstract", ex);
			abs.setAbstractHash(Sha256Hash.withHash(new byte[0]));
			return Sha256Hash.withHash(new byte[0]);
		}
	}

	@Override
	public boolean isPresent(Sha256Hash hash) {
		return true;
	}
	
	@Override
	public boolean isPresent(Block block) {
		return true;
	}
	
	@Override
	public void stop() {}
	
	@Override
	public long getCurrentHeight() {
		return 0;
	}
}
