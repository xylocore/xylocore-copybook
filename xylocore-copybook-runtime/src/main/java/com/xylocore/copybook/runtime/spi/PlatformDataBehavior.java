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
import java.text.DateFormat;
import java.util.Date;

import com.xylocore.copybook.runtime.CopybookContext;
import com.xylocore.copybook.runtime.SignPosition;
import com.xylocore.copybook.runtime.SignType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface PlatformDataBehavior
{
    /**
     * FILLIN
     * 
     * @return
     */
    public String getBehaviorName();
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * 
     * @return
     */
    public boolean isDisplayCharsBlank( CopybookContext   aContext,
                                        int               aOffset,
                                        int               aLength   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * 
     * @return
     */
    public boolean isNumericDisplayBlank( CopybookContext   aContext,
                                          int               aOffset,
                                          int               aDigits,
                                          SignType          aSignType,
                                          SignPosition      aSignPosition );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     */
    public void blankNumericDisplay( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aDigits,
                                     SignType          aSignType,
                                     SignPosition      aSignPosition );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     */
    public void decodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aBuffer
     */
    public void decodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    CharBuffer        aBuffer   );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aTargetData
     */
    public void decodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    char[]            aTargetData );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aTargetData
     * @param       aTargetStart
     */
    public void decodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    char[]            aTargetData,
                                    int               aTargetStart );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     */
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength   );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aBuffer
     */
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    CharBuffer        aBuffer   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     */
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    CharSequence      aSourceData );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     * @param       aSourceStart
     */
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    CharSequence      aSourceData,
                                    int               aSourceStart );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     */
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    char[]            aSourceData );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     * @param       aSourceStart
     */
    public void encodeDisplayChars( CopybookContext   aContext,
                                    int               aOffset,
                                    int               aLength,
                                    char[]            aSourceData,
                                    int               aSourceStart );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * 
     * @return
     */
    public boolean isDbcsCharsBlank( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * 
     * @return
     */
    public boolean isNumericDbcsBlank( CopybookContext   aContext,
                                       int               aOffset,
                                       int               aDigits,
                                       SignType          aSignType,
                                       SignPosition      aSignPosition );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     */
    public void blankNumericDbcs( CopybookContext   aContext,
                                  int               aOffset,
                                  int               aDigits,
                                  SignType          aSignType,
                                  SignPosition      aSignPosition );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     */
    public void decodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aBuffer
     */
    public void decodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 CharBuffer        aBuffer   );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aTargetData
     */
    public void decodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 char[]            aTargetData );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aTargetData
     * @param       aTargetStart
     */
    public void decodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 char[]            aTargetData,
                                 int               aTargetStart );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     */
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength   );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aBuffer
     */
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 CharBuffer        aBuffer   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     */
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 CharSequence      aSourceData );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     * @param       aSourceStart
     */
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 CharSequence      aSourceData,
                                 int               aSourceStart );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     */
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 char[]            aSourceData );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     * @param       aSourceStart
     */
    public void encodeDbcsChars( CopybookContext   aContext,
                                 int               aOffset,
                                 int               aLength,
                                 char[]            aSourceData,
                                 int               aSourceStart );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * 
     * @return
     */
    public boolean isNationalCharsBlank( CopybookContext   aContext,
                                         int               aOffset,
                                         int               aLength   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * 
     * @return
     */
    public boolean isNumericNationalBlank( CopybookContext   aContext,
                                           int               aOffset,
                                           int               aDigits,
                                           SignType          aSignType,
                                           SignPosition      aSignPosition );
    

    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     */
    public void blankNumericNational( CopybookContext   aContext,
                                      int               aOffset,
                                      int               aDigits,
                                      SignType          aSignType,
                                      SignPosition      aSignPosition );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     */
    public void decodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aBuffer
     */
    public void decodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     CharBuffer        aBuffer   );

    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aTargetData
     */
    public void decodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     char[]            aTargetData );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aTargetData
     * @param       aTargetStart
     */
    public void decodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     char[]            aTargetData,
                                     int               aTargetStart );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     */
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength   );


    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aBuffer
     */
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     CharBuffer        aBuffer   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     */
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     CharSequence      aSourceData );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     * @param       aSourceStart
     */
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     CharSequence      aSourceData,
                                     int               aSourceStart );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     */
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     char[]            aSourceData );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aLength
     * @param       aSourceData
     * @param       aSourceStart
     */
    public void encodeNationalChars( CopybookContext   aContext,
                                     int               aOffset,
                                     int               aLength,
                                     char[]            aSourceData,
                                     int               aSourceStart );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * 
     * @return
     */
    public long decodeNumericDisplay( CopybookContext   aContext,
                                      int               aOffset,
                                      int               aDigits,
                                      SignType          aSignType,
                                      SignPosition      aSignPosition );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     */
    public void encodeNumericDisplay( CopybookContext   aContext,
                                      int               aOffset,
                                      long              aValue,
                                      int               aDigits,
                                      SignType          aSignType,
                                      SignPosition      aSignPosition );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     * 
     * @return
     */
    public long decodeNumericNational( CopybookContext   aContext,
                                       int               aOffset,
                                       int               aDigits,
                                       SignType          aSignType,
                                       SignPosition      aSignPosition );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aSignType
     * @param       aSignPosition
     */
    public void encodeNumericNational( CopybookContext   aContext,
                                       int               aOffset,
                                       long              aValue,
                                       int               aDigits,
                                       SignType          aSignType,
                                       SignPosition      aSignPosition );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * @param       aIsSigned
     * 
     * @return
     */
    public long decodeBinary( CopybookContext   aContext,
                              int               aOffset,
                              int               aDigits,
                              boolean           aIsSigned );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aIsSigned
     */
    public void encodeBinary( CopybookContext   aContext,
                              int               aOffset,
                              long              aValue,
                              int               aDigits,
                              boolean           aIsSigned );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public float decodeComp1( CopybookContext   aContext,
                              int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp1( CopybookContext   aContext,
                             int               aOffset,
                             float             aValue    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public double decodeComp2( CopybookContext   aContext,
                               int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encodeComp2( CopybookContext   aContext,
                             int               aOffset,
                             double            aValue    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aDigits
     * 
     * @return
     */
    public long decodeComp3( CopybookContext   aContext,
                             int               aOffset,
                             int               aDigits   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aDigits
     * @param       aIsSigned
     */
    public void encodeComp3( CopybookContext   aContext,
                             int               aOffset,
                             long              aValue,
                             int               aDigits,
                             boolean           aIsSigned );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public long decode2ByteComp5( CopybookContext   aContext,
                                  int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encode2ByteComp5( CopybookContext   aContext,
                                  int               aOffset,
                                  long              aValue    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public long decode4ByteComp5( CopybookContext   aContext,
                                  int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encode4ByteComp5( CopybookContext   aContext,
                                  int               aOffset,
                                  long              aValue    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * 
     * @return
     */
    public long decode8ByteComp5( CopybookContext   aContext,
                                  int               aOffset   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     */
    public void encode8ByteComp5( CopybookContext   aContext,
                                  int               aOffset,
                                  long              aValue    );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aValue
     * @param       aFormat
     */
    public void encodeDate( CopybookContext   aContext,
                            int               aOffset,
                            Date              aValue,
                            DateFormat        aFormat   );
    
    
    /**
     * FILLIN
     * 
     * @param       aContext
     * @param       aOffset
     * @param       aFormat
     */
    public Date decodeDate( CopybookContext   aContext,
                            int               aOffset,
                            DateFormat        aFormat   );
}
