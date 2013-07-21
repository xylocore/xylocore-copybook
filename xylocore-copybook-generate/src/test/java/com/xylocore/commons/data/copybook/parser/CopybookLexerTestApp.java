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
