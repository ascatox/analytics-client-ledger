package eng.it.analytics.client.tool;


/**
 * @author ascatox
 */
public class XMLReader {
    /*private DispatchPlan dispatchPlan = new DispatchPlan();

    private Logger logger = LogManager.getLogger(XMLReader.class);


    public XMLReader() {
    }

    public Map<Item, List<Node>> readDispactPlan() throws ConveyorHubException {
        //FIXME provare con jackson!!!
       // writeDispatchPlan();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DispatchPlan.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            InputStream resource = getClass().getResourceAsStream("/dispatchPlan.xml");
            DispatchPlan dispatchPlan = (DispatchPlan) jaxbUnmarshaller.unmarshal(resource);  //FIXME path

            logger.info("Object DispatchPlan build on!!!");
            System.out.println(dispatchPlan);
            Map<Item, List<Node>> map = fromObjectToMap(dispatchPlan);
            return map;


        } catch (Exception e) {
            logger.error(e);
            throw new ConveyorHubException(e);
        }
    }*/


    /*private void writeDispatchPlan() {
        JAXBContext jaxbContext = null;
        DispatchPlan dispatchPlan = new DispatchPlan();
        Route route = new Route();
        Item item = new Item();
        item.setId(1);
        item.setType("oven");
        Item item1= new Item();
        item1.setId(2);
        item1.setType("fridge");
        Node node = new Node();
        node.setId(11);
        node.setFork(1);
        Node node1 = new Node();
        node1.setId(11);
        node1.setFork(1);
        List nodes = new ArrayList();
        nodes.add(node);
        nodes.add(node1);
        route.setNode(nodes);
        route.setItem(item);
        route.setItem(item1);
        List routes = new ArrayList();
        routes.add(route);
        Bay bay = new Bay();
        bay.setId(1);
        List<Bay> bays = new ArrayList<>();
        bays.add(bay);
        dispatchPlan.setBay(bays);
        dispatchPlan.setRoute(routes);
        try {
            jaxbContext = JAXBContext.newInstance(DispatchPlan.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(dispatchPlan, System.out);

        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
    */

 /*   private Map<Item, List<Node>> fromObjectToMap(DispatchPlan dispatchPlan) {

        Map<Item, List<Node>> map = new Hashtable<>();         //FIXME maybe incorrect
        for (Route route : dispatchPlan.getRoute()) {
            Item item = route.getItem();
            map.put(item, route.getNode());
        }
       *//* for(Map.Entry<Item, ArrayList<Node>> entry : map.entrySet()){
            for(Node node : entry.getValue()) {
                System.out.println(node.toString());
            } }

        map.forEach((key, value) -> System.out.println(key + ":" + value));

        for (Map.Entry<Item, ArrayList<Node>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }*//*

        logger.debug("Map are created from .xml");

        return map;
    }


    public int searchItemRoute(Item item, Map<Item, List<Node>> dispatchPlan) { // metodo che estrapola la biforcazione di un Item

        List<Node> nodeArrayList = dispatchPlan.get(item);
        Node node = nodeArrayList.get(1);
        logger.info("Item route extract");
        return node.getId();
    }


    public int counterForkFromXML() {//FIXME
        int count = 0;

        for (Bay bay : dispatchPlan.getBay())

            logger.info("Number of exit bay:" + bay);
        count++;

        return count;
    }*/
}





