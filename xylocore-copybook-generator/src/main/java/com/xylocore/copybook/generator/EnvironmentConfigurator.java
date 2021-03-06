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

package com.xylocore.copybook.generator;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.copybook.generator.domain.config.Metadata;
import com.xylocore.copybook.generator.domain.config.parser.MetadataMarshallingStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class EnvironmentConfigurator
{
    //
    // Members
    //
    
    
    public static final String  CLASS_NAME_KEY              = "ClassName";
    public static final String  GENERATION_ROOT_DIR_KEY     = "GenerationRootDir";
    public static final String  METADATA_FILENAME_KEY       = "MetadataFilename";
    public static final String  COPYBOOK_FILENAME_KEY       = "CopybookFilename";
    public static final String  IMPLICIT_RECORD_NAME_KEY    = "ImplicitRecordName";

    private Map<String,String>  overrideProperties          = new HashMap<>();;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Default constructor.
     */
    public EnvironmentConfigurator()
    {
        reset();
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPropertyName
     * 
     * @return
     */
    public String getOverrideProperty( String aPropertyName )
    {
        return overrideProperties.get( aPropertyName );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Map<String,String> getOverrideProperties()
    {
        return Collections.unmodifiableMap( overrideProperties );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aPropertyName
     * @param       aPropertyValue
     */
    public void setOverrideProperty( String   aPropertyName,
                                     String   aPropertyValue )
    {
        if ( StringUtils.isBlank( aPropertyName ) )
        {
            throw new IllegalArgumentException( "property name cannot be null, empty, or blank" );
        }
        
        overrideProperties.put( aPropertyName, aPropertyValue );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aProperties
     */
    public void setOverrideProperties( Map<String,String> aProperties )
    {
        overrideProperties.clear();
        
        if ( aProperties != null )
        {
            overrideProperties.putAll( aProperties );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Environment configure()
    {
        String      myMetadataFilename = overrideProperties.get( METADATA_FILENAME_KEY );
        Environment myEnvironment      = configure( myMetadataFilename );
        
        return myEnvironment;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFilename
     * 
     * @return
     */
    public Environment configure( String aFilename )
    {
        File myFile = null;
        
        if ( aFilename != null )
        {
            if ( StringUtils.isBlank( aFilename ) )
            {
                throw new EnvironmentConfigurationException( "the copybook metadata filename cannot be empty or blank" );
            }
            
            myFile = new File( aFilename );
        }
        
        return configure( myFile );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFile
     * 
     * @return
     */
    public Environment configure( File aFile )
    {
        Metadata myMetadata;
        
        if ( aFile != null )
        {
            myMetadata = MetadataMarshallingStrategy.getInstance().unmarshal( aFile );
        }
        else
        {
            myMetadata = new Metadata();
        }
        
        Environment myEnvironment = new Environment( myMetadata );
        
        applyOverrides( myEnvironment );
        
        return myEnvironment;
    }
    
    
    /**
     * FILLIN
     */
    public void reset()
    {
        overrideProperties.clear();
    }
    

    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    private void applyOverrides( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        setGenerationRootDirectory( aEnvironment );
        setClassName              ( aEnvironment );
        setCopybookFilename       ( aEnvironment );
        setImplicitRecordName     ( aEnvironment );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    private void setGenerationRootDirectory( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        String myPropertyValue = overrideProperties.get( GENERATION_ROOT_DIR_KEY );
        if ( myPropertyValue != null )
        {
            aEnvironment.setGenerationRootDirectory( myPropertyValue );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    private void setClassName( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        String myPropertyValue = overrideProperties.get( CLASS_NAME_KEY );
        if ( myPropertyValue != null )
        {
            aEnvironment.getMetadata().setClassName( myPropertyValue );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    private void setCopybookFilename( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        String myPropertyValue = overrideProperties.get( COPYBOOK_FILENAME_KEY );
        if ( myPropertyValue != null )
        {
            aEnvironment.getMetadata().setCopybookFilename( myPropertyValue );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    private void setImplicitRecordName( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        String myPropertyValue = overrideProperties.get( IMPLICIT_RECORD_NAME_KEY );
        if ( myPropertyValue != null )
        {
            aEnvironment.getMetadata().setImplicitRecordName( myPropertyValue );
        }
    }
}
