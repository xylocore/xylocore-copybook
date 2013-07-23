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
import com.xylocore.copybook.generator.domain.HighValue;
import com.xylocore.copybook.generator.domain.LowValue;
import com.xylocore.copybook.generator.domain.QuoteValue;
import com.xylocore.copybook.generator.domain.SpaceValue;
import com.xylocore.copybook.generator.domain.StringValue;
import com.xylocore.copybook.generator.domain.Value;
import com.xylocore.copybook.generator.domain.ZeroValue;
import com.xylocore.copybook.generator.emit.BufferEmitter;
import com.xylocore.copybook.runtime.ConstantCharacterString;
import com.xylocore.copybook.runtime.ConstantValue;
import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.StringConstantValue;


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
     * @param       aEmitter
     * @param       aElement
     */
    protected void emitCommonVariableMethodParts( BufferEmitter   aEmitter,
                                                  Element         aElement  )
    {
        int myElementWidth = aElement.getNonIndexedSize();
        
        aEmitter.append( ", "           )
                .append( myElementWidth )
                .append( ", "           )
                ;

        emitFlags( aEmitter, aElement );
    }


    @Override
    protected void emitVariableMethodParts( BufferEmitter        aEmitter,
                                            Element              aElement,
                                            AccessorMethodInfo   aAccessorMethodInfo )
    {
        DataType myDataType = aAccessorMethodInfo.getDataType();
        
        emitCommonVariableMethodParts( aEmitter, aElement );
        
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
    
    
    protected void emitVariableConverterParts( BufferEmitter        aEmitter,
                                               Element              aElement,
                                               AccessorMethodInfo   aAccessorMethodInfo )
    {
        String myConverterVariableName = aAccessorMethodInfo.getConverterInstanceVariableName();
        
        aEmitter.append( ", "                                                               )
                .append( myConverterVariableName != null ? myConverterVariableName : "null" )
                ;
    }
    
    
    protected void emitVariableNullEquivalentStrategiesParts( BufferEmitter   aEmitter,
                                                              DataElement     aElement  )
    {
        String myNullEquivalentStrategySetVariableName = aElement.getNullEquivalentStrategySetInstanceVariableName();
        
        aEmitter.append( ", "                                                                                               )
                .append( myNullEquivalentStrategySetVariableName != null ? myNullEquivalentStrategySetVariableName : "null" )
                ;
    }
    

    protected void emitVariableIsBlankMethodParts( BufferEmitter   aEmitter,
                                                   Element         aElement  )
    {
        emitCommonVariableMethodParts( aEmitter, aElement );
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
        emitCommonVariableMethodParts( aEmitter, aConditionalVariableElement );
    }
   
    
    /**
     * FILLIN
     * 
     * @param       aBuffer
     * @param       aElement
     */
    private void emitFlags( BufferEmitter   aEmitter,
                            Element         aElement  )
    {
        assert aEmitter != null;
        assert aElement != null;
        
        aEmitter.append( "AlphanumericPICFlags."                   )
                .append( aElement.isJustified() ? "RIGHT" : "LEFT" )
                .append( "_JUSTIFY | AlphanumericPICFlags."        )
                ;
 
        if ( aElement.getTotalPossibleStringTypesPresent() == 1 )
        {
            if ( aElement.isDisplayPresent() )
            {
                aEmitter.append( "DISPLAY" );
            }
            else if ( aElement.isDbcsPresent() )
            {
                aEmitter.append( "DBCS" );
            }
            else // aElement.isNationalPresent()
            {
                aEmitter.append( "NATIONAL" );
            }
        }
        else
        {
            // TODO: separate the multiple string types child elements into separate emit calls
            aEmitter.append( "DISPLAY" );
        }
        
        aEmitter.append( " | AlphanumericPICFlags."                                                  )
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
