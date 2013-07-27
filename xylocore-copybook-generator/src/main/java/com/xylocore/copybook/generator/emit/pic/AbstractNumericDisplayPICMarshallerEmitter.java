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

import com.xylocore.commons.util.BufferEmitter;
import com.xylocore.copybook.generator.domain.AccessorMethodInfo;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.NumericValue;
import com.xylocore.copybook.generator.domain.Value;
import com.xylocore.copybook.generator.domain.ZeroValue;
import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractNumericDisplayPICMarshallerEmitter
    extends
        AbstractPICMarshallerEmitter
{
    //
    // Class implementation
    //
    
    
    /**
     * Constructor.
     */
    public AbstractNumericDisplayPICMarshallerEmitter( String aNativeType )
    {
        super( aNativeType );
    }
    
    
    @Override
    protected void emitVariableMethodParts( BufferEmitter        aEmitter,
                                            Element              aElement,
                                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        assert aEmitter            != null;
        assert aElement            != null;
        assert aAccessorMethodInfo != null;
        
        DataType myDataType = aAccessorMethodInfo.getDataType();
        
        emitVariableCommonWithPrecisionInfoMethodParts( aEmitter, aElement );
        
        if ( myDataType == DataType.Date )
        {
            emitVariableConverterParts( aEmitter, aElement, aAccessorMethodInfo );
        }
        
        if
        (
            myDataType == DataType.BigInteger ||
            myDataType == DataType.BigDecimal ||
            myDataType == DataType.String     ||
            myDataType == DataType.Date
        )
        {
            emitVariableNullEquivalentStrategiesParts( aEmitter, (DataElement) aElement );
        }
    }
    

    @Override
    protected void emitVariableIsBlankMethodParts( BufferEmitter   aEmitter,
                                                   Element         aElement  )
    {
        assert aEmitter != null;
        assert aElement != null;
        
        emitVariableCommonMethodParts( aEmitter, aElement );
    }
    

    @Override
    protected void emitVariableIsValidMethodParts( BufferEmitter        aEmitter,
                                                   Element              aElement,
                                                   AccessorMethodInfo   aAccessorMethodInfo )
    {
        emitVariableMethodParts( aEmitter, aElement, aAccessorMethodInfo );
    }
    

    @Override
    protected void emitVariableConditionNameMethodParts( BufferEmitter   aEmitter,
                                                         Element         aConditionalVariableElement )
    {
        emitVariableCommonWithPrecisionInfoMethodParts( aEmitter, aConditionalVariableElement );
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
                .append( ", "                              )
                .append( SignType.class.getName()          )
                .append( "."                               )
                .append( aElement.getSignType().toString() )
                .append( ", "                              )
                ;
 
        if ( aElement.getSignType() != SignType.None )
        {
            aEmitter.append( SignPosition.class.getName()          )
                    .append( "."                                   )
                    .append( aElement.getSignPosition().toString() )
                    ;
        }
        else
        {
            aEmitter.append( "null" );
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aEmitter
     * @param       aElement
     */
    protected void emitVariableCommonWithPrecisionInfoMethodParts( BufferEmitter   aEmitter,
                                                                   Element         aElement )
    {
        emitVariableCommonMethodParts( aEmitter, aElement );
        
        aEmitter.append( ", "                          )
                .append( aElement.getPrecision()       )
                .append( ", "                          )
                .append( aElement.getScalingPosition() )
                ;
    }
    

    @Override
    protected void emitVariableConverterParts( BufferEmitter        aEmitter,
                                               Element              aElement,
                                               AccessorMethodInfo   aAccessorMethodInfo )
    {
        String myConverterVariableName = aAccessorMethodInfo.getConverterInstanceVariableName();
        
        aEmitter.append( ", "                                                               )
                .append( myConverterVariableName != null ? myConverterVariableName : "null" )
                ;
    }
    

    @Override
    protected void emitVariableNullEquivalentStrategiesParts( BufferEmitter   aEmitter,
                                                              DataElement     aElement  )
    {
        String myNullEquivalentStrategySetVariableName = aElement.getNullEquivalentStrategySetInstanceVariableName();
        
        aEmitter.append( ", "                                                                                               )
                .append( myNullEquivalentStrategySetVariableName != null ? myNullEquivalentStrategySetVariableName : "null" )
                ;
    }
    

    @Override
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

    
    
    
    //
    // PICMarshallerEmitter interface implementation
    //
    

    @Override
    public boolean isBlankMethodNeeded( Element aElement )
    {
        return true;
    }
}
