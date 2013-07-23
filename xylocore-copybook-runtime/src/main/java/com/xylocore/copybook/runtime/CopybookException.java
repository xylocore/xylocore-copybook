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


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookException
    extends
        RuntimeException
{
    //
    // Members
    //
    
    
    private static final long   serialVersionUID    = -4881163384743890685L;
    
    private final CopybookError standardError;
    private final Object        platformError;
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aStandardError
     * @param       aPlatformError
     */
    public CopybookException( CopybookError   aStandardError,
                              Object          aPlatformError  )
    {
        this( null, null, aStandardError, aPlatformError );
    }


    /**
     * FILLIN
     * 
     * @param       aMessage
     * @param       aStandardError
     * @param       aPlatformError
     */
    public CopybookException( String          aMessage,
                              CopybookError   aStandardError,
                              Object          aPlatformError  )
    {
        this( aMessage, null, aStandardError, aPlatformError );
    }


    /**
     * FILLIN
     * 
     * @param       aCause
     * @param       aStandardError
     * @param       aPlatformError
     */
    public CopybookException( Throwable       aCause,
                              CopybookError   aStandardError,
                              Object          aPlatformError  )
    {
        this( null, aCause, aStandardError, aPlatformError );
    }


    /**
     * FILLIN
     * 
     * @param       aMessage
     * @param       aCause
     * @param       aStandardError
     * @param       aPlatformError
     */
    public CopybookException( String          aMessage,
                              Throwable       aCause,
                              CopybookError   aStandardError,
                              Object          aPlatformError  )
    {
        super( aMessage, aCause );
        
        standardError = aStandardError;
        platformError = aPlatformError;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public CopybookError getStandardError()
    {
        return standardError;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Object getPlatformError()
    {
        return platformError;
    }
}
