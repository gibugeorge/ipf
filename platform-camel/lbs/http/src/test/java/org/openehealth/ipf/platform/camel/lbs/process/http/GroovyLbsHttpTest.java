/*
 * Copyright 2008 the original author or authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.openehealth.ipf.platform.camel.lbs.process.http;

import static org.junit.Assert.assertEquals;

import org.apache.camel.Exchange;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openehealth.ipf.platform.camel.test.junit.DirtySpringContextJUnit4ClassRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * This test uses {@link DirtySpringContextJUnit4ClassRunner} which is an 
 * alternative to {@link SpringJUnit4ClassRunner} that recreates the Spring
 * application context for the next test class. 
 * <b><p>
 * Do not simply copy this code. It could result it bad performance of tests.
 * Use the standard {@link SpringJUnit4ClassRunner} if you don't need this, 
 * which is usually the case.
 * </b><p>
 * This class requires that the Spring application context is recreated because
 * it creates HTTP endpoints. These endpoints will not be thrown away and use 
 * the tcp/ip ports and endpoint names. Subsequent quests could fail because
 * the ports are in use and exchanges might not be received by the correct 
 * endpoint.
 * <p>
 * This class runs all tests in its base class with a specific configuration
 * 
 * @author Jens Riemschneider
 */
@RunWith(DirtySpringContextJUnit4ClassRunner.class) // DO NOT SIMPLY COPY!!! see above
@ContextConfiguration(locations = { "/context-lbs-route-http-groovy.xml" })
public class GroovyLbsHttpTest extends AbstractLbsHttpTest {
    /**
     * Test to verify that example code works 
     */
    @Test
    public void testExample1() throws Exception {        
        PostMethod method = new PostMethod("http://localhost:9452/lbstest_example1");
        method.setRequestEntity(new FileRequestEntity(file, "unknown/unknown"));

        mock.expectedMessageCount(1);
        mock.expectedHeaderReceived("tokenfound", "yes");

        httpClient.executeMethod(method);
        method.releaseConnection();

        mock.assertIsSatisfied();
    }

    /**
     * Test to verify that example code works 
     */
    @Test
    public void testExample2() throws Exception {        
        PostMethod method = new PostMethod("http://localhost:9452/lbstest_example2");
        method.setRequestEntity(new StringRequestEntity("testtext", "text/plain", null));

        mock.expectedMessageCount(1);
        mock.expectedHeaderReceived("textfound", "yes");

        httpClient.executeMethod(method);
        method.releaseConnection();

        mock.assertIsSatisfied();
    }

    /**
     * Test to verify that example code works 
     */
    @Test
    public void testExample3() throws Exception {        
        PostMethod method = new PostMethod("http://localhost:9452/lbstest_example3");
        method.setRequestEntity(new StringRequestEntity("testtext", "text/plain", null));

        mock.expectedMessageCount(1);

        httpClient.executeMethod(method);
        method.releaseConnection();

        mock.assertIsSatisfied();
        Exchange exchange = mock.getReceivedExchanges().get(0);
        assertEquals(2, exchange.getIn().getAttachments().size());
    }
}
