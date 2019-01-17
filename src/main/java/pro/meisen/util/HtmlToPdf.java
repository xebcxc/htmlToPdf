package pro.meisen.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author meisen
 * 2017-07-06
 */
public class HtmlToPdf {

    private static final String DEST = "target/HelloWorld_CN_HTML.pdf";
    private static final String HTML = "template.html";
    private static final String FONT = "msyh.ttf";

    public static void main(String[] args) throws IOException, DocumentException {
        htmlToPDF();
    }

    private static void htmlToPDF() throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new FileInputStream(HTML), null, Charset.forName("UTF-8"), fontProvider);
        document.close();
    }

}
