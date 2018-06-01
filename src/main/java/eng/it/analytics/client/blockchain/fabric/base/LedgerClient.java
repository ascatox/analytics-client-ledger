package eng.it.analytics.client.blockchain.fabric.base;


import eng.it.analytics.client.blockchain.fabric.model.Data;
import eng.it.analytics.client.exception.SmartClientException;

public interface LedgerClient {

    /**
     *
     * @param
     * @return
     * @throws
     */

    //Bay getBay(Item item) throws SmartClientException;

    Data getData(String key) throws SmartClientException;

    void putData( Data data) throws  SmartClientException;

}