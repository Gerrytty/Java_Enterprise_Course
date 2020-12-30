package ru.itits.rabbitmqprojet;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException, IOException {

        String path = "/home/yuliya/Desktop/test";
        String path2 = "/home/yuliya/Documents/kek.odg";

        PdfDocument pdfDocument = new PdfDocument(new PdfReader(path2),
                new PdfWriter(new File(path + "/out.pdf")));


        PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDocument, true);
        System.out.println(form.getFormFields());

//        form.getField("name").setReadOnly(true);
//        form.getField("name").setValue("Михайлова Юлия Александровна");

        Document document = new Document(pdfDocument);
        document.close();
        pdfDocument.close();


////        // IO
//        File htmlSource = new File("/home/yuliya/Desktop/test/input.html");
//        File pdfDest = new File("/home/yuliya/Desktop/test/output.pdf");
//        // pdfHTML specific code
//        ConverterProperties converterProperties = new ConverterProperties();
//        HtmlConverter.convertToPdf(new FileInputStream(htmlSource), new FileOutputStream(pdfDest), converterProperties);

//        Employee employee = Employee.builder()
//                .firstName("Julia")
//                .secondName("Mihaylova")
//                .lastName("Alexandrovna")
//                .salary(10000000)
//                .position("lead java developer")
//                .passportNumber("1234567")
//                .phone("+79870669605")
//                .build();
//
//        VacationDocumentCreator vacationDocumentCreator = new VacationDocumentCreator(VacationDocument.builder()
//                .status(Status.WAIT)
//                .employee(employee)
//                .startDate(new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2021"))
//                .build());
//
//        vacationDocumentCreator.createDocument();
    }
}
