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


package com.xylocore.copybook.generator.visitor;

import java.util.ArrayList;
import java.util.List;

import com.xylocore.copybook.generator.domain.ElementFilter;
import com.xylocore.copybook.generator.domain.WildcardElementFilter;
import com.xylocore.copybook.generator.domain.config.ConfigVisitor;
import com.xylocore.copybook.generator.domain.config.DataElementConfig;
import com.xylocore.copybook.generator.domain.config.ExcludedElementFilterConfig;
import com.xylocore.copybook.generator.domain.config.IncludedElementFilterConfig;


/**
 * A configuration visitor that gathers the element filters for a particular
 * data element configuration.
 * 
 * @author      Eric R. Medley
 */

public class FilterCollectionVisitor
    extends
        ConfigVisitor
{
    //
    // Members
    //
    
    
    private List<ElementFilter> filters;
    
    
    
    
    //
    // Class implementation
    //

    
    /**
     * Returns the element filters for the specified data element configuration.
     * 
     * @param       aElement
     *                  The data element configuration element of interest.
     *                  
     * @return      The element filters for the specified data element configuration.
     */
    public List<ElementFilter> collectFilters( DataElementConfig aElement )
    {
        filters = new ArrayList<>();
        
        aElement.accept( this );
        
        return filters;
    }
    

    @Override
    public void leaveDataElement( DataElementConfig aElement )
    {
        if ( filters.isEmpty() )
        {
            filters.add( new WildcardElementFilter( aElement.getId(), true ) );
        }
    }
    
    
    @Override
    public boolean shouldVisitElementFilters( DataElementConfig aElement )
    {
        return true;
    }
    
    
    @Override
    public void visitIncludedElementFilter( IncludedElementFilterConfig aElementFilter )
    {
        filters.add( new WildcardElementFilter( aElementFilter.getName(), true ) );
    }
    
    
    @Override
    public void visitExcludedElementFilter( ExcludedElementFilterConfig aElementFilter )
    {
        filters.add( new WildcardElementFilter( aElementFilter.getName(), false ) );
    }
}
