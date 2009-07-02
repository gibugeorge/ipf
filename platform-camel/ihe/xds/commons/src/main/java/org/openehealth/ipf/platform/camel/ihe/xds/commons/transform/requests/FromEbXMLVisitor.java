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
package org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests;

import static org.apache.commons.lang.Validate.notNull;

import org.openehealth.ipf.platform.camel.ihe.xds.commons.ebxml.AdhocQueryRequest;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.FindDocumentsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.FindFoldersQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.FindSubmissionSetsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetAllQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetAssociationsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetDocumentsAndAssociationsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetDocumentsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetFolderAndContentsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetFoldersForDocumentQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetFoldersQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetRelatedDocumentsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetSubmissionSetAndContentsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.GetSubmissionSetsQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.SqlQuery;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.requests.query.Query.Visitor;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.FindDocumentsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.FindFoldersQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.FindSubmissionSetsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetAllQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetAssociationsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetDocumentsAndAssociationsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetDocumentsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetFolderAndContentsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetFoldersForDocumentQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetFoldersQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetRelatedDocumentsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetSubmissionSetAndContentsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.GetSubmissionSetsQueryTransformer;
import org.openehealth.ipf.platform.camel.ihe.xds.commons.transform.requests.query.SqlQueryTransformer;

/**
 * Query visitor to transform an ebXML representation into a query.
 * @author Jens Riemschneider
 */
final class FromEbXMLVisitor implements Visitor {
    private final AdhocQueryRequest ebXML;

    FromEbXMLVisitor(AdhocQueryRequest ebXML) {
        notNull(ebXML, "ebXML cannot be null");
        this.ebXML = ebXML;
    }

    @Override
    public void visit(SqlQuery query) {
        new SqlQueryTransformer().fromEbXML(query, ebXML);
    }

    @Override
    public void visit(FindDocumentsQuery query) {
        new FindDocumentsQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(FindFoldersQuery query) {
        new FindFoldersQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetSubmissionSetsQuery query) {
        new GetSubmissionSetsQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetSubmissionSetAndContentsQuery query) {
        new GetSubmissionSetAndContentsQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetRelatedDocumentsQuery query) {
        new GetRelatedDocumentsQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetFoldersQuery query) {
        new GetFoldersQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetFoldersForDocumentQuery query) {
        new GetFoldersForDocumentQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetFolderAndContentsQuery query) {
        new GetFolderAndContentsQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetDocumentsQuery query) {
        new GetDocumentsQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetDocumentsAndAssociationsQuery query) {
        new GetDocumentsAndAssociationsQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetAssociationsQuery query) {
        new GetAssociationsQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(GetAllQuery query) {
        new GetAllQueryTransformer().fromEbXML(query, ebXML);                
    }

    @Override
    public void visit(FindSubmissionSetsQuery query) {
        new FindSubmissionSetsQueryTransformer().fromEbXML(query, ebXML);                
    }
}