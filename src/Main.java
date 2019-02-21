import org.w3c.dom.CharacterData;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

class Main {
    public static void main(String[] args) {

        Main tester = new Main();
        try {
            URL gunlukKurUrl = new URL("https://tcmb.gov.tr/kurlar/today.xml");
            InputStream xml = gunlukKurUrl.openStream();
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();
            NodeList nodeListem = doc.getElementsByTagName("Currency");
            for (int i = 0; i < nodeListem.getLength(); i++) {

                Element element = (Element) nodeListem.item(i);
                System.out.println(element.getAttribute("CurrencyCode"));
                System.out.println(gitElementDegeri(element, "Isim"));
                System.out.println(gitElementDegeri(element, "CurrencyName"));
                System.out.println(gitElementDegeri(element, "BanknoteBuying"));
                System.out.println(gitElementDegeri(element, "BanknoteSelling"));
                System.out.println(gitElementDegeri(element, "ForexBuying"));
                System.out.println(gitElementDegeri(element, "ForexSelling"));
                System.out.println(gitElementDegeri(element, "Unit"));

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }


    private static String gitElementDegeri(Element parentElement, String label) {
        String retval = "";
        Element requiredElement = (Element) parentElement.getElementsByTagName(label).item(0);

        try {
            Node child = requiredElement.getFirstChild();
            if (child instanceof CharacterData) {
                CharacterData cd = (CharacterData) child;
                retval = cd.getData();
            }
        } catch (Exception ignored) {
        }
        return retval;
    }
}
