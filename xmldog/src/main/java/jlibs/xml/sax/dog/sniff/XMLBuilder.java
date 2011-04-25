/**
 * JLibs: Common Utilities for Java
 * Copyright (C) 2009  Santhosh Kumar T <santhosh.tekuri@gmail.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */

package jlibs.xml.sax.dog.sniff;

import jlibs.xml.sax.dog.NodeType;
import jlibs.xml.sax.helpers.MyNamespaceSupport;
import org.xml.sax.Attributes;

import javax.xml.stream.XMLStreamReader;
import java.util.Enumeration;

/**
 * @author Santhosh Kumar T
 */
public abstract class XMLBuilder{
    // changed to true by Event in onStartDocument/onStartElement if current event is hit
    // changed to false by doEndElement() when curNode is completely populated
    // keeps changing values true <-> false, so that nodes are created only portions of xml
    boolean active = false;

    protected abstract Object onStartDocument();
    protected abstract Object onStartElement(String uri, String localName, String qualifiedName);
    protected abstract Object onEvent(Event event);
    protected abstract Object onEndElement();
    protected abstract void onEndDocument();

    Object doEndElement(){
        assert active;
        Object node = onEndElement();
        if(node==null)
            active = false;
        return node;
    }

    public void onAttributes(Event event, Attributes attrs){
        assert active;
        int len = attrs.getLength();
        for(int i=0; i<len; i++){
            event.setData(NodeType.ATTRIBUTE, attrs.getURI(i), attrs.getLocalName(i), attrs.getQName(i), attrs.getValue(i));
            onEvent(event);
        }
    }

    public void onAttributes(Event event, XMLStreamReader reader){
        assert active;
        int len = reader.getAttributeCount();
        for(int i=0; i<len; i++){
            String prefix = reader.getAttributePrefix(i);
            String localName = reader.getAttributeLocalName(i);
            String qname = prefix.length()==0 ? localName : prefix+':'+localName;
            String uri = reader.getAttributeNamespace(i);
            if(uri==null)
                uri = "";
            event.setData(NodeType.ATTRIBUTE, uri, localName, qname, reader.getAttributeValue(i));
            onEvent(event);
        }
    }

    public void onNamespaces(Event event, MyNamespaceSupport nsSupport){
        assert active;
        Enumeration<String> prefixes = nsSupport.getPrefixes();
        while(prefixes.hasMoreElements()){
            String prefix = prefixes.nextElement();
            String uri = nsSupport.getURI(prefix);
            event.setData(NodeType.NAMESPACE, "", prefix, prefix, uri);
            onEvent(event);
        }
    }
}
