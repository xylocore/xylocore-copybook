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


package com.xylocore.commons.data.copybook.generator;

import java.util.HashMap;
import java.util.Map;

import com.xylocore.commons.data.copybook.domain.config.Environment;
import com.xylocore.commons.data.copybook.domain.config.EnvironmentConfigurator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookClassGeneratorApplication
{
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       args
     */
    public static void main( String[] args )
    {
        try
        {
            Map<String,String> myProperties = parseArguments( args );
            
            EnvironmentConfigurator myConfigurator = new EnvironmentConfigurator();
            myConfigurator.setOverrideProperties( myProperties );
            
            Environment            myEnvironment = myConfigurator.configure();
            CopybookClassGenerator myGenerator   = new CopybookClassGenerator( myEnvironment );
            
            myGenerator.generate();
        }
        catch ( Exception myException )
        {
            // TODO: better error handling
            myException.printStackTrace();
        }
    }
    

    /**
     * FILLIN
     * 
     * @param       args
     * 
     * @return
     */
    private static Map<String,String> parseArguments( String[] args )
    {
        int                myArgCount   = args.length;
        Map<String,String> myProperties = new HashMap<String,String>();
        int                myIndex      = 0;
        
        while ( myIndex < myArgCount )
        {
            String myArg = args[myIndex];
            
            if ( ! myArg.startsWith( "-" ) )
            {
                break;
            }
            
            if ( myArg.equals( "-package" ) )
            {
                myProperties.put( EnvironmentConfigurator.PACKAGE_NAME_KEY, args[myIndex+1] );
                myIndex += 2;
            }
            else if ( myArg.equals( "-class" ) )
            {
                myProperties.put( EnvironmentConfigurator.CLASS_NAME_KEY, args[myIndex+1] );
                myIndex += 2;
            }
            else if ( myArg.equals( "-genrootdir" ) )
            {
                myProperties.put( EnvironmentConfigurator.GENERATION_ROOT_DIR_KEY, args[myIndex+1] );
                myIndex += 2;
            }
            else if ( myArg.equals( "-metafile" ) )
            {
                myProperties.put( EnvironmentConfigurator.ENVIRONMENT_METADATA_FILENAME_KEY, args[myIndex+1] );
                myIndex += 2;
            }
            else if ( myArg.equals( "-implrecname" ) )
            {
                myProperties.put( EnvironmentConfigurator.IMPLICIT_RECORD_NAME_KEY, args[myIndex+1] );
                myIndex += 2;
            }
            else
            {
                throw new RuntimeException( "bad argument: " + myArg );
            }
        }

        if ( myIndex < myArgCount )
        {
            myProperties.put( EnvironmentConfigurator.COPYBOOK_FILENAME_KEY, args[myIndex] );
            myIndex++;
        }
        
        if ( myIndex < myArgCount )
        {
            // TODO: too many arguments
        }
        
        return myProperties;
    }
}
