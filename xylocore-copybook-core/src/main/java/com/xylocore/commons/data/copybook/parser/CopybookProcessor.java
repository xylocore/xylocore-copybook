package com.xylocore.commons.data.copybook.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.data.copybook.domain.Copybook;
import com.xylocore.commons.data.copybook.domain.config.Environment;


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
            
            Copybook myCopybook = myListener.getCopybook();
            
            return myCopybook;
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
            File myInputFile = new File( myFilename );
            
            if ( StringUtils.isBlank( FilenameUtils.getFullPath( myFilename ) ) )
            {
                if ( ! myInputFile.isFile() )
                {
                    String myMetadataFilename = aEnvironment.getMetadataFilename();
                    if ( myMetadataFilename != null )
                    {
                        String myMetadataPath = FilenameUtils.getFullPath( myMetadataFilename );
                        if ( StringUtils.isNotBlank( myMetadataPath ) )
                        {
                            myInputFile = new File( myMetadataPath, myFilename );
                        }
                    }
                }
            }
            
            myInputStream = new FileInputStream( myInputFile );
        }

        return myInputStream;
    }
}
