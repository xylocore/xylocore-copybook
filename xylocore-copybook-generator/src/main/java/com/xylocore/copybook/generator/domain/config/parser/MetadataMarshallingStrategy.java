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

package com.xylocore.copybook.generator.domain.config.parser;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.xylocore.copybook.generator.EnvironmentConfigurationException;
import com.xylocore.copybook.generator.domain.config.Metadata;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class MetadataMarshallingStrategy
{
    //
    // Members
    //
    
    
    private static final MetadataMarshallingStrategy    instance                    = new MetadataMarshallingStrategy();
    
    private JAXBContext                                 jaxbContext;
    private Unmarshaller                                unmarshaller;
    private EnvironmentConfigurationException           initializationException;

    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    private MetadataMarshallingStrategy()
    {
        try
        {
            jaxbContext  = JAXBContext.newInstance( Metadata.class );
            unmarshaller = jaxbContext.createUnmarshaller();
        }
        catch ( Exception myException )
        {
            initializationException =
                    new EnvironmentConfigurationException( "cannot initialize the copybook metadata JAXB context: " +
                                                               myException.getMessage(),
                                                           myException                                                );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public static MetadataMarshallingStrategy getInstance()
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
     * @param       aMetadataFilename
     * 
     * @return
     */
    public Metadata unmarshal( File aMetadataFile )
    {
        if ( aMetadataFile == null )
        {
            throw new IllegalArgumentException( "a copybook metadata filemust be specified" );
        }

        try
        {
            return (Metadata) unmarshaller.unmarshal( aMetadataFile );
        }
        catch ( Exception myException )
        {
            throw new EnvironmentConfigurationException( "unable to load copybook metadata file " +
                                                             aMetadataFile.getName()              +
                                                             ": "                                 +
                                                             myException.getMessage(),
                                                         myException                                );
        }
    }
}
