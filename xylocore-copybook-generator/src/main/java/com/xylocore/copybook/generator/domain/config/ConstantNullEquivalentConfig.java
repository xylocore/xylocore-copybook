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

import java.util.Map;

import com.xylocore.copybook.runtime.DataUsageCategory;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ConstantNullEquivalentConfig
    extends
        NullEquivalentConfig
{
    //
    // Members
    //
    
    
    private DataUsageCategory   dataUsageCategory;
    private String              value;
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * Default constructor.
     */
    public ConstantNullEquivalentConfig()
    {
        super( "ConstantNullEquivalent" );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public DataUsageCategory getDataUsageCategory()
    {
        return dataUsageCategory;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataUsageCategory
     */
    public void setDataUsageCategory( DataUsageCategory aDataUsageCategory )
    {
        dataUsageCategory = aDataUsageCategory;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getValue()
    {
        return value;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aValue
     */
    public void setValue( String aValue )
    {
        value = aValue;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitConstantNullEquivalent( this );
        
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
        
        aVisitor.leaveConstantNullEquivalent( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigEntityDescribable#buildDescribableLabelValuePairs(java.util.Map)
     */
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        aLabelValueMap.put( "DataUsageCategory", dataUsageCategory.name() );
        aLabelValueMap.put( "Value"            , value                    );
    }
}
