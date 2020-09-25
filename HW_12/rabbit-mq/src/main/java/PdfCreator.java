import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

public class PdfCreator {

    private static final String DEST = "/home/yuliya/Desktop/";

    private static ObjectMapper objectMapper = new ObjectMapper();

    public void create(String type, String messageFromQueue) throws FileNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(messageFromQueue, UserInfo.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST + type  + "_" + userInfo.getName() + "_" + userInfo.getSecondName() + ".pdf"));
        Document document = new Document(pdf);
        document.add(new Paragraph("Statement"));
        document.add(new Paragraph("I " + userInfo.getSecondName() + " " + userInfo.getName()));
        document.add(new Paragraph("Passport number " + userInfo.getPassportNumber() + " issued " + userInfo.getDate()));
        document.add(new Paragraph("Ask to " + type + " me"));
        document.add(new Paragraph("Signature _____________"));
        document.close();
        System.out.println("Done");
    }

}
