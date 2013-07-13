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


package com.xylocore.commons.data.copybook.runtime.converters;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.xylocore.commons.data.copybook.runtime.CopybookContext;
import com.xylocore.commons.data.copybook.runtime.CopybookError;
import com.xylocore.commons.data.copybook.runtime.DataUsageCategory;
import com.xylocore.commons.data.copybook.runtime.SignPosition;
import com.xylocore.commons.data.copybook.runtime.SignType;
import com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller;
import com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller;
import com.xylocore.commons.util.DateHelper;
import com.xylocore.commons.util.FormatHelper;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */
public class SimpleDateFormatDateConverter
    extends
        AbstractConverter
{
    //
    // Members
    //

    
    private static final Set<DataUsageCategory> supportedDataUsageCategories;
    
    private String                              pattern;
    
    
    
    
    //
    // Static initializer
    //
    
    
    static
    {
        Set<DataUsageCategory> mySet = new HashSet<DataUsageCategory>();
        mySet.add( DataUsageCategory.Alphanumeric    );
        mySet.add( DataUsageCategory.NumericDisplay  );
        mySet.add( DataUsageCategory.NumericNational );
        
        supportedDataUsageCategories = Collections.unmodifiableSet( mySet );
    }
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aPattern
     */
    public SimpleDateFormatDateConverter( String aPattern )
    {
        assert StringUtils.isNotEmpty( aPattern );
        
        pattern = aPattern;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.nulleq.AbstractNullEquivalentStrategy#getSupportedDataUsageCategories()
     */
    protected Set<DataUsageCategory> getSupportedDataUsageCategories()
    {
        return supportedDataUsageCategories;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.AbstractConverter#isExternalNumericValid(com.xylocore.commons.data.copybook.runtime.DataUsageCategory, com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    public boolean isExternalNumericValid( DataUsageCategory              aDataUsageCategory,
                                           ExternalNumericPICMarshaller   aPICMarshaller,
                                           CopybookContext                aContext,
                                           int                            aOffset,
                                           int                            aDigits,
                                           SignType                       aSignType,
                                           SignPosition                   aSignPosition,
                                           int                            aPrecision,
                                           int                            aScalingPosition )
    {
        Object myValue =
                decodeExternalNumeric( aDataUsageCategory,
                                       aPICMarshaller,
                                       aContext,
                                       aOffset,
                                       aDigits,
                                       aSignType,
                                       aSignPosition,
                                       aPrecision,
                                       aScalingPosition    );
        
        return ( myValue != null );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.AbstractConverter#decodeExternalNumeric(com.xylocore.commons.data.copybook.runtime.DataUsageCategory, com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    protected Object decodeExternalNumeric( DataUsageCategory              aDataUsageCategory,
                                            ExternalNumericPICMarshaller   aPICMarshaller,
                                            CopybookContext                aContext,
                                            int                            aOffset,
                                            int                            aDigits,
                                            SignType                       aSignType,
                                            SignPosition                   aSignPosition,
                                            int                            aPrecision,
                                            int                            aScalingPosition )
    {
        assert aPICMarshaller != null;
        assert aContext       != null;
        
        long myValue = aPICMarshaller.decodeAsLong( aContext, aOffset, aDigits, aSignType, aSignPosition, aPrecision, aScalingPosition );
        Date myDate;

        if ( ! aContext.isError() )
        {
            StringBuilder myBuffer = aContext.getWorkStringBuilder( pattern.length() );
            FormatHelper.formatNumber( myBuffer, myValue, pattern.length(), true, FormatHelper.Justify.Right );
            
            SimpleDateFormat myDateFormat    = DateHelper.getThreadLocalSimpleDateFormat( pattern );
            ParsePosition    myParsePosition = new ParsePosition( 0 );
        
            myDate = myDateFormat.parse( myBuffer.toString(), myParsePosition );
            if ( myDate == null )
            {
                aContext.setError( CopybookError.InvalidDateFormat, null );
            }
        }
        else
        {
            myDate = null;
        }
        
        return myDate;
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.AbstractConverter#encodeExternalNumeric(com.xylocore.commons.data.copybook.runtime.DataUsageCategory, com.xylocore.commons.data.copybook.runtime.marshallers.ExternalNumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, com.xylocore.commons.data.copybook.runtime.SignType, com.xylocore.commons.data.copybook.runtime.SignPosition, int, int)
     */
    protected void encodeExternalNumeric( DataUsageCategory              aDataUsageCategory,
                                          ExternalNumericPICMarshaller   aPICMarshaller,
                                          CopybookContext                aContext,
                                          int                            aOffset,
                                          Object                         aValue,
                                          int                            aDigits,
                                          SignType                       aSignType,
                                          SignPosition                   aSignPosition,
                                          int                            aPrecision,
                                          int                            aScalingPosition )
    {
        assert aPICMarshaller != null;
        assert aContext       != null;
        
        long myValue = 0;
        
        if ( aValue != null )
        {
            SimpleDateFormat myDateFormat = DateHelper.getThreadLocalSimpleDateFormat( pattern );
            String           myString     = myDateFormat.format( aValue );
            
            try
            {
                myValue = Long.parseLong( myString );
        
                aPICMarshaller.encodeAsLong( aContext,
                                             aOffset,
                                             myValue,
                                             aDigits,
                                             aSignType,
                                             aSignPosition,
                                             aPrecision,
                                             aScalingPosition );
            }
            catch ( NumberFormatException myNumberFormatException )
            {
                aContext.setError( CopybookError.InvalidDateFormat, null );
            }
        }
        else
        {
            aContext.setError( CopybookError.NullNotAllowed, null );
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.AbstractConverter#isExternalAlphanumericValid(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    protected boolean isExternalAlphanumericValid( AlphanumericPICMarshaller   aPICMarshaller,
                                                   CopybookContext             aContext,
                                                   int                         aOffset,
                                                   int                         aSize,
                                                   int                         aFlags          )
    {
        return ( decodeExternalAlphanumeric( aPICMarshaller, aContext, aOffset, aSize, aFlags ) != null );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.AbstractConverter#decodeAlphanumeric(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, int, int)
     */
    protected Object decodeExternalAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
                                                 CopybookContext             aContext,
                                                 int                         aOffset,
                                                 int                         aSize,
                                                 int                         aFlags          )
    {
        assert aPICMarshaller != null;
        assert aContext       != null;
        
        String myString = aPICMarshaller.decodeAsString( aContext, aOffset, aSize, aFlags, null );
        Date   myDate;
        
        if ( ! aContext.isError() )
        {
            SimpleDateFormat          myDateFormat    = DateHelper.getThreadLocalSimpleDateFormat( pattern );
            ParsePosition             myParsePosition = new ParsePosition( 0 );
        
            myDate = myDateFormat.parse( myString, myParsePosition );
            if ( myDate == null )
            {
                aContext.setError( CopybookError.InvalidDateFormat, null );
            }
        }
        else
        {
            myDate = null;
        }
        
        return myDate;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.AbstractConverter#encodeExternalAlphanumeric(com.xylocore.commons.data.copybook.runtime.marshallers.AlphanumericPICMarshaller, com.xylocore.commons.data.copybook.runtime.CopybookContext, int, java.lang.Object, int, int)
     */
    protected void encodeExternalAlphanumeric( AlphanumericPICMarshaller   aPICMarshaller,
                                               CopybookContext             aContext,
                                               int                         aOffset,
                                               Object                      aValue,
                                               int                         aSize,
                                               int                         aFlags          )
    {
        assert aPICMarshaller != null;
        assert aContext       != null;

        if ( aValue != null )
        {
            SimpleDateFormat myDateFormat = DateHelper.getThreadLocalSimpleDateFormat( pattern );
            aPICMarshaller.encodeAsString( aContext, aOffset, myDateFormat.format( aValue ), aSize, aFlags, null );
        }
        else
        {
            aContext.setError( CopybookError.NullNotAllowed, null );
        }
    }
    

    
    
    //
    // Converter interface implementation
    //
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.Converter#emitDeclaration(com.xylocore.commons.util.XyStringBuffer)
     */
    public void emitDeclaration( StringBuilder aBuffer )
    {
        aBuffer.append( "new "               )
               .append( getClass().getName() )
               .append( "( \""               )
               .append( pattern              )
               .append( "\" )"               )
               ;
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.converters.AbstractConverter#compareTo(java.lang.Object)
     */
    public int compareTo( Converter aOpaqueRhs )
    {
        int myCmp = super.compareTo( aOpaqueRhs );
        if ( myCmp == 0 )
        {
            myCmp = pattern.compareTo( ((SimpleDateFormatDateConverter) aOpaqueRhs).pattern );
        }
        
        return myCmp;
    }
}
