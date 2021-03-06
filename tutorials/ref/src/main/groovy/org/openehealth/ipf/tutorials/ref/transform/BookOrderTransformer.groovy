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
package org.openehealth.ipf.tutorials.ref.transform

import org.openehealth.ipf.commons.core.modules.api.Transmogrifier

/**
 * @author Martin Krasser
 */
class BookOrderTransformer implements Transmogrifier {
     
     def ns = 'http://www.openehealth.org/tutorial'
     
     // --------------------------------------------------------
     //  Implementation method
     // --------------------------------------------------------

     Object zap(Object order, Object[] params) {
         def builder = params[0] // via params().builder() in DSL 
         builder.order(xmlns:ns, category:order.category.text()) {
             customer(order.customer.text())
             item(order.item.text())
             count(order.count.text())
         }
         builder.result
     }

}