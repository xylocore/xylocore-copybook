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

import com.xylocore.commons.util.JavaLanguageHelper;
import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.DataType;



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
        if ( aClassName != null )
        {
            if ( ! JavaLanguageHelper.isJavaClassName( aClassName ) )
            {
                throw new IllegalArgumentException( "invalid class name (" + aClassName + ")" );
            }
            
            int myEndOfPackageIndex = aClassName.lastIndexOf( '.' );
            if ( myEndOfPackageIndex != -1 )
            {
                packageName = aClassName.substring( 0, myEndOfPackageIndex );
                className   = aClassName.substring( myEndOfPackageIndex+1 );
            }
            else
            {
                packageName = "";
                className   = aClassName;
            }
        }
        else
        {
            packageName = null;
            className   = null;
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
        if ( aDataType != DataType.Integer && aDataType != DataType.Long )
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
        aLabelValueMap.put( "MetadataFilename"        , metadataFilename         );
        aLabelValueMap.put( "PackageName"             , packageName              );
        aLabelValueMap.put( "ClassName"               , className                );
        aLabelValueMap.put( "GenerationRootDirectory" , generationRootDirectory  );
        aLabelValueMap.put( "CopybookFilename"        , copybookFilename         );
        aLabelValueMap.put( "ImplicitRecordName"      , implicitRecordName       );
        aLabelValueMap.put( "CopybookContextClassName", copybookContextClassName );
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
        
        aVisitor.visitEnvironment( this );

        if ( mappingMetadata != null )
        {
            mappingMetadata.accept( aVisitor );
        }
        
        aVisitor.leaveEnvironment( this );
    }
}
