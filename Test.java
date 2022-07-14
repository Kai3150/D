import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.*;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Test {
    public static void main(String args[]) {

        try (
                FileInputStream fis = new FileInputStream("serial.dat");
                ObjectInputStream ois = new ObjectInputStream(fis);
        ) {

            Document document = (Document) ois.readObject();


            XPath xpath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = xpath.compile("/BookList/Book");

            NodeList nodeList = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

            JFrame frame = new JFrame();

            String texts = "";
            // 5.XPathでの検索結果を持っているNodeListの内容でループ
            for (int i = 0; i < nodeList.getLength(); i++) {
                // 6.要素を検索しているのでNodeの実体はElement。キャストして使う。
                Element element = (Element) nodeList.item(i);

                // 7.Elementから必要な情報を取得して出力する


                texts += "isbn = " + element.getAttribute("isbn") + "\n" + "title = " + element.getAttribute("title") + "\n" + "author = " + element.getAttribute("author") + "\n" + "text = " + element.getTextContent();

            }

            JLabel text = new JLabel(texts);
            System.out.println(texts);
            frame.add(text);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("JLabelを使って画像やテキストを表示");
            frame.setSize(530, 170);
            frame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
