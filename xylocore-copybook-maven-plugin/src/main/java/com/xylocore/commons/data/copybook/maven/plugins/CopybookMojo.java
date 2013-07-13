package com.xylocore.commons.data.copybook.maven.plugins;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.MavenProjectHelper;

import com.xylocore.commons.data.copybook.generator.CopybookClassGenerator;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookMojo
    extends
        AbstractMojo
{
    //
    // Members
    //


    /**
     * Project instance.
     *
     * @parameter   default-value="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;


    /**
     * FILLIN
     *
     * @component
     */
    private MavenProjectHelper projectHelper;


    /**
     * Root directory for outputting the generated classes.
     *
     * @parameter
     */
    private File generationRootDirectory;
    
    
    
    
    //
    // Class implementation
    //
    
    
    public void execute()
            throws MojoExecutionException,
                   MojoFailureException
    {
        CopybookClassGenerator myGenerator = new CopybookClassGenerator();
        myGenerator.setGenerationRootDirectory( generationRootDirectory );
        
    }
}
