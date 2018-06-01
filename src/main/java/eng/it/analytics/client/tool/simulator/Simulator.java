package eng.it.analytics.client.tool.simulator;

import eng.it.analytics.client.exception.SmartClientException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author clod16
 */
@Component
public final class Simulator {
    private Logger logger = LogManager.getLogger(Simulator.class);

   // @Autowired
    //private Conveyor conveyor;
   // @Autowired
   // private HandlerManager handlerManager;
    private int index;

    public Simulator() {
    }

    public void simulate() throws SmartClientException {
        logger.info("Simulation step at: " + new Date());




    }


    public void startup() throws SmartClientException {
        try {

            //Init loop
            //Init baie
            logger.info("Simulation starts");
        } catch (Exception e) {
            throw new SmartClientException(e.getMessage());
        }
    }

}