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


package com.xylocore.copybook.runtime;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.xylocore.copybook.runtime.StringConstantValue;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class StringConstantValueUnitTest
{
    //
    // Class implementation
    //
    

    @Test
    public void singleValueWithNoWhitespace()
    {
        StringConstantValue myConstantValue = new StringConstantValue( "CCC" );
        
        assertEquals(  1, myConstantValue.compareTo( "AAA" ) );
        assertEquals(  0, myConstantValue.compareTo( "CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "EEE" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "CC"   ) );
        assertEquals( -1, myConstantValue.compareTo( "CCCC" ) );
    }
    
    
    @Test
    public void testConstantCharacterString()
    {
        StringConstantValue myConstantValue = new StringConstantValue( 'C', 3 );
        
        assertEquals(  1, myConstantValue.compareTo( "AAA" ) );
        assertEquals(  0, myConstantValue.compareTo( "CCC" ) );
        assertEquals( -1, myConstantValue.compareTo( "EEE" ) );
        
        assertEquals(  1, myConstantValue.compareTo( "CC"   ) );
        assertEquals( -1, myConstantValue.compareTo( "CCCC" ) );
    }
    
    
    @Test
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
    
    
    @Test
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
    
    
    @Test
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
    
    
    @Test
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
