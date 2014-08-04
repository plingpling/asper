/*
 * *************************************************************************************
 *  Copyright (C) 2008 EsperTech, Inc. All rights reserved.                            *
 *  http://esper.codehaus.org                                                          *
 *  http://www.espertech.com                                                           *
 *  ---------------------------------------------------------------------------------- *
 *  The software in this package is published under the terms of the GPL license       *
 *  a copy of which has been included with this distribution in the license.txt file.  *
 * *************************************************************************************
 */

package com.espertech.esper.support.bean;

import java.io.Serializable;

public class SupportMarketDataIDBean implements Serializable
{
    private String symbol;
    private String id;
    private double price;

    public SupportMarketDataIDBean(String symbol, String id, double price)
    {
        this.symbol = symbol;
        this.id = id;
        this.price = price;
    }

    public String getSymbol()
    {
        return symbol;
    }

    public double getPrice()
    {
        return price;
    }

    public String getId()
    {
        return id;
    }
}
