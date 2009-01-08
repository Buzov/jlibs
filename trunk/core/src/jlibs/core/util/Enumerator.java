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

import java.util.Iterator;
import java.util.Enumeration;

/**
 * @author Santhosh Kumar T
 */
public class Enumerator<E> implements Iterator<E>{
    private Enumeration<E> enumer;

    public Enumerator(Enumeration<E> enumer){
        this.enumer = enumer;
    }

    @Override
    public boolean hasNext(){
        return enumer.hasMoreElements();
    }

    @Override
    public E next(){
        return enumer.nextElement();
    }

    @Override
    public void remove(){
        throw new UnsupportedOperationException();
    }
}
