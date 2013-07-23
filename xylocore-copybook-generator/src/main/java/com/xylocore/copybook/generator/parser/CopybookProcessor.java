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


package com.xylocore.copybook.generator.parser;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.xylocore.copybook.generator.parser.CopybookLexer;
import com.xylocore.copybook.generator.parser.CopybookParser;
import com.xylocore.copybook.generator.domain.Copybook;
import com.xylocore.copybook.generator.domain.config.Environment;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookProcessor
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     * 
     * @return
     */
    public Copybook process( Environment aEnvironment )
    {
        try ( InputStream myInputStream = openCopybookSource( aEnvironment ) )
        {
            CopybookInputStream            myCIS         = new CopybookInputStream( myInputStream,
                                                                                    aEnvironment.getRightMarginLimit() );
            ANTLRInputStream               myInput       = new ANTLRInputStream( myCIS );
            CopybookLexer                  myLexer       = new CopybookLexer( myInput );
            CommonTokenStream              myTokenStream = new CommonTokenStream( myLexer );
            CopybookParser                 myParser      = new CopybookParser( myTokenStream );
            ParseTree                      myParseTree   = myParser.copybook();
            ParseTreeWalker                myWalker      = new ParseTreeWalker();
            CopybookTransformationListener myListener    = new CopybookTransformationListener( aEnvironment );
            
            myWalker.walk( myListener, myParseTree );
            
            return myListener.getCopybook();
        }
        catch ( IOException myIOException )
        {
            // TODO: implement better exception processing
            throw new RuntimeException( myIOException );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     * 
     * @return
     * 
     * @exception   IOException
     */
    private InputStream openCopybookSource( Environment aEnvironment )
            throws IOException
    {
        assert aEnvironment != null;
        
        String      myFilename     = aEnvironment.getCopybookFilename();
        InputStream myInputStream;
        
        if ( myFilename.equals( "-" ) )
        {
            myInputStream = System.in;
        }
        else
        {
            Path myInputFile = Paths.get( myFilename );

            if ( myInputFile.getParent() == null )
            {
                if ( ! Files.isRegularFile( myInputFile ) )
                {
                    String myMetadataFilename = aEnvironment.getMetadataFilename();
                    if ( myMetadataFilename != null )
                    {
                        Path myMetadataPath = Paths.get( myMetadataFilename ).getParent();
                        if ( myMetadataPath != null )
                        {
                            myInputFile = myMetadataPath.resolve( myFilename );
                        }
                    }
                }
            }

            myInputStream = Files.newInputStream( myInputFile );
        }

        return myInputStream;
    }
}
