/*
 * GeneratorEnvironment.java
 *
 * Copyright 2005 The Palantir Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xylocore.commons.data.copybook.domain.config;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.DataType;



/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Environment
    implements
        ConfigEntityDescribable,
        ConfigVisitable
{
    //
    // Members
    //
    
    
    private String              metadataFilename;
    private String              copybookFilename;
    private String              packageName;
    private String              className;
    private String              generationRootDirectory;
    private String              implicitRecordName;
    private DataType            minimumIntegerDataType;
    private DataType            minimumFloatingPointDataType;
    private MappingMetadata     mappingMetadata;
    private NameConverter       nameConverter                   = DefaultNameConverter.getInstance();
    private int                 rightMarginLimit                = 72;
    private String              copybookContextClassName        = CopybookContext.class.getName();
    
    
    
    
    //
    // Class implementation
    //


    /**
     * FILLIN
     *
     * @return
     */
    public String getMetadataFilename()
    {
        return metadataFilename;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aMetadataFilename
     */
    public void setMetadataFilename( String aMetadataFilename )
    {
        metadataFilename = aMetadataFilename;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
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
    public String getPackageName()
    {
        return packageName;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPackageName
     */
    public void setPackageName( String aPackageName )
    {
        if ( aPackageName == null )
        {
            aPackageName = "";
        }
        
        packageName = aPackageName;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
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
        if ( StringUtils.isNotEmpty( aClassName ) )
        {
            int myEndOfPackageIndex = aClassName.lastIndexOf( '.' );
            if ( myEndOfPackageIndex != -1 )
            {
                String myPackageName = aClassName.substring( 0, myEndOfPackageIndex );
                
                aClassName = aClassName.substring( myEndOfPackageIndex+1 );
                if ( StringUtils.isEmpty( aClassName ) )
                {
                    throw new IllegalArgumentException( "a class name cannot end with a period" );
                }
                
                setPackageName( myPackageName );
                
                className = aClassName;
            }
            else
            {
                className = aClassName;
            }
        }
        else
        {
            className = null;
        }
    }

    
    /**
     * FILLIN
     * 
     * @param       aGenerationRootDirectory
     * 
     * @return
     */
    public String getGenerationRootDirectory()
    {
        return generationRootDirectory;
    }

    
    /**
     * FILLIN
     * 
     * @param       aGenerationRootDirectory
     */
    public void setGenerationRootDirectory( String aGenerationRootDirectory )
    {
        generationRootDirectory = aGenerationRootDirectory;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
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
        assert aDataType == DataType.Integer ||
               aDataType == DataType.Long;
        
        minimumIntegerDataType = aDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
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
        assert aDataType == DataType.Float   ||
               aDataType == DataType.Double;
        
        minimumFloatingPointDataType = aDataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
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
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public NameConverter getNameConverter()
    {
        return nameConverter;
    }
    
    
    /**
     * FILLIN
     * 
     * @param aNameConverter
     */
    public void setNameConverter( NameConverter aNameConverter )
    {
        nameConverter = aNameConverter;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
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
    public String getCopybookContextClassName()
    {
        return copybookContextClassName;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aCopybookContextClassName
     */
    public void setCopybookContextClassName( String aCopybookContextClassName )
    {
        copybookContextClassName = aCopybookContextClassName;
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        return ConfigEntityDescriber.simpleDescribe( this );
    }
    
    
    

    //
    // ConfigEntityDescribable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigEntityDescribable#buildDescribableLabelValuePairs(java.util.Map)
     */
    public void buildDescribableLabelValuePairs( Map<String,String> aLabelValueMap )
    {
        aLabelValueMap.put( "MetadataFilename"        , metadataFilename         );
        aLabelValueMap.put( "PackageName"             , packageName              );
        aLabelValueMap.put( "ClassName"               , className                );
        aLabelValueMap.put( "GenerationRootDirectory" , generationRootDirectory  );
        aLabelValueMap.put( "CopybookFilename"        , copybookFilename         );
        aLabelValueMap.put( "ImplicitRecordName"      , implicitRecordName       );
        aLabelValueMap.put( "CopybookContextClassName", copybookContextClassName );
    }


    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigEntityDescribable#buildDescribableCollections(java.util.Map)
     */
    public void buildDescribableCollections( Map<String, Object> aCollectionsMap )
    {
        aCollectionsMap.put( "MappingMetadata", mappingMetadata );
    }



    
    //
    // ConfigVisitable interface implementation
    //
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitable#accept(com.xylocore.commons.data.copybook.domain.config.ConfigVisitor)
     */
    public void accept( ConfigVisitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitEnvironment( this );

        if ( mappingMetadata != null )
        {
            mappingMetadata.accept( aVisitor );
        }
        
        aVisitor.leaveEnvironment( this );
    }
}
