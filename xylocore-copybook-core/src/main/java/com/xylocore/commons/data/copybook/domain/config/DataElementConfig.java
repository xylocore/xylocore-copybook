package com.xylocore.commons.data.copybook.domain.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class DataElementConfig
    extends
        ElementConfig
{
    //
    // Members
    //
    
    
    private List<ElementFilterConfig>   filters         = Collections.<ElementFilterConfig>emptyList();
    private List<AccessorConfig>        accessors       = Collections.<AccessorConfig>emptyList();
    private List<NullEquivalentConfig>  nullEquivalents = Collections.<NullEquivalentConfig>emptyList();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public List<ElementFilterConfig> getFilters()
    {
        return filters;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFilters
     */
    public void setFilters( List<ElementFilterConfig> aFilters )
    {
        filters =
                ( aFilters != null && ! aFilters.isEmpty() )
                    ? new ArrayList<ElementFilterConfig>( aFilters )
                    : Collections.<ElementFilterConfig>emptyList();
    }

    
    /**
     * FILLIN
     * 
     * @param       aFilter
     */
    public void addFilter( ElementFilterConfig aFilter )
    {
        assert aFilter != null;
        
        if ( filters == Collections.<ElementFilterConfig>emptyList() )
        {
            filters = new ArrayList<ElementFilterConfig>();
        }
        
        filters.add( aFilter );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public List<AccessorConfig> getAccessors()
    {
        return accessors;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessors
     */
    public void setAccessors( List<AccessorConfig> aAccessors )
    {
        accessors =
                ( aAccessors != null && ! aAccessors.isEmpty() )
                        ? new ArrayList<AccessorConfig>( aAccessors )
                        : Collections.<AccessorConfig>emptyList();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void addAccessor( AccessorConfig aAccessor )
    {
        assert aAccessor != null;
        
        if ( accessors == Collections.<AccessorConfig>emptyList() )
        {
            accessors = new ArrayList<AccessorConfig>();
        }
        
        accessors.add( aAccessor );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public List<NullEquivalentConfig> getNullEquivalents()
    {
        return nullEquivalents;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalents
     */
    public void setNullEquivalents( List<NullEquivalentConfig> aNullEquivalents )
    {
        nullEquivalents =
                ( aNullEquivalents != null && ! aNullEquivalents.isEmpty() )
                        ? new ArrayList<NullEquivalentConfig>( aNullEquivalents )
                        : Collections.<NullEquivalentConfig>emptyList();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     */
    public void addNullEquivalent( NullEquivalentConfig aNullEquivalent )
    {
        assert aNullEquivalent != null;
        
        if ( nullEquivalents == Collections.<NullEquivalentConfig>emptyList() )
        {
            nullEquivalents = new ArrayList<NullEquivalentConfig>();
        }
        
        nullEquivalents.add( aNullEquivalent );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitDataElement( this );
        
        super.acceptVisit( aVisitor );
    }


    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptChildren( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptChildren( aVisitor );
        
        if ( aVisitor.shouldVisitElementFilters( this ) )
        {
            for ( ElementFilterConfig myFilter : filters )
            {
                if ( aVisitor.shouldVisitElementFilter( myFilter ) )
                {
                    myFilter.accept( aVisitor );
                }
            }
        }
        
        if ( aVisitor.shouldVisitAccessors( this ) )
        {
            for ( AccessorConfig myAccessor : accessors )
            {
                if ( aVisitor.shouldVisitAccessor( myAccessor ) )
                {
                    myAccessor.accept( aVisitor );
                }
            }
        }
        
        if ( aVisitor.shouldVisitNullEquivalents( this ) )
        {
            for ( NullEquivalentConfig myNullEquivalent : nullEquivalents )
            {
                if ( aVisitor.shouldVisitNullEquivalent( myNullEquivalent ) )
                {
                    myNullEquivalent.accept( aVisitor );
                }
            }
        }
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
        
        aVisitor.leaveDataElement( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigEntityDescribable#buildDescribableCollections(java.util.Map)
     */
    public void buildDescribableCollections( Map<String, Object> aCollectionsMap )
    {
        aCollectionsMap.put( "Filters"  , filters   );
        aCollectionsMap.put( "Accessors", accessors );
        aCollectionsMap.put( "NullEquivalents", nullEquivalents );
    }
}
