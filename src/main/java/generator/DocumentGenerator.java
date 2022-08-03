package generator;

import generator.model.Document;
import generator.model.DocumentType;

import java.util.concurrent.ThreadLocalRandom;

public class DocumentGenerator {
    private int documentId = 0;

    public Document generate() {
        final String documentNummer = "DSR" + documentId++;
        final int randomInt = ThreadLocalRandom.current().nextInt(DocumentType.values().length);
        final DocumentType documentType = DocumentType.values()[randomInt];

        return new Document(documentNummer, documentType);
    }
}
