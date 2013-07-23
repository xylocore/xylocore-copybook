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

public class NoNameElement
    extends
        Element
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aLevel
     */
    public NoNameElement( int aLevel )
    {
        super( aLevel );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Element#acceptVisit(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptVisit( Visitor aVisitor )
    {
        aVisitor.visitNoNameElement( this );
        
        super.acceptVisit( aVisitor );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Element#acceptLeave(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptLeave( Visitor aVisitor )
    {
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveNoNameElement( this );
    }
}
