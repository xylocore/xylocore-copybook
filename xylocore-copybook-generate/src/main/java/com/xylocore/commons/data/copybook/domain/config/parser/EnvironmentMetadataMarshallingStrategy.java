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

package com.xylocore.commons.data.copybook.domain.config.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.exolab.castor.xml.Unmarshaller;
import org.xml.sax.InputSource;

import com.xylocore.commons.data.copybook.domain.config.Environment;
import com.xylocore.commons.data.copybook.domain.config.EnvironmentConfigurationException;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class EnvironmentMetadataMarshallingStrategy
{
    //
    // Members
    //
    
    
    public static final String                                  MAP_RESOURCE_NAME           = "ConfigMarshalling.xml";
    
    private static final EnvironmentMetadataMarshallingStrategy instance                    = new EnvironmentMetadataMarshallingStrategy();
    
    private Unmarshaller                                        unmarshaller;
    private EnvironmentConfigurationException                   initializationException;

    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    private EnvironmentMetadataMarshallingStrategy()
    {
        try ( InputStream myInputStream = getClass().getResourceAsStream( MAP_RESOURCE_NAME ) )
        {
            if ( myInputStream == null )
            {
                throw new FileNotFoundException( MAP_RESOURCE_NAME );
            }
    
            loadMappings();
        }
        catch ( Exception myException )
        {
            initializationException =
                    new EnvironmentConfigurationException( "cannot load the metadata configuration marshalling map file: " +
                                                               MAP_RESOURCE_NAME,
                                                           myException                                                       );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static EnvironmentMetadataMarshallingStrategy getInstance()
    {
        if ( instance.initializationException != null )
        {
            throw instance.initializationException;
        }
        
        return instance;
    }
    
    
    /**
     * FILLIN
     * 
     * @exception   MappingException
     * @exception   IOException
     */
    private void loadMappings()
            throws MappingException,
                   IOException
    {
        try ( InputStream myInputStream = getClass().getResourceAsStream( MAP_RESOURCE_NAME ) )
        {
            assert myInputStream != null;
            
            InputSource myInputSource = new InputSource( myInputStream );
            myInputSource.setSystemId( MAP_RESOURCE_NAME );
            
            Mapping myMapping = new Mapping( getClass().getClassLoader() );
            myMapping.loadMapping( myInputSource );
            
            unmarshaller = new Unmarshaller( myMapping );
            unmarshaller.setIgnoreExtraAttributes( false );
        }
    }

    
    /**
     * FILLIN
     * 
     * @param       aMetadataFilename
     * 
     * @return
     */
    public Environment unmarshal( File aMetadataFile )
    {
        assert aMetadataFile != null;

        try ( Reader myReader = new FileReader( aMetadataFile ) )
        {
            return (Environment) unmarshaller.unmarshal( myReader );
        }
        catch ( Exception myException )
        {
            throw new EnvironmentConfigurationException( "unable to load environment metadata file " +
                                                             aMetadataFile.getName()                 +
                                                             ": "                                    +
                                                             myException.getMessage(),
                                                         myException                                   );
        }
    }
}
