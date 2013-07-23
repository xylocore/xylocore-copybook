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

import java.io.Serializable;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public final class PICSlice
    implements
        Serializable
{
    //
    // Members
    //
    
    
    private static final long   serialVersionUID    = 1455960964414797102L;

    private PICSymbolType       type;
    private int                 count;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aType
     */
    public PICSlice( PICSymbolType aType )
    {
        type  = aType;
        count = 1;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public PICSymbolType getType()
    {
        return type;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getCount()
    {
        return count;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCount
     */
    public void setCount( int aCount )
    {
        count = aCount;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPrecedingSlice
     * 
     * @return
     */
    public boolean isAllowedAfter( PICSlice aPrecedingSlice )
    {
        // TODO
        return true;
    }
}
