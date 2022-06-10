/*******************************************************************************
 * Copyright (c) 2013,2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.example.jca.adapter;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import jakarta.resource.ResourceException;
import jakarta.resource.spi.ConnectionManager;
import jakarta.resource.spi.ConnectionRequestInfo;
import jakarta.resource.spi.ManagedConnection;
import jakarta.resource.spi.ManagedConnectionFactory;
import jakarta.resource.spi.ResourceAdapter;
import jakarta.resource.spi.ResourceAdapterAssociation;

import javax.security.auth.Subject;

/**
 * Example managed connection factory.
 */
public class ManagedConnectionFactoryImpl implements ManagedConnectionFactory, ResourceAdapterAssociation {
    private static final long serialVersionUID = 4553301962289038364L;

    /**
     * This is the in-memory data store
     */
    final static ConcurrentHashMap<String, ConcurrentLinkedQueue<Map<?, ?>>> tables = new ConcurrentHashMap<String, ConcurrentLinkedQueue<Map<?, ?>>>();

    ResourceAdapterImpl adapter;
    private String tableName;

    @Override
    public Object createConnectionFactory() throws ResourceException {
        return createConnectionFactory(null);
    }

    @Override
    public Object createConnectionFactory(ConnectionManager cm) throws ResourceException {
        return new ConnectionFactoryImpl(cm, this);
    }

    @Override
    public ManagedConnection createManagedConnection(Subject subject, ConnectionRequestInfo cri) throws ResourceException {
        return new ManagedConnectionImpl();
    }

    @Override
    public PrintWriter getLogWriter() throws ResourceException {
        return null;
    }

    @Override
    public ResourceAdapter getResourceAdapter() {
        return adapter;
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public ManagedConnection matchManagedConnections(@SuppressWarnings("rawtypes") Set connections, Subject subject, ConnectionRequestInfo cri) throws ResourceException {
        for (Object mc : connections)
            if (mc instanceof ManagedConnectionImpl)
                return (ManagedConnection) mc;
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter logWriter) throws ResourceException {
    }

    @Override
    public void setResourceAdapter(ResourceAdapter adapter) throws ResourceException {
        this.adapter = (ResourceAdapterImpl) adapter;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        tables.putIfAbsent(tableName, new ConcurrentLinkedQueue<Map<?, ?>>());
    }
}