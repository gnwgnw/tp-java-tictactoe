package resource;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * Created by titaevskiy.s on 20.11.14
 */
public class ResourceFactory {
    private static ResourceFactory resourceFactory;

    private ResourceFactory() {
    }

    public static ResourceFactory instance() {
        if (resourceFactory == null) {
            resourceFactory = new ResourceFactory();
        }
        return resourceFactory;
    }

    public Object get(String fileName) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();

            saxParser.parse(fileName, handler);

            return handler.getObject();
        }
        catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
