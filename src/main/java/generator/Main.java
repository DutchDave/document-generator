package generator;

import org.apache.pulsar.client.api.PulsarClientException;

public class Main {
    public static void main(String[] args) {
        final PulsarConnector connector;
        try {
            connector = new PulsarConnector();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
        final DocumentGenerator documentGenerator = new DocumentGenerator();
        final Scheduler scheduler = new Scheduler(0, 100, connector, documentGenerator);
        scheduler.run();
    }
}
