package pro.meisen.base;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * @author meisen
 * 2017-07-06
 */
public class TestCreatePDF {

    private static final String DEST = "target/HelloWorld.pdf";
    private static final String DEST_CN = "target/HelloWorld_CN.pdf";
    private static final String FONT = "msyh.ttf";

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
//       createHelloWorld();
        javaToPdfCn();
    }


    private static void javaToPdfCn() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST_CN));
        document.open();
        Font f1 = FontFactory.getFont(FONT, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        document.add(new Paragraph("Hello, xebcxc", f1));
        document.close();
        writer.close();
    }

    private static void createHelloWorld() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        document.add(new Paragraph("hello world"));
        document.close();
        writer.close();
    }
}
