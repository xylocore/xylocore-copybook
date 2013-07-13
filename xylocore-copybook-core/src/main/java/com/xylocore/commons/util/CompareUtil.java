package com.xylocore.commons.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;


/**
 * FILLIN
 * 
 * @author Eric Medley
 */

public class CompareUtil
{
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     * 
     * @return
     */
    public static int compare( boolean   aLhs,
                               boolean   aRhs  )
    {
        return ( ( aLhs == aRhs ) ? 0 : ( aLhs ? 1 : -1 ) );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     * 
     * @return
     */
    public static int compare( int   aLhs,
                               int   aRhs  )
    {
        return ( ( aLhs == aRhs ) ? 0 : ( aLhs < aRhs ? -1 : 1 ) );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     * 
     * @return
     */
    public static <T extends Comparable<T>> int compare( T   aLhs,
                                                         T   aRhs  )
    {
        int myCmp;
        
        if ( aLhs != null )
        {
            if ( aRhs != null )
            {
                myCmp = aLhs.compareTo( aRhs );
            }
            else
            {
                myCmp = 1;
            }
        }
        else
        {
            if ( aRhs != null )
            {
                myCmp = -1;
            }
            else
            {
                myCmp = 0;
            }
        }
        
        return myCmp;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     * 
     * @return
     */
    public static boolean isEqual( boolean   aLhs,
                                   boolean   aRhs  )
    {
        return ( aLhs == aRhs );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     * 
     * @return
     */
    public static boolean isEqual( int   aLhs,
                                   int   aRhs  )
    {
        return ( aLhs == aRhs );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     * 
     * @return
     */
    public static <T> boolean isEqual( T   aLhs,
                                       T   aRhs  )
    {
        return ( ( aLhs == null && aRhs == null ) || ( aLhs != null && aRhs != null && aLhs.equals( aRhs ) ) );
    }

    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     * 
     * @return
     */
    public static boolean isEqual( BigDecimal   aLhs,
                                   BigDecimal   aRhs  )
    {
        return ( ( aLhs == null && aRhs == null ) || ( aLhs != null && aRhs != null && aLhs.compareTo( aRhs ) == 0 ) );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     * 
     * @return
     */
    public static boolean isEqual( Date   aLhs,
                                   Date   aRhs  )
    {
        return ( ( aLhs == null && aRhs == null ) || ( aLhs != null && aRhs != null && (aLhs.getTime()/1000) == (aRhs.getTime()/1000) ) );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhsSet
     * @param       aRhsSet
     * 
     * @return
     */
    public static <T> boolean isEqual( Collection<T>   aLhsSet,
                                       Collection<T>   aRhsSet  )
    {
        boolean myIsEqual = false;
        
        if ( aLhsSet != null && aRhsSet != null )
        {
            if ( aLhsSet.size() == aRhsSet.size() )
            {
                myIsEqual = true;
                
                Iterator<T> myLhsIterator = aLhsSet.iterator();
                Iterator<T> myRhsIterator = aRhsSet.iterator();
                
                while ( myLhsIterator.hasNext() )
                {
                    Object myLhs = myLhsIterator.next();
                    Object myRhs = myRhsIterator.next();
                    
                    myIsEqual = myLhs.equals( myRhs );
                }
            }
        }
        else if ( aLhsSet == null && aRhsSet == null )
        {
            myIsEqual = true;
        }
        
        return myIsEqual;
    }
}
