package com.xylocore.commons.data.copybook.runtime;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ConstantCharacterString
    implements
        Comparable<ConstantCharacterString>,
        CharSequence
{
    //
    // Members
    //
    
    
    private char    constantChar;
    private int     length;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @param       aConstantChar
     * @param       aLength
     */
    public ConstantCharacterString( char   aConstantChar,
                                    int    aLength        )
    {
        if ( aLength < 0 )
        {
            throw new IllegalArgumentException( "length cannot be less than zero" );
        }
        
        constantChar = aConstantChar;
        length       = aLength;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public char getConstantChar()
    {
        return constantChar;
    }
    
    
    
    
    //
    // CharSequence interface implementation
    //
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.CharSequence#charAt(int)
     */
    public char charAt( int aIndex )
    {
        if ( aIndex < 0 || aIndex >= length )
        {
            throw new IndexOutOfBoundsException();
        }
        
        return constantChar;
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.CharSequence#length()
     */
    public int length()
    {
        return length;
    }
    
    
    /*
     * (non-Javadoc)
     * @see java.lang.CharSequence#subSequence(int, int)
     */
    public CharSequence subSequence( int   aStart,
                                     int   aEnd    )
    {
        if ( aStart < 0 || aEnd < 0 || aEnd > length || aStart > aEnd )
        {
            throw new IndexOutOfBoundsException();
        }
        
        return toString().subSequence( aStart, aEnd );
    }
    

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString()
    {
        StringBuffer myBuffer = new StringBuffer();
        for ( int i = 0, ci = length ; i < ci ; i++ )
        {
            myBuffer.append( constantChar );
        }
        
        return myBuffer.toString();
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    

    /*
     * (non-Javadoc)
     * @see java.lang.Comparable#compareTo(T)
     */
    public int compareTo( ConstantCharacterString aRhs )
    {
        int myCmp = ( constantChar < aRhs.constantChar ) ? -1 : (( constantChar > aRhs.constantChar ) ? 1 : 0);
        if ( myCmp == 0 )
        {
            myCmp = ( length < aRhs.length ) ? -1 : (( length > aRhs.length ) ? 1 : 0);
        }
        
        return myCmp;
    }
}
