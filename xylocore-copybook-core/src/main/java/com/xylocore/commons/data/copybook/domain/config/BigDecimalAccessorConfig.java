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


package com.xylocore.commons.data.copybook.domain.config;

import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class BigDecimalAccessorConfig
    extends
        AccessorConfig
{
    //
    // Members
    //
    
    
    private int scale;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public BigDecimalAccessorConfig()
    {
        super( DataType.BigDecimal );
        
        scale = 0;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public int getScale()
    {
        return scale;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aScale
     */
    public void setScale( int aScale )
    {
        scale = aScale;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitBigDecimalAccessor( this );
        
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
        
        aVisitor.leaveBigDecimalAccessor( this );
    }
    

    
    
    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.AccessorConfig#buildDescribableLabelValuePairs(java.util.Map)
     */
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        super.buildDescribableLabelValuePairs( aLabelValueMap );
        
        aLabelValueMap.put( "Scale", Integer.toString( scale ) );
    }
}
