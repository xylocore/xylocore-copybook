package com.xylocore.commons.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class DateHelper
{
    //
    // Members
    //
    
    
    private static final ThreadLocal<Map<String,SimpleDateFormat>> threadLocalSimpleDateFormat;
    
    
    
    
    //
    // Static initializer
    //
    
    
    static
    {
        threadLocalSimpleDateFormat =
                new ThreadLocal<Map<String,SimpleDateFormat>>()
                    {
                        protected Map<String,SimpleDateFormat> initialValue()
                        {
                            return new HashMap<>();
                        }
                    };
    }
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     * 
     * @param       aPattern
     * 
     * @return
     */
    public static SimpleDateFormat getThreadLocalSimpleDateFormat( String aPattern )
    {
        Map<String,SimpleDateFormat> myMappings = threadLocalSimpleDateFormat.get();
        SimpleDateFormat             myFormat   = myMappings.get( aPattern );
        
        if ( myFormat == null )
        {
            myFormat = new SimpleDateFormat( aPattern );
            myFormat.setLenient( false );
            
            myMappings.put( aPattern, myFormat );
        }
        
        return myFormat;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aBirthdate
     * 
     * @return
     */
    public static int calculateAge( Date aBirthdate )
    {
        Calendar myAge = Calendar.getInstance( Locale.getDefault() );
        myAge.setTimeInMillis( Math.abs( aBirthdate.getTime() - System.currentTimeMillis() ) );
        return myAge.get( Calendar.YEAR ) - 1970;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFormat
     * @param       aFormattedDate
     * 
     * @return
     */
    public static Date parse( SimpleDateFormat   aFormat,
                              String             aFormattedDate )
    {
        Date myDate;
        
        try
        {
            myDate = aFormat.parse( aFormattedDate );
        }
        catch ( Exception myException )
        {
            // Ignore exception - no date found
            myDate = null;
        }
        
        return myDate;
    }
}
