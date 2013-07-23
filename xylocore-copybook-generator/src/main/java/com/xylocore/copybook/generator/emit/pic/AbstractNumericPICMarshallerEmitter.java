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

import java.math.BigDecimal;

import com.xylocore.copybook.generator.domain.AccessorMethodInfo;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.NumericValue;
import com.xylocore.copybook.generator.domain.Value;
import com.xylocore.copybook.generator.domain.ZeroValue;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractNumericPICMarshallerEmitter
    extends
        AbstractPICMarshallerEmitter
{
    //
    // Class implementation
    //
    
    
    /**
     * Constructor.
     */
    public AbstractNumericPICMarshallerEmitter( String aNativeType )
    {
        super( aNativeType );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aElement
     */
    protected void emitVariableCommonMethodParts( StringBuilder   aBuffer,
                                                  Element         aElement )
    {
        assert aBuffer  != null;
        assert aElement != null;
        
        aBuffer.append( ", "                              )
               .append( aElement.getDigits()              )
               .append( ", SignType."                     )
               .append( aElement.getSignType().toString() )
               .append( ", "                              )
               .append( aElement.getPrecision()           )
               .append( ", "                              )
               .append( aElement.getScalingPosition()     )
               ;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    protected void emitVariableMethodParts( StringBuilder        aBuffer,
                                            Element              aElement,
                                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aBuffer             != null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        emitVariableCommonMethodParts( aBuffer, aElement );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableIsValidMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    protected void emitVariableIsValidMethodParts( StringBuilder        aBuffer,
                                                   Element              aElement,
                                                   AccessorMethodInfo   aAccessorMethodInfo )
    {
        emitVariableMethodParts( aBuffer, aElement, aAccessorMethodInfo );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableConditionNameMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element)
     */
    protected void emitVariableConditionNameMethodParts( StringBuilder   aBuffer,
                                                         Element         aConditionalVariableElement )
    {
        emitVariableCommonMethodParts( aBuffer, aConditionalVariableElement );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#convertValue(com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.Value)
     */
    protected Comparable<?> convertValue( Element   aElement,
                                          Value     aValue    )
    {
        BigDecimal myConvertedValue;
        
        if ( aValue instanceof NumericValue )
        {
            myConvertedValue = ((NumericValue) aValue).getValue();
        }
        else if ( aValue instanceof ZeroValue )
        {
            myConvertedValue = BigDecimal.valueOf( 0 );
        }
        else
        {
            throw new IllegalStateException();
        }

        return myConvertedValue;
    }
}
