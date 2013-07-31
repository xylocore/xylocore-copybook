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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.xylocore.copybook.generator.EnvironmentConfigurationException;
import com.xylocore.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

@XmlAccessorType( XmlAccessType.PROPERTY                          )
@XmlRootElement ( name      = "metadata"                          )
@XmlType        ( propOrder = { "copybookFilename",
                                "className",
                                "implicitRecordName",
                                "minimumIntegerDataType",
                                "minimumFloatingPointDataType",
                                "rightMarginLimit",
                                "nameConverterClassName",
                  		        "mappingMetadata"               } )
public class Metadata
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private String              copybookFilename;
    private String              className;
    private String              implicitRecordName;
    private DataType            minimumIntegerDataType;
    private DataType            minimumFloatingPointDataType;
    private int                 rightMarginLimit;
    private NameConverter       nameConverter;
    private MappingMetadata     mappingMetadata;
    
    
    
    
    //
    // Instance initialization
    //
    
    
    {
        setMinimumIntegerDataType      ( DataType.Byte                        );
        setMinimumFloatingPointDataType( DataType.Float                       );
        setNameConverterClassName      ( DefaultNameConverter.class.getName() );
        setRightMarginLimit            ( 72                                   );
    }
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElement( name = "copybook-filename" )
    public String getCopybookFilename()
    {
        return copybookFilename;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCopybookFilename
     */
    public void setCopybookFilename( String aCopybookFilename )
    {
        copybookFilename = aCopybookFilename;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElement( name = "class-name" )
    public String getClassName()
    {
        return className;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aClassName
     */
    public void setClassName( String aClassName )
    {
        className = aClassName;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElement( name = "implicit-record-name" )
    public String getImplicitRecordName()
    {
        return implicitRecordName;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aImplicitRecordName
     */
    public void setImplicitRecordName( String aImplicitRecordName )
    {
        implicitRecordName = aImplicitRecordName;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElement( name = "minimum-integer-data-type" )
    public DataType getMinimumIntegerDataType()
    {
        return minimumIntegerDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     */
    public void setMinimumIntegerDataType( DataType aDataType )
    {
        if
        (
            aDataType != DataType.Byte    &&
            aDataType != DataType.Short   &&
            aDataType != DataType.Integer &&
            aDataType != DataType.Long
        )
        {
            throw new IllegalArgumentException( "data type cannot be used for the minimum integer data type: " + aDataType );
        }

        minimumIntegerDataType = aDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElement( name = "minimum-floating-point-data-type" )
    public DataType getMinimumFloatingPointDataType()
    {
        return minimumFloatingPointDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     */
    public void setMinimumFloatingPointDataType( DataType aDataType )
    {
        if ( aDataType != DataType.Float && aDataType != DataType.Double )
        {
            throw new IllegalArgumentException( "data type cannot be used for the minimum floating point data type: " + aDataType );
        }

        minimumFloatingPointDataType = aDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElement( name = "right-margin-limit" )
    public int getRightMarginLimit()
    {
        return rightMarginLimit;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aRightMarginLimit
     */
    public void setRightMarginLimit( int aRightMarginLimit )
    {
        rightMarginLimit = aRightMarginLimit;
    }
    

    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElement( name = "name-converter" )
    public String getNameConverterClassName()
    {
        return nameConverter.getClass().getName();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aClassName
     */
    public void setNameConverterClassName( String aClassName )
    {
        try
        {
            @SuppressWarnings( "unchecked" )
            Class<? extends NameConverter> myClass = (Class<? extends NameConverter>) Class.forName( aClassName );
            
            nameConverter = myClass.newInstance();
        }
        catch ( Exception myException )
        {
            throw new EnvironmentConfigurationException( "unable to create name converter: " +
                                                             myException.getMessage(),
                                                         myException                           );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlTransient
    public NameConverter getNameConverter()
    {
        return nameConverter;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    @XmlElement( name = "mapping-metadata" )
    public MappingMetadata getMappingMetadata()
    {
        return mappingMetadata;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMappingMetadata
     */
    public void setMappingMetadata( MappingMetadata aMappingMetadata )
    {
        mappingMetadata = aMappingMetadata;
    }
    
    
    @Override
    public String toString()
    {
        return ConfigEntityDescriber.simpleDescribe( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    @Override
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        aLabelValueMap.put( "CopybookFilename"            , copybookFilename                     );
        aLabelValueMap.put( "ClassName"                   , className                            );
        aLabelValueMap.put( "ImplicitRecordName"          , implicitRecordName                   );
        aLabelValueMap.put( "MinimumIntegerDataType"      , minimumIntegerDataType.name()        );
        aLabelValueMap.put( "MinimumFloatingPointDataType", minimumFloatingPointDataType.name()  );
        aLabelValueMap.put( "RightMarginLimit"            , Integer.toString( rightMarginLimit ) );
        aLabelValueMap.put( "NameConverterClassName"      , nameConverter.getClass().getName()   );
    }


    @Override
    public void buildDescribableCollections( Map<String, Object> aCollectionsMap )
    {
        aCollectionsMap.put( "MappingMetadata", mappingMetadata );
    }



    
    //
    // ConfigVisitable interface implementation
    //
    

    @Override
    public void accept( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitMetadata( this );

        if ( mappingMetadata != null )
        {
            mappingMetadata.accept( aVisitor );
        }
        
        aVisitor.leaveMetadata( this );
    }
}
