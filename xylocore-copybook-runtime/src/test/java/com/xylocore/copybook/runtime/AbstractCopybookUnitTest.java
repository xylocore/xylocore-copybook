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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.xylocore.copybook.runtime.AlphanumericPICFlags;
import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.CopybookError;
import com.xylocore.copybook.runtime.converters.Converter;
import com.xylocore.copybook.runtime.converters.SimpleDateFormatDateConverter;
import com.xylocore.copybook.runtime.nulleq.BlankNullEquivalentStrategy;
import com.xylocore.copybook.runtime.nulleq.ConstantNullEquivalentStrategy;
import com.xylocore.copybook.runtime.nulleq.NullEquivalentStrategy;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public abstract class AbstractCopybookUnitTest
{
    //
    // Members
    //
    
    
    protected static final int              FLAGS                               = AlphanumericPICFlags.LEFT_JUSTIFY         |
                                                                                  AlphanumericPICFlags.DISPLAY              |
                                                                                  AlphanumericPICFlags.TRIM_WHITESPACE;
    protected static final int              EXACT_FLAGS                         = AlphanumericPICFlags.LEFT_JUSTIFY         |
                                                                                  AlphanumericPICFlags.DISPLAY              |
                                                                                  AlphanumericPICFlags.MAINTAIN_WHITESPACE;
    
    private TestCopybook                    copybook;
    private CopybookContext                 context;
    private Converter                       converter;
    private BlankNullEquivalentStrategy     blankNullEquivalentStrategy;
    private ConstantNullEquivalentStrategy  constantNullEquivalentStrategy;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected TestCopybook getCopybook()
    {
        return copybook;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected CopybookContext getContext()
    {
        return context;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    protected abstract ConstantNullEquivalentStrategy createConstantNullEquivalentStrategy();
    
    
    @Before
    public void setUp()
    {
        copybook                       = new TestCopybook( 100 );
        context                        = copybook.createContext( "ASCII" );
        converter                      = new SimpleDateFormatDateConverter( "yyyyMMdd" );
        blankNullEquivalentStrategy    = BlankNullEquivalentStrategy.getInstance();
        constantNullEquivalentStrategy = createConstantNullEquivalentStrategy();
    }
    

    @Test
    public void isStringValidNoNullEquivalents()
    {
        CopybookContext myContext = getContext();
        
        myContext.setBuffer( "0000000020060701" );
        myContext.setError( null, null );
        
        assertTrue( isValidAsString( 0, 8, EXACT_FLAGS, null ) );
        assertNull( myContext.getStandardError()               );
        assertNull( myContext.getPlatformError()               );
        
        assertTrue( isValidAsString( 8, 8, EXACT_FLAGS, null ) );
        assertNull( myContext.getStandardError()               );
        assertNull( myContext.getPlatformError()               );
    }
    

    @Test
    public void isStringValidSingleNullEquivalent()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy };
        
        myContext.setBuffer( "000000009999999920060701" );
        myContext.setError( null, null );
        
        assertTrue( isValidAsString( 0, 8, EXACT_FLAGS, myNullStrategies ) );
        assertNull( myContext.getStandardError()                           );
        assertNull( myContext.getPlatformError()                           );
        
        assertTrue( isValidAsString( 8, 8, EXACT_FLAGS, myNullStrategies ) );
        assertNull( myContext.getStandardError()                           );
        assertNull( myContext.getPlatformError()                           );
        
        assertTrue( isValidAsString( 16, 8, EXACT_FLAGS, myNullStrategies ) );
        assertNull( myContext.getStandardError()                            );
        assertNull( myContext.getPlatformError()                            );
    }
    

    @Test
    public void isStringValidMultipleNullEquivalents()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy,
                                                      blankNullEquivalentStrategy     };

        myContext.setBuffer( "0000000099999999        20060701" );
        myContext.setError( null, null );

        assertTrue( isValidAsString( 0, 8, EXACT_FLAGS, myNullStrategies ) );
        assertNull( myContext.getStandardError()                           );
        assertNull( myContext.getPlatformError()                           );

        assertTrue( isValidAsString( 8, 8, EXACT_FLAGS, myNullStrategies ) );
        assertNull( myContext.getStandardError()                           );
        assertNull( myContext.getPlatformError()                           );

        assertTrue( isValidAsString( 16, 8, EXACT_FLAGS, myNullStrategies ) );
        assertNull( myContext.getStandardError()                            );
        assertNull( myContext.getPlatformError()                            );

        assertTrue( isValidAsString( 24, 8, EXACT_FLAGS, myNullStrategies ) );
        assertNull( myContext.getStandardError()                            );
        assertNull( myContext.getPlatformError()                            );
    }
    

    @Test
    public void decodeStringNoNullEquivalents()
    {
        CopybookContext myContext = getContext();
        
        myContext.setBuffer( "0000000020060701" );
        
        myContext.setError( null, null );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "00000000" );
        assertFalse ( myContext.isError()                                   );
        assertSame( myContext.getStandardError(), CopybookError.None        );
        
        myContext.setError( null, null );
        assertEquals( decodeAsString( 8, 8, EXACT_FLAGS, null ), "20060701"       );
        assertFalse ( myContext.isError()                                         );
        assertSame  ( myContext.getStandardError(), CopybookError.None            );
    }
    

    @Test
    public void decodeStringSingleNullEquivalent()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy };

        myContext.setBuffer( "000000009999999920060701" );
        
        myContext.setError( null, null );
        assertNull ( decodeAsString( 0, 8, EXACT_FLAGS, myNullStrategies )   );
        assertFalse( myContext.isError()                              );
        assertSame ( myContext.getStandardError(), CopybookError.None );

        myContext.setError( null, null );
        assertEquals( decodeAsString( 8, 8, EXACT_FLAGS, myNullStrategies ), "99999999" );
        assertFalse ( myContext.isError()                                               );
        assertSame  ( myContext.getStandardError(), CopybookError.None                  );

        myContext.setError( null, null );
        assertEquals( decodeAsString( 16, 8, EXACT_FLAGS, myNullStrategies ), "20060701" );
        assertFalse ( myContext.isError()                                                );
        assertSame  ( myContext.getStandardError(), CopybookError.None                   );
    }
    

    @Test
    public void decodeStringMultipleNullEquivalents()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy,
                                                      blankNullEquivalentStrategy     };

        myContext.setBuffer( "0000000099999999        20060701" );

        myContext.setError( null, null );
        assertNull ( decodeAsString( 0, 8, EXACT_FLAGS, myNullStrategies )   );
        assertFalse( myContext.isError()                              );
        assertSame ( myContext.getStandardError(), CopybookError.None );

        myContext.setError( null, null );
        assertEquals( decodeAsString( 8, 8, EXACT_FLAGS, myNullStrategies ), "99999999" );
        assertFalse ( myContext.isError()                                               );
        assertSame  ( myContext.getStandardError(), CopybookError.None                  );

        myContext.setError( null, null );
        assertNull ( decodeAsString( 16, 8, EXACT_FLAGS, myNullStrategies )  );
        assertFalse( myContext.isError()                              );
        assertSame ( myContext.getStandardError(), CopybookError.None );

        myContext.setError( null, null );
        assertEquals( decodeAsString( 24, 8, EXACT_FLAGS, myNullStrategies ), "20060701" );
        assertFalse ( myContext.isError()                                                );
        assertSame  ( myContext.getStandardError(), CopybookError.None                   );
    }
    

    @Test
    public void encodeStringNoNullEquivalents()
    {
        CopybookContext myContext = getContext();
        
        myContext.setBuffer( "xxxxxxxxyyyyyyyy" );
        myContext.setError( null, null );
        encodeAsString( 0, null, 8, EXACT_FLAGS | AlphanumericPICFlags.NULLS_NOT_ALLOWED, null );
        assertTrue  ( myContext.isError()                                        );
        assertSame  ( myContext.getStandardError(), CopybookError.NullNotAllowed );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "xxxxxxxx"      );
        encodeAsString( 8, null, 8, EXACT_FLAGS | AlphanumericPICFlags.NULLS_NOT_ALLOWED, null );
        assertTrue  ( myContext.isError()                                        );
        assertSame  ( myContext.getStandardError(), CopybookError.NullNotAllowed );
        assertEquals( decodeAsString( 8, 8, EXACT_FLAGS, null ), "yyyyyyyy"      );

        myContext.setBuffer( "xxxxxxxxyyyyyyyy" );
        myContext.setError( null, null );
        encodeAsString( 0, null, 8, EXACT_FLAGS | AlphanumericPICFlags.NULLS_ALLOWED, null );
        assertFalse ( myContext.isError()                                                  );
        assertSame  ( myContext.getStandardError(), CopybookError.None                     );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "        "                );
        encodeAsString( 8, null, 8, EXACT_FLAGS | AlphanumericPICFlags.NULLS_ALLOWED, null );
        assertFalse ( myContext.isError()                                                  );
        assertSame  ( myContext.getStandardError(), CopybookError.None                     );
        assertEquals( decodeAsString( 8, 8, EXACT_FLAGS, null ), "        "                );

        myContext.setBuffer( "xxxxxxxxyyyyyyyy" );
        myContext.setError( null, null );
        encodeAsString( 0, "20060701", 8, EXACT_FLAGS, null                   );
        assertFalse   ( myContext.isError()                                   );
        assertSame    ( myContext.getStandardError(), CopybookError.None      );
        assertEquals  ( decodeAsString( 0, 8, EXACT_FLAGS, null ), "20060701" );
        encodeAsString( 8, "20060701", 8, EXACT_FLAGS, null                   );
        assertFalse   ( myContext.isError()                                   );
        assertSame    ( myContext.getStandardError(), CopybookError.None      );
        assertEquals  ( decodeAsString( 8, 8, EXACT_FLAGS, null ), "20060701" );
    }
    

    @Test
    public void encodeStringSingleNullEquivalent()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy };

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, null, converter, myNullStrategies                  );
        assertFalse ( myContext.isError()                                   );
        assertSame  ( myContext.getStandardError(), CopybookError.None      );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "00000000" );

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsString( 0, "20060701", 8, EXACT_FLAGS, myNullStrategies     );
        assertFalse ( myContext.isError()                                   );
        assertSame  ( myContext.getStandardError(), CopybookError.None      );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "20060701" );
    }
    

    @Test
    public void encodeStringMultipleNullEquivalents()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy,
                                                      blankNullEquivalentStrategy     };

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsString( 0, null, 8, EXACT_FLAGS, myNullStrategies );
        assertFalse ( myContext.isError()                              );
        assertSame  ( myContext.getStandardError(), CopybookError.None );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "00000000"    );

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsString( 0, "20060701", 8, EXACT_FLAGS, myNullStrategies     );
        assertFalse ( myContext.isError()                                   );
        assertSame  ( myContext.getStandardError(), CopybookError.None      );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "20060701" );

        myNullStrategies = new NullEquivalentStrategy[] { blankNullEquivalentStrategy,
                                                          constantNullEquivalentStrategy };
        
        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, null, converter, myNullStrategies                  );
        assertFalse ( myContext.isError()                                   );
        assertSame  ( myContext.getStandardError(), CopybookError.None      );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "        " );

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsString( 0, "20060701", 8, EXACT_FLAGS, myNullStrategies       );
        assertFalse   ( myContext.isError()                                   );
        assertSame    ( myContext.getStandardError(), CopybookError.None      );
        assertEquals  ( decodeAsString( 0, 8, EXACT_FLAGS, null ), "20060701" );
    }

    
    @Test
    public void isDateValidNoNullEquivalents()
    {
        CopybookContext myContext = getContext();
        
        myContext.setBuffer( "0000000020060701" );
        myContext.setError( null, null );
        
        assertFalse( isValidAsDate( 0, converter, null ) );
        assertNull ( myContext.getStandardError()        );
        assertNull ( myContext.getPlatformError()        );
        
        assertTrue ( isValidAsDate( 8, converter, null ) );
        assertNull ( myContext.getStandardError()        );
        assertNull ( myContext.getPlatformError()        );
    }
    

    @Test
    public void isDateValidSingleNullEquivalent()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy };
        
        myContext.setBuffer( "000000009999999920060701" );
        myContext.setError( null, null );
        
        assertTrue ( isValidAsDate( 0, converter, myNullStrategies ) );
        assertNull ( myContext.getStandardError()                    );
        assertNull ( myContext.getPlatformError()                    );
        
        assertFalse( isValidAsDate( 8, converter, myNullStrategies ) );
        assertNull ( myContext.getStandardError()                    );
        assertNull ( myContext.getPlatformError()                    );
        
        assertTrue ( isValidAsDate( 16, converter, myNullStrategies ) );
        assertNull ( myContext.getStandardError()                     );
        assertNull ( myContext.getPlatformError()                     );
    }
    

    @Test
    public void isDateValidMultipleNullEquivalents()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy,
                                                      blankNullEquivalentStrategy     };

        myContext.setBuffer( "0000000099999999        20060701" );
        myContext.setError( null, null );

        assertTrue ( isValidAsDate( 0, converter, myNullStrategies ) );
        assertNull ( myContext.getStandardError()                    );
        assertNull ( myContext.getPlatformError()                    );

        assertFalse( isValidAsDate( 8, converter, myNullStrategies ) );
        assertNull ( myContext.getStandardError()                    );
        assertNull ( myContext.getPlatformError()                    );

        assertTrue ( isValidAsDate( 16, converter, myNullStrategies ) );
        assertNull ( myContext.getStandardError()                     );
        assertNull ( myContext.getPlatformError()                     );

        assertTrue ( isValidAsDate( 24, converter, myNullStrategies ) );
        assertNull ( myContext.getStandardError()                     );
        assertNull ( myContext.getPlatformError()                     );
    }
    

    @Test
    public void decodeDateNoNullEquivalents()
    {
        CopybookContext myContext = getContext();
        
        myContext.setBuffer( "0000000020060701" );
        
        myContext.setError( null, null );
        assertNull( decodeAsDate( 0, converter, null )                            );
        assertTrue( myContext.isError()                                           );
        assertSame( myContext.getStandardError(), CopybookError.InvalidDateFormat );
        
        myContext.setError( null, null );
        assertEquals( decodeAsDate( 8, converter, null ), getDate( "07-01-2006" ) );
        assertFalse ( myContext.isError()                                         );
        assertSame  ( myContext.getStandardError(), CopybookError.None            );
    }
    

    @Test
    public void decodeDateSingleNullEquivalent()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy };

        myContext.setBuffer( "000000009999999920060701" );
        
        myContext.setError( null, null );
        assertNull ( decodeAsDate( 0, converter, myNullStrategies )   );
        assertFalse( myContext.isError()                              );
        assertSame ( myContext.getStandardError(), CopybookError.None );

        myContext.setError( null, null );
        assertNull( decodeAsDate( 8, converter, myNullStrategies )                );
        assertTrue( myContext.isError()                                           );
        assertSame( myContext.getStandardError(), CopybookError.InvalidDateFormat );

        myContext.setError( null, null );
        assertEquals( decodeAsDate( 16, converter, myNullStrategies ), getDate( "07-01-2006" ) );
        assertFalse ( myContext.isError()                                                      );
        assertSame  ( myContext.getStandardError(), CopybookError.None                         );
    }
    

    @Test
    public void decodeDateMultipleNullEquivalents()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy,
                                                      blankNullEquivalentStrategy     };

        myContext.setBuffer( "0000000099999999        20060701" );

        myContext.setError( null, null );
        assertNull ( decodeAsDate( 0, converter, myNullStrategies )   );
        assertFalse( myContext.isError()                              );
        assertSame ( myContext.getStandardError(), CopybookError.None );

        myContext.setError( null, null );
        assertNull( decodeAsDate( 8, converter, myNullStrategies )                );
        assertTrue( myContext.isError()                                           );
        assertSame( myContext.getStandardError(), CopybookError.InvalidDateFormat );

        myContext.setError( null, null );
        assertNull ( decodeAsDate( 16, converter, myNullStrategies )  );
        assertFalse( myContext.isError()                              );
        assertSame ( myContext.getStandardError(), CopybookError.None );

        myContext.setError( null, null );
        assertEquals( decodeAsDate( 24, converter, myNullStrategies ), getDate( "07-01-2006" ) );
        assertFalse ( myContext.isError()                                                      );
        assertSame  ( myContext.getStandardError(), CopybookError.None                         );
    }
    

    @Test
    public void encodeDateNoNullEquivalents()
    {
        CopybookContext myContext = getContext();
        
        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, null, converter, null );
        assertTrue  ( myContext.isError()                                        );
        assertSame  ( myContext.getStandardError(), CopybookError.NullNotAllowed );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "xxxxxxxx"              );

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, getDate( "07-01-2006" ), converter, null );
        assertFalse ( myContext.isError()                              );
        assertSame  ( myContext.getStandardError(), CopybookError.None );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "20060701"    );
    }
    

    @Test
    public void encodeDateSingleNullEquivalent()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy };

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, null, converter, myNullStrategies );
        assertFalse ( myContext.isError()                              );
        assertSame  ( myContext.getStandardError(), CopybookError.None );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "00000000"    );

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, getDate( "07-01-2006" ), converter, myNullStrategies );
        assertFalse ( myContext.isError()                              );
        assertSame  ( myContext.getStandardError(), CopybookError.None );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "20060701"    );
    }
    

    @Test
    public void encodeDateMultipleNullEquivalents()
    {
        CopybookContext          myContext        = getContext();
        NullEquivalentStrategy[] myNullStrategies = { constantNullEquivalentStrategy,
                                                      blankNullEquivalentStrategy     };

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, null, converter, myNullStrategies );
        assertFalse ( myContext.isError()                              );
        assertSame  ( myContext.getStandardError(), CopybookError.None );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "00000000"    );

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, getDate( "07-01-2006" ), converter, myNullStrategies );
        assertFalse ( myContext.isError()                              );
        assertSame  ( myContext.getStandardError(), CopybookError.None );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "20060701"    );

        myNullStrategies = new NullEquivalentStrategy[] { blankNullEquivalentStrategy,
                                                          constantNullEquivalentStrategy };
        
        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, null, converter, myNullStrategies );
        assertFalse ( myContext.isError()                              );
        assertSame  ( myContext.getStandardError(), CopybookError.None );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "        "    );

        encodeAsString( 0, "xxxxxxxx", 8, EXACT_FLAGS, null );
        myContext.setError( null, null );
        encodeAsDate( 0, getDate( "07-01-2006" ), converter, myNullStrategies );
        assertFalse ( myContext.isError()                              );
        assertSame  ( myContext.getStandardError(), CopybookError.None );
        assertEquals( decodeAsString( 0, 8, EXACT_FLAGS, null ), "20060701"    );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    protected boolean isValidAsString( int                        aOffset,
                                       int                        aSize,
                                       int                        aFlags,
                                       NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        return getCopybook().isAlphanumericAsStringValid( getContext(), aOffset, aSize, aFlags, aNullEquivalentStrategies );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOffset
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    protected String decodeAsString( int                        aOffset,
                                     int                        aSize,
                                     int                        aFlags,
                                     NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        return getCopybook().decodeAlphanumericAsString( getContext(), aOffset, aSize, aFlags, aNullEquivalentStrategies );
    }
    
 
    /**
     * FILLIN
     * 
     * @param       aOffset
     * @param       aValue
     * @param       aSize
     * @param       aFlags
     * @param       aNullEquivalentStrategies
     */
    protected void encodeAsString( int                        aOffset,
                                   String                     aValue,
                                   int                        aSize,
                                   int                        aFlags,
                                   NullEquivalentStrategy[]   aNullEquivalentStrategies )
    {
        getCopybook().encodeAlphanumericAsString( getContext(), aOffset, aValue, aSize, aFlags, aNullEquivalentStrategies );
    }

    
    /**
     * FILLIN
     * 
     * @param       aOffset
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    protected abstract boolean isValidAsDate( int                        aOffset,
                                              Converter                  aConverter,
                                              NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
 
    /**
     * FILLIN
     * 
     * @param       aOffset
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    protected abstract Date decodeAsDate( int                        aOffset,
                                          Converter                  aConverter,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
 
    /**
     * FILLIN
     * 
     * @param       aOffset
     * @param       aValue
     * @param       aConverter
     * @param       aNullEquivalentStrategies
     * 
     * @return
     */
    protected abstract void encodeAsDate( int                        aOffset,
                                          Date                       aValue,
                                          Converter                  aConverter,
                                          NullEquivalentStrategy[]   aNullEquivalentStrategies );
    
    
    /**
     * FILLIN
     * 
     * @param       aString
     * 
     * @return
     */
    protected Date getDate( String aString )
    {
        try
        {
            SimpleDateFormat mySimpleDateFormat = new SimpleDateFormat( "MM-dd-yyyy" );
            return mySimpleDateFormat.parse( aString );
        }
        catch ( ParseException myParseException )
        {
            return null;
        }
    }
    
    
    /**
     * FILLIN
     *
     * @param       aString
     */
    protected void setupBuffer( String aString )
    {
        assert aString != null;
        
        CopybookContext myContext = getContext();
        
        myContext.setBuffer( aString );
        myContext.setError( null, null );
    }
}
