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
package org.openehealth.ipf.platform.camel.ihe.xdsb.commons.cxf.audit;

import java.io.OutputStream;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.SoapVersion;
import org.apache.cxf.binding.soap.interceptor.SoapOutInterceptor.SoapOutEndingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.Phase;


/**
 * CXF client-side interceptor that reads the payload collected by the output  
 * stream proxy installed in {@link ClientOutputStreamSubstituteInterceptor}.
 * <p>
 * Usable on client side only. 
 * 
 * @author Dmytro Rud
 */
public class ClientPayloadExtractorInterceptor extends AuditInterceptor {

    /**
     * Constructor.
     * 
     * @param auditStrategy
     *      an audit strategy instance
     */
    public ClientPayloadExtractorInterceptor(AuditStrategy auditStrategy) {
        super(Phase.WRITE_ENDING, auditStrategy);
        addAfter(SoapOutEndingInterceptor.class.getName());
    }
    
    
    public void handleMessage(Message message) {
        // check whether we should process
        if(isAuditDisabled() || ( ! getAuditStrategy().needSavePayload())) {
            return;
        }
        AuditDataset auditDataset = getAuditDataset(message);

        // determine what is the actual SOAP Envelope prefix
        // (we need it to extract SOAP document from the collected payload, 
        // as the latter can contain parts of MIME markup as well)
        String soapEnvelopePrefix;
        try {
            SoapVersion soapVersion = ((SoapMessage)message).getVersion();
            XMLStreamWriter xmlWriter = message.getContent(XMLStreamWriter.class);
            soapEnvelopePrefix = xmlWriter.getPrefix(soapVersion.getNamespace());
        } catch(XMLStreamException xmlse) {
            // actually cannot occur
            throw new Fault(xmlse);
        }

        // get the payload and store it in the audit dataset
        WrappedOutputStream wrapper = (WrappedOutputStream)message.getContent(OutputStream.class);
        String payload = wrapper.getCollectedPayloadAndDeactivate(soapEnvelopePrefix); 
        auditDataset.setPayload(payload);
    }
}