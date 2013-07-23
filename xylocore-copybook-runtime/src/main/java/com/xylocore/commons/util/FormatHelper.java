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


package com.xylocore.commons.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * <p>This class provides (mostly static class) methods to format a value
 * within a given text area, with specified justification.</p>
 * <b>Notes:</b>
 * <p>There are four different justification modes allowed:
 * <ol>
 *     <li> LEFT_JUSTIFY - the input string begins at the first character
 *          of the formatted output string. (Any excess input characters
 *          at the end are ignored).</li>
 *     <li> RIGHT_JUSTIFY - the input string ends at the last character
 *          of the formatted output string. (Any excess input characters
 *          at the beginning are ignored).</li>
 *     <li> CENTER - the input string is centered in the formatted output
 *          string. (Any excess input characters at either end are
 *          ignored).</li>
 *     <li> LEFT_JUSTIFY_ELLIPSIS - the input string begins at the first
 *          character of the formatted output string. However, if there are
 *          excess input characters at the end, the final character of the
 *          formatted string is replaced with a period.</li>
 * </ol>
 *  The formatted string is padded with spaces whenever necessary.</p>
 *  
 *  @author         Eric R. Medley
 */

public class FormatHelper
{
    //
    // Nested interfaces
    //
    
    
    private static interface BufferAdapter
        extends
            CharSequence,
            Appendable
    {
        public void setCharAt( int    aIndex,
                               char   aChar   );
        
        public void setLength( int aLength );
        
        public void ensureCapacity( int aMinimumCapacity );
    }
    
    
    
    
    //
    // Nested classes
    //
    
    
    private static abstract class BasicBufferAdapter<T extends Appendable & CharSequence>
        implements
            BufferAdapter
    {
        //
        // Members
        //
        
        protected T buffer;
        
        
        //
        // Class implementation
        //
        
        /**
         * Default constructor.
         */
        public BasicBufferAdapter()
        {
        }
        
        /**
         * FILLIN
         * 
         * @param       aBuffer
         */
        public void setBuffer( T aBuffer )
        {
            buffer = aBuffer;
        }
        
        
        //
        // BufferAdapter interface implementation
        //
        
        @Override
        public Appendable append( CharSequence aCharSequence )
                throws IOException
        {
            buffer.append( aCharSequence );
            
            return this;
        }

        @Override
        public Appendable append( CharSequence   aCharSequence,
                                  int            aStart,
                                  int            aEnd           )
                throws IOException
        {
            buffer.append( aCharSequence, aStart, aEnd );
            
            return this;
        }

        @Override
        public Appendable append( char aChar )
                throws IOException
        {
            buffer.append( aChar );
            
            return this;
        }

        @Override
        public int length()
        {
            return buffer.length();
        }

        @Override
        public char charAt( int aIndex )
        {
            return buffer.charAt( aIndex );
        }

        @Override
        public CharSequence subSequence( int   aStart,
                                         int   aEnd    )
        {
            return buffer.subSequence( aStart, aEnd );
        }
    }
    
    private static class StringBufferAdapter
        extends
            BasicBufferAdapter<StringBuffer>
    {
        public StringBufferAdapter()
        {
        }
        
        public void setCharAt( int    aIndex,
                               char   aChar   )
        {
            buffer.setCharAt( aIndex, aChar );
        }
        
        public void setLength( int aLength )
        {
            buffer.setLength( aLength );
        }
        
        public void ensureCapacity( int aMinimumCapacity )
        {
            buffer.ensureCapacity( aMinimumCapacity );
        }
    }
    
    private static class StringBuilderAdapter
        extends
            BasicBufferAdapter<StringBuilder>
    {
        public StringBuilderAdapter()
        {
        }
        
        public void setCharAt( int    aIndex,
                               char   aChar   )
        {
            buffer.setCharAt( aIndex, aChar );
        }
        
        public void setLength( int aLength )
        {
            buffer.setLength( aLength );
        }
        
        public void ensureCapacity( int aMinimumCapacity )
        {
            buffer.ensureCapacity( aMinimumCapacity );
        }
    }
    
    private static class ThreadInfo
    {
        //
        // Members
        //

        private List<StringBuffer>      tempStringBufferCache   = new ArrayList<>();
        private List<StringBuilder>     tempStringBuilderCache  = new ArrayList<>();
        private NumberFormat            numberFormatter         = NumberFormat.getNumberInstance();
        private SimpleDateFormat        dateFormatter           = new SimpleDateFormat();
        private FieldPosition           unusedFieldPosition     = new FieldPosition( 0 );
        private StringBufferAdapter     stringBufferAdapter     = new StringBufferAdapter();
        private StringBuilderAdapter    stringBuilderAdapter    = new StringBuilderAdapter();


        //
        // Class implementation
        //
        
        public StringBuffer getTemporaryStringBuffer()
        {
            StringBuffer myBuffer;
            
            if ( tempStringBufferCache.isEmpty() )
            {
                myBuffer = new StringBuffer( 32 );
            }
            else
            {
                myBuffer = tempStringBufferCache.remove( tempStringBufferCache.size()-1 );
                myBuffer.setLength( 0 );
            }

            return myBuffer;
        }
        
        public void returnTemporaryStringBuffer( StringBuffer aBuffer )
        {
            assert aBuffer != null;
            
            tempStringBufferCache.add( aBuffer );
        }
        
        public StringBuilder getTemporaryStringBuilder()
        {
            StringBuilder myBuffer;
            
            if ( tempStringBuilderCache.isEmpty() )
            {
                myBuffer = new StringBuilder( 32 );
            }
            else
            {
                myBuffer = tempStringBuilderCache.remove( tempStringBuilderCache.size()-1 );
                myBuffer.setLength( 0 );
            }

            return myBuffer;
        }
        
        public void returnTemporaryStringBuilder( StringBuilder aBuffer )
        {
            assert aBuffer != null;
            
            tempStringBuilderCache.add( aBuffer );
        }

        public NumberFormat getNumberFormatter()
        {
            return numberFormatter;
        }

        public SimpleDateFormat getDateFormatter()
        {
            return dateFormatter;
        }

        public FieldPosition getUnusedFieldPosition()
        {
            return unusedFieldPosition;
        }
        
        public StringBufferAdapter getStringBufferAdapter( StringBuffer aBuffer )
        {
            stringBufferAdapter.setBuffer( aBuffer );
            return stringBufferAdapter;
        }
        
        public StringBuilderAdapter getStringBuilderAdapter( StringBuilder aBuilder )
        {
            stringBuilderAdapter.setBuffer( aBuilder );
            return stringBuilderAdapter;
        }
    }
    
    
    
    
    //
    // Constants
    //
    
    
    public enum Justify
    {
        /**
         * <p>An indication that the output string is to be formatted
         * such that the input string is centered within the field.</p>
         * <p>(Any excess input characters at either end are ignored.)</p>
         */
        Center,
        
        /**
         * <p>An indication that the output string is to be formatted
         * such that the input string begins at the first character
         * of the output.</p>
         * <p>(Any excess input characters at the end are ignored.)</p>
         */
        Left,
        
        /**
         * <p>An indication that the output string is to be formatted
         * such that the input string begins at the first character
         * of the output.</p>
         * <p>However, if there are excess input characters at the
         * end, the final character of output is to be replaced
         * with a period.</p>
         */
        LeftEllipses,
        
        /**
         * <p>An indication that the output string is to be formatted
         * such that the input string ends with the last character
         * of the output.</p>
         * <p>(Any excess input characters at the beginning are
         * ignored.)</p>
         */
        Right
    }
    
    
    
    
    //
    // Members
    //

    
    /**
     * <p>Thread-local storage for formatters and buffers, etc. that are allocated
     * on a per-thread basis.</p>
     */
    private static final ThreadLocal<ThreadInfo> threadLocalInfo;




    //
    // Static initializers
    //


    static
    {
        threadLocalInfo =
                new ThreadLocal<ThreadInfo>()
                    {
                        protected ThreadInfo initialValue()
                        {
                            return new ThreadInfo();
                        }
                    };
    }




    //
    // Class implementation
    //


    /**
     * This method formats a character sequence value into a new
     * string of specified length and specified justification.
     *  
     * @param       aCharSequence
     *                  The original character sequence into which the secondary
     *                  character sequence is to be embedded.
     * @param       aEmbedCharSequence
     *                  The character sequence to be embedded into the original
     *                  character sequence.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justification).
     *
     * @return      A string containing the original character sequence with the
     *              secondary character sequence embedded in it with the appropriate
     *              justification.
     */
    public static String embedString( CharSequence   aCharSequence,
                                      CharSequence   aEmbedCharSequence,
                                      Justify        aJustification      )
    {
        String myString;

        if ( aEmbedCharSequence.length() != 0 )
        {
            ThreadInfo           myThreadInfo    = getThreadInfo();
            StringBuilder        myBuffer        = myThreadInfo.getTemporaryStringBuilder();
            StringBuilderAdapter myBufferAdapter = myThreadInfo.getStringBuilderAdapter( myBuffer );
            
            try
            {
                myBuffer.append( aCharSequence );
                embedString( myBufferAdapter, 0, aEmbedCharSequence.length(), aEmbedCharSequence, aJustification );
                myString = myBuffer.toString();
            }
            finally
            {
                myThreadInfo.returnTemporaryStringBuilder( myBuffer );
            }
        }
        else
        {
            myString = aCharSequence.toString();
        }

        return myString;
    }


    /**
     * This method formats a character sequence value with the specified length
     * and justification, putting at the specified offset within the
     * string buffer.
     *  
     * @param       aBuffer
     *                  The string buffer into which the secondary
     *                  string is to be embedded.
     * @param       aBufferOffset
     *                  The offset within the string buffer that the
     *                  secondary string is to be embedded.
     * @param       aTargetWidth
     *                  The width of the target area into which the supplied
     *                  string will be embedded.
     * @param       aEmbedCharSequence
     *                  The character sequence to be embedded into the original
     *                  string.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     */
    public static void embedString( StringBuffer   aBuffer,
                                    int            aBufferOffset,
                                    int            aTargetWidth,
                                    CharSequence   aEmbedCharSequence,
                                    Justify        aJustification      )
    {
        StringBufferAdapter myBufferAdapter = getThreadInfo().getStringBufferAdapter( aBuffer );
        
        embedString( myBufferAdapter,
                     aBufferOffset,
                     aTargetWidth,
                     aEmbedCharSequence,
                     aJustification      );
    }
    
    
    /**
     * This method formats a character sequence value with the specified length
     * and justification, putting at the specified offset within the
     * string buffer.
     *  
     * @param       aBuffer
     *                  The string buffer into which the secondary
     *                  string is to be embedded.
     * @param       aBufferOffset
     *                  The offset within the string buffer that the
     *                  secondary string is to be embedded.
     * @param       aTargetWidth
     *                  The width of the target area into which the supplied
     *                  string will be embedded.
     * @param       aEmbedCharSequence
     *                  The character sequence to be embedded into the original
     *                  string.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     */
    public static void embedString( StringBuilder   aBuffer,
                                    int             aBufferOffset,
                                    int             aTargetWidth,
                                    CharSequence    aEmbedCharSequence,
                                    Justify         aJustification      )
    {
        StringBuilderAdapter myBufferAdapter = getThreadInfo().getStringBuilderAdapter( aBuffer );
        
        embedString( myBufferAdapter,
                     aBufferOffset,
                     aTargetWidth,
                     aEmbedCharSequence,
                     aJustification      );
    }
    
    
    /**
     * This method formats a character sequence value with the specified length
     * and justification, putting at the specified offset within the
     * string buffer.
     *  
     * @param       aBuffer
     *                  The string buffer into which the secondary
     *                  string is to be embedded.
     * @param       aBufferOffset
     *                  The offset within the string buffer that the
     *                  secondary string is to be embedded.
     * @param       aTargetWidth
     *                  The width of the target area into which the supplied
     *                  string will be embedded.
     * @param       aEmbedCharSequence
     *                  The character sequence to be embedded into the original
     *                  string.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     */
    private static void embedString( BufferAdapter   aBuffer,
                                     int             aBufferOffset,
                                     int             aTargetWidth,
                                     CharSequence    aEmbedCharSequence,
                                     Justify         aJustification      )
    {
        if ( aBufferOffset >= aBuffer.length() )
        {
            throw new IllegalArgumentException( "the buffer offset must be less than the buffer length" );
        }
        if ( aBufferOffset + aTargetWidth > aBuffer.length() )
        {
            throw new IllegalArgumentException( "the buffer offset and target width must be less than the buffer length" );
        }

        int myLength = aEmbedCharSequence.length();

        // Non-zero size requested - embed the string
        if ( myLength > 0 )
        {
            // Copy the original string to the target buffer
            int myCopyLength  = ( myLength < aTargetWidth ) ? myLength : aTargetWidth;
            int mySourceStart = 0;
            int myTargetStart = 0;

            // Calculate the parts to copy based upon justification
            switch ( aJustification )
            {
                // Left justification
                case Left:
                case LeftEllipses:

                    break;

                // Center justification
                case Center:

                    if ( myLength > aTargetWidth )
                    {
                        mySourceStart = (myLength - aTargetWidth) / 2;
                    }
                    else
                    {
                        myTargetStart = (aTargetWidth - myLength) / 2;
                    }
                    break;

                // Right justification
                case Right:

                    if ( myLength > aTargetWidth )
                    {
                        mySourceStart = myLength - aTargetWidth;
                    }
                    else
                    {
                        myTargetStart = aTargetWidth - myLength;
                    }
                    break;
            }

            // Perform the actual copy
            int myCopyBufferOffset = aBufferOffset + myTargetStart;
            for ( int i = 0 ; i < myCopyLength ; i++ )
            {
                aBuffer.setCharAt( myCopyBufferOffset + i, aEmbedCharSequence.charAt( mySourceStart + i ) );
            }

            // Special case: ellipsis for "too-long" source string
            if ( aJustification == Justify.LeftEllipses )
            {
                if ( myLength > aTargetWidth )
                {
                    aBuffer.setCharAt( aBufferOffset + aTargetWidth - 1, '.' );
                }
            }
        }
    }


    /**
     * This method escapes quotes that are embedded in a character sequence.
     *  
     * @param       aString
     *                  The string to escape.
     *
     * @return      The string with the quotes escaped.
     */
    public static String escapeQuotes( CharSequence aCharSequence )
    {
        ThreadInfo    myThreadInfo    = getThreadInfo();
        StringBuilder myBuffer        = myThreadInfo.getTemporaryStringBuilder();
        BufferAdapter myBufferAdapter = myThreadInfo.getStringBuilderAdapter( myBuffer );

        try
        {
            escapeQuotes( myBufferAdapter, aCharSequence );
            
            return myBuffer.toString();
        }
        finally
        {
            myThreadInfo.returnTemporaryStringBuilder( myBuffer );
        }
    }


    /**
     * This method escapes quotes that are embedded in a string buffer.
     *
     * @param       aBuffer
     *                  The buffer to escape.
     */
    public static void escapeQuotes( StringBuffer aBuffer )
    {
        StringBufferAdapter myBufferAdapter = getThreadInfo().getStringBufferAdapter( aBuffer );
        
        escapeQuotes( myBufferAdapter );
    }


    /**
     * This method escapes quotes that are embedded in a string buffer.
     *
     * @param       aBuffer
     *                  The buffer to escape.
     */
    public static void escapeQuotes( StringBuilder aBuffer )
    {
        StringBuilderAdapter myBufferAdapter = getThreadInfo().getStringBuilderAdapter( aBuffer );
        
        escapeQuotes( myBufferAdapter );
    }
    

    /**
     * This method escapes quotes that are embedded in a string buffer.
     *
     * @param       aBuffer
     *                  The buffer to escape.
     */
    private static void escapeQuotes( BufferAdapter aBuffer )
    {
        int myLength = aBuffer.length();
        int myCount  = 0;

        for ( int i = 0 ; i < myLength ; i++ )
        {
            char myChar = aBuffer.charAt( i );
            if ( myChar == '\'' || myChar == '\"' )
            {
                myCount++;
            }
        }

        if ( myCount != 0 )
        {
            aBuffer.setLength( myLength + myCount );

            int mySourcePos = -1;
            int myDestPos   = mySourcePos + myCount;

            while ( mySourcePos >= 0 && mySourcePos != myDestPos )
            {
                char myChar = aBuffer.charAt( mySourcePos );
                aBuffer.setCharAt( myDestPos, myChar );
                if ( myChar == '\'' || myChar == '\"' )
                {
                    myDestPos--;
                    aBuffer.setCharAt( myDestPos, '\\' );
                }

                mySourcePos--;
                myDestPos--;
            }
        }
    }


    /**
     * This method escapes quotes that are embedded in a string and
     * puts the resulting string on the end of the specified string
     * buffer.
     *
     * @param       aBuffer
     *                  The buffer that the new string with the escaped
     *                  quotes will be appended to.
     * @param       aCharSequence
     *                  The character sequence to escape.
     */
    public static void escapeQuotes( StringBuffer   aBuffer,
                                     CharSequence   aCharSequence )
    {
        StringBufferAdapter myBufferAdapter = getThreadInfo().getStringBufferAdapter( aBuffer );
        
        escapeQuotes( myBufferAdapter, aCharSequence );
    }


    /**
     * This method escapes quotes that are embedded in a string and
     * puts the resulting string on the end of the specified string
     * buffer.
     *
     * @param       aBuffer
     *                  The buffer that the new string with the escaped
     *                  quotes will be appended to.
     * @param       aCharSequence
     *                  The character sequence to escape.
     */
    public static void escapeQuotes( StringBuilder   aBuffer,
                                     CharSequence    aCharSequence )
    {
        StringBuilderAdapter myBufferAdapter = getThreadInfo().getStringBuilderAdapter( aBuffer );
        
        escapeQuotes( myBufferAdapter, aCharSequence );
    }
    

    /**
     * This method escapes quotes that are embedded in a string and
     * puts the resulting string on the end of the specified string
     * buffer.
     *
     * @param       aBuffer
     *                  The buffer that the new string with the escaped
     *                  quotes will be appended to.
     * @param       aCharSequence
     *                  The character sequence to escape.
     */
    private static void escapeQuotes( BufferAdapter   aBuffer,
                                      CharSequence    aCharSequence )
    {
        try
        {
            for ( int i = 0, c = aCharSequence.length() ; i < c ; i++ )
            {
                char ch = aCharSequence.charAt( i );
    
                switch ( ch )
                {
                    case '\'':
                    case '\"':
                        aBuffer.append( '\\' );
                        break;
                }
                aBuffer.append( ch );
            }
        }
        catch ( IOException myIOException )
        {
            // Ignore - the Appendable object is either a StringBuilder or StringBuffer
        }
    }


    /**
     * This method formats a character sequence into a new
     * string of specified length, with a default of left
     * justification.
     *  
     * @param       aCharSequence
     *                  The character sequence to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     *
     * @return      The formatted and left-justified string.
     *
     * @exception   NegativeArraySizeException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static String formatString( CharSequence   aCharSequence,
                                       int            aSize          )
            throws NegativeArraySizeException
    {
        return formatString( aCharSequence, aSize, Justify.Left );
    }


    /**
     * This method formats a character sequence into a
     * string of specified length and specified justification.
     *  
     * @param       aCharSequence
     *                  The character sequence to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @return      The formatted and justified string.
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static String formatString( CharSequence  aCharSequence,
                                       int           aSize,
                                       Justify       aJustification )
    {
        ThreadInfo    myThreadInfo    = getThreadInfo();
        StringBuilder myBuffer        = myThreadInfo.getTemporaryStringBuilder();
        BufferAdapter myBufferAdapter = myThreadInfo.getStringBuilderAdapter( myBuffer );

        try
        {
            formatString( myBufferAdapter, aCharSequence, aSize, aJustification );
            
            return myBuffer.toString();
        }
        finally
        {
            myThreadInfo.returnTemporaryStringBuilder( myBuffer );
        }
    }


    /**
     * This method formats a character sequence into a new
     * string of specified length, with a default of left
     * justification, appending the resulting string to the specified
     * buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aCharSequence
     *                  The character sequence to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     *
     * @exception   NegativeArraySizeException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static void formatString( StringBuffer   aBuffer,
                                     CharSequence   aCharSequence,
                                     int            aSize          )
            throws NegativeArraySizeException
    {
        formatString( aBuffer, aCharSequence, aSize, Justify.Left );
    }


    /**
     * This method formats a character sequence into a new
     * string of specified length and specified justification and appends
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aCharSequence
     *                  The character sequence to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static void formatString( StringBuffer   aBuffer,
                                     CharSequence   aCharSequence,
                                     int            aSize,
                                     Justify        aJustification )
    {
        StringBufferAdapter myBufferAdapter = getThreadInfo().getStringBufferAdapter( aBuffer );
        
        formatString( myBufferAdapter, aCharSequence, aSize, aJustification );
    }


    /**
     * This method formats a character sequence into a new
     * string of specified length, with a default of left
     * justification, appending the resulting string to the specified
     * buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aCharSequence
     *                  The character sequence to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     *
     * @exception   NegativeArraySizeException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static void formatString( StringBuilder   aBuffer,
                                     CharSequence    aCharSequence,
                                     int             aSize          )
            throws NegativeArraySizeException
    {
        formatString( aBuffer, aCharSequence, aSize, Justify.Left );
    }


    /**
     * This method formats a character sequence into a new
     * string of specified length and specified justification and appends
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aCharSequence
     *                  The character sequence to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static void formatString( StringBuilder   aBuffer,
                                     CharSequence    aCharSequence,
                                     int             aSize,
                                     Justify         aJustification )
    {
        StringBuilderAdapter myBufferAdapter = getThreadInfo().getStringBuilderAdapter( aBuffer );
        
        formatString( myBufferAdapter, aCharSequence, aSize, aJustification );
    }    

    
    /**
     * This method formats a character sequence into a new
     * string of specified length and specified justification and appends
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aCharSequence
     *                  The character sequence to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    private static void formatString( BufferAdapter   aBuffer,
                                      CharSequence    aCharSequence,
                                      int             aSize,
                                      Justify         aJustification )
    {
        // Validate the size (it must be positive or zero)
        if ( aSize < 0 )
        {
            throw new IllegalArgumentException( "Invalid output string size (" + aSize + ") specified" );
        }

        // Zero size requested - return an empty string
        if ( aSize != 0 )
        {
            // Copy the original string into a blank target buffer
            int myBufferOffset = aBuffer.length();
            stringOfCharacters( aBuffer, ' ', aSize );
            embedString( aBuffer, myBufferOffset, aSize, aCharSequence, aJustification );
        }
    }
    

    /**
     * This method formats a date value using the specified pattern
     * and returns a String containing the newly formatted date.
     *
     * @param       aDate
     *                  The date that will be formatted.
     * @param       aPattern
     *                  A string that specifies how the date string will be
     *                  formatted.
     *
     * @return      The formatted date string.
     */
    public static String formatDate( Date     aDate,
                                     String   aPattern )
    {
        ThreadInfo   myThreadInfo = getThreadInfo();
        StringBuffer myBuffer     = myThreadInfo.getTemporaryStringBuffer();

        try
        {
            formatDate( myBuffer, aDate, aPattern );
            
            return myBuffer.toString();
        }
        finally
        {
            myThreadInfo.returnTemporaryStringBuffer( myBuffer );
        }
    }


