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

import com.xylocore.commons.util.BufferEmitter;
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

public class Computational1PICMarshallerEmitter
    extends
        AbstractPICMarshallerEmitter
{
    //
    // Members
    //
    
    
    private static final Computational1PICMarshallerEmitter instance = new Computational1PICMarshallerEmitter();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Private constructor as per the singleton pattern.
     */
    private Computational1PICMarshallerEmitter()
    {
        super( "Comp1" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static Computational1PICMarshallerEmitter getInstance()
    {
        return instance;
    }
    
    
    @Override
    protected String getDelegateIsValidMethodName( DataType aDataType )
    {
        return "isValid";
    }
    
    
    @Override
    protected void emitVariableConditionNameMethodParts( BufferEmitter   aEmitter,
                                                         Element         aConditionalVariableElement )
    {
    }
    
    
    @Override
    protected Comparable<?> convertValue( Element   aElement,
                                          Value     aValue    )
    {
        float myFloat;
        
        if ( aValue instanceof NumericValue )
        {
            myFloat = ((NumericValue) aValue).getValue().floatValue();
        }
        else if ( aValue instanceof ZeroValue )
        {
            myFloat = 0.0f;
        }
        else
        {
            throw new IllegalStateException();
        }
        
        return new Float( myFloat );
    }
}
