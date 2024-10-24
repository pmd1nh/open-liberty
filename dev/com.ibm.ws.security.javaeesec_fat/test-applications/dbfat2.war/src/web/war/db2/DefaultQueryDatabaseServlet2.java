/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package web.war.db2;

import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.sql.DataSource;

import com.ibm.ws.security.javaeesec.fat_helper.Constants;

import componenttest.app.FATDatabaseServlet;

public class DefaultQueryDatabaseServlet2 extends FATDatabaseServlet {
    private static final long serialVersionUID = 6698194309425789687L;

    private final String callerTable = "CALLERS";
    private final String callerGroups = "CALLER_GROUPS";

    @Override
    public void init() throws ServletException {
        System.out.println("Creating database for DatabaseIdentityStore");
        try {
            DataSource ds = (DataSource) new InitialContext().lookup("jdbc/derby2fat");

            Connection conn = ds.getConnection();
            try {
                Statement stmt1 = conn.createStatement();
                stmt1.executeUpdate("create schema dbuser1");
                stmt1.close();
            } finally {
                conn.close();
            }

            FATDatabaseServlet.createTable(ds, callerTable, "name varchar(30), password varchar(300)");

            FATDatabaseServlet.createTable(ds, callerGroups, "group_name VARCHAR(36), caller_name VARCHAR(36)");

            conn = ds.getConnection();
            try {
                Statement stmt1 = conn.createStatement();
                stmt1.executeUpdate("insert into " + callerTable + " (password, name) values ('" + Constants.DB_USER1_PWD_HASH + "' , '" + Constants.DB_USER1 + "')");
                stmt1.close();

                stmt1 = conn.createStatement();
                stmt1.executeUpdate("insert into " + callerTable + " (password, name) values ('" + Constants.DB_USER2_PWD_HASH + "' , '" + Constants.DB_USER2 + "')");
                stmt1.close();

                stmt1 = conn.createStatement();
                stmt1.executeUpdate("insert into " + callerTable + " (password, name) values ('" + Constants.DB_USER3_PWD_HASH + "' , '" + Constants.DB_USER3 + "')");
                stmt1.close();

            } finally {
                conn.close();
            }
        } catch (Exception e) {
            System.out.println("Failed to create database for DatabaseIdentityStore: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("Created database for DatabaseIdentityStore");
    }

}
