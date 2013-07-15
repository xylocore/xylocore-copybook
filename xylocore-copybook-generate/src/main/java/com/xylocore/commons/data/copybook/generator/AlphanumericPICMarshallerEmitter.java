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


package com.xylocore.commons.data.copybook.generator;

import com.xylocore.commons.data.copybook.domain.AccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.HighValue;
import com.xylocore.commons.data.copybook.domain.LowValue;
import com.xylocore.commons.data.copybook.domain.QuoteValue;
import com.xylocore.commons.data.copybook.domain.SpaceValue;
import com.xylocore.commons.data.copybook.domain.StringValue;
import com.xylocore.commons.data.copybook.domain.Value;
import com.xylocore.commons.data.copybook.domain.ZeroValue;
import com.xylocore.commons.data.copybook.runtime.ConstantCharacterString;
import com.xylocore.commons.data.copybook.runtime.ConstantValue;
import com.xylocore.commons.data.copybook.runtime.DataType;
import com.xylocore.commons.data.copybook.runtime.StringConstantValue;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AlphanumericPICMarshallerEmitter
    extends
        AbstractPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final AlphanumericPICMarshallerEmitter instance = new AlphanumericPICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Private constructor as per the singleton pattern.
     */
    private AlphanumericPICMarshallerEmitter()
    {
        super( "Alphanumeric" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static AlphanumericPICMarshallerEmitter getInstance()
    {
        return instance;
    }
    

    /**
     * FILLIN
     *
     * @param       aBuffer
     * @param       aElement
     */
    protected void emitCommonVariableMethodParts( StringBuilder   aBuffer,
                                                  Element         aElement )
    {
        int myElementWidth = aElement.getNonIndexedSize();
        
        aBuffer.append( ", "           )
               .append( myElementWidth )
               .append( ", "           )
               ;

        emitFlags( aBuffer, aElement );
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
     */
    protected void emitVariableMethodParts( StringBuilder        aBuffer,
                                            Element              aElement,
                                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        DataType myDataType = aAccessorMethodInfo.getDataType();
        
        emitCommonVariableMethodParts( aBuffer, aElement );
        
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
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableNullStrategiesParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element, com.xylocore.commons.data.copybook.domain.AccessorMethodInfo)
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
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableIsBlankMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element)
     */
    protected void emitVariableIsBlankMethodParts( StringBuilder   aBuffer,
                                                   Element         aElement )
    {
        emitCommonVariableMethodParts( aBuffer, aElement );
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
        emitCommonVariableMethodParts( aBuffer, aConditionalVariableElement );
    }
   
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     */
    private void emitFlags( StringBuilder   aBuffer,
                            Element         aElement )
    {
        assert aBuffer  != null;
        assert aElement != null;
        
        aBuffer.append( "AlphanumericPICFlags."                   )
               .append( aElement.isJustified() ? "RIGHT" : "LEFT" )
               .append( "_JUSTIFY | AlphanumericPICFlags."        )
               ;
 
        if ( aElement.getTotalPossibleStringTypesPresent() == 1 )
        {
            if ( aElement.isDisplayPresent() )
            {
                aBuffer.append( "DISPLAY" );
            }
            else if ( aElement.isDbcsPresent() )
            {
                aBuffer.append( "DBCS" );
            }
            else // aElement.isNationalPresent()
            {
                aBuffer.append( "NATIONAL" );
            }
        }
        else
        {
            // TODO: separate the multiple string types child elements into separate emit calls
            aBuffer.append( "DISPLAY" );
        }
        
        aBuffer.append( " | AlphanumericPICFlags."                                                  )
               .append( aElement.isMaintainWhitespace() ? "MAINTAIN_WHITESPACE" : "TRIM_WHITESPACE" )
               .append( " | AlphanumericPICFlags."                                                  )
               .append( aElement.isNullAllowed() ? "NULLS_ALLOWED" : "NULLS_NOT_ALLOWED"            )
               ;
    }
    

    /**
     * FILLIN
     *
     * @param       aElement
     * @param       aValue
     * 
     * @return
     */
    protected Comparable<?> convertValue( Element   aElement,
                                          Value     aValue    )
    {
        Comparable<?> myComparable;
        
        if ( aValue instanceof StringValue )
        {
            myComparable = ((StringValue) aValue).getValue();
        }
        else if ( aValue instanceof ZeroValue )
        {
            myComparable = new ConstantCharacterString( '0', aElement.getNonIndexedSize() );
        }
        else if ( aValue instanceof HighValue )
        {
            myComparable =
                    new ConstantCharacterString( aElement.isNationalPresent() ? '\uffff' : '\u00ff',
                                                 aElement.getNonIndexedSize()                        );
        }
        else if ( aValue instanceof LowValue )
        {
            myComparable = new ConstantCharacterString( '\u0000', aElement.getNonIndexedSize() );
        }
        else if ( aValue instanceof SpaceValue )
        {
            myComparable = new ConstantCharacterString( ' ', aElement.getNonIndexedSize() );
        }
        else if ( aValue instanceof QuoteValue )
        {
            // TODO: use compiler option to determine QUOTE/APOST
            myComparable = new ConstantCharacterString( '"', aElement.getNonIndexedSize() );
        }
        else
        {
            throw new IllegalStateException();
        }

        return myComparable;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#createConstantValue(java.lang.Comparable, java.lang.Comparable)
     */
    protected ConstantValue createConstantValue( Comparable<?>   aValue1,
                                                 Comparable<?>   aValue2  )
    {
        return new StringConstantValue( aValue1, aValue2 );
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
