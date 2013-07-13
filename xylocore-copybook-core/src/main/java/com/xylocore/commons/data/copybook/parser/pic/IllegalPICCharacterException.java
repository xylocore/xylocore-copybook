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

public class IllegalPICCharacterException
    extends
        PICCharacterException
{
    //
    // Members
    //
    
    
    private static final long serialVersionUID = 5123367329212481875L;
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     * 
     * @param       aIllegalChar
     * @param       aOffset
     */
    public IllegalPICCharacterException( char   aIllegalChar,
                                         int    aOffset       )
    {
        super( "IllegalPICChar", aIllegalChar, aOffset );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected String getMessagePrefix()
    {
        return "illegal PIC character";
    }
}
