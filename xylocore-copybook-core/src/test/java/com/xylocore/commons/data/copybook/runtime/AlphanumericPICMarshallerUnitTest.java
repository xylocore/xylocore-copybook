package com.xylocore.commons.data.copybook.runtime;

import com.xylocore.commons.data.copybook.runtime.marshallers.impl.AlphanumericPICMarshallerImpl;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AlphanumericPICMarshallerUnitTest
    extends
        AbstractPICMarshallerUnitTest
{
    //
    // Members
    //
    

    private static final AlphanumericPICMarshallerImpl marshaller = AlphanumericPICMarshallerImpl.getInstance();

    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     */
    public void testStringMarshalling()
    {
        performTest( 5,
                     AlphanumericPICFlags.DEFAULT,
                     "ABC",
                     "ABC"                         );
        
        performTest( 5,
                     AlphanumericPICFlags.DISPLAY                  |
                         AlphanumericPICFlags.LEFT_JUSTIFY         |
                         AlphanumericPICFlags.MAINTAIN_WHITESPACE,
                     "ABC",
                     "ABC  "                                         );
        
        performTest( 5,
                     AlphanumericPICFlags.DISPLAY              |
                         AlphanumericPICFlags.RIGHT_JUSTIFY    |
                         AlphanumericPICFlags.TRIM_WHITESPACE,
                     "ABC",
                     "ABC"                                       );
   
        performTest( 5,
                     AlphanumericPICFlags.DISPLAY                  |
                         AlphanumericPICFlags.RIGHT_JUSTIFY        |
                         AlphanumericPICFlags.MAINTAIN_WHITESPACE,
                     "ABC",
                     "  ABC"                                         );
        
        performTest( 5,
                     AlphanumericPICFlags.DEFAULT,
                     "ABCDEFGH",
                     "ABCDE"                       );
   
        performTest( 5,
                     AlphanumericPICFlags.DISPLAY                  |
                         AlphanumericPICFlags.LEFT_JUSTIFY         |
                         AlphanumericPICFlags.MAINTAIN_WHITESPACE,
                     "ABCDEFGH",
                     "ABCDE"                                         );
       
        performTest( 5,
                     AlphanumericPICFlags.DISPLAY              |
                         AlphanumericPICFlags.RIGHT_JUSTIFY    |
                         AlphanumericPICFlags.TRIM_WHITESPACE,
                     "ABCDEFGH",
                     "DEFGH"                                     );
    
        performTest( 5,
                     AlphanumericPICFlags.DISPLAY                  |
                         AlphanumericPICFlags.RIGHT_JUSTIFY        |
                         AlphanumericPICFlags.MAINTAIN_WHITESPACE,
                     "ABCDEFGH",
                     "DEFGH"                                         );
    }
    

    /**
     * FILLIN
     * 
     * @param       aDigits
     * @param       aSignType
     * @param       aPrecision
     * @param       aScalingPosition
     * @param       aEncodeValue
     * @param       aDecodeValue
     */
    private void performTest( int        aSize,
                              int        aFlags,
                              String     aEncodeValue,
                              String     aDecodeValue      )
    {
        marshaller.encodeAsString( context, 0, aEncodeValue, aSize, aFlags, null );
        assertEquals( aDecodeValue, marshaller.decodeAsString( context, 0, aSize, aFlags, null ) );
    }
}
