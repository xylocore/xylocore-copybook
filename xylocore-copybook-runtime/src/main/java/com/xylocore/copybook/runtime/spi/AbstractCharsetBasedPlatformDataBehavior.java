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


package com.xylocore.copybook.runtime.spi;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.UnsupportedCharsetException;

import com.xylocore.commons.util.FormatHelper;
import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public abstract class AbstractCharsetBasedPlatformDataBehavior
    extends
        AbstractPlatformDataBehavior
{
    //
    // Members
    //
    
    
    private Charset         displayCharset;
    private CharsetDecoder  displayDecoder;
    private CharsetEncoder  displayEncoder;
    private Charset         dbcsCharset;
    private CharsetDecoder  dbcsDecoder;
    private CharsetEncoder  dbcsEncoder;
    private Charset         nationalCharset;
    private CharsetDecoder  nationalDecoder;
    private CharsetEncoder  nationalEncoder;

    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aBehaviorName
     * @param       aDisplayCharsetName
     * @param       aDbcsCharsetName
     * @param       aNationalCharsetName
     */
    public AbstractCharsetBasedPlatformDataBehavior( String   aBehaviorName,
                                                     String   aDisplayCharsetName,
                                                     String   aDbcsCharsetName,
                                                     String   aNationalCharsetName )
    {
        super( aBehaviorName );
        
        if ( aDisplayCharsetName == null || aDisplayCharsetName.length() == 0 )
        {
            throw new IllegalArgumentException( "aDisplayCharsetName" );
        }
        if ( aDbcsCharsetName == null || aDbcsCharsetName.length() == 0 )
        {
            throw new IllegalArgumentException( "aDbcsCharsetName" );
        }
        if ( aNationalCharsetName == null || aNationalCharsetName.length() == 0 )
        {
            throw new IllegalArgumentException( "aNationalCharsetName" );
        }

        displayCharset = getCharset( aDisplayCharsetName );
        displayDecoder = displayCharset.newDecoder();
        displayEncoder = displayCharset.newEncoder();
        setupCoders( displayDecoder, displayEncoder );
        
        dbcsCharset = Charset.forName( aDbcsCharsetName );
        dbcsDecoder = dbcsCharset.newDecoder();
        dbcsEncoder = dbcsCharset.newEncoder();
        setupCoders( dbcsDecoder, dbcsEncoder );

        nationalCharset = Charset.forName( aNationalCharsetName );
        nationalDecoder = nationalCharset.newDecoder();
        nationalEncoder = nationalCharset.newEncoder();
        setupCoders( nationalDecoder, nationalEncoder );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDecoder
     * @param       aEncoder
     */
    private void setupCoders( CharsetDecoder   aDecoder,
                              CharsetEncoder   aEncoder  )
    {
        assert aDecoder != null;
        assert aEncoder != null;
        
        // TODO: allow user to specify coding error action
        aDecoder.onMalformedInput     ( CodingErrorAction.IGNORE );
        aDecoder.onUnmappableCharacter( CodingErrorAction.IGNORE );
        
        // TODO: allow user to specify coding error action
        aEncoder.onMalformedInput     ( CodingErrorAction.IGNORE );
        aEncoder.onUnmappableCharacter( CodingErrorAction.IGNORE );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCharsetName
     * 
     * @return
     */
    private Charset getCharset( String aCharsetName )
    {
        Charset myCharset;
        
        try
        {
            myCharset = Charset.forName( aCharsetName );
        }
        catch ( UnsupportedCharsetException myUnsupportedCharsetException )
        {
            try
            {
                @SuppressWarnings( "unchecked" )
                Class<Charset> myCharsetClass = (Class<Charset>) Class.forName( "sun.nio.cs.ext." + aCharsetName );
                
                myCharset = myCharsetClass.newInstance();
            }
            catch ( Exception myException )
            {
                throw myUnsupportedCharsetException;
            }
        }
        
        return myCharset;
    }
    

    /**
     * FILLIN
     * 
     * @param       aDecoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     */
    private void decodeChars( CharsetDecoder    aDecoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength             )
    {
        if ( aLength < 0 )
        {
            throw new IllegalArgumentException( "aLength" );
        }
        
        decodeChars( aDecoder,
                     aContext,
                     aBytesPerCharacter,
                     aOffset,
                     aLength,
                     aContext.getWorkBuffer( aLength ) );
    }
    

    /**
     * FILLIN
     * 
     * @param       aDecoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     * @param       aTargetBuffer
     */
    private void decodeChars( CharsetDecoder    aDecoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength,
                              CharBuffer        aTargetBuffer       )
    {
        assert aDecoder != null;
        
        if ( aContext == null )
        {
            throw new IllegalArgumentException( "aContext" );
        }
        if ( aTargetBuffer == null )
        {
            throw new IllegalArgumentException( "aTargetBuffer" );
        }
        if ( aOffset < 0 )
        {
            throw new IllegalArgumentException( "aOffset" );
        }
        if ( aLength < 0 || aLength > aTargetBuffer.remaining() )
        {
            throw new IllegalArgumentException( "aLength" );
        }
        
        aContext.decode( aDecoder,
                         aOffset,
                         aOffset+aBytesPerCharacter*aLength,
                         aTargetBuffer                       );
    }

    
    /**
     * FILLIN
     * 
     * @param       aDecoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     * @param       aTargetData
     */
    private void decodeChars( CharsetDecoder    aDecoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength,
                              char[]            aTargetData         )
    {
        decodeChars( aDecoder,
                     aContext,
                     aBytesPerCharacter,
                     aOffset,
                     aLength,
                     aTargetData,
                     0                   );
    }


    /**
     * FILLIN
     * 
     * @param       aDecoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     * @param       aTargetData
     * @param       aTargetStart
     */
    private void decodeChars( CharsetDecoder    aDecoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength,
                              char[]            aTargetData,
                              int               aTargetStart        )
    {
        assert aDecoder != null;
        
        if ( aContext == null )
        {
            throw new IllegalArgumentException( "aContext" );
        }
        
        CharBuffer myWorkBuffer = aContext.getWorkBuffer( aLength );

        aContext.decode( aDecoder,
                         aOffset,
                         aOffset+aBytesPerCharacter*aLength,
                         myWorkBuffer                        );
        
        myWorkBuffer.flip();
        myWorkBuffer.get( aTargetData, aTargetStart, aLength );
    }


    /**
     * FILLIN
     * 
     * @param       aEncoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     */
    private void encodeChars( CharsetEncoder    aEncoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength             )
    {
        if ( aLength < 0 )
        {
            throw new IllegalArgumentException( "aLength" );
        }
        
        encodeChars( aEncoder,
                     aContext,
                     aBytesPerCharacter,
                     aOffset,
                     aLength,
                     aContext.getWorkBuffer( aLength ) );
    }


    /**
     * FILLIN
     * 
     * @param       aEncoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     * @param       aSourceBuffer
     */
    private void encodeChars( CharsetEncoder    aEncoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength,
                              CharBuffer        aSourceBuffer       )
    {
        assert aEncoder != null;
        
        if ( aContext == null )
        {
            throw new IllegalArgumentException( "aContext" );
        }
        if ( aSourceBuffer == null )
        {
            throw new IllegalArgumentException( "aSourceBuffer" );
        }
        if ( aOffset < 0 )
        {
            throw new IllegalArgumentException( "aOffset" );
        }
        if ( aLength < 0 || aLength > aSourceBuffer.remaining() )
        {
            throw new IllegalArgumentException( "aLength" );
        }
        
        aContext.encode( aEncoder,
                         aBytesPerCharacter,
                         aOffset,
                         aSourceBuffer       );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEncoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     */
    private void encodeChars( CharsetEncoder    aEncoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength,
                              CharSequence      aSourceData         )
    {
        encodeChars( aEncoder,
                     aContext,
                     aBytesPerCharacter,
                     aOffset,
                     aLength,
                     aSourceData,
                     0                   );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEncoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     * @param       aSourceStart
     */
    private void encodeChars( CharsetEncoder    aEncoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength,
                              CharSequence      aSourceData,
                              int               aSourceStart        )
    {
        assert aEncoder != null;
        
        if ( aContext == null )
        {
            throw new IllegalArgumentException( "aContext" );
        }
        if ( aOffset < 0 )
        {
            throw new IllegalArgumentException( "aOffset" );
        }
        if ( aLength < 0 || aLength > aContext.getBuffer().remaining() )
        {
            throw new IllegalArgumentException( "aLength" );
        }
        if ( aSourceData == null )
        {
            throw new IllegalArgumentException( "aSourceData" );
        }
        
        int mySourceEnd = aSourceStart + aLength;

        if ( aSourceStart < 0 || mySourceEnd > aSourceData.length() )
        {
            throw new IllegalArgumentException( "aSourceStart" );
        }
        
        CharBuffer myWorkBuffer = aContext.getWorkBuffer( aLength );
        append( myWorkBuffer, aSourceData, aSourceStart, aLength );
        myWorkBuffer.flip();
        
        aContext.encode( aEncoder,
                         aBytesPerCharacter,
                         aOffset,
                         myWorkBuffer        );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEncoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     */
    private void encodeChars( CharsetEncoder    aEncoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength,
                              char[]            aSourceData         )
    {
        if ( aSourceData == null )
        {
            throw new IllegalArgumentException( "aSourceData" );
        }

        encodeChars( aEncoder,
                     aContext,
                     aBytesPerCharacter,
                     aOffset,
                     aLength,
                     aSourceData,
                     0                   );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEncoder
     * @param       aContext
     * @param       aBytesPerCharacter
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     * @param       aSourceStart
     */
    private void encodeChars( CharsetEncoder    aEncoder,
                              CopybookContext   aContext,
                              int               aBytesPerCharacter,
                              int               aOffset,
                              int               aLength,
                              char[]            aSourceData,
                              int               aSourceStart        )
    {
        assert aEncoder != null;
        
        if ( aContext == null )
        {
            throw new IllegalArgumentException( "aContext" );
        }
        if ( aOffset < 0 )
        {
            throw new IllegalArgumentException( "aOffset" );
        }
        if ( aLength < 0 || aLength > aContext.getBuffer().remaining() )
        {
            throw new IllegalArgumentException( "aLength" );
        }
        if ( aSourceData == null )
        {
            throw new IllegalArgumentException( "aSourceData" );
        }
        
        int mySourceEnd = aSourceStart + aLength;

        if ( aSourceStart < 0 || mySourceEnd > aSourceData.length )
        {
            throw new IllegalArgumentException( "aSourceStart" );
        }

        CharBuffer myWorkBuffer = aContext.getWorkBuffer( aLength );
        myWorkBuffer.put( aSourceData, aSourceStart, aLength );
        myWorkBuffer.flip();

        aContext.encode( aEncoder,
                         aBytesPerCharacter,
                         aOffset,
                         myWorkBuffer        );
    }
    
    
    
    
    //
    // PlatformDataBehavior interface implementation
    //
    

    @Override
    public boolean isNumericDisplayBlank( CopybookContext   aContext,
                                          int               aOffset,
                                          int               aDigits,
                                          SignType          aSignType,
                                          SignPosition      aSignPosition )
    {
        int myLength = aDigits;
        if ( aSignType == SignType.Separate )
        {
            myLength++;
        }
        
        return isDisplayCharsBlank( aContext, aOffset, myLength );
    }
    
    
    @Override
    public void blankNumericDisplay( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aDigits,
                                     SignType          aSignType,
                                     SignPosition      aSignPosition )
    {
        int myLength = aDigits;
        if ( aSignType == SignType.Separate )
        {
            myLength++;
        }
        
        StringBuilder myBuffer = aContext.getWorkStringBuilder( myLength );
        FormatHelper.stringOfCharacters( myBuffer, ' ', myLength );
        
        encodeDisplayChars( aContext, aOffset, myBuffer.length(), myBuffer );
    }
    

    @Override
    public void decodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength   )
    {
        decodeChars( displayDecoder,
                     aContext,
                     1,
                     aOffset,
                     aLength         );
    }
    

    @Override
    public void decodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    CharBuffer        aTargetBuffer )
    {
        decodeChars( displayDecoder,
                     aContext,
                     1,
                     aOffset,
                     aLength,
                     aTargetBuffer   );
    }

    
    @Override
    public void decodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    char[]            aTargetData )
    {
        decodeChars( displayDecoder,
                     aContext,
                     1,
                     aOffset,
                     aLength,
                     aTargetData     );
    }


    @Override
    public void decodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    char[]            aTargetData,
                                    int               aTargetStart )
    {
        decodeChars( displayDecoder,
                     aContext,
                     1,
                     aOffset,
                     aLength,
                     aTargetData,
                     aTargetStart    );
    }


    @Override
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength   )
    {
        encodeChars( displayEncoder,
                     aContext,
                     1,
                     aOffset,
                     aLength         );
    }


    @Override
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    CharBuffer        aSourceBuffer )
    {
        encodeChars( displayEncoder,
                     aContext,
                     1,
                     aOffset,
                     aLength,
                     aSourceBuffer   );
    }
    
    
    @Override
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    CharSequence      aSourceData )
    {
        encodeChars( displayEncoder,
                     aContext,
                     1,
                     aOffset,
                     aLength,
                     aSourceData     );
    }
    
    
    @Override
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    CharSequence      aSourceData,
                                    int               aSourceStart )
    {
        encodeChars( displayEncoder,
                     aContext,
                     1,
                     aOffset,
                     aLength,
                     aSourceData,
                     aSourceStart    );
    }
    
    
    @Override
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    char[]            aSourceData )
    {
        encodeChars( displayEncoder,
                     aContext,
                     1,
                     aOffset,
                     aLength,
                     aSourceData     );
    }
    
    
    @Override
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    char[]            aSourceData,
                                    int               aSourceStart )
    {
        encodeChars( displayEncoder,
                     aContext,
                     1,
                     aOffset,
                     aLength,
                     aSourceData,
                     aSourceStart    );
    }
    

    @Override
    public boolean isNumericDbcsBlank( CopybookContext   aContext,
                                       int               aOffset,
                                       int               aDigits,
                                       SignType          aSignType,
                                       SignPosition      aSignPosition )
    {
        return isDbcsCharsBlank( aContext,
                                 aOffset,
                                 aDigits   );
    }
    
    
    @Override
    public void blankNumericDbcs( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition )
    {
        int myLength = aDigits;
        if ( aSignType == SignType.Separate )
        {
            myLength++;
        }
        
        StringBuilder myBuffer = aContext.getWorkStringBuilder( myLength );
        FormatHelper.stringOfCharacters( myBuffer, ' ', myLength );
        
        encodeDbcsChars( aContext, aOffset, myBuffer.length(), myBuffer );
    }
    

    @Override
    public void decodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength   )
    {
        decodeChars( dbcsDecoder,
                     aContext,
                     2,
                     aOffset,
                     aLength      );
    }
    

    @Override
    public void decodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 CharBuffer        aTargetBuffer )
    {
        decodeChars( dbcsDecoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aTargetBuffer );
    }

    
    @Override
    public void decodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 char[]            aTargetData )
    {
        decodeChars( dbcsDecoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aTargetData  );
    }


    @Override
    public void decodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 char[]            aTargetData,
                                 int               aTargetStart )
    {
        decodeChars( dbcsDecoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aTargetData,
                     aTargetStart );
    }


    @Override
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength   )
    {
        encodeChars( dbcsEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength      );
    }


    @Override
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 CharBuffer        aSourceBuffer )
    {
        encodeChars( dbcsEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceBuffer );
    }
    
    
    @Override
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 CharSequence      aSourceData )
    {
        encodeChars( dbcsEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceData  );
    }
    
    
    @Override
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 CharSequence      aSourceData,
                                 int               aSourceStart )
    {
        encodeChars( dbcsEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceData,
                     aSourceStart );
    }
    
    
    @Override
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 char[]            aSourceData )
    {
        encodeChars( dbcsEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceData  );
    }
    
    
    @Override
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 char[]            aSourceData,
                                 int               aSourceStart )
    {
        encodeChars( dbcsEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceData,
                     aSourceStart );
    }
    

    @Override
    public boolean isNumericNationalBlank( CopybookContext   aContext,
                                           int               aOffset,
                                           int               aDigits,
                                           SignType          aSignType,
                                           SignPosition      aSignPosition )
    {
        return isNationalCharsBlank( aContext,
                                     aOffset,
                                     aDigits   );
    }
    
    
    @Override
    public void blankNumericNational( CopybookContext   aContext,
                                      int               aOffset,
                                      int               aDigits,
                                      SignType          aSignType,
                                      SignPosition      aSignPosition )
    {
        int myLength = aDigits;
        if ( aSignType == SignType.Separate )
        {
            myLength++;
        }
        
        StringBuilder myBuffer = aContext.getWorkStringBuilder( myLength );
        FormatHelper.stringOfCharacters( myBuffer, ' ', myLength );
        
        encodeNationalChars( aContext, aOffset, myLength, myBuffer );
    }
    
    
    @Override
    public void decodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength   )
    {
        decodeChars( nationalDecoder,
                     aContext,
                     2,
                     aOffset,
                     aLength          );
    }
    

    @Override
    public void decodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     CharBuffer        aTargetBuffer )
    {
        decodeChars( nationalDecoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aTargetBuffer    );
    }

    
    @Override
    public void decodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     char[]            aTargetData )
    {
        decodeChars( nationalDecoder,
                     aContext,
                     2, 
                     aOffset,
                     aLength,
                     aTargetData      );
    }


    @Override
    public void decodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     char[]            aTargetData,
                                     int               aTargetStart )
    {
        decodeChars( nationalDecoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aTargetData,
                     aTargetStart     );
    }


    @Override
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength   )
    {
        encodeChars( nationalEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength          );
    }


    @Override
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     CharBuffer        aSourceBuffer )
    {
        encodeChars( nationalEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceBuffer    );
    }
    
    
    @Override
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     CharSequence      aSourceData )
    {
        encodeChars( nationalEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceData      );
    }
    
    
    @Override
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     CharSequence      aSourceData,
                                     int               aSourceStart )
    {
        encodeChars( nationalEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceData,
                     aSourceStart     );
    }
    
    
    @Override
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     char[]            aSourceData )
    {
        encodeChars( nationalEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceData      );
    }
    
    
    @Override
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     char[]            aSourceData,
                                     int               aSourceStart )
    {
        encodeChars( nationalEncoder,
                     aContext,
                     2,
                     aOffset,
                     aLength,
                     aSourceData,
                     aSourceStart     );
    }
}
