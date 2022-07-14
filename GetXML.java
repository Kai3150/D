import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;

import java.net.URL;

public class GetXML {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(new URL("https://www.data.jma.go.jp/developer/xml/feed/extra_l.xml").openStream());

        // 2.XPathの処理を実行するXPathのインスタンスを取得する
        XPath xpath = XPathFactory.newInstance().newXPath();
        // 3.XPathでの検索条件を作る
        XPathExpression expr = xpath.compile("/feed/entry/title");
        // 4.DocumentをXPathで検索して、結果をDOMのNodeListで受け取る
        NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

        // 5.XPathでの検索結果を持っているNodeListの内容でループ
        for (int i = 0; i < nodeList.getLength(); i++) {
            // 6.要素を検索しているのでNodeの実体はElement。キャストして使う。
            Element element = (Element) nodeList.item(i);

            // 7.Elementから必要な情報を取得して出力する
            System.out.println("text = " + element.getTextContent());
            System.out.println();
        }

    }
}