    /**
     * This method formats a date value using the specified pattern
     * and appends the new string to the specified string buffer.
     *
     * @param       aBuffer
     *                  The buffer that the date string will be appended to.
     * @param       aDate
     *                  The date that will be formatted.
     * @param       aPattern
     *                  A string that specifies how the date string will be
     *                  formatted.
     */
    public static void formatDate( StringBuffer   aBuffer,
                                   Date           aDate,
                                   String         aPattern )
    {
        ThreadInfo       myThreadInfo    = getThreadInfo();
        SimpleDateFormat myDateFormatter = myThreadInfo.getDateFormatter();
        FieldPosition    myFieldPosition = myThreadInfo.getUnusedFieldPosition();

        myDateFormatter.applyPattern( aPattern );
        myDateFormatter.format( aDate, aBuffer, myFieldPosition );
    }


    /**
     * This method formats a date value using the specified pattern
     * and appends the new string to the specified string buffer.
     *
     * @param       aBuffer
     *                  The buffer that the date string will be appended to.
     * @param       aDate
     *                  The date that will be formatted.
     * @param       aPattern
     *                  A string that specifies how the date string will be
     *                  formatted.
     */
    public static void formatDate( StringBuilder   aBuffer,
                                   Date            aDate,
                                   String          aPattern )
    {
        ThreadInfo       myThreadInfo    = getThreadInfo();
        StringBuffer     myTempBuffer    = myThreadInfo.getTemporaryStringBuffer();
        SimpleDateFormat myDateFormatter = myThreadInfo.getDateFormatter();
        FieldPosition    myFieldPosition = myThreadInfo.getUnusedFieldPosition();

        try
        {
            myDateFormatter.applyPattern( aPattern );
            myDateFormatter.format( aDate, myTempBuffer, myFieldPosition );
            aBuffer.append( myTempBuffer );
        }
        finally
        {
            myThreadInfo.returnTemporaryStringBuffer( myTempBuffer );
        }
    }    
    

    /**
     * This method formats a date value using the specified pattern
     * and returns a String containing the newly formatted date.
     *
     * @param       aDate
     *                  The date that will be formatted.
     * @param       aFormatter
     *                  A date formatting object used to format the specified date.
     *
     * @return      The formatted date string.
     */
    public static String formatDate( Date         aDate,
                                     DateFormat   aFormatter )
    {
        ThreadInfo   myThreadInfo = getThreadInfo();
        StringBuffer myBuffer     = myThreadInfo.getTemporaryStringBuffer();

        try
        {
            formatDate( myBuffer, aDate, aFormatter );
            
            return myBuffer.toString();
        }
        finally
        {
            myThreadInfo.returnTemporaryStringBuffer( myBuffer );
        }
    }


    /**
     * This method formats a date value using the specified pattern
     * and appends the new string to the specified string buffer.
     *
     * @param       aBuffer
     *                  The buffer that the date string will be appended to.
     * @param       aDate
     *                  The date that will be formatted.
     * @param       aFormatter
     *                  A date formatting object used to format the specified date.
     */
    public static void formatDate( StringBuffer   aBuffer,
                                   Date           aDate,
                                   DateFormat     aFormatter )
    {
        ThreadInfo       myThreadInfo    = getThreadInfo();
        FieldPosition    myFieldPosition = myThreadInfo.getUnusedFieldPosition();

        aFormatter.format( aDate, aBuffer, myFieldPosition );
    }


    /**
     * This method formats a date value using the specified pattern
     * and appends the new string to the specified string buffer.
     *
     * @param       aBuffer
     *                  The buffer that the date string will be appended to.
     * @param       aDate
     *                  The date that will be formatted.
     * @param       aFormatter
     *                  A date formatting object used to format the specified date.
     */
    public static void formatDate( StringBuilder   aBuffer,
                                   Date            aDate,
                                   DateFormat      aFormatter )
    {
        ThreadInfo       myThreadInfo    = getThreadInfo();
        StringBuffer     myTempBuffer    = myThreadInfo.getTemporaryStringBuffer();
        FieldPosition    myFieldPosition = myThreadInfo.getUnusedFieldPosition();

        try
        {
            aFormatter.format( aDate, myTempBuffer, myFieldPosition );
            aBuffer.append( myTempBuffer );
        }
        finally
        {
            myThreadInfo.returnTemporaryStringBuffer( myTempBuffer );
        }
    }


