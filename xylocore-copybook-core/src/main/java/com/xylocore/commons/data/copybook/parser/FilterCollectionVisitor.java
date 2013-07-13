package com.xylocore.commons.data.copybook.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.xylocore.commons.data.copybook.domain.config.ConfigVisitor;
import com.xylocore.commons.data.copybook.domain.config.DataElementConfig;
import com.xylocore.commons.data.copybook.domain.config.ExcludedElementFilterConfig;
import com.xylocore.commons.data.copybook.domain.config.IncludedElementFilterConfig;


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
