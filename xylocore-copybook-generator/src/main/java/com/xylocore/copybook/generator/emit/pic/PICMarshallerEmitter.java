//
//   Copyright 2013 The Palantir Corporation
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.
//


package com.xylocore.copybook.generator.emit.pic;

import com.xylocore.copybook.generator.domain.AccessorMethodInfo;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.Level88Element;
import com.xylocore.copybook.generator.domain.ValueRange;
import com.xylocore.copybook.generator.emit.BufferEmitter;
import com.xylocore.copybook.runtime.marshallers.PICMarshaller;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface PICMarshallerEmitter
{
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aMarshaller
     * @param       aInstanceName
     */
    public void emitMarshaller( BufferEmitter   aEmitter,
                                PICMarshaller   aMarshaller,
                                String          aInstanceName );
    
    
    /**
     * FILLIN
     *
     * @param       aEmitter
     * @param       aElement
     * @param       aValueRange
     */
    public void emitValueRange( BufferEmitter   aEmitter,
                                Element         aElement,
                                ValueRange      aValueRange );
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aMarshaller
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    public void emitReader( BufferEmitter        aEmitter,
                            PICMarshaller        aMarshaller,
                            Element              aElement,
                            AccessorMethodInfo   aAccessorMethodInfo );
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aMarshaller
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    public void emitWriter( BufferEmitter        aEmitter,
                            PICMarshaller        aMarshaller,
                            Element              aElement,
                            AccessorMethodInfo   aAccessorMethodInfo );
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    public boolean isBlankMethodNeeded( Element aElement );
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aMarshaller
     * @param       aElement
     */
    public void emitBlankMethodCall( BufferEmitter   aEmitter,
                                     PICMarshaller   aMarshaller,
                                     Element         aElement     );
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    public boolean isNullMethodNeeded( DataElement aElement );
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aMarshaller
     * @param       aElement
     */
    public void emitNullMethodCall( BufferEmitter   aEmitter,
                                    PICMarshaller   aMarshaller,
                                    DataElement     aElement     );

    
    /**
     * FILLIN
     * 
     * @param       aElement
     * @param       aAccessorMethodInfo
     * 
     * @return
     */
    public boolean isValidMethodNeeded( Element              aElement,
                                        AccessorMethodInfo   aAccessorMethodInfo );
    
    
    /**
     * FILLIN
     * 
     * @param       aEmitter
     * @param       aMarshaller
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    public void emitValidMethodCall( BufferEmitter        aEmitter,
                                     PICMarshaller        aMarshaller,
                                     Element              aElement,
                                     AccessorMethodInfo   aAccessorMethodInfo );
    
    
    
    /**
     * FILLIN
     *
     * @param       aEmitter
     * @param       aConditionalVariableElement
     * @param       aConditionNameElement
     * @param       aConditionNameValueMapName
     */
    public void emitConditionNameMethodCall( BufferEmitter    aEmitter,
                                             Element          aConditionalVariableElement,
                                             Level88Element   aConditionNameElement,
                                             String           aConditionNameValueMapName   );
}
