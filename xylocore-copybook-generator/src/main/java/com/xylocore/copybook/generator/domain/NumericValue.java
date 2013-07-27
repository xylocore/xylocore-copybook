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


package com.xylocore.copybook.generator.domain;

import java.math.BigDecimal;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class NumericValue
    extends
        Value
{
    //
    // Members
    //
    
    
    private BigDecimal value;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     */
    public NumericValue( BigDecimal aValue )
    {
        value = aValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public BigDecimal getValue()
    {
        return value;
    }
    
    
    @Override
    public String toString()
    {
        return value.toString();
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    
    
    @Override
    public int compareTo( Value aOpaqueRhs )
    {
        int myCmp = super.compareTo( aOpaqueRhs );
        if ( myCmp == 0 )
        {
            NumericValue myRhs = (NumericValue) aOpaqueRhs;
            myCmp = value.compareTo( myRhs.value );
        }
        
        return myCmp;
    }
}
