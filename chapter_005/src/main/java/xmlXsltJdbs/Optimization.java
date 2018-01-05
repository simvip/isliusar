package xmlxsltjdbs;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.sql.*;

/**
 * Created by Ivan Sliusar on 26.12.2017.
 * Red Line Soft corp.
 */
public class Optimization {
    /**
     * Driver name.
     */
    private final String driverName = "org.sqlite.JDBC";
    /**
     * Connection string.
     */
    private String connectionString;
    /**
     * Amount records in base.
     */
    private int amountRecords;
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Main procedure.
     */
    public void run() {
        connect();

        initialBase();
        safefilenumber1();
        safefielnumber2();
        parsingFile();

        closeConnection();
    }

    /**
     * Parsing file.
     */
    private void parsingFile() {
        try {
            File fXmlFile = new File(System.getProperty("user.dir") + "\\2.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            NodeList nList = doc.getElementsByTagName("entry");
            int sum = 0;
            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);
                String value = nNode.getTextContent().replace("field=", "");
                sum += Integer.parseInt(value);
            }

            System.out.println("Sum " + sum);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Setter connection (been conseption).
     *
     * @param connectionString Connection
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * Setter amount records in base.
     *
     * @param amountRecords int
     */
    public void setAmountRecords(int amountRecords) {
        this.amountRecords = amountRecords;
    }

    /**
     * connect to base.
     */
    private void connect() {
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
    }

    /**
     * Colse connection.
     */
    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
            return;
        }
    }

    /**
     * Init base start values.
     */
    private void initialBase() {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS test(field INT)");
            statement.execute("DELETE FROM test");
            statement.close();

            PreparedStatement pStatmen = connection.prepareStatement("INSERT INTO test (field) VALUES (?)");
            for (int i = 0; i < amountRecords; i++) {
                pStatmen.setInt(1, (int) (Math.random() * 10));
                pStatmen.execute();
            }
            pStatmen.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Create table");

    }

    /**
     * Create and safe file number one.
     */
    private void safefilenumber1() {

        try {
            Statement statement = connection.createStatement();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;

            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElementNS("", "entries");
            doc.appendChild(rootElement);

            ResultSet rs = statement.executeQuery("SELECT * FROM test");
            while (rs.next()) {
                rootElement.appendChild(getNewNode(doc, rs.getInt("field")));
            }
            rs.close();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult file = new StreamResult(new File(System.getProperty("user.dir") + "\\1.xml"));
            DOMSource xmlSource = new DOMSource(doc);
            transformer.transform(xmlSource, file);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println("Save file 1.xml");
    }

    /**
     * Services function - getting new node.
     *
     * @param doc   Document
     * @param value int
     * @return Node
     */
    private static Node getNewNode(Document doc, int value) {
        Element element = doc.createElement("entry");
        //element.setAttribute("field", String.valueOf(value));
        element.appendChild(getNewElements(doc, element, "field", String.valueOf(value)));
        return element;
    }

    /**
     * утилитный метод для создание нового узла XML-файла
     *
     * @param doc     Document
     * @param element Element
     * @param name    String
     * @param value   String
     * @return Node Node
     */
    private static Node getNewElements(Document doc, Element element, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    /**
     * Create and safe file number 2.
     */
    private void safefielnumber2() {
        try {
            String dir = System.getProperty("user.dir") + "\\";

            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File(dir + "transform.xslt"));
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new File(dir + "1.xml"));
            transformer.transform(text, new StreamResult(new File(dir + "2.xml")));

            System.out.println("Save file 2.xml");

        } catch (TransformerException e) {

            e.printStackTrace();

        }

    }

}
