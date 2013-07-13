package com.xylocore.commons.data.copybook.parser;

import java.io.FileInputStream;

import com.xylocore.commons.data.copybook.parser.CopybookLexer;
import com.xylocore.commons.data.copybook.parser.CopybookParser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookLexerTestApp
{
    //
    // Nested interfaces
    //
    
    
    public static interface TestLocationManipulator
        extends
            LocationManipulator
    {
        public void newline();
    }

    
    
    
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
            FileInputStream     myIS    = new FileInputStream( args[0] );
            CopybookInputStream myCIS   = new CopybookInputStream( myIS, 72 );
            ANTLRInputStream    myInput = new ANTLRInputStream( myCIS );
            CopybookLexer       myLexer = new CopybookLexer( myInput );

            CommonTokenStream myTokenStream = new CommonTokenStream( myLexer );
            CopybookParser    myParser      = new CopybookParser( myTokenStream );

            myParser.copybook();
            
            
            
//            while ( (myToken = myLexer.nextToken()).getType() != CopybookLexer.EOF )
//            {
//                System.out.println( myToken.toString() );
//            }
        }
        catch ( Exception myException )
        {
            myException.printStackTrace();
        }
    }
}
