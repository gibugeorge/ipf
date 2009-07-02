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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query;

import static org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.QueryParameter.*;

import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.AdhocQueryRequest;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.FindDocumentsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.ebxml.IdentifiableTransformer;

/**
 * Transforms between a {@link FindDocumentsQuery} and {@link AdhocQueryRequest}.
 * @author Jens Riemschneider
 */
public class FindDocumentsQueryTransformer {
    private final IdentifiableTransformer identifiableTransformer = 
        new IdentifiableTransformer();
    
    public void toEbXML(FindDocumentsQuery query, AdhocQueryRequest ebXML) {
        if (query == null || ebXML == null) {
            return;
        }
        
        QuerySlotHelper slots = new QuerySlotHelper(ebXML);
        
        ebXML.setId(query.getType().getId());
        
        String value = identifiableTransformer.toEbXML(query.getPatientId());
        slots.fromString(DOC_ENTRY_PATIENT_ID, value);
        
        slots.fromStringList(DOC_ENTRY_AUTHOR_PERSON, query.getAuthorPersons());

        slots.fromNumber(DOC_ENTRY_CREATION_TIME_FROM, query.getCreationTime().getFrom());
        slots.fromNumber(DOC_ENTRY_CREATION_TIME_TO, query.getCreationTime().getTo());

        slots.fromNumber(DOC_ENTRY_SERVICE_START_TIME_FROM, query.getServiceStartTime().getFrom());
        slots.fromNumber(DOC_ENTRY_SERVICE_START_TIME_TO, query.getServiceStartTime().getTo());
        
        slots.fromNumber(DOC_ENTRY_SERVICE_STOP_TIME_FROM, query.getServiceStopTime().getFrom());
        slots.fromNumber(DOC_ENTRY_SERVICE_STOP_TIME_TO, query.getServiceStopTime().getTo());

        slots.fromStatus(DOC_ENTRY_STATUS, query.getStatus());
        
        slots.fromCode(DOC_ENTRY_FORMAT_CODE, query.getFormatCodes());
        slots.fromCode(DOC_ENTRY_CLASS_CODE, query.getClassCodes());
        slots.fromCode(DOC_ENTRY_HEALTH_CARE_FACILITY_TYPE_CODE, query.getHealthCareFacilityTypeCodes());        
        slots.fromCode(DOC_ENTRY_PRACTICE_SETTING_CODE, query.getPracticeSettingCodes());
        slots.fromCode(DOC_ENTRY_EVENT_CODE, query.getEventCodes());
        slots.fromCode(DOC_ENTRY_CONFIDENTIALITY_CODE, query.getConfidentialityCodes());
    }
    
    public void fromEbXML(FindDocumentsQuery query, AdhocQueryRequest ebXML) {
        if (query == null || ebXML == null) {
            return;
        }
        
        QuerySlotHelper slots = new QuerySlotHelper(ebXML);
        String patientId = slots.toString(DOC_ENTRY_PATIENT_ID);
        query.setPatientId(identifiableTransformer.fromEbXML(patientId));
        
        slots.toCode(DOC_ENTRY_CLASS_CODE, query.getClassCodes());
        slots.toCode(DOC_ENTRY_PRACTICE_SETTING_CODE, query.getPracticeSettingCodes());
        slots.toCode(DOC_ENTRY_HEALTH_CARE_FACILITY_TYPE_CODE, query.getHealthCareFacilityTypeCodes());
        slots.toCode(DOC_ENTRY_EVENT_CODE, query.getEventCodes());
        slots.toCode(DOC_ENTRY_CONFIDENTIALITY_CODE, query.getConfidentialityCodes());
        slots.toCode(DOC_ENTRY_FORMAT_CODE, query.getFormatCodes());
        
        slots.toStringList(DOC_ENTRY_AUTHOR_PERSON, query.getAuthorPersons());
        
        query.getCreationTime().setFrom(slots.toNumber(DOC_ENTRY_CREATION_TIME_FROM));
        query.getCreationTime().setTo(slots.toNumber(DOC_ENTRY_CREATION_TIME_TO));
        
        query.getServiceStartTime().setFrom(slots.toNumber(DOC_ENTRY_SERVICE_START_TIME_FROM));
        query.getServiceStartTime().setTo(slots.toNumber(DOC_ENTRY_SERVICE_START_TIME_TO));

        query.getServiceStopTime().setFrom(slots.toNumber(DOC_ENTRY_SERVICE_STOP_TIME_FROM));
        query.getServiceStopTime().setTo(slots.toNumber(DOC_ENTRY_SERVICE_STOP_TIME_TO));
        
        slots.toStatus(DOC_ENTRY_STATUS, query.getStatus());
    }
}
