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


package com.xylocore.copybook.maven.plugins;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.compiler.util.scan.InclusionScanException;
import org.codehaus.plexus.compiler.util.scan.SimpleSourceInclusionScanner;
import org.codehaus.plexus.compiler.util.scan.SourceInclusionScanner;
import org.codehaus.plexus.compiler.util.scan.mapping.SourceMapping;
import org.codehaus.plexus.compiler.util.scan.mapping.SuffixMapping;

import com.xylocore.copybook.generator.CopybookClassGenerator;
import com.xylocore.copybook.generator.Environment;
import com.xylocore.copybook.generator.EnvironmentConfigurator;

//import com.xylocore.commons.data.copybook.generator.CopybookClassGenerator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

@Mojo( name                         = "cbgen",
       defaultPhase                 = LifecyclePhase.GENERATE_SOURCES,
       requiresDependencyResolution = ResolutionScope.COMPILE,
       requiresProject              = true                             )
public class CopybookMojo
    extends
        AbstractMojo
{
    //
    // Members
    //

    
    private static final String COPYBOOK_DEFAULT_EXTENSION          = "cpy";
    private static final String COPYBOOK_METADATA_DEFAULT_EXTENSION = "cpymeta";
    

    /**
     * The current Maven project.
     */
    @Parameter( property = "project",
                required = true,
                readonly = true       )
    protected MavenProject project;

    
    /**
     * The directory where the copybook files ({@code *.cpy}) are located.
     */
    @Parameter( defaultValue = "${basedir}/src/main/copybooks")
    private File sourceDirectory;

    
    /**
     * Specify output directory where the Java files are generated.
     */
    @Parameter( defaultValue = "${project.build.directory}/generated-sources/copybooks" )
    private File outputDirectory;
    
    
    /**
     * Provides an explicit list of all the copybooks that should be included in
     * the generate phase of the plugin.
     * <p/>
     * A set of Ant-like inclusion patterns used to select files from the source
     * directory for processing. By default, the pattern
     * <code>**&#47;*.cpy</code> is used to select copybook files.
     */
    @Parameter
    protected Set<String> includes = new HashSet<>();

    
    /**
     * A set of Ant-like exclusion patterns used to prevent certain files from
     * being processed. By default, this set is empty such that no files are
     * excluded.
     */
    @Parameter
    protected Set<String> excludes = new HashSet<>();
    
    
    /**
     * The default extension for copybooks. By default, this is <code>cpy</code>.
     */
    @Parameter( property     = "copybook.default.extension",
                defaultValue = COPYBOOK_DEFAULT_EXTENSION    )
    private String copybookDefaultExtension;
    
    
    /**
     * The default extension for copybook metadata files. By default, this is <code>cpymeta</code>.
     */
    @Parameter( property     = "copybook.metadata.default.extension",
                defaultValue = COPYBOOK_METADATA_DEFAULT_EXTENSION    )
    private String metadataDefaultExtension;


    
    
    //
    // Class implementation
    //
    

    @Override
    public void execute()
            throws MojoExecutionException,
                   MojoFailureException
    {
        if ( getLog().isDebugEnabled() )
        {
            getLog().debug( "COPYBOOK: sourceDirectory: " + sourceDirectory );
            getLog().debug( "COPYBOOK: outputDirectory: " + outputDirectory );
        }
        
        if ( ! sourceDirectory.isDirectory() )
        {
            getLog().info( "COPYBOOK: No copybooks to process in " + sourceDirectory.getAbsolutePath() );
            return;
        }

        // Make sure that the output directory exists
        if ( ! outputDirectory.exists() )
        {
            outputDirectory.mkdirs();
        }

        // Find all of the copybooks that need to be processed
        try
        {
            List<String> myCopybooks = findCopybookFiles( sourceDirectory );

            getLog().info( "COPYBOOK: processing source directory " + sourceDirectory.getAbsolutePath() );
    
            if ( ! myCopybooks.isEmpty() )
            {
                for ( String myCopybook : myCopybooks )
                {
                    generateCopybook( myCopybook );
                }
            }
            else
            {
                getLog().info( "COPYBOOK: No copybooks to process" );
            }
            
            if ( project != null )
            {
                // Tell Maven that there are some new source files underneath the output directory.
                addSourceRoot( outputDirectory );
            }
        }
        catch ( InclusionScanException myInclusionScanException )
        {
            getLog().error( "COPYBOOK: Fatal error occured while searching for the copybooks to process", myInclusionScanException );
            
            throw new MojoExecutionException( "Fatal error occured while searching for the copybooks to process", myInclusionScanException );
        }
    }


    /**
     * FILLIN
     * 
     * @param       aSourceDirectory
     * 
     * @return
     * 
     * @throws      InclusionScanException
     */
    private List<String> findCopybookFiles( File aSourceDirectory )
            throws InclusionScanException
    {
        String myCopybookExtension = getCopybookDefaultExtension();
        
        // Which files under the source set should we be looking for as copybook files
        SourceMapping mySourceMapping =
                new SuffixMapping( myCopybookExtension,
                                   Collections.<String>emptySet() );

        // What are the sets of includes (defaulted or otherwise).
        Set<String> myCalculatedIncludes = includes;
        if ( myCalculatedIncludes == null || myCalculatedIncludes.isEmpty() )
        {
            myCalculatedIncludes = Collections.singleton( "**/*." + myCopybookExtension );
        }

        SourceInclusionScanner myScanner = new SimpleSourceInclusionScanner( myCalculatedIncludes, excludes );
        myScanner.addSourceMapping( mySourceMapping );
        
        Set<File>    myCopybookFiles = myScanner.getIncludedSources( aSourceDirectory, null );
        List<String> myCopybooks     = new ArrayList<>();
        
        for ( File myCopybookFile : myCopybookFiles )
        {
            getLog().debug( "COPYBOOK: copybook file '" + myCopybookFile.getPath() + "' detected." );
            
            String myRelativePathBase = findSourceSubdirectory( aSourceDirectory, myCopybookFile.getPath() );
            String myRelativePath     = myRelativePathBase + myCopybookFile.getName();
            
            getLog().debug( "COPYBOOK:    ... relative path is: " + myRelativePath );
            
            myCopybooks.add( myRelativePath );
        }

        return myCopybooks;
    }
    
    
    /**
     * Given the source directory and the full path to a copybook,
     * produce the path to the named copybook in relative terms to the
     * {@code aSourceDirectory}. This will then allow CBGEN to produce output
     * relative to the base of the output directory and reflect the input
     * organization of the copybooks.
     *
     * @param       aSourceDirectory
     *                  The source directory {@link File} object.
     * @param       aCopybookFilename
     *                  The full path to the input copybook file.
     *                  
     * @return      The path to the copybook file relative to the source directory
     */
    private String findSourceSubdirectory( File     aSourceDirectory,
                                           String   aCopybookFilename  )
    {
        String mySourcePath = aSourceDirectory.getPath() + File.separator;

        if ( ! aCopybookFilename.startsWith( mySourcePath ) )
        {
            throw new IllegalArgumentException( "expected "             +
                                                aCopybookFilename       +
                                                " to be prefixed with " +
                                                aSourceDirectory          );
        }

        File   myUnprefixedCopybookFile = new File( aCopybookFilename.substring( mySourcePath.length() ) );
        String myParent                 = myUnprefixedCopybookFile.getParent();

        return ( myParent != null ? myParent + File.separator : "" );
    }

    
    /**
     * FILLIN
     * 
     * @param       aCopybookFilename
     */
    private void generateCopybook( String aCopybookFilename )
    {
        File   myCopybookFile     = new File( sourceDirectory, aCopybookFilename );
        String myCopybookPath     = myCopybookFile.getAbsolutePath();
        File   myMetadataFile     = generateMetadataFile( myCopybookFile );
        String myMetadataFilename = myMetadataFile.getAbsolutePath();
        String myGenRootDirectory = outputDirectory.getAbsolutePath();
        
        String myClassName   = aCopybookFilename;
        String myPackageName = null;
        
        int myIndex = myClassName.lastIndexOf( File.separatorChar );
        if ( myIndex != -1 )
        {
            myClassName   = aCopybookFilename.substring( myIndex+1 );
            myPackageName = aCopybookFilename.substring( 0, myIndex );
            myPackageName = myPackageName.replace( File.separatorChar, '.' );
        }
        
        myIndex = myClassName.lastIndexOf( '.' );
        if ( myIndex != -1 )
        {
            myClassName = myClassName.substring( 0, myIndex );
            myClassName = myClassName.replace( ".", "" );
        }

        myClassName = myClassName.substring( 0, 1 ).toUpperCase() + myClassName.substring( 1 );
        if ( myPackageName != null )
        {
            myClassName = myPackageName + "." + myClassName;
        }
        
        Map<String,String> myCliProperties = new HashMap<>();
        myCliProperties.put( EnvironmentConfigurator.CLASS_NAME_KEY         , myClassName        );
        myCliProperties.put( EnvironmentConfigurator.GENERATION_ROOT_DIR_KEY, myGenRootDirectory );
        myCliProperties.put( EnvironmentConfigurator.COPYBOOK_FILENAME_KEY  , myCopybookPath     );

        boolean myMetadataFileExists = myMetadataFile.isFile();
        
        if ( myMetadataFileExists )
        {
            myCliProperties.put( EnvironmentConfigurator.METADATA_FILENAME_KEY, myMetadataFilename );
        }
        
        getLog().info( "COPYBOOK: processing copybook " + myCopybookPath );
        
        if ( getLog().isDebugEnabled() )
        {
            getLog().debug( "COPYBOOK:             class name: " + myClassName        );
            getLog().debug( "COPYBOOK:           gen root dir: " + myGenRootDirectory );
            getLog().debug( "COPYBOOK:      copybook filename: " + myCopybookPath     );
            
            getLog().debug( "COPYBOOK:      metadata filename: "                   +
                            myMetadataFilename                                     +
                            " ("                                                   +
                            ( myMetadataFileExists ? "exists" : "does not exist" ) +
                            ")"                                                      );
        }
        
        EnvironmentConfigurator myConfigurator = new EnvironmentConfigurator();
        myConfigurator.setOverrideProperties( myCliProperties );
        
        Environment            myEnvironment = myConfigurator.configure();
        CopybookClassGenerator myGenerator   = new CopybookClassGenerator( myEnvironment );
        
        myGenerator.generate();
    }

    
    /**
     * FILLIN
     * 
     * @return
     */
    private String getCopybookDefaultExtension()
    {
        String myExtension = copybookDefaultExtension;
        
        if ( Utils.isBlank( myExtension ) )
        {
            myExtension = COPYBOOK_DEFAULT_EXTENSION;
        }
        
        return myExtension;
    }

    
    /**
     * FILLIN
     * 
     * @param       aCopybookFile
     * 
     * @return
     */
    private File generateMetadataFile( File aCopybookFile )
    {
        String myMetadataExtension = getCopybookMetadataDefaultExtension();
        String myBaseFilename      = aCopybookFile.getName();
        int    myIndex             = myBaseFilename.lastIndexOf( '.' );
        
        if ( myIndex != -1 )
        {
            myBaseFilename = myBaseFilename.substring( 0, myIndex );
        }
        
        String myMetadataFilename = myBaseFilename + "." + myMetadataExtension;
        
        return new File( aCopybookFile.getParent(), myMetadataFilename );
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    private String getCopybookMetadataDefaultExtension()
    {
        String myExtension = metadataDefaultExtension;
        
        if ( Utils.isBlank( myExtension ) )
        {
            myExtension = COPYBOOK_METADATA_DEFAULT_EXTENSION;
        }
        
        return myExtension;
    }


    /**
     * FILLIN
     * 
     * @param       aOutputDirectory
     */
    private void addSourceRoot( File aOutputDirectory )
    {
        project.addCompileSourceRoot( aOutputDirectory.getPath() );
    }
}
