/*
 * PICCharacterException.java
 *
 * Copyright 2005 The Palantir Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xylocore.commons.data.copybook.parser.pic;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public abstract class PICCharacterException
    extends
        PICException
{
    //
    // Members
    //
    
    
    private static final long   serialVersionUID    = 4833818303593788921L;
    
    private char                charOfInterest;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aTag
     * @param       aSymbolOfInterest
     * @param       aOffset
     */
    public PICCharacterException( String   aTag,
                                  char     aCharOfInterest,
                                  int      aOffset          )
    {
        super( aTag, aOffset );
        
        charOfInterest = aCharOfInterest;
    }

    
    /**
     * Returns a clean error message (no line number/column information).
     * 
     * @return
     */
    public String getMessage()
    {
        StringBuilder myBuffer = new StringBuilder();
        myBuffer.append( getMessagePrefix() )
                .append( ": "               )
                ;

        if ( charOfInterest >= ' ' && charOfInterest <= '~' )
        {
            myBuffer.append( '\''           )
                    .append( charOfInterest )
                    .append( '\''           )
                    ;
        }
        else
        {
            myBuffer.append( "0x"                                                )
                    .append( Integer.toHexString( charOfInterest ).toUpperCase() )
                    ;
        }
        
        return myBuffer.toString();
    }


    /**
     * FILLIN
     * 
     * @return
     */
    protected abstract String getMessagePrefix();
}
