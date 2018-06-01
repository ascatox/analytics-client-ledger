package integration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import eng.it.analytics.client.blockchain.fabric.base.BlockchainFactory;
import eng.it.analytics.client.blockchain.fabric.base.LedgerClient;
import eng.it.analytics.client.blockchain.fabric.model.Data;
import eng.it.analytics.client.exception.SmartClientException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class End2EndTest {


    static LedgerClient ledgerClient;

    @BeforeClass
    public static void begin() {
        try {
            //ProxyConfig.setProxy();
            BlockchainFactory factory = new BlockchainFactory();
            ledgerClient = factory.getType();

        } catch (SmartClientException e) {
            assertFalse(e.getMessage(), true);
        }
    }

    @AfterClass
    public static void end() {
        ledgerClient = null;
    }




    @Test
    public void testPutGetData(){

        Data data = new Data();
        data.setPayload("ABCDEFGHILMNOPQRSTUVZ");
        data.setKey("A1");
        try{
            ledgerClient.putData(data);
            Data data1 = ledgerClient.getData(data.getKey());
            System.out.println("Test Data: " +data.getPayload());
            System.out.println("Ledger Dataa: "+data1.getPayload());
            assertEquals(data1.getPayload(), data.getPayload());


        } catch (SmartClientException e) {
            assertFalse(e.getMessage(), true);
        }


    }


}
