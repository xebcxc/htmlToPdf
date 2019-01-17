package pro.meisen.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author meisen
 * 2017-07-06
 */
public class HtmlToPdfFreeMaker {

    private static final String DEST = "target/HelloWorld_CN_HTML_FREEMAKER.pdf";
    private static final String HTML = "template_freemaker.html";
    private static final String FONT = "msyh.ttf";

    private static Configuration freemarkerCfg = null;


    static {
        freemarkerCfg =new Configuration();
        //freemarker的模板目录
        try {
            freemarkerCfg.setDirectoryForTemplateLoading(new File(PathUtil.getCurrentPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, DocumentException {
        Map<String,Object> data = new HashMap<>();
        data.put("name","xebcxc!");
        String content = HtmlToPdfFreeMaker.freeMarkerRender(data,HTML);
        HtmlToPdfFreeMaker.htmlToFreePDF(content);
    }

    private static void htmlToFreePDF(String content) throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(DEST));
        document.open();
        XMLWorkerFontProvider fontProvider = new XMLWorkerFontProvider(XMLWorkerFontProvider.DONTLOOKFORFONTS);
        fontProvider.register(FONT);
        XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document,  new ByteArrayInputStream(content.getBytes()), null, Charset.forName("UTF-8"), fontProvider);
        document.close();
    }


    /**
     * freemarker渲染html
     */
    public static String freeMarkerRender(Map<String, Object> data, String htmlTmp) {
        Writer out = new StringWriter();
        try {
            // 获取模板,并设置编码方式
            Template template = freemarkerCfg.getTemplate(htmlTmp);
            template.setEncoding("UTF-8");
            // 合并数据模型与模板
            template.process(data, out); //将合并后的数据和模板写入到流中，这里使用的字符流
            out.flush();
            return out.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }
}