    /**
     * This method formats an integer value into a new
     * string of specified length and specified justification.
     *  
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aZeroPadded
     *                  Whether the number should have leading zeroes
     *                  or not.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @return      The formatted and justified string.
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static String formatNumber( long      aNumber,
                                       int       aSize,
                                       boolean   aZeroPadded,
                                       Justify   aJustification )
    {
        return formatNumber( aNumber, aSize, aZeroPadded, aJustification, 10 );
    }


    /**
     * This method formats an integer value into a new
     * string of specified length and specified justification.
     *  
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aZeroPadded
     *                  Whether the number should have leading zeroes
     *                  or not.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     * @param       aRadix
     *                  The radix to use when converting the integer.
     *
     * @return      The formatted and justified string.
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static String formatNumber( long      aNumber,
                                       int       aSize,
                                       boolean   aZeroPadded,
                                       Justify   aJustification,
                                       int       aRadix          )
    {
        ThreadInfo    myThreadInfo = getThreadInfo();
        StringBuilder myBuffer     = myThreadInfo.getTemporaryStringBuilder();

        try
        {
            formatNumber( myBuffer, aNumber, aSize, aZeroPadded, aJustification );
    
            return myBuffer.toString();
        }
        finally
        {
            myThreadInfo.returnTemporaryStringBuilder( myBuffer );
        }
    }


    /**
     * This method formats an integer value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aZeroPadded
     *                  Whether the number should have leading zeroes
     *                  or not.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static void formatNumber( StringBuffer   aBuffer,
                                     long           aNumber,
                                     int            aSize,
                                     boolean        aZeroPadded,
                                     Justify        aJustification )
    {
        formatNumber( aBuffer, aNumber, aSize, aZeroPadded, aJustification, 10 );
    }


    /**
     * This method formats an integer value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aZeroPadded
     *                  Whether the number should have leading zeroes
     *                  or not.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     * @param       aRadix
     *                  The radix to use when converting the integer.
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static void formatNumber( StringBuffer   aBuffer,
                                     long           aNumber,
                                     int            aSize,
                                     boolean        aZeroPadded,
                                     Justify        aJustification,
                                     int            aRadix          )
    {
        StringBufferAdapter myBufferAdapter = getThreadInfo().getStringBufferAdapter( aBuffer );
        
        formatNumber( myBufferAdapter, aNumber, aSize, aZeroPadded, aJustification, aRadix );
    }


    /**
     * This method formats an integer value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aZeroPadded
     *                  Whether the number should have leading zeroes
     *                  or not.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static void formatNumber( StringBuilder   aBuffer,
                                     long            aNumber,
                                     int             aSize,
                                     boolean         aZeroPadded,
                                     Justify         aJustification )
    {
        formatNumber( aBuffer, aNumber, aSize, aZeroPadded, aJustification, 10 );
    }


    /**
     * This method formats an integer value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aZeroPadded
     *                  Whether the number should have leading zeroes
     *                  or not.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     * @param       aRadix
     *                  The radix to use when converting the integer.
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    public static void formatNumber( StringBuilder   aBuffer,
                                     long            aNumber,
                                     int             aSize,
                                     boolean         aZeroPadded,
                                     Justify         aJustification,
                                     int             aRadix          )
    {
        StringBuilderAdapter myBufferAdapter = getThreadInfo().getStringBuilderAdapter( aBuffer );
        
        formatNumber( myBufferAdapter, aNumber, aSize, aZeroPadded, aJustification, aRadix );
    }
    

    /**
     * This method formats an integer value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aZeroPadded
     *                  Whether the number should have leading zeroes
     *                  or not.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     * @param       aRadix
     *                  The radix to use when converting the integer.
     *
     * @exception   IllegalArgumentException
     *                  A negative value was passed as the size of the
     *                  string to be formatted.
     */
    private static void formatNumber( BufferAdapter   aBuffer,
                                      long            aNumber,
                                      int             aSize,
                                      boolean         aZeroPadded,
                                      Justify         aJustification,
                                      int             aRadix          )
    {
        if ( aSize < 0 )
        {
            throw new IllegalArgumentException( "Invalid output string size (" + aSize + ") specified" );
        }

        if ( aSize != 0 )
        {
            StringBuilder myFormatBuffer = getFormattedNumber( aNumber, aSize, aZeroPadded, '0', aRadix );

            try
            {
                int myBufferOffset = aBuffer.length();
                stringOfCharacters( aBuffer, ' ', aSize );
                embedString( aBuffer, myBufferOffset, aSize, myFormatBuffer, aJustification );
            }
            finally
            {
                getThreadInfo().returnTemporaryStringBuilder( myFormatBuffer );
            }
        }
    }


    /**
     * This method formats a double value into a new
     * string of specified length and specified justification.
     *  
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aTotalIntegerDigits
     *                  The exact number of integer digits to be shown;
     *                  if less than zero, then zero is used.
     * @param       aTotalFractionDigits
     *                  The exact number of fraction digits to be shown;
     *                  if less than zero, then zero is used.
     * @param       aShowDecimalSeparator
     *                  Indicates whether the decimal separator should
     *                  be shown (true) or not shown (false).
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @return      The formatted and justified string.
     *
     * @see         java.text.NumberFormat
     *
     * @exception   IllegalArgumentException
     *                  <li>A negative value was passed as the size of the
     *                  string to be formatted.</li
     *                  <li>Either he number of integer or fraction digits
     *                  must be greater than 0.</li>
     */
    public static String formatNumber( double    aNumber,
                                       int       aSize,
                                       int       aTotalIntegerDigits,
                                       int       aTotalFractionDigits,
                                       boolean   aShowDecimalSeparator,
                                       Justify   aJustification         )
    {
        ThreadInfo    myThreadInfo = getThreadInfo();
        StringBuilder myBuffer     = myThreadInfo.getTemporaryStringBuilder();

        try
        {
            formatNumber( myBuffer,
                          aNumber,
                          aSize,
                          aTotalIntegerDigits,
                          aTotalFractionDigits,
                          aShowDecimalSeparator,
                          aJustification         );
            
            return myBuffer.toString();
        }
        finally
        {
            myThreadInfo.returnTemporaryStringBuilder( myBuffer );
        }
    }


    /**
     * This method formats a double value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aTotalIntegerDigits
     *                  The exact number of integer digits to be shown;
     *                  if less than zero, then zero is used.
     * @param       aTotalFractionDigits
     *                  The exact number of fraction digits to be shown;
     *                  if less than zero, then zero is used.
     * @param       aShowDecimalSeparator
     *                  Indicates whether the decimal separator should
     *                  be shown (true) or not shown (false).
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @see         java.text.NumberFormat
     *
     * @exception   IllegalArgumentException
     *                  <li>A negative value was passed as the size of the
     *                  string to be formatted.</li
     *                  <li>Either he number of integer or fraction digits
     *                  must be greater than 0.</li>
     */
    public static void formatNumber( StringBuffer   aBuffer,
                                     double         aNumber,
                                     int            aSize,
                                     int            aTotalIntegerDigits,
                                     int            aTotalFractionDigits,
                                     boolean        aShowDecimalSeparator,
                                     Justify        aJustification         )
    {
        StringBufferAdapter myBufferAdapter = getThreadInfo().getStringBufferAdapter( aBuffer );
        
        formatNumber( myBufferAdapter,
                      aNumber,
                      aSize,
                      aTotalIntegerDigits,
                      aTotalFractionDigits,
                      aShowDecimalSeparator,
                      aJustification         );
    }


