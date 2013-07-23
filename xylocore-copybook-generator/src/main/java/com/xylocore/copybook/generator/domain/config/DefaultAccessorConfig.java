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

package com.xylocore.copybook.generator.domain.config;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class DefaultAccessorConfig
    extends
        AccessorConfig
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitDefaultAccessor( this );
        
        super.acceptVisit( aVisitor );
    }


    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptLeave( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveDefaultAccessor( this );
    }
}
