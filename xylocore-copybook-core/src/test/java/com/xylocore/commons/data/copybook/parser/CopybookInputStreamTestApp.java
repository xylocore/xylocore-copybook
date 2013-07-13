package com.xylocore.commons.data.copybook.parser;

import java.io.EOFException;
import java.io.FileInputStream;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class CopybookInputStreamTestApp
{
    //
    // Nested interfaces
    //
    
    
//    public static interface TestLocationManipulator
//        extends
//            LocationManipulator
//    {
//        public void newline();
//    }

    
    
    
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
            CopybookInputStream myCIS =
                    new CopybookInputStream( new FileInputStream( args[0] ),
                                             72                              );

            try
            {
                int myChar;
                
                while ( (myChar = myCIS.read()) != -1 )
                {
                    System.out.write( myChar );
                }
            }
            catch ( EOFException myEOFException )
            {
            }
        }
        catch ( Exception myException )
        {
            myException.printStackTrace();
        }
    }
}
