package edu.asm.hoctap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class MySaxHandler extends DefaultHandler {
    ArrayList<Item> items;
    Item item_tam;
    String chuoi_tam;
    boolean vao_item=false;
    public MySaxHandler() {
        items=new ArrayList<Item>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        if(qName.equalsIgnoreCase("item")) {
            item_tam=new Item();
            vao_item=true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
        if(vao_item==true)
            chuoi_tam=new String(ch,start,length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
        if(qName.equalsIgnoreCase("item")) {
            items.add(item_tam);
        }else if(vao_item==true) {

            if(qName.equalsIgnoreCase("title"))
                item_tam.setTitle(chuoi_tam);

            if(qName.equalsIgnoreCase("description"))
                item_tam.setDescription(chuoi_tam);

            if(qName.equalsIgnoreCase("link"))
                item_tam.setLink(chuoi_tam);

            if(qName.equalsIgnoreCase("pubdate"))
                item_tam.setPubdate(chuoi_tam);

        }

    }
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

}