    /**
     * This method formats a double value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aTotalIntegerDigits
     *                  The exact number of integer digits to be shown;
     *                  if less than zero, then zero is used.
     * @param       aTotalFractionDigits
     *                  The exact number of fraction digits to be shown;
     *                  if less than zero, then zero is used.
     * @param       aShowDecimalSeparator
     *                  Indicates whether the decimal separator should
     *                  be shown (true) or not shown (false).
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @see         java.text.NumberFormat
     *
     * @exception   IllegalArgumentException
     *                  <li>A negative value was passed as the size of the
     *                  string to be formatted.</li
     *                  <li>Either he number of integer or fraction digits
     *                  must be greater than 0.</li>
     */
    public static void formatNumber( StringBuilder   aBuffer,
                                     double          aNumber,
                                     int             aSize,
                                     int             aTotalIntegerDigits,
                                     int             aTotalFractionDigits,
                                     boolean         aShowDecimalSeparator,
                                     Justify         aJustification         )
    {
        StringBuilderAdapter myBufferAdapter = getThreadInfo().getStringBuilderAdapter( aBuffer );
        
        formatNumber( myBufferAdapter,
                      aNumber,
                      aSize,
                      aTotalIntegerDigits,
                      aTotalFractionDigits,
                      aShowDecimalSeparator,
                      aJustification         );
    }    

    
    /**
     * This method formats a double value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aTotalIntegerDigits
     *                  The exact number of integer digits to be shown;
     *                  if less than zero, then zero is used.
     * @param       aTotalFractionDigits
     *                  The exact number of fraction digits to be shown;
     *                  if less than zero, then zero is used.
     * @param       aShowDecimalSeparator
     *                  Indicates whether the decimal separator should
     *                  be shown (true) or not shown (false).
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @see         java.text.NumberFormat
     *
     * @exception   IllegalArgumentException
     *                  <li>A negative value was passed as the size of the
     *                  string to be formatted.</li
     *                  <li>Either he number of integer or fraction digits
     *                  must be greater than 0.</li>
     */
    private static void formatNumber( BufferAdapter   aBuffer,
                                      double          aNumber,
                                      int             aSize,
                                      int             aTotalIntegerDigits,
                                      int             aTotalFractionDigits,
                                      boolean         aShowDecimalSeparator,
                                      Justify         aJustification         )
    {
        if ( aSize < 0 )
        {
            throw new IllegalArgumentException( "Invalid output string size (" + aSize + ") specified" );
        }

        if ( aTotalIntegerDigits <= 0 && aTotalFractionDigits <= 0 )
        {
            throw new IllegalArgumentException( "Either the number of integer digits or fraction digits must be > 0" );
        }

        if ( aSize != 0 )
        {
            ThreadInfo    myThreadInfo           = getThreadInfo();
            StringBuffer  myTempBuffer           = myThreadInfo.getTemporaryStringBuffer();
            NumberFormat  myFormatter            = myThreadInfo.getNumberFormatter();
            FieldPosition myFieldPosition        = myThreadInfo.getUnusedFieldPosition();
            int           myActualIntegerDigits  = Math.max( 0, aTotalIntegerDigits  );
            int           myActualFractionDigits = Math.max( 0, aTotalFractionDigits );

            myFormatter.setMinimumIntegerDigits ( myActualIntegerDigits  );
            myFormatter.setMaximumIntegerDigits ( myActualIntegerDigits  );
            myFormatter.setMinimumFractionDigits( myActualFractionDigits );
            myFormatter.setMaximumFractionDigits( myActualFractionDigits );
            myFormatter.setGroupingUsed         ( false                  );

            try
            {
                myFormatter.format( aNumber, myTempBuffer, myFieldPosition );
                if ( ! aShowDecimalSeparator )
                {
                    if ( aNumber < 0.0 )
                    {
                        myTempBuffer.deleteCharAt( myActualIntegerDigits + 1 );
                    }
                    else
                    {
                        myTempBuffer.deleteCharAt( myActualIntegerDigits );
                    }
                }
                formatString( aBuffer, myTempBuffer, aSize, aJustification );
            }
            finally
            {
                myThreadInfo.returnTemporaryStringBuffer( myTempBuffer );
            }
        }
    }


    /**
     * This method formats a double value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aFormatter
     *                  The formatter to use to format the number.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @see         java.text.NumberFormat
     *
     * @exception   IllegalArgumentException
     *                  <li>A negative value was passed as the size of the
     *                  string to be formatted.</li
     *                  <li>Either he number of integer or fraction digits
     *                  must be greater than 0.</li>
     */
    public static void formatNumber( StringBuffer   aBuffer,
                                     double         aNumber,
                                     int            aSize,
                                     NumberFormat   aFormatter,
                                     Justify        aJustification )
    {
        StringBufferAdapter myBufferAdapter = getThreadInfo().getStringBufferAdapter( aBuffer );
        
        formatNumber( myBufferAdapter, aNumber, aSize, aFormatter, aJustification );
    }


    /**
     * This method formats a double value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aFormatter
     *                  The formatter to use to format the number.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @see         java.text.NumberFormat
     *
     * @exception   IllegalArgumentException
     *                  <li>A negative value was passed as the size of the
     *                  string to be formatted.</li
     *                  <li>Either he number of integer or fraction digits
     *                  must be greater than 0.</li>
     */
    public static void formatNumber( StringBuilder   aBuffer,
                                     double          aNumber,
                                     int             aSize,
                                     NumberFormat    aFormatter,
                                     Justify         aJustification )
    {
        StringBuilderAdapter myBufferAdapter = getThreadInfo().getStringBuilderAdapter( aBuffer );
        
        formatNumber( myBufferAdapter, aNumber, aSize, aFormatter, aJustification );
    }
    

