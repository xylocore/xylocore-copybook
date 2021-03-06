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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

@XmlAccessorType( XmlAccessType.PROPERTY )
@XmlSeeAlso
(
    {
        IncludedElementFilterConfig.class,
        ExcludedElementFilterConfig.class
    }
)
public abstract class ElementFilterConfig
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private String name;
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     */
    public ElementFilterConfig()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param aName
     */
    public ElementFilterConfig( String aName )
    {
        setName( aName );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlAttribute
    public String getName()
    {
        return name;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aName
     */
    public void setName( String aName )
    {
        name = aName;
    }
    

    @Override
    public String toString()
    {
        return ConfigEntityDescriber.simpleDescribe( this );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitElementFilter( this );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptChildren( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptLeave( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.leaveElementFilter( this );
    }
    

    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    @Override
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        aLabelValueMap.put( "Name", name );
    }


    @Override
    public void buildDescribableCollections( Map<String, Object> aCollectionsMap )
    {
    }



    
    //
    // ConfigVisitable interface implementation
    //
    

    @Override
    public void accept( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        acceptVisit   ( aVisitor );
        acceptChildren( aVisitor );
        acceptLeave   ( aVisitor );
    }
}
