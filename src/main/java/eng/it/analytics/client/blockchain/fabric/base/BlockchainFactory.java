package eng.it.analytics.client.blockchain.fabric.base;

import eng.it.analytics.client.blockchain.fabric.FabricLedgerClient;
import eng.it.analytics.client.exception.SmartClientException;

import java.util.Properties;

/**
 * @author ascatox
 * This class decides the type of blockchain we can implement
 */
public final class BlockchainFactory {
    /**
     * @param type describer the type of blockchain
     */
    //private static Properties resourceBundle = ResourceBundle.getBundle("application", java.util.Locale.getDefault());
    private static Properties properties = new Properties();
    /*static {
        try {
            properties.load(BlockchainFactory.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    private static String type =  properties.getProperty("BLOCKCHAIN_TYPE");

    public LedgerClient getType(BlockchainType blockchainType) throws SmartClientException {
        if (blockchainType.equals(BlockchainType.HL_FABRIC))
            return new FabricLedgerClient();
        return null;

    }

    public LedgerClient getType() throws SmartClientException {
       // if (type.equalsIgnoreCase(BlockchainType.HL_FABRIC.name()))
            return new FabricLedgerClient();
       // else
        //    return new FabricLedgerClient();
        //return null;

    }
}
