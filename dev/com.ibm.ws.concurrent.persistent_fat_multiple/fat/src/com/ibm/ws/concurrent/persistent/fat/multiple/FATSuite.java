/*******************************************************************************
 * Copyright (c) 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.concurrent.persistent.fat.multiple;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ibm.websphere.simplicity.Machine;

import componenttest.topology.impl.LibertyFileManager;
import componenttest.topology.impl.LibertyServer;
import componenttest.topology.impl.LibertyServerFactory;
import componenttest.topology.utils.ExternalTestServiceDockerClientStrategy;

@RunWith(Suite.class)
@SuiteClasses({ MultiplePersistentExecutorsTest.class })
public class FATSuite {
	
	protected static LibertyServer server = LibertyServerFactory.getLibertyServer("com.ibm.ws.concurrent.persistent.fat.multiple");

    @BeforeClass
    public static void beforeSuite() throws Exception {
        // Delete the Derby-only database that is used by the persistent scheduled executor
        Machine machine = server.getMachine();
        String installRoot = server.getInstallRoot();
        LibertyFileManager.deleteLibertyDirectoryAndContents(machine, installRoot + "/usr/shared/resources/data/persistmultidb");
        
        //Allows local tests to switch between using a local docker client, to using a remote docker client. 
        ExternalTestServiceDockerClientStrategy.clearTestcontainersConfig();
    }
}