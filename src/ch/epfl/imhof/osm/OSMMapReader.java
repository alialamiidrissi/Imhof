package ch.epfl.imhof.osm;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import ch.epfl.imhof.PointGeo;
import ch.epfl.imhof.osm.OSMRelation.Member.Type;

/**
 * construire une carte OpenStreetMap à partir de données stockées dans un
 * fichier au format OSM.
 *
 * @author Ben Lalah Ali (251758)
 * @author Alami Idrissi Ali (251759)
 */

public final class OSMMapReader {

    private OSMMapReader() {

    }

    private static InputSource readFile(String fileName, boolean unGZip)
            throws Exception {
        try {
            InputStream i = new FileInputStream(fileName);
            if (unGZip) {
                GZIPInputStream a = new GZIPInputStream(i);
                return new InputSource(a);
            } else
                return new InputSource(i);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 
     * @param fileName
     *            : Nom du fichier OSM.
     * @param unGZip
     *            : Vrai si le fichier compressé en format GZip.
     * @return La carte OSM contenue dans le fichier de nom donné, en le
     *         décompressant avec gzip.
     */
    public static OSMMap readOSMFile(String fileName, boolean unGZip) {
        try {
            XMLReader factory = XMLReaderFactory.createXMLReader();
            ContentHandler handler = new Handler();
            factory.setContentHandler(handler);
            factory.parse(readFile(fileName, unGZip));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class Handler implements ContentHandler {
        private OSMMap.Builder builder;
        private OSMWay.Builder wBuilder;
        private OSMRelation.Builder rBuilder;
        private boolean endedR = true;

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {

        }

        @Override
        public void endDocument() throws SAXException {

        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            switch (qName) {
            case "way":
                if (!wBuilder.isIncomplete())
                    builder.addWay(wBuilder.build());
                break;
            case "relation":
                OSMRelation relation = null;
                try {
                    relation = rBuilder.build();
                    builder.addRelation(relation);
                } catch (Exception e) {

                }
                endedR = true;
                break;
            }

        }

        @Override
        public void endPrefixMapping(String prefix) throws SAXException {
            // TODO Auto-generated method stub

        }

        @Override
        public void ignorableWhitespace(char[] ch, int start, int length)
                throws SAXException {
            // TODO Auto-generated method stub

        }

        @Override
        public void processingInstruction(String target, String data)
                throws SAXException {
            // TODO Auto-generated method stub

        }

        @Override
        public void setDocumentLocator(Locator locator) {
            // TODO Auto-generated method stub

        }

        @Override
        public void skippedEntity(String name) throws SAXException {
            // TODO Auto-generated method stub

        }

        @Override
        public void startDocument() throws SAXException {

        }

        @Override
        public void startElement(String uri, String localName, String qName,
                Attributes atts) throws SAXException {
            switch (qName) {
            case "node":
                long id = Long.parseLong(atts.getValue("id"));
                double lon = Double.parseDouble(atts.getValue("lon")),
                la = Double.parseDouble(atts.getValue("la"));
                PointGeo point = new PointGeo(lon, la);
                OSMNode.Builder nBuilder = new OSMNode.Builder(id, point);
                builder.addNode(nBuilder.build());
                break;
            case "way":
                long idWay = Long.parseLong(atts.getValue("id"));
                wBuilder = new OSMWay.Builder(idWay);
                break;
            case "nd":
                OSMNode node = builder.nodeForId(Long.parseLong(atts
                        .getValue("ref")));
                if (node != null)
                    wBuilder.addNode(node);
                else
                    wBuilder.setIncomplete();
                break;
            case "tag":
                String k = atts.getValue("k");
                String v = atts.getValue("v");
                if (endedR)
                    wBuilder.setAttribute(k, v);
                else
                    rBuilder.setAttribute(k, v);

            case "relation":
                long idR = Long.parseLong(atts.getValue("id"));
                rBuilder = new OSMRelation.Builder(idR);
                endedR = false;
                break;
            case "member":
                Type type = null;
                OSMEntity membre = null;
                long idM = Long.parseLong(atts.getValue("ref"));
                String rType = atts.getValue("type");
                if (rType.equals("node")) {
                    type = Type.NODE;
                    membre = builder.nodeForId(idM);
                } else if (rType.equals("way")) {
                    type = Type.WAY;
                    membre = builder.wayForId(idM);
                } else if (rType.equals("relation")) {
                    type = Type.RELATION;
                    membre = builder.wayForId(idM);
                }
                String role = atts.getValue("role");
                if (type != null && membre != null)
                    rBuilder.addMember(type, role, membre);
                else
                    rBuilder.setIncomplete();
                break;
            }

        }

        @Override
        public void startPrefixMapping(String prefix, String uri)
                throws SAXException {
            // TODO Auto-generated method stub

        }

    }

}
