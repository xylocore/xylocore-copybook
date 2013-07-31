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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

@XmlAccessorType( XmlAccessType.PROPERTY            )
@XmlRootElement ( name      = "data-element"        )
@XmlType        ( propOrder = { "filters",
                                "accessors",
                                "nullEquivalents" } )
public class DataElementConfig
    extends
        ElementConfig
{
    //
    // Members
    //
    
    
    private List<ElementFilterConfig>   filters         = new ArrayList<>();
    private List<AccessorConfig>        accessors       = new ArrayList<>();
    private List<NullEquivalentConfig>  nullEquivalents = new ArrayList<>();
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElementRef
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
        filters.clear();
        
        if ( aFilters != null )
        {
            filters.addAll( aFilters );
        }
    }

    
    /**
     * FILLIN
     * 
     * @param       aFilter
     */
    public void addFilter( ElementFilterConfig aFilter )
    {
        if ( aFilter == null )
        {
            throw new IllegalArgumentException( "a filter must be specified" );
        }
        
        filters.add( aFilter );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElementRef
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
        accessors.clear();
        
        if ( aAccessors != null )
        {
            accessors.addAll( aAccessors );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void addAccessor( AccessorConfig aAccessor )
    {
        if ( aAccessor == null )
        {
            throw new IllegalArgumentException( "an accessor must be specified" );
        }
        
        accessors.add( aAccessor );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElementRef
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
        nullEquivalents.clear();
        
        if ( aNullEquivalents != null )
        {
            nullEquivalents.addAll( aNullEquivalents );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     */
    public void addNullEquivalent( NullEquivalentConfig aNullEquivalent )
    {
        if ( aNullEquivalent == null )
        {
            throw new IllegalArgumentException( "a null equivalent must be specified" );
        }
        
        nullEquivalents.add( aNullEquivalent );
    }
    
    
    @Override
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitDataElement( this );
        
        super.acceptVisit( aVisitor );
    }


    @Override
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
    
    
    @Override
    protected void acceptLeave( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveDataElement( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    @Override
    public void buildDescribableCollections( Map<String,Object> aCollectionsMap )
    {
        aCollectionsMap.put( "Filters"        , filters         );
        aCollectionsMap.put( "Accessors"      , accessors       );
        aCollectionsMap.put( "NullEquivalents", nullEquivalents );
    }
}
