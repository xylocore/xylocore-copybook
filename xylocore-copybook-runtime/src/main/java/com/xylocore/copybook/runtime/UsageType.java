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


package com.xylocore.copybook.runtime;

import java.util.HashSet;
import java.util.Set;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public enum UsageType
{
    //
    // Enumerated constants
    //
    
    
    Binary,
    Computational1,
    Computational2,
    Computational3,
    Computational5,
    Display,
    Display1,
    Index,
    National,
    ObjectReference,
    Pointer,
    ProcedurePointer,
    FunctionPointer;
    
    
    
    
    //
    // Members
    //
    
    
    private static final Set<UsageType> computationalUsageTypes = new HashSet<UsageType>();


    
    
    //
    // Static initialization
    //
    
    
    static
    {
        computationalUsageTypes.add( Binary         );
        computationalUsageTypes.add( Computational1 );
        computationalUsageTypes.add( Computational2 );
        computationalUsageTypes.add( Computational3 );
        computationalUsageTypes.add( Computational5 );
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Returns a flag indicating whether the UsageType is a computational
     * usage type.
     */
    public boolean isComputational()
    {
        return computationalUsageTypes.contains( this );
    }
}