    /**
     * This method formats a double value into a new
     * string of specified length and specified justification, appending
     * that string to the specified buffer.
     *  
     * @param       aBuffer
     *                  The buffer that the formatted string will be
     *                  appended to.
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aFormatter
     *                  The formatter to use to format the number.
     * @param       aJustification
     *                  The justification style to be used (i.e. left,
     *                  right, or center justified).
     *
     * @see         java.text.NumberFormat
     *
     * @exception   IllegalArgumentException
     *                  <li>A negative value was passed as the size of the
     *                  string to be formatted.</li
     *                  <li>Either he number of integer or fraction digits
     *                  must be greater than 0.</li>
     */
    private static void formatNumber( BufferAdapter   aBuffer,
                                      double          aNumber,
                                      int             aSize,
                                      NumberFormat    aFormatter,
                                      Justify         aJustification )
    {
        if ( aSize < 0 )
        {
            throw new IllegalArgumentException( "Invalid output string size (" + aSize + ") specified" );
        }

        if ( aSize != 0 )
        {
            ThreadInfo    myThreadInfo    = getThreadInfo();
            StringBuffer  myTempBuffer    = myThreadInfo.getTemporaryStringBuffer();
            FieldPosition myFieldPosition = myThreadInfo.getUnusedFieldPosition();

            try
            {
                aFormatter.format( aNumber, myTempBuffer, myFieldPosition );
                formatString( aBuffer, myTempBuffer, aSize, aJustification );
            }
            finally
            {
                myThreadInfo.returnTemporaryStringBuffer( myTempBuffer );
            }
        }
    }


    /**
     * This method forms a string of given length, filled with the
     * specified character.
     *  
     * @param       aCharacter
     *                  The character to be repeated in the string.
     * @param       aSize
     *                  The length of the string of repeat characters.
     *
     * @return      The string of specified length filled with the
     *              supplied character.
     */
    public static String stringOfCharacters( char  aCharacter,
                                             int   aSize       )
    {
        String myString;

        if ( aSize <= 0 )
        {
            myString = "";
        }
        else
        {
            ThreadInfo    myThreadInfo = getThreadInfo();
            StringBuilder myBuffer     = myThreadInfo.getTemporaryStringBuilder();

            try
            {
                stringOfCharacters( myBuffer, aCharacter, aSize );
                
                myString = myBuffer.toString();
            }
            finally
            {
                myThreadInfo.returnTemporaryStringBuilder( myBuffer );
            }
        }

        return myString;
    }


    /**
     * This method forms a string of given length, filled with the
     * specified character, and appends it to the specified buffer.
     *  
     * @param       aBuffer
     *                  The string buffer that the characters will
     *                  be appended to.
     * @param       aCharacter
     *                  The character to be repeated in the string.
     * @param       aSize
     *                  The length of the string of repeat characters.
     */
    public static void stringOfCharacters( StringBuffer   aBuffer,
                                           char           aCharacter,
                                           int            aSize       )
    {
        StringBufferAdapter myBufferAdapter = getThreadInfo().getStringBufferAdapter( aBuffer );
        
        stringOfCharacters( myBufferAdapter, aCharacter, aSize );
    }


    /**
     * This method forms a string of given length, filled with the
     * specified character, and appends it to the specified buffer.
     *  
     * @param       aBuffer
     *                  The string buffer that the characters will
     *                  be appended to.
     * @param       aCharacter
     *                  The character to be repeated in the string.
     * @param       aSize
     *                  The length of the string of repeat characters.
     */
    public static void stringOfCharacters( StringBuilder   aBuffer,
                                           char            aCharacter,
                                           int             aSize       )
    {
        StringBuilderAdapter myBufferAdapter = getThreadInfo().getStringBuilderAdapter( aBuffer );
        
        stringOfCharacters( myBufferAdapter, aCharacter, aSize );
    }
    

    /**
     * This method forms a string of given length, filled with the
     * specified character, and appends it to the specified buffer.
     *  
     * @param       aBuffer
     *                  The string buffer that the characters will
     *                  be appended to.
     * @param       aCharacter
     *                  The character to be repeated in the string.
     * @param       aSize
     *                  The length of the string of repeat characters.
     */
    private static void stringOfCharacters( BufferAdapter   aBuffer,
                                            char            aCharacter,
                                            int             aSize       )
    {
        try
        {
            aBuffer.ensureCapacity( aBuffer.length() + aSize );
            for ( int i = 0 ; i < aSize ; i++ )
            {
                aBuffer.append( aCharacter );
            }
        }
        catch ( IOException myIOException )
        {
            // Ignore - the Appendable is either a StringBuilder or a StringBuffer
        }
    }


    /**
     * This method formats the specified number in a buffer, accounting
     * for the specified size, padding character, and radix.
     *
     * @param       aNumber
     *                  The number to be formatted.
     * @param       aSize
     *                  The length of the formatted string.
     * @param       aIsPadded
     *                  Whether the number should have leading padding.
     * @param       aPadChar
     *                  The character to use for padding.
     * @param       aRadix
     *                  The radix to use when converting the integer.
     *
     * @return      A buffer containing the formatted number.
     */
    private static StringBuilder getFormattedNumber( long      aNumber,
                                                     int       aSize,
                                                     boolean   aIsPadded,
                                                     char      aPadChar,
                                                     int       aRadix     )
    {
        StringBuilder myBuffer = getThreadInfo().getTemporaryStringBuilder();

        boolean myIsNegative = false;
        if ( aNumber < 0 )
        {
            myIsNegative = true;
            aNumber      = -aNumber;
        }

        while ( aNumber != 0 )
        {
            int myDigit = (int) (aNumber % aRadix);
            myBuffer.append( Character.forDigit( myDigit, aRadix ) );

            aNumber /= aRadix;
        }

        if ( aIsPadded )
        {
            int myZeroStringLength = aSize - myBuffer.length();
            if ( myIsNegative )
            {
                myZeroStringLength--;
            }

            while ( myZeroStringLength > 0 )
            {
                myBuffer.append( aPadChar );
                myZeroStringLength--;
            }
        }

        if ( myIsNegative )
        {
            myBuffer.append( '-' );
        }

        myBuffer.reverse();

        return myBuffer;
    }


    /**
     * This method returns the <code>ThreadInfo</code> object that
     * has been associated with the current thread. If the thread
     * does not have a <code>ThreadInfo</code> object associated with
     * it, one is created and then associated with the current thread.
     *
     * @return      The <code>ThreadInfo</code> object of the
     *              current thread.
     */
    private static ThreadInfo getThreadInfo()
    {
        return threadLocalInfo.get();
    }
}
