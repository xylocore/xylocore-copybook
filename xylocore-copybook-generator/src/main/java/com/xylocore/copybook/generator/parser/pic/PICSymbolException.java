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


package com.xylocore.copybook.generator.parser.pic;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public abstract class PICSymbolException
    extends
        PICException
{
    //
    // Members
    //
    
    
    private static final long   serialVersionUID    = 6199248907658498090L;
    
    private String              symbol;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aTag
     * @param       aSymbol
     * @param       aOffset
     */
    public PICSymbolException( String   aTag,
                               String   aSymbol,
                               int      aOffset  )
    {
        super( aTag, aOffset );
        
        symbol = aSymbol;
    }

    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getSymbol()
    {
        return symbol;
    }
    
    
    /**
     * Returns a clean error message (no line number/column information).
     * 
     * @return
     */
    public String getMessage()
    {
        StringBuffer myBuffer = new StringBuffer();
        myBuffer.append( getMessagePrefix()     )
                .append( ": "                   )
                .append( getSymbolDescription() )
                ;
        
        return myBuffer.toString();
    }


    /**
     * FILLIN
     * 
     * @return
     */
    protected abstract String getMessagePrefix();
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected String getSymbolDescription()
    {
        return symbol;
    }
}
