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


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public abstract class Value
    implements
        Comparable<Value>
{
    //
    // Class implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals( Object aOpaqueRhs )
    {
        if ( aOpaqueRhs == null || ! (aOpaqueRhs instanceof Value) )
        {
            return false;
        }
        
        if ( this == aOpaqueRhs )
        {
            return true;
        }
        
        Value myRhs = (Value) aOpaqueRhs;
        
        return ( compareTo( myRhs ) == 0 );
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo( Value aRhs )
    {
        return getClass().getName().compareTo( aRhs.getClass().getName() );
    }
}
