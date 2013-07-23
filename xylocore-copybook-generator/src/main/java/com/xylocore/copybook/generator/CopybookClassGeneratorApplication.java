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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.xylocore.commons.util.AbstractApplication;
import com.xylocore.copybook.generator.domain.config.Environment;
import com.xylocore.copybook.generator.domain.config.EnvironmentConfigurator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookClassGeneratorApplication
    extends
        AbstractApplication
{
    //
    // Members
    //
    
    
    private Map<String,String> cliProperties;

    
    
    
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
        CopybookClassGeneratorApplication myApplication = new CopybookClassGeneratorApplication();
        myApplication.run( args );
    }
    

    @Override
    protected void buildOptions( Options aOptions )
    {
        aOptions.addOption( "p", "package"    , true, "the package for the generated copybook class" );
        aOptions.addOption( "c", "class"      , true, "the name of the copybook class"               );
        aOptions.addOption( "g", "genrootdir" , true, "the generation root directory"                );
        aOptions.addOption( "m", "metafile"   , true, "the environment metafile filename"            );
        aOptions.addOption( "i", "implrecname", true, "the implicit record name"                     );
    }
                              
    
    @Override
    protected void processArguments( CommandLine aCommandLine )
            throws ParseException
    {
        cliProperties = new HashMap<>();

        checkOption( aCommandLine, "package"    , EnvironmentConfigurator.PACKAGE_NAME_KEY                  );
        checkOption( aCommandLine, "class"      , EnvironmentConfigurator.CLASS_NAME_KEY                    );
        checkOption( aCommandLine, "genrootdir" , EnvironmentConfigurator.GENERATION_ROOT_DIR_KEY           );
        checkOption( aCommandLine, "metafile"   , EnvironmentConfigurator.ENVIRONMENT_METADATA_FILENAME_KEY );
        checkOption( aCommandLine, "implrecname", EnvironmentConfigurator.IMPLICIT_RECORD_NAME_KEY          );
        
        @SuppressWarnings( "unchecked" )
        List<String> myArgList = aCommandLine.getArgList();
        
        if (  myArgList.size() == 0 )
        {
            throw new ParseException( "missing arguments" );
        }
        else if ( myArgList.size() > 1 )
        {
            throw new ParseException( "too many arguments" );
        }
        
        cliProperties.put( EnvironmentConfigurator.COPYBOOK_FILENAME_KEY, myArgList.get( 0 ) );
    }


    /**
     * FILLIN
     * 
     * @param       aCommandLine
     * @param       aOptionName
     * @param       aKey
     */
    private void checkOption( CommandLine   aCommandLine,
                              String        aOptionName,
                              String        aKey          )
    {
        if ( aCommandLine.hasOption( aOptionName ) )
        {
            cliProperties.put( aKey, aCommandLine.getOptionValue( aOptionName ) );
        }
    }
    

    @Override
    protected void runImpl()
            throws Exception
    {
        EnvironmentConfigurator myConfigurator = new EnvironmentConfigurator();
        myConfigurator.setOverrideProperties( cliProperties );
        
        Environment            myEnvironment = myConfigurator.configure();
        CopybookClassGenerator myGenerator   = new CopybookClassGenerator( myEnvironment );
        
        myGenerator.generate();
    }
}
