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
import com.xylocore.copybook.generator.emit.BufferEmitter;


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
     * @param       aEmitter
     * @param       aElement
     */
    protected void emitVariableCommonMethodParts( BufferEmitter   aEmitter,
                                                  Element         aElement  )
    {
        assert aEmitter != null;
        assert aElement != null;
        
        aEmitter.append( ", "                              )
                .append( aElement.getDigits()              )
                .append( ", SignType."                     )
                .append( aElement.getSignType().toString() )
                .append( ", "                              )
                .append( aElement.getPrecision()           )
                .append( ", "                              )
                .append( aElement.getScalingPosition()     )
                ;
    }
    

    @Override
    protected void emitVariableMethodParts( BufferEmitter        aEmitter,
                                            Element              aElement,
                                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aEmitter            != null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        emitVariableCommonMethodParts( aEmitter, aElement );
    }
    

    protected void emitVariableIsValidMethodParts( BufferEmitter        aEmitter,
                                                   Element              aElement,
                                                   AccessorMethodInfo   aAccessorMethodInfo )
    {
        emitVariableMethodParts( aEmitter, aElement, aAccessorMethodInfo );
    }
    

    protected void emitVariableConditionNameMethodParts( BufferEmitter   aEmitter,
                                                         Element         aConditionalVariableElement )
    {
        emitVariableCommonMethodParts( aEmitter, aConditionalVariableElement );
    }
    
    
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
