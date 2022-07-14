import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XPathSample {
    public static void main(String[] args) throws Exception {
        // 1.Documentを作るまでの流れはDOMと同じ
        FileInputStream is = new FileInputStream(Paths.get("./bookList.xml").toFile());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(is);

        // 2.XPathの処理を実行するXPathのインスタンスを取得する
        XPath xpath = XPathFactory.newInstance().newXPath();
        // 3.XPathでの検索条件を作る
        XPathExpression expr = xpath.compile("/BookList/Book");
        // 4.DocumentをXPathで検索して、結果をDOMのNodeListで受け取る
        NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

        // 5.XPathでの検索結果を持っているNodeListの内容でループ
        for (int i = 0; i < nodeList.getLength(); i++) {
            // 6.要素を検索しているのでNodeの実体はElement。キャストして使う。
            Element element = (Element) nodeList.item(i);

            // 7.Elementから必要な情報を取得して出力する
            System.out.println("isbn = " + element.getAttribute("isbn"));
            System.out.println("title = " + element.getAttribute("title"));
            System.out.println("author = " + element.getAttribute("author"));
            System.out.println("text = " + element.getTextContent());
            System.out.println();
        }

        try (
                FileOutputStream fos = new FileOutputStream("serial.dat");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
                oos.writeObject(document);

        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

    }
}
