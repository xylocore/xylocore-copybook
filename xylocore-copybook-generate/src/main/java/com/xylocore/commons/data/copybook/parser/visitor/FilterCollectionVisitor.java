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


package com.xylocore.commons.data.copybook.parser.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xylocore.commons.data.copybook.domain.config.ConfigVisitor;
import com.xylocore.commons.data.copybook.domain.config.DataElementConfig;
import com.xylocore.commons.data.copybook.domain.config.ExcludedElementFilterConfig;
import com.xylocore.commons.data.copybook.domain.config.IncludedElementFilterConfig;
import com.xylocore.commons.data.copybook.parser.ElementFilter;
import com.xylocore.commons.data.copybook.parser.WildcardElementFilter;


/**
 * FILLIN
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
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    public ElementFilter[] collectFilters( DataElementConfig aElement )
    {
        filters = Collections.<ElementFilter>emptyList();
        
        aElement.accept( this );
        
        return filters.toArray( new ElementFilter[filters.size()] );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveDataElement(com.xylocore.commons.data.copybook.domain.config.DataElementConfig)
     */
    public void leaveDataElement( DataElementConfig aElement )
    {
        if ( filters.isEmpty() )
        {
            addFilter( new WildcardElementFilter( aElement.getId(), true ) );
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#shouldVisitElementFilters(com.xylocore.commons.data.copybook.domain.config.DataElementConfig)
     */
    public boolean shouldVisitElementFilters( DataElementConfig aElement )
    {
        return true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitIncludedElementFilter(com.xylocore.commons.data.copybook.domain.config.IncludedElementFilterConfig)
     */
    public void visitIncludedElementFilter( IncludedElementFilterConfig aElementFilter )
    {
        addFilter( new WildcardElementFilter( aElementFilter.getName(), true ) );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitExcludedElementFilter(com.xylocore.commons.data.copybook.domain.config.ExcludedElementFilterConfig)
     */
    public void visitExcludedElementFilter( ExcludedElementFilterConfig aElementFilter )
    {
        addFilter( new WildcardElementFilter( aElementFilter.getName(), false ) );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFilter
     */
    private void addFilter( ElementFilter aFilter )
    {
        assert aFilter != null;
        
        if ( filters == Collections.<ElementFilter>emptyList() )
        {
            filters = new ArrayList<ElementFilter>();
        }
        
        filters.add( aFilter );
    }
}
