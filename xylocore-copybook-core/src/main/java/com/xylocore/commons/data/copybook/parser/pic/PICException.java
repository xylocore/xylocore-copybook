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


package com.xylocore.commons.data.copybook.parser.pic;


/**
 * FILLIN
 *
 * @author      Eric Medley
 */

public class PICException
    extends
        Exception
{
    //
    // Members
    //
    
    
    private static final long   serialVersionUID    = 2820240800948325657L;
    
    private int                 offset;


    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     * @param       aOffset
     */
    public PICException( String   aMessage,
                         int      aOffset    )
    {
        super( aMessage );
        
        offset = aOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getOffset()
    {
        return offset;
    }
}
