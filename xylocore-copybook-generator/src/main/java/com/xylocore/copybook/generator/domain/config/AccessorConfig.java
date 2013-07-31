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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import com.xylocore.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

@XmlAccessorType( XmlAccessType.PROPERTY )
@XmlSeeAlso
(
    {
        BigDecimalAccessorConfig.class,
        BigIntegerAccessorConfig.class,
        ByteAccessorConfig.class,
        CharAccessorConfig.class,
        DateAccessorConfig.class,
        DefaultAccessorConfig.class,
        DoubleAccessorConfig.class,
        FloatAccessorConfig.class,
        IntegerAccessorConfig.class,
        LongAccessorConfig.class,
        NoDefaultAccessorConfig.class,
        ShortAccessorConfig.class,
        StringAccessorConfig.class
    }
)
public abstract class AccessorConfig
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private DataType    dataType;
    private boolean     isDefault;
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     */
    public AccessorConfig()
    {
        this( null );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     */
    public AccessorConfig( DataType aDataType )
    {
        dataType = aDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlTransient
    public DataType getDataType()
    {
        return dataType;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    @XmlTransient
    public boolean isDefault()
    {
        return isDefault;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aIsDefault
     */
    public void setDefault( boolean aIsDefault )
    {
        isDefault = aIsDefault;
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
        
        aVisitor.visitAccessor( this );
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
        
        aVisitor.leaveAccessor( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    @Override
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        aLabelValueMap.put( "DataType" , ( dataType != null ) ? dataType.toString() : null );
        aLabelValueMap.put( "IsDefault", isDefault ? "true" : "false"                      );
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
