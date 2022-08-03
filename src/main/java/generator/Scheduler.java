package generator;

import generator.model.Document;
import org.apache.pulsar.client.api.PulsarClientException;

import java.util.Timer;
import java.util.TimerTask;

public class Scheduler {
    private final long delay;
    private final long period;
    private final PulsarConnector pulsarConnector;
    private final DocumentGenerator documentGenerator;

    public Scheduler(long delay, long period, PulsarConnector pulsarConnector, DocumentGenerator documentGenerator) {
        this.delay = delay;
        this.period = period;
        this.pulsarConnector = pulsarConnector;
        this.documentGenerator = documentGenerator;
    }

    public void run() {
        new Timer().scheduleAtFixedRate(new GenerateAndSendDocument(), delay, period);
    }

    private class GenerateAndSendDocument extends TimerTask {
        @Override
        public void run() {
            final Document document = documentGenerator.generate();
            System.out.println(document);
            try {
                pulsarConnector.send(document);
            } catch (PulsarClientException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
