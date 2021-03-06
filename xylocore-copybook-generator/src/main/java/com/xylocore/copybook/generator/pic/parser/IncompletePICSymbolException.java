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


package com.xylocore.copybook.generator.pic.parser;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class IncompletePICSymbolException
    extends
        PICSymbolException
{
    //
    // Members
    //
    
    
    private static final long serialVersionUID = 2737660607987198159L;
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     * 
     * @param       aIncompleteSymbol
     * @param       aOffset
     */
    public IncompletePICSymbolException( String   aIncompleteSymbol,
                                         int      aOffset            )
    {
        super( "IncompletePICSymbol", aIncompleteSymbol, aOffset );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected String getMessagePrefix()
    {
        return "Incomplete PIC symbol";
    }
}
