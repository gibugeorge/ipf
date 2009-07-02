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

import static org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.QueryParameter.DOC_ENTRY_UNIQUE_ID;
import static org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.QueryParameter.DOC_ENTRY_UUID;

import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.AdhocQueryRequest;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetFoldersForDocumentQuery;

/**
 * Transforms between a {@link GetFoldersForDocumentQuery} and {@link AdhocQueryRequest}.
 * @author Jens Riemschneider
 */
public class GetFoldersForDocumentQueryTransformer extends GetByIDQueryTransformer<GetFoldersForDocumentQuery> {
    public GetFoldersForDocumentQueryTransformer() {
        super(DOC_ENTRY_UUID, DOC_ENTRY_UNIQUE_ID);
    }
}
