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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.copybook.generator.domain.Copybook;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.config.Environment;
import com.xylocore.copybook.generator.domain.config.EnvironmentConfigurationException;
import com.xylocore.copybook.generator.emit.CopybookClassEmitter;
import com.xylocore.copybook.generator.parser.CopybookProcessor;
import com.xylocore.copybook.generator.visitor.CopybookNormalizationVisitor;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookClassGenerator
{
    //
    // Members
    //
    
    
    private Environment             environment;
    private Copybook                copybook;
    private List<Element>           elementsOfInterest;
    private Set<Element>            excludedElements;
    private CopybookClassEmitter    copybookClassEmitter;
    
    
    

    //
    // Instance initialization
    //
    
    
    {
        elementsOfInterest   = new ArrayList<>();
        excludedElements     = new HashSet<>();
        copybookClassEmitter = new CopybookClassEmitter();
    }
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public CopybookClassGenerator( Environment aEnvironment )
    {
        if ( aEnvironment == null )
        {
            throw new IllegalArgumentException( "a environment must be specified" );
        }
        
        environment = aEnvironment;
    }
    
    
    /**
     * FILLIN
     */
    public void generate()
    {
        validateEnvironment();

        try
        {
            processCopybook();
            
            String myPackageName             = environment.getPackageName();
            String myClassName               = environment.getClassName();
            File   myGenerationRootDirectory = new File( environment.getGenerationRootDirectory() );
            
            File myOutputDirectory =
                    StringUtils.isNotEmpty( myPackageName )
                        ? new File( myGenerationRootDirectory, myPackageName.replace( '.', File.separatorChar ) )
                        : myGenerationRootDirectory;
            myOutputDirectory.mkdirs();
            
            File myOutputFile = new File( myOutputDirectory, myClassName + ".java" );
    
            try ( FileWriter myWriter = new FileWriter( myOutputFile ) )
            {
                selectElementsOfInterest();
                
                copybookClassEmitter.generate( environment, copybook, elementsOfInterest, myWriter );
            }
            catch ( IOException myIOException )
            {
                // TODO: throw an appropriate exception
            }
        }
        catch ( Exception myException )
        {
            // TODO: throw an appropriate exception
            throw new RuntimeException( myException.getMessage(), myException );
        }
    }
    

    /**
     * FILLIN
     */
    private void processCopybook()
    {
        parseCopybook();
        normalizeCopybook();
    }
    
    
    /**
     * FILLIN
     */
    private void parseCopybook()
    {
        try
        {
            CopybookProcessor myProcessor = new CopybookProcessor();
            copybook = myProcessor.process( environment );
        }
        catch ( Exception myException )
        {
            // TODO: throw an appropriate exception
            throw new RuntimeException( myException );
        }
    }
    

    /**
     * FILLIN
     */
    private void normalizeCopybook()
    {
        CopybookNormalizationVisitor myVisitor = new CopybookNormalizationVisitor( environment );
        myVisitor.normalize( copybook );
    }
    
    
    /**
     * FILLIN
     */
    private void validateEnvironment()
    {
        assert environment != null;
        
        String myGenerationRootDirectoryName = environment.getGenerationRootDirectory();
        if ( myGenerationRootDirectoryName == null )
        {
            throw new EnvironmentConfigurationException( "a generation root directory cannot be null" );
        }
        
        File myGenerationRootDirectory = new File( myGenerationRootDirectoryName );
        
        if
        (
            myGenerationRootDirectory != null         &&
            myGenerationRootDirectory.exists()        &&
            ! myGenerationRootDirectory.isDirectory()
        )
        {
            throw new EnvironmentConfigurationException( "the specified generation root directory (" +
                                                         myGenerationRootDirectory.getPath()         +
                                                         ") exists but is not a directory"             );
        }
        
        // TODO: implement validation
    }
    
    
    /**
     * FILLIN
     */
    private void selectElementsOfInterest()
    {
        elementsOfInterest.clear();
        excludedElements  .clear();
        
        checkElement( copybook );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    private void checkElement( Element aElement )
    {
        if
        (
            aElement instanceof DataElement                               &&
            ! excludedElements.contains( aElement )                       &&
            ! ((DataElement) aElement).getAccessorMethodInfos().isEmpty()
        )
        {
            elementsOfInterest.add( aElement );
        }

        if ( aElement.getFirstChild() != null )
        {
            for ( Element myChild = aElement.getFirstChild() ; myChild != null ; myChild = myChild.getNextSibling() )
            {
                checkElement( myChild );
            }
        }
    }
}
