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

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.copybook.generator.domain.config.parser.EnvironmentMetadataMarshallingStrategy;


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
    
    
    public static final String  PACKAGE_NAME_KEY                    = "PackageName";
    public static final String  CLASS_NAME_KEY                      = "ClassName";
    public static final String  GENERATION_ROOT_DIR_KEY             = "GenerationRootDir";
    public static final String  ENVIRONMENT_METADATA_FILENAME_KEY   = "EnvMetadataFilename";
    public static final String  COPYBOOK_FILENAME_KEY               = "CopybookFilename";
    public static final String  IMPLICIT_RECORD_NAME_KEY            = "ImplicitRecordName";

    private Map<String,String>  overrideProperties;
    
    
    
    
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
        
        if ( overrideProperties == Collections.<String,String>emptyMap() )
        {
            overrideProperties = new HashMap<String,String>();
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
        if ( aProperties != null && aProperties.size() != 0 )
        {
            for ( Map.Entry<String,String> myEntry : aProperties.entrySet() )
            {
                String myPropertyName  = myEntry.getKey();
                String myPropertyValue = myEntry.getValue();
                
                setOverrideProperty( myPropertyName, myPropertyValue );
            }
        }
        else
        {
            overrideProperties = Collections.<String,String>emptyMap();
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Environment configure()
    {
        Environment myEnvironment;

        String myMetadataFilename = overrideProperties.get( ENVIRONMENT_METADATA_FILENAME_KEY );
        if ( myMetadataFilename != null )
        {
            if ( StringUtils.isBlank( myMetadataFilename ) )
            {
                throw new EnvironmentConfigurationException( "the environment metadata filename cannot be empty or blank" );
            }
            
            myEnvironment = configure( myMetadataFilename );
        }
        else
        {
            myEnvironment = new Environment();
            
            applyOverrides( myEnvironment );
        }
        
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
        if ( StringUtils.isBlank( aFilename ) )
        {
            throw new EnvironmentConfigurationException( "the environment metadata filename cannot be null, empty, or blank" );
        }
        
        return configure( new File( aFilename ) );
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
        Environment myEnvironment = EnvironmentMetadataMarshallingStrategy.getInstance().unmarshal( aFile );
        
        applyOverrides( myEnvironment );
        
        return myEnvironment;
    }
    
    
    /**
     * FILLIN
     */
    public void reset()
    {
        overrideProperties = Collections.<String,String>emptyMap();
    }
    

    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    private void applyOverrides( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        setMetadataFilename       ( aEnvironment );
        setPackageName            ( aEnvironment );
        setClassName              ( aEnvironment );
        setGenerationRootDirectory( aEnvironment );
        setCopybookFilename       ( aEnvironment );
        setImplicitRecordName     ( aEnvironment );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aEnvironment
     */
    private void setMetadataFilename( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        String myPropertyValue = overrideProperties.get( ENVIRONMENT_METADATA_FILENAME_KEY );
        if ( myPropertyValue != null )
        {
            aEnvironment.setMetadataFilename( myPropertyValue );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    private void setPackageName( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        String myPropertyValue = overrideProperties.get( PACKAGE_NAME_KEY );
        if ( myPropertyValue != null )
        {
            aEnvironment.setPackageName( myPropertyValue );
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
            aEnvironment.setClassName( myPropertyValue );
        }
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
    private void setCopybookFilename( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        String myPropertyValue = overrideProperties.get( COPYBOOK_FILENAME_KEY );
        if ( myPropertyValue != null )
        {
            aEnvironment.setCopybookFilename( myPropertyValue );
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
            aEnvironment.setImplicitRecordName( myPropertyValue );
        }
    }
}
