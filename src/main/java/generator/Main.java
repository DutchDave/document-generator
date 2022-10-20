package generator;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.PulsarClientException;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("Hello world!");
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
