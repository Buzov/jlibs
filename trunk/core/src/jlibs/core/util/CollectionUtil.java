/**
 * JLibs: Common Utilities for Java
 * Copyright (C) 2009  Santhosh Kumar T
 * <p/>
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * <p/>
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 */

package jlibs.core.util;

import jlibs.core.graph.Filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * @author Santhosh Kumar T
 */
public class CollectionUtil{
    /**
     * Creates Properties from given inputStream and returns it.
     * NOTE: the given stream is closed by this method
     */
    public static Properties readProperties(InputStream is, Properties defaults) throws IOException{
        Properties props = new Properties(defaults);
        try{
            props.load(is);
        }finally{
            is.close();
        }
        return props;
    }

    /**
     * Adds objects in array to the given collection
     *
     * @return the same collection which is passed as argument
     */
    @SuppressWarnings({"unchecked", "ManualArrayToCollectionCopy"})
    public static <E, T extends E> Collection<E> addAll(Collection<E> c, T... array){
        for(T obj: array)
            c.add(obj);
        return c;
    }

    /**
     * Removes objects in array to the given collection
     *
     * @return the same collection which is passed as argument
     */
    @SuppressWarnings("unchecked")
    public static <E, T extends E> Collection<E> removeAll(Collection<E> c, T... array){
        for(T obj: array)
            c.remove(obj);
        return c;
    }

    /**
     * Adds the given item to the list at specified <code>index</code>.
     * if <code>index</code> is greater than list size, it simply appends
     * to the list.
     */
    public static <E, T extends E> void add(List<E> list, int index, T item){
        if(index<list.size())
            list.add(index, item);
        else
            list.add(item);
    }

    /**
     * Returns List with elements from given collections which are selected
     * by specified filter
     */
    public static <T> List<T> filter(Collection<T> c, Filter<T> filter){
        if(c.size()==0)
            return Collections.emptyList();

        List<T> filteredList = new ArrayList<T>(c.size());
        for(T element: c){
            if(filter.select(element))
                filteredList.add(element);
        }
        return filteredList;
    }
}