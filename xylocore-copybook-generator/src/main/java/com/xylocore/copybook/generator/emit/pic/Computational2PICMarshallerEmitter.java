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

import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.NumericValue;
import com.xylocore.copybook.generator.domain.Value;
import com.xylocore.copybook.generator.domain.ZeroValue;
import com.xylocore.copybook.runtime.DataType;



/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Computational2PICMarshallerEmitter
    extends
        AbstractPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final Computational2PICMarshallerEmitter instance = new Computational2PICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational2PICMarshallerEmitter()
    {
        super( "Comp2" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational2PICMarshallerEmitter getInstance()
    {
        return instance;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#getDelegateIsValidMethodName(com.xylocore.commons.data.copybook.runtime.DataType)
     */
    protected String getDelegateIsValidMethodName( DataType aDataType )
    {
        return "isValid";
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.generator.AbstractPICMarshallerEmitter#emitVariableConditionNameMethodParts(com.xylocore.commons.util.XyStringBuffer, com.xylocore.commons.data.copybook.domain.Element)
     */
    protected void emitVariableConditionNameMethodParts( StringBuilder   aBuffer,
                                                         Element         aConditionalVariableElement )
    {
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
        double myDouble;
        
        if ( aValue instanceof NumericValue )
        {
            myDouble = ((NumericValue) aValue).getValue().doubleValue();
        }
        else if ( aValue instanceof ZeroValue )
        {
            myDouble = 0.0d;
        }
        else
        {
            throw new IllegalStateException();
        }
        
        return new Double( myDouble );
    }
}
