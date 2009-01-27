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

package jlibs.xml.sax.sniff.model.listeners;

import jlibs.xml.sax.sniff.model.ResultType;
import jlibs.xml.sax.sniff.model.Results;

/**
 * @author Santhosh Kumar T
 */
public class LogicalOperation extends DerivedResults{
    private boolean and;

    public LogicalOperation(boolean and){
        this.and = and;
    }

    @Override
    public ResultType resultType(){
        return ResultType.BOOLEAN;
    }

    @Override
    public void prepareResults(){
        Results lhsMember = members.get(0);
        lhsMember.prepareResults();
        boolean lhs = lhsMember.asBoolean();

        if(and && !lhs){
            addResult(-1, String.valueOf(false));
            return;
        }

        if(!and && lhs){
            addResult(-1, String.valueOf(true));
            return;
        }

        Results rhsMember = members.get(1);
        rhsMember.prepareResults();
        boolean rhs = rhsMember.asBoolean();
        addResult(-1, String.valueOf(rhs));
    }
 
    @Override
    public String getName(){
        return and ? "and" : "or";
    }
}