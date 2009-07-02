/*
 * Copyright 2009 the original author or authors.
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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.ebxml30;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.AdhocQueryRequest;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.Slot;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.ebxml30.EbXMLFactory30;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.metadata.Code;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetFolderAndContentsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.QueryType;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.QueryParameter;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetDocumentsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetFolderAndContentsQueryTransformer;

/**
 * Tests for {@link GetDocumentsQueryTransformer}.
 * @author Jens Riemschneider
 */
public class GetFolderAndContentsQueryTransformerTest {
    private GetFolderAndContentsQueryTransformer transformer;
    private GetFolderAndContentsQuery query;
    private AdhocQueryRequest ebXML;
    
    @Before
    public void setUp() {
        transformer = new GetFolderAndContentsQueryTransformer();
        query = new GetFolderAndContentsQuery();

        query.getUUIDs().add("uuid1");
        query.getUUIDs().add("uuid2");
        query.getUniqueIDs().add("uniqueId1");
        query.getUniqueIDs().add("uniqueId2");
        query.setHomeCommunityID("home");
        query.getConfidentialityCodes().getOuterList().add(
                Arrays.asList(new Code("code10", null, "scheme10"), new Code("code11", null, "scheme11")));
        query.getConfidentialityCodes().getOuterList().add(
                Arrays.asList(new Code("code12", null, "scheme12")));
        query.getFormatCodes().add(new Code("code13", null, null));
        query.getFormatCodes().add(new Code("code14", null, null));

        ebXML = new EbXMLFactory30().createAdhocQueryRequest();
    }
    
    @Test
    public void testToEbXML() {
        transformer.toEbXML(query, ebXML);
        assertEquals(QueryType.GET_FOLDER_AND_CONTENTS.getId(), ebXML.getId());
        
        assertEquals(Arrays.asList("('uuid1')", "('uuid2')"),
                ebXML.getSlotValues(QueryParameter.FOLDER_UUID.getSlotName()));
        
        assertEquals(Arrays.asList("('uniqueId1')", "('uniqueId2')"),
                ebXML.getSlotValues(QueryParameter.FOLDER_UNIQUE_ID.getSlotName()));

        assertEquals(Arrays.asList("'home'"),
                ebXML.getSlotValues(QueryParameter.HOME.getSlotName()));
        
        assertEquals(Arrays.asList("('code13')", "('code14')"),
                ebXML.getSlotValues(QueryParameter.DOC_ENTRY_FORMAT_CODE.getSlotName()));
        
        List<Slot> slots = ebXML.getSlots(QueryParameter.DOC_ENTRY_CONFIDENTIALITY_CODE.getSlotName());
        assertEquals(2, slots.size());
        assertEquals(Arrays.asList("('code10^^scheme10')", "('code11^^scheme11')"), slots.get(0).getValueList());
        assertEquals(Arrays.asList("('code12^^scheme12')"), slots.get(1).getValueList());
        
        assertEquals(6, ebXML.getSlots().size());
    }
    
    @Test
    public void testToEbXMLNull() {
        transformer.toEbXML(null, ebXML);
        assertEquals(0, ebXML.getSlots().size());
    }
    
    @Test
    public void testToEbXMLEmpty() {
        transformer.toEbXML(new GetFolderAndContentsQuery(), ebXML);
        assertEquals(0, ebXML.getSlots().size());
    }

    
    
    @Test
    public void testFromEbXML() {
        transformer.toEbXML(query, ebXML);
        GetFolderAndContentsQuery result = new GetFolderAndContentsQuery();
        transformer.fromEbXML(result, ebXML);
        
        assertEquals(query, result);
    }
    
    @Test
    public void testFromEbXMLNull() {
        GetFolderAndContentsQuery result = new GetFolderAndContentsQuery();
        transformer.fromEbXML(result, null);        
        assertEquals(new GetFolderAndContentsQuery(), result);
    }
        
    @Test
    public void testFromEbXMLEmpty() {
        GetFolderAndContentsQuery result = new GetFolderAndContentsQuery();
        transformer.fromEbXML(result, ebXML);        
        assertEquals(new GetFolderAndContentsQuery(), result);
    }
}
