package generator;

import generator.model.Document;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.impl.schema.JSONSchema;

public class PulsarConnector {
    private static final String connectionUrl = "pulsar://localhost:6650";
    private static final String topicName = "persistent://public/default/document-topic";

    private final PulsarClient client;
    private final Producer<Document> producer;

    public PulsarConnector() throws PulsarClientException {
        client = PulsarClient.builder()
                .serviceUrl(connectionUrl)
                .build();

        producer = client.newProducer(JSONSchema.of(Document.class))
                .topic(topicName)
                .create();
    }

    public void send(Document document) throws PulsarClientException {
        System.out.println("Sending message: " + document);
        producer.send(document);
    }

    public void close() {
        try {
            producer.close();
            client.close();
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
    }
}
