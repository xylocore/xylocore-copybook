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


package com.xylocore.commons.data.copybook.domain;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ValueRange
{
    //
    // Members
    //
    
    
    private Value value1;
    private Value value2;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @param       aValue1
     */
    public ValueRange( Value aValue1 )
    {
        this( aValue1, aValue1 );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue1
     * @param       aValue2
     */
    public ValueRange( Value   aValue1,
                       Value   aValue2  )
    {
        value1 = aValue1;
        value2 = aValue2;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public boolean isSingleValue()
    {
        return ( value1 == value2 );
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public Value getValue1()
    {
        return value1;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public Value getValue2()
    {
        return value2;
    }
}
