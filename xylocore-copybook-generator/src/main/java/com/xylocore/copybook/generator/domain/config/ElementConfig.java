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
        DataElementConfig.class,
        GroupElementConfig.class
    }
)
public abstract class ElementConfig
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private String id;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlAttribute( name = "id", required = true )
    public String getId()
    {
        return id;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aId
     */
    public void setId( String aId )
    {
        id = aId;
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
        
        aVisitor.visitElement( this );
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
        
        aVisitor.leaveElement( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    @Override
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        aLabelValueMap.put( "Id", id );
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
