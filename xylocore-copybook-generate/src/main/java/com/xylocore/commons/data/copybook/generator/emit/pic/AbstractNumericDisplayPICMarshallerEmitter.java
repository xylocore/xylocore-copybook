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


package com.xylocore.commons.data.copybook.generator.emit.pic;

import java.math.BigDecimal;

import com.xylocore.commons.data.copybook.domain.AccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.NumericValue;
import com.xylocore.commons.data.copybook.domain.Value;
import com.xylocore.commons.data.copybook.domain.ZeroValue;
import com.xylocore.commons.data.copybook.runtime.DataType;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;


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
        
        DataType myDataType = aAccessorMethodInfo.getDataType();
        
        emitVariableCommonWithPrecisionInfoMethodParts( aBuffer, aElement );
        
        if ( myDataType == DataType.Date )
        {
            emitVariableConverterParts( aBuffer, aElement, aAccessorMethodInfo );
        }
        
        if
        (
            myDataType == DataType.BigInteger ||
            myDataType == DataType.BigDecimal ||
            myDataType == DataType.String     ||
            myDataType == DataType.Date
        )
        {
            emitVariableNullEquivalentStrategiesParts( aBuffer, (DataElement) aElement );
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableIsBlankMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element)
     */
    protected void emitVariableIsBlankMethodParts( StringBuilder   aBuffer,
                                                   Element         aElement )
    {
        assert aBuffer             != null;
        assert aElement            != null;
        
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
        emitVariableCommonWithPrecisionInfoMethodParts( aBuffer, aConditionalVariableElement );
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
               .append( ", "                              )
               .append( SignType.class.getName()          )
               .append( "."                               )
               .append( aElement.getSignType().toString() )
               .append( ", "                              )
               ;
 
        if ( aElement.getSignType() != SignType.None )
        {
            aBuffer.append( SignPosition.class.getName()          )
                   .append( "."                                   )
                   .append( aElement.getSignPosition().toString() )
                   ;
        }
        else
        {
            aBuffer.append( "null" );
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aElement
     */
    protected void emitVariableCommonWithPrecisionInfoMethodParts( StringBuilder   aBuffer,
                                                                   Element         aElement )
    {
        emitVariableCommonMethodParts( aBuffer, aElement );
        
        aBuffer.append( ", "                          )
               .append( aElement.getPrecision()       )
               .append( ", "                          )
               .append( aElement.getScalingPosition() )
               ;
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableConverterParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    protected void emitVariableConverterParts( StringBuilder        aBuffer,
                                               Element              aElement,
                                               AccessorMethodInfo   aAccessorMethodInfo )
    {
        String myConverterVariableName = aAccessorMethodInfo.getConverterInstanceVariableName();
        
        aBuffer.append( ", "                                                               )
               .append( myConverterVariableName != null ? myConverterVariableName : "null" )
               ;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableNullEquivalentStrategiesParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    protected void emitVariableNullEquivalentStrategiesParts( StringBuilder   aBuffer,
                                                              DataElement     aElement )
    {
        String myNullEquivalentStrategySetVariableName = aElement.getNullEquivalentStrategySetInstanceVariableName();
        
        aBuffer.append( ", "                                                                                               )
               .append( myNullEquivalentStrategySetVariableName != null ? myNullEquivalentStrategySetVariableName : "null" )
               ;
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

    
    
    
    //
    // PICMarshallerEmitter interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#isBlankMethodNeeded(com.xylocore.commons.data.copybook.domain.Element)
     */
    public boolean isBlankMethodNeeded( Element aElement )
    {
        return true;
    }
}
