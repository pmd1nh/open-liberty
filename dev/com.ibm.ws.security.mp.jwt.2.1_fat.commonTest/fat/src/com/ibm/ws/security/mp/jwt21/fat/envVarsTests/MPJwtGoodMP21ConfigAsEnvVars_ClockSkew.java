/*******************************************************************************
 * Copyright (c) 2022, 2023 IBM Corporation and others.
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
package com.ibm.ws.security.mp.jwt21.fat.envVarsTests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.ibm.ws.security.fat.common.mp.jwt.MPJwt21FatConstants;
import com.ibm.ws.security.fat.common.mp.jwt.utils.MP21ConfigSettings;
import com.ibm.ws.security.mp.jwt21.fat.sharedTests.GenericEnvVarsAndSystemPropertiesTests;

import componenttest.annotation.Server;
import componenttest.annotation.SkipForRepeat;
import componenttest.custom.junit.runner.FATRunner;
import componenttest.custom.junit.runner.Mode;
import componenttest.custom.junit.runner.Mode.TestMode;
import componenttest.topology.impl.LibertyServer;

/**
 * This is the test class that will verify that we get the correct behavior when we
 * have mp-config defined as environment variables
 * We'll test with a server.xml that will NOT have a mpJwt config, the app will NOT have mp-config specified
 * Therefore, we'll be able to show that the config is coming from the system properties
 * We also test with a conflicting config in server.xml - we'll show that this value overrides the environment variables
 *
 **/

@Mode(TestMode.FULL)
@RunWith(FATRunner.class)
@SkipForRepeat({ MPJwt21FatConstants.MP_JWT_11, MPJwt21FatConstants.MP_JWT_12, MPJwt21FatConstants.MP_JWT_20 })
public class MPJwtGoodMP21ConfigAsEnvVars_ClockSkew extends GenericEnvVarsAndSystemPropertiesTests {

    public static Class<?> thisClass = MPJwtGoodMP21ConfigAsEnvVars_ClockSkew.class;

    @Server("com.ibm.ws.security.mp.jwt.2.1.fat")
    public static LibertyServer envVarsResourceServer;

    @BeforeClass
    public static void setUp() throws Exception {

        commonMpJwt21Setup(envVarsResourceServer, "rs_server_AltConfigNotInApp_allowClockSkewOverrideServerXmlConfig.xml", 1, 1, MP21ConfigSettings.DefaultKeyMgmtKeyAlg, MPConfigLocation.ENV_VAR);

    }

    @Test
    public void MPJwtGoodMP21ConfigAsEnvVars_ClockSkew_Test() throws Exception {
        genericUsePropTest(5, setShortTokenAgeExpectations(envVarsResourceServer));
    }

    @Test
    public void MPJwtGoodMP21ConfigAsEnvVars_ClockSkewe_overriddenByServerXml_test() throws Exception {
        genericServerXmlOverridePropTest("rs_server_AltConfigNotInApp_disallowClockSkewOverrideServerXmlConfig.xml", 5);
    }
}
