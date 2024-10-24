/*******************************************************************************
 * Copyright (c) 2016 IBM Corporation and others.
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

package com.ibm.ws.cloudant;

import java.util.Collections;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import com.ibm.ws.cloudant.internal.CloudantService;
import com.ibm.ws.runtime.metadata.ComponentMetaData;
import com.ibm.ws.threadContext.ComponentMetaDataAccessorImpl;
import com.ibm.wsspi.application.lifecycle.ApplicationRecycleComponent;
import com.ibm.wsspi.application.lifecycle.ApplicationRecycleContext;
import com.ibm.wsspi.resource.ResourceFactory;
import com.ibm.wsspi.resource.ResourceInfo;

@Component(name = "com.ibm.ws.cloudant.cloudantDatabase", configurationPolicy = ConfigurationPolicy.REQUIRE, service = { ResourceFactory.class,
                                                                                                                         ApplicationRecycleComponent.class }, property = { "creates.objectClass=com.cloudant.client.api.Database" })
public class CloudantDatabaseService implements ResourceFactory, ApplicationRecycleComponent {
    /**
     * Names of applications using this ResourceFactory
     */
    private final Set<String> applications = Collections.newSetFromMap(new ConcurrentHashMap<String, Boolean>());

    /**
     * Resource factory that owns the Cloudant Builder configuration and manages CloudantClient instances.
     */
    private CloudantService cloudantSvc;

    /**
     * Service properties
     */
    private Dictionary<String, Object> props;

    @Activate
    protected void activate(ComponentContext context) {
        props = context.getProperties();
    }

    /**
     * Invoked when a cloudant Database is injected or looked up.
     *
     * @param info resource ref info, or null if direct lookup.
     * @return instance of com.cloudant.client.api.Database
     */
    @Override
    public Object createResource(ResourceInfo info) throws Exception {
        ComponentMetaData cData = ComponentMetaDataAccessorImpl.getComponentMetaDataAccessor().getComponentMetaData();
        if (cData != null)
            applications.add(cData.getJ2EEName().getApplication());

        return cloudantSvc.createResource(
                                          (String) props.get("databaseName"),
                                          (Boolean) props.get("create"),
                                          info == null ? ResourceInfo.AUTH_APPLICATION : info.getAuth(),
                                          info == null ? null : info.getLoginPropertyList());
    }

    @Deactivate
    protected void deactivate(ComponentContext context) {}

    @Override
    public ApplicationRecycleContext getContext() {
        return null;
    }

    @Override
    public Set<String> getDependentApplications() {
        Set<String> members = new HashSet<String>(applications);
        applications.removeAll(members);
        return members;
    }

    @Reference(target = "(id=unbound)")
    protected void setCloudantService(ResourceFactory svc) {
        cloudantSvc = (CloudantService) svc;
    }

    protected void unsetCloudantService(ResourceFactory svc) {
        cloudantSvc = null;
    }

    /**
     * Returns the underlying cloudantClient object for this database and the provided resource config
     *
     * @param info The ResourceConfig used with the associated cloudantDatabase lookup
     * @return the cloudantClient object, or null
     * @throws Exception
     */
    public Object getCloudantClient(ResourceInfo info) throws Exception {
        return cloudantSvc.getCloudantClient(
                                             info == null ? ResourceInfo.AUTH_APPLICATION : info.getAuth(),
                                             info == null ? null : info.getLoginPropertyList());
    }
}