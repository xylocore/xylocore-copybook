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


package com.xylocore.commons.data.copybook.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xylocore.commons.data.copybook.runtime.DataType;
import com.xylocore.commons.data.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class DataElement
    extends
        Element
{
    //
    // Members
    //
    

    private Map<DataType,AccessorMethodInfo>    accessorMethodInfos;
    private boolean                             noDefaultAccessor;
    private List<Level88Element>                level88Elements;
    private List<NullEquivalentStrategy>        nullEquivalentStrategies;
    private String                              nullEquivalentStrategySetInstanceVariableName;

    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aName
     * @param       aLevel
     */
    public DataElement( String   aName,
                        int      aLevel )
    {
        super( aName, aLevel );
        
        accessorMethodInfos      = Collections.<DataType,AccessorMethodInfo>emptyMap();
        level88Elements          = Collections.<Level88Element>emptyList();
        nullEquivalentStrategies = Collections.<NullEquivalentStrategy>emptyList();
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Map<DataType,AccessorMethodInfo> getAccessorMethodInfos()
    {
        return accessorMethodInfos;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessorMethodInfo
     */
    public void addAccessorMethodInfo( AccessorMethodInfo aAccessorMethodInfo )
    {
        if ( accessorMethodInfos == Collections.EMPTY_MAP )
        {
            accessorMethodInfos = new HashMap<DataType,AccessorMethodInfo>();
        }
        
        accessorMethodInfos.put( aAccessorMethodInfo.getDataType(), aAccessorMethodInfo );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public boolean isNoDefaultAccessor()
    {
        return noDefaultAccessor;
    }
    

    /**
     * FILLIN
     * 
     * @param       aNoDefaultAccessor
     */
    public void setNoDefaultAccessor( boolean aNoDefaultAccessor )
    {
        noDefaultAccessor = aNoDefaultAccessor;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public List<Level88Element> getLevel88Elements()
    {
        return level88Elements;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void addLevel88Element( Level88Element aElement )
    {
        assert aElement != null;

        if ( level88Elements == Collections.EMPTY_LIST )
        {
            level88Elements = new ArrayList<Level88Element>();
        }
        
        aElement.setParent( this );
        
        level88Elements.add( aElement );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public List<NullEquivalentStrategy> getNullEquivalentStrategies()
    {
        return nullEquivalentStrategies;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalentStrategies
     */
    public void setNullEquivalentStrategies( List<NullEquivalentStrategy> aNullEquivalentStrategies )
    {
        if ( aNullEquivalentStrategies != null && ! aNullEquivalentStrategies.isEmpty() )
        {
            nullEquivalentStrategies = new ArrayList<NullEquivalentStrategy>( aNullEquivalentStrategies );
        }
        else
        {
            nullEquivalentStrategies = Collections.<NullEquivalentStrategy>emptyList();
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalentStrategy
     */
    public void addNullEquivalentStrategy( NullEquivalentStrategy aNullEquivalentStrategy )
    {
        assert aNullEquivalentStrategy != null;
        
// TODO: remove
//if ( "IPPFX7-MASTER-CO-NUMBER".equals( getName() ) )
{
    System.out.println();
}
        if ( nullEquivalentStrategies == Collections.EMPTY_LIST )
        {
            nullEquivalentStrategies = new ArrayList<NullEquivalentStrategy>();
        }
        
        nullEquivalentStrategies.add( aNullEquivalentStrategy );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getNullEquivalentStrategySetInstanceVariableName()
    {
        return nullEquivalentStrategySetInstanceVariableName;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVariableName
     */
    public void setNullEquivalentStrategySetInstanceVariableName( String aVariableName )
    {
        nullEquivalentStrategySetInstanceVariableName = aVariableName;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.BasicElement#acceptVisit(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptVisit( Visitor aVisitor )
    {
        aVisitor.visitDataElement( this );
        
        super.acceptVisit( aVisitor );
    }


    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptChildren( Visitor aVisitor )
    {
        assert aVisitor != null;

        super.acceptChildren( aVisitor );
        
        if ( aVisitor.shouldVisitLevel88Elements( this ) )
        {
            for ( Level88Element myElement : level88Elements )
            {
                if ( aVisitor.shouldVisitLevel88Element( myElement ) )
                {
                    myElement.accept( aVisitor );
                }
            }
        }
        
        for ( AccessorMethodInfo myAccessorMethodInfo : accessorMethodInfos.values() )
        {
            myAccessorMethodInfo.accept( aVisitor );
        }
    }

    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.BasicElement#acceptLeave(com.xylocore.commons.data.copybook.domain.Visitor)
     */
    protected void acceptLeave( Visitor aVisitor )
    {
        super.acceptLeave( aVisitor );
        
        aVisitor.leaveDataElement( this );
    }
}
