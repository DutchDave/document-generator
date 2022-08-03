package generator.model;

import lombok.Data;

@Data
public class Document {
    private final String documentNummer;
    private final DocumentType documentType;
}
