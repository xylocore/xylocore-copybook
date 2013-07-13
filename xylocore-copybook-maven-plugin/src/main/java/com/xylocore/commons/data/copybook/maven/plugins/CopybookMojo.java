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
