/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package com.ibm.ws.jpa.tests.spec10.entitymanager;

import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ibm.ws.jpa.tests.spec10.entitymanager.tests.AbstractFATSuite;
import com.ibm.ws.jpa.tests.spec10.entitymanager.tests.JPA10EntityManager_EJB;
import com.ibm.ws.jpa.tests.spec10.entitymanager.tests.JPA10EntityManager_Web;
import com.ibm.ws.jpa.tests.spec10.entitymanager.tests.TestOLGH19182_EJB;
import com.ibm.ws.jpa.tests.spec10.entitymanager.tests.TestOLGH19182_Web;

import componenttest.rules.repeater.FeatureReplacementAction;
import componenttest.rules.repeater.RepeatTests;

@RunWith(Suite.class)
@SuiteClasses({
                JPA10EntityManager_EJB.class,
                JPA10EntityManager_Web.class,
                TestOLGH19182_EJB.class,
                TestOLGH19182_Web.class,
                componenttest.custom.junit.runner.AlwaysPassesTest.class
})
public class FATSuite extends AbstractFATSuite {

    @ClassRule
    public static RepeatTests r = RepeatTests.with(FeatureReplacementAction.EE10_FEATURES());

}