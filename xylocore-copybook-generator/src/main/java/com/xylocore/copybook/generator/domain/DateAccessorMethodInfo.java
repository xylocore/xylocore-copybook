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

import com.xylocore.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class DateAccessorMethodInfo
    extends
        AccessorMethodInfo
{
    //
    // Members
    //
    
    
    private String pattern;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public DateAccessorMethodInfo()
    {
        super( DataType.Date );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getPattern()
    {
        return pattern;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPattern
     */
    public void setPattern( String aPattern )
    {
        pattern = aPattern;
    }
    

    @Override
    protected void acceptVisit( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitDateAccessorMethodInfo( this );
        
        super.acceptVisit( aVisitor );
    }
    
    
    @Override
    protected void acceptLeave( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveDateAccessorMethodInfo( this );
    }
}
