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
 * @author      Eric R. Medley
 */

public class MutuallyExclusivePICSymbolsUsedException
    extends
        PICSymbolException
{
    //
    // Members
    //
    
    
    private static final long   serialVersionUID    = 6506705854509471413L;
    
    private String              symbolInUse;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aSymbol
     * @param       aSymbolInUse
     * @param       aOffset
     */
    public MutuallyExclusivePICSymbolsUsedException( String   aSymbol,
                                                     String   aSymbolInUse,
                                                     int      aOffset       )
    {
        super( "MutuallyExclusivePICSymbolsUsed", aSymbol, aOffset );
        
        symbolInUse = aSymbolInUse;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getSymbolInUse()
    {
        return symbolInUse;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected String getMessagePrefix()
    {
        return "Mutually exclusive PIC symbols used";
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected String getSymbolDescription()
    {
        return getSymbol() + " and " + symbolInUse;
    }
}
