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


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookDomainException
    extends
        RuntimeException
{
    //
    // Members
    //
    
    
    private static final long serialVersionUID = 6415907529287572838L;
    
    
    
    
    //
    // Class implementationm
    //


    /**
     * FILLIN
     */
    public CopybookDomainException()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     */
    public CopybookDomainException( String aMessage )
    {
        super( aMessage );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCause
     */
    public CopybookDomainException( Throwable aCause )
    {
        super( aCause );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMessage
     * @param       aCause
     */
    public CopybookDomainException( String      aMessage,
                                    Throwable   aCause    )
    {
        super( aMessage, aCause );
    }
}
