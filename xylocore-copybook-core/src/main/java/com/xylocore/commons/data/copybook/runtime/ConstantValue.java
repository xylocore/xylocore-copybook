package com.xylocore.commons.data.copybook.runtime;


/**
 * FILLIN
 *
 * @author      Eric R. Medley
 */

public class ConstantValue
    implements
        Comparable<Comparable<?>>
{
    //
    // Members
    //
    
    
    private Comparable<?> value1;
    private Comparable<?> value2;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     *
     * @param       aValue
     */
    public ConstantValue( Comparable<?> aValue )
    {
        this( aValue, null );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aValue1
     * @param       aValue2
     */
    public ConstantValue( Comparable<?>   aValue1,
                          Comparable<?>   aValue2  )
    {
        if ( aValue1 == null )
        {
            throw new IllegalArgumentException( "aValue1 cannot be null" );
        }
        if ( aValue2 != null )
        {
            if ( aValue1.getClass() != aValue2.getClass() )
            {
                throw new IllegalArgumentException( "aValue1 and aValue2 must be the same type" );
            }
            if ( compareValues( aValue1, aValue2 ) > 0 )
            {
                throw new IllegalArgumentException( "aValue1 must be less than or equal to aValue2" );
            }
        }
        
        value1 = aValue1;
        value2 = aValue2;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public Comparable<?> getValue1()
    {
        return value1;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public Comparable<?> getValue2()
    {
        return value2;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aOpaqueLhs
     * @param       aOpaqueRhs
     * 
     * @return
     */
    protected int compareValues( Comparable<?>   aOpaqueLhs,
                                 Comparable<?>   aOpaqueRhs  )
    {
        assert aOpaqueLhs != null;
        assert aOpaqueRhs != null;

        @SuppressWarnings( "unchecked" )
        Comparable<Object> myLhs = (Comparable<Object>) aOpaqueLhs;
        
        @SuppressWarnings( "unchecked" )
        Comparable<Object> myRhs = (Comparable<Object>) aOpaqueRhs;
        
        return myLhs.compareTo( myRhs );
    }
    
    
    
    
    //
    // Comparable interface implementation
    //
    

    public int compareTo( Comparable<?> aRhs )
    {
        int myCmp = compareValues( aRhs, value1 );
        if ( myCmp > 0 && value2 != null )
        {
            myCmp = compareValues( aRhs, value2 );
            if ( myCmp < 0 )
            {
                myCmp = 0;
            }
        }
        
        return -myCmp;
    }
}
