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


package com.xylocore.commons.data.copybook.parser;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

class CopybookInputStream
    extends
        InputStream
{
    // TODO: implement using FilterInputStream
    
    //
    // Members
    //
    
    
    public static final int     INDICATOR_COLUMN        = 7;
    public static final int     DEFAULT_RIGHT_MARGIN    = 72;

    private InputStream         sourceStream;
    private int                 rightMargin;
    private int                 column;
    private boolean             inComment;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aSourceStream
     */
    public CopybookInputStream( InputStream aSourceStream )
    {
        this( aSourceStream, DEFAULT_RIGHT_MARGIN );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aSourceStream
     * @param       aRightMargin
     */
    public CopybookInputStream( InputStream   aSourceStream,
                                int           aRightMargin   )
    {
        if ( aSourceStream == null )
        {
            throw new IllegalArgumentException( "aSourceStream" );
        }
        if ( aRightMargin < 72 )
        {
            throw new IllegalArgumentException( "right margin must be 72 or greater" );
        }
        
        sourceStream = aSourceStream;
        rightMargin  = aRightMargin;
        column       = 1;
        inComment    = false;
    }
    
    
    /* (non-Javadoc)
     * @see java.io.InputStream#read()
     */
    public int read()
            throws IOException
    {
        try
        {
            int myChar = sourceStream.read();
            if ( myChar == '\r' || myChar == '\n' )
            {
                column    = 1;
                inComment = false;
            }
            else
            {
                if ( column == INDICATOR_COLUMN )
                {
                    inComment = ( myChar == '*' || myChar == '/' );
                    if ( ! inComment && myChar != ' ' )
                    {
                        // TODO: how to report back this error
                        
                        myChar = ' ';
                    }
                }
                
                if ( column < INDICATOR_COLUMN || column > rightMargin || inComment )
                {
                    myChar = ' ';
                }
                
                column++;
            }
            
            return myChar;
        }
        catch ( EOFException myEOFException )
        {
            return -1;
        }
    }
}
