package ru.itits.rabbitmqprojet.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.parser.listener.IPdfTextLocation;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.pdfcleanup.autosweep.CompositeCleanupStrategy;
import com.itextpdf.pdfcleanup.autosweep.PdfAutoSweep;
import com.itextpdf.pdfcleanup.autosweep.RegexBasedCleanupStrategy;
import lombok.SneakyThrows;
import ru.itits.rabbitmqprojet.model.VacationDocument;

public class VacationDocCreate {

    @SneakyThrows
    public void createVacationDocument(VacationDocument vacationDocument) {
        CompositeCleanupStrategy strategy = new CompositeCleanupStrategy();
        strategy.add(new RegexBasedCleanupStrategy("ФИО").setRedactionColor(null));

        String fullName = vacationDocument.getEmployee().getSecondName() + " " +
                vacationDocument.getEmployee().getFirstName() + " " +
                vacationDocument.getEmployee().getLastName();

        PdfWriter writer = new PdfWriter(Vars.PATH_DOC + "/vacation_" + fullName + ".pdf");

        writer.setCompressionLevel(0);
        PdfDocument pdf = new PdfDocument(new PdfReader(Vars.PATH_DOC + "/vacation_template.pdf"), writer);
        PdfAutoSweep autoSweep = new PdfAutoSweep(strategy);
        autoSweep.cleanUp(pdf);

        for (IPdfTextLocation location : strategy.getResultantLocations()) {
            PdfPage page = pdf.getPage(location.getPageNumber() + 1);
            PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamAfter(), page.getResources(), page.getDocument());
            Canvas canvas = new Canvas(pdfCanvas, pdf, location.getRectangle());
            canvas.add(new Paragraph(fullName).setFontSize(15));
        }

        pdf.close();
    }

}
