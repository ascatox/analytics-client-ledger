package eng.it.analytics.client.blockchain.fabric;

import eng.it.analytics.client.exception.SmartClientException;
import eng.it.analytics.client.tool.JsonConverter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import eng.it.analytics.client.blockchain.fabric.base.LedgerClient;
import eng.it.analytics.client.blockchain.fabric.config.ConfigManager;
import eng.it.analytics.client.blockchain.fabric.config.Configuration;
import eng.it.analytics.client.blockchain.fabric.config.Organization;
import eng.it.analytics.client.blockchain.fabric.helper.Function;
import eng.it.analytics.client.blockchain.fabric.helper.InvokeReturn;
import eng.it.analytics.client.blockchain.fabric.helper.LedgerInteractionHelper;
import eng.it.analytics.client.blockchain.fabric.helper.QueryReturn;
import eng.it.analytics.client.blockchain.fabric.model.Data;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//@Component
final public class FabricLedgerClient implements LedgerClient {

    private final static Logger log = LogManager.getLogger(FabricLedgerClient.class);

    private LedgerInteractionHelper ledgerInteractionHelper;
    private ConfigManager configManager;
    private Validator validator;

    public FabricLedgerClient() throws SmartClientException {
        doLedgerClient();
    }


    private void doLedgerClient() throws SmartClientException {
        try {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            validator = factory.getValidator();
            configManager = ConfigManager.getInstance();
            Configuration configuration = configManager.getConfiguration();
            if (null == configuration || null == configuration.getOrganizations() || configuration.getOrganizations().isEmpty()) {
                log.error("Configuration missing!!! Check you config file!!!");
                throw new SmartClientException("Configuration missing!!! Check you config file!!!");
            }
            List<Organization> organizations = configuration.getOrganizations();
            if (null == organizations || organizations.isEmpty())
                throw new SmartClientException("Organizations missing!!! Check you config file!!!");
            //for (Organization org : organizations) {
            //FIXME multiple Organizations
            ledgerInteractionHelper = new LedgerInteractionHelper(configManager, organizations.get(0));
            //}
        } catch (Exception e) {
            log.error(e);
            throw new SmartClientException(e);
        }
    }


    private String doInvoke(Function fcn, List<String> stringArrayList) throws SmartClientException {

        final InvokeReturn invokeReturn = ledgerInteractionHelper.invokeChaincode(fcn.name(), stringArrayList);
        try {
            log.debug("BEFORE -> Store Completable Future at " + System.currentTimeMillis());
            invokeReturn.getCompletableFuture().get(configManager.getConfiguration().getTimeout(), TimeUnit.MILLISECONDS);
            log.debug("AFTER -> Store Completable Future at " + System.currentTimeMillis());
            final String payload = invokeReturn.getPayload();
            return payload;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.error(fcn.name().toUpperCase() + " " + e.getMessage());
            throw new SmartClientException(fcn.name() + " " + e.getMessage());
        }
    }


    private List<String> doQuery(Function fcn, List<String> args, boolean isCollection) throws SmartClientException {
        List<String> data = new ArrayList<>();
        try {
            final List<QueryReturn> queryReturns = ledgerInteractionHelper.queryChainCode(fcn.name(), args, null);
            for (QueryReturn queryReturn : queryReturns) {
                data.add(queryReturn.getPayload());
            }
            return data;
        } catch (Exception e) {
            log.error(fcn.name() + " " + e.getMessage());
            throw new SmartClientException(fcn.name() + " " + e.getMessage());
        }
    }

    @Override
    public final Data getData(String key ) throws SmartClientException {
        if( key.isEmpty()){
            throw new SmartClientException(Function.getData.name() + "is in error, No input data!");
        }
        List<String> args = new ArrayList<>();
        args.add(key);

        List<String> payloads = doQuery(Function.getData, args, false);
        Data data = new Data();
        data.setPayload(payloads.get(0));
        data.setKey(key);
        return data;
    }

    @Override
    public void putData(Data data) throws SmartClientException {
        if( data == null){
            throw new SmartClientException((Function.putData.name() + "is in error, No input data!"));
        }
        List<String> stringArrayList = new ArrayList<>();
        stringArrayList.add(data.getKey());
        stringArrayList.add(data.getPayload());
        final String payload = doInvoke(Function.putData, stringArrayList);
        log.debug("Payload retrieved: " + payload);
    }


}
