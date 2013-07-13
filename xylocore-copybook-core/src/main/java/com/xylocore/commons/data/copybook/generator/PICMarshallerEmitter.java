package com.xylocore.commons.data.copybook.generator;

import com.xylocore.commons.data.copybook.domain.AccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.Level88Element;
import com.xylocore.commons.data.copybook.domain.ValueRange;
import com.xylocore.commons.data.copybook.runtime.marshallers.PICMarshaller;


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
     * @param       aBuffer
     * @param       aMarshaller
     * @param       aInstanceName
     */
    public void emitMarshaller( StringBuilder   aBuffer,
                                PICMarshaller   aMarshaller,
                                String          aInstanceName );
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aElement
     * @param       aValueRange
     */
    public void emitValueRange( StringBuilder   aBuffer,
                                Element         aElement,
                                ValueRange      aValueRange );
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aMarshaller
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    public void emitReader( StringBuilder        aBuffer,
                            PICMarshaller        aMarshaller,
                            Element              aElement,
                            AccessorMethodInfo   aAccessorMethodInfo );
    
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aMarshaller
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    public void emitWriter( StringBuilder        aBuffer,
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
     * @param       aBuffer
     * @param       aMarshaller
     * @param       aElement
     */
    public void emitBlankMethodCall( StringBuilder   aBuffer,
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
     * @param       aBuffer
     * @param       aMarshaller
     * @param       aElement
     */
    public void emitNullMethodCall( StringBuilder   aBuffer,
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
     * @param       aBuffer
     * @param       aMarshaller
     * @param       aElement
     * @param       aAccessorMethodInfo
     */
    public void emitValidMethodCall( StringBuilder        aBuffer,
                                     PICMarshaller        aMarshaller,
                                     Element              aElement,
                                     AccessorMethodInfo   aAccessorMethodInfo );
    
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aConditionalVariableElement
     * @param       aConditionNameElement
     * @param       aConditionNameValueMapName
     */
    public void emitConditionNameMethodCall( StringBuilder    aBuffer,
                                             Element          aConditionalVariableElement,
                                             Level88Element   aConditionNameElement,
                                             String           aConditionNameValueMapName   );
}
