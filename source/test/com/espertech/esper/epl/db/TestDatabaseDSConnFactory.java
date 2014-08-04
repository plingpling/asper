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

package com.espertech.esper.epl.db;

import com.espertech.esper.client.ConfigurationDBRef;
import com.espertech.esper.support.epl.SupportDatabaseService;
import com.espertech.esper.support.epl.SupportInitialContextFactory;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import junit.framework.TestCase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class TestDatabaseDSConnFactory extends TestCase
{
    private DatabaseDSConnFactory databaseDSConnFactory;

    public void setUp()
    {
        MysqlDataSource mySQLDataSource = new MysqlDataSource();
        mySQLDataSource.setUser(SupportDatabaseService.DBUSER);
        mySQLDataSource.setPassword(SupportDatabaseService.DBPWD);
        mySQLDataSource.setURL("jdbc:mysql://localhost/test");

        String envName = "java:comp/env/jdbc/MySQLDB";
        SupportInitialContextFactory.addContextEntry(envName, mySQLDataSource);

        ConfigurationDBRef config = new ConfigurationDBRef();
        Properties properties = new Properties();
        properties.put("java.naming.factory.initial", SupportInitialContextFactory.class.getName());
        config.setDataSourceConnection(envName, properties);

        databaseDSConnFactory = new DatabaseDSConnFactory((ConfigurationDBRef.DataSourceConnection)config.getConnectionFactoryDesc(), config.getConnectionSettings());
    }

    public void testGetConnection() throws Exception
    {
        Connection connection = databaseDSConnFactory.getConnection();
        tryAndCloseConnection(connection);
    }

    private void tryAndCloseConnection(Connection connection) throws Exception
    {
        Statement stmt = connection.createStatement();
        stmt.execute("select 1 from dual");
        ResultSet result = stmt.getResultSet();
        result.next();
        assertEquals(1, result.getInt(1));
        result.close();
        stmt.close();
        connection.close();
    }

    private static Log log = LogFactory.getLog(TestDatabaseDSConnFactory.class);
}
