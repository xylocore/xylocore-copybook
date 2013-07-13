package com.xylocore.commons.data.copybook.runtime;

import junit.framework.TestCase;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class StringConstantValueUnitTest
    extends
        TestCase
{
    //
    // Class implementation
    //
    
    
    public void testSingleValueWithNoWhitespace()
    {
        StringConstantValue myConstantValue = new StringConstantValue( "CCC" );
        
        assertEquals(  1, myConstantValue.compareTo( "AAA" ) );
        assertEquals(  0, myConstantValue.compareTo( "CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "EEE" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "CC"   ) );
        assertEquals( -1, myConstantValue.compareTo( "CCCC" ) );
    }
    
    
    public void testConstantCharacterString()
    {
        StringConstantValue myConstantValue = new StringConstantValue( 'C', 3 );
        
        assertEquals(  1, myConstantValue.compareTo( "AAA" ) );
        assertEquals(  0, myConstantValue.compareTo( "CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "EEE" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "CC"   ) );
        assertEquals( -1, myConstantValue.compareTo( "CCCC" ) );
    }
    
    
    public void testSingleValueWithTrailingWhitespace()
    {
        StringConstantValue myConstantValue = new StringConstantValue( "CCC  " );
        
        assertEquals(  1, myConstantValue.compareTo( "AAA" ) );
        assertEquals(  0, myConstantValue.compareTo( "CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "EEE" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "AAA    " ) );
        assertEquals(  0, myConstantValue.compareTo( "CCC    " ) );
        assertEquals( -1, myConstantValue.compareTo( "EEE    " ) );
        
        assertEquals(  1, myConstantValue.compareTo( "CC    "   ) );
        assertEquals( -1, myConstantValue.compareTo( "CCCC    " ) );
        
        assertEquals(  1, myConstantValue.compareTo( "CC"   ) );
        assertEquals( -1, myConstantValue.compareTo( "CCCC" ) );
    }
    
    
    public void testSingleValueWithLeadingWhitespace()
    {
        StringConstantValue myConstantValue = new StringConstantValue( "  CCC" );
        
        assertEquals( -1, myConstantValue.compareTo( "AAA" ) );
        assertEquals( -1, myConstantValue.compareTo( "CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "EEE" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "  AAA" ) );
        assertEquals(  0, myConstantValue.compareTo( "  CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "  EEE" ) );
        
        assertEquals( -1, myConstantValue.compareTo( "CC    "   ) );
        assertEquals( -1, myConstantValue.compareTo( "CCCC    " ) );
        
        assertEquals(  1, myConstantValue.compareTo( "  CC"   ) );
        assertEquals( -1, myConstantValue.compareTo( "  CCCC" ) );
    }
    
    
    public void testSingleValueWithLeadingAndTrailingWhitespace()
    {
        StringConstantValue myConstantValue = new StringConstantValue( "  CCC  " );
        
        assertEquals( -1, myConstantValue.compareTo( "AAA" ) );
        assertEquals( -1, myConstantValue.compareTo( "CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "EEE" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "  AAA" ) );
        assertEquals(  0, myConstantValue.compareTo( "  CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "  EEE" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "  AAA    " ) );
        assertEquals(  0, myConstantValue.compareTo( "  CCC    " ) );
        assertEquals( -1, myConstantValue.compareTo( "  EEE    " ) );
        
        assertEquals( -1, myConstantValue.compareTo( "CC  "   ) );
        assertEquals( -1, myConstantValue.compareTo( "CCCC  " ) );
        
        assertEquals(  1, myConstantValue.compareTo( "  CC"   ) );
        assertEquals( -1, myConstantValue.compareTo( "  CCCC" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "  CC    "   ) );
        assertEquals( -1, myConstantValue.compareTo( "  CCCC    " ) );
    }
    
    
    public void testRangeValueWithNoWhitespace()
    {
        StringConstantValue myConstantValue = new StringConstantValue( "CCC", "EEE" );
        
        assertEquals(  1, myConstantValue.compareTo( "AAA" ) );
        assertEquals(  0, myConstantValue.compareTo( "CCC" ) );
        assertEquals(  0, myConstantValue.compareTo( "DDD" ) );
        assertEquals(  0, myConstantValue.compareTo( "EEE" ) );
        assertEquals( -1, myConstantValue.compareTo( "GGG" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "CC"   ) );
        assertEquals(  0, myConstantValue.compareTo( "CCCC" ) );
        assertEquals(  0, myConstantValue.compareTo( "DD"   ) );
        assertEquals(  0, myConstantValue.compareTo( "DDDD" ) );
        assertEquals(  0, myConstantValue.compareTo( "EE"   ) );
        assertEquals( -1, myConstantValue.compareTo( "EEEE" ) );
    }
}
