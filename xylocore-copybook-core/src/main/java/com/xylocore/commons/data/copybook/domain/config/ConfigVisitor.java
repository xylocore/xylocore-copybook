package com.xylocore.commons.data.copybook.domain.config;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ConfigVisitor
{
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public void visitEnvironment( Environment aEnvironment )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public void leaveEnvironment( Environment aEnvironment )
    {
    }
    

    /**
     * FILLIN
     * 
     * @param       aMappingMetadata
     */
    public void visitMappingMetadata( MappingMetadata aMappingMetadata )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aMappingMetadata
     */
    public void leaveMappingMetadata( MappingMetadata aMappingMetadata )
    {
    }
    

    /**
     * FILLIN
     * 
     * @param       aGroupElement
     */
    public void visitGroupElement( GroupElementConfig aGroupElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aGroupElement
     */
    public void leaveGroupElement( GroupElementConfig aGroupElement )
    {
    }
    

    /**
     * FILLIN
     * 
     * @param       aDataElement
     */
    public void visitDataElement( DataElementConfig aDataElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataElement
     */
    public void leaveDataElement( DataElementConfig aDataElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void visitElement( ElementConfig aElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void leaveElement( ElementConfig aElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    public boolean shouldVisitElementFilters( DataElementConfig aElement )
    {
        return false;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementFilter
     * 
     * @return
     */
    public boolean shouldVisitElementFilter( ElementFilterConfig aElementFilter )
    {
        return true;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementFilter
     */
    public void visitElementFilter( ElementFilterConfig aElementFilter )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementFilter
     */
    public void leaveElementFilter( ElementFilterConfig aElementFilter )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementFilter
     */
    public void visitIncludedElementFilter( IncludedElementFilterConfig aElementFilter )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementFilter
     */
    public void leaveIncludedElementFilter( IncludedElementFilterConfig aElementFilter )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementFilter
     */
    public void visitExcludedElementFilter( ExcludedElementFilterConfig aElementFilter )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElementFilter
     */
    public void leaveExcludedElementFilter( ExcludedElementFilterConfig aElementFilter )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     * 
     * @return
     */
    public boolean shouldVisitAccessors( DataElementConfig aElement )
    {
        return false;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     * 
     * @return
     */
    public boolean shouldVisitAccessor( AccessorConfig aAccessor )
    {
        return true;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitAccessor( AccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveAccessor( AccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitByteAccessor( ByteAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveByteAccessor( ByteAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitCharAccessor( CharAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveCharAccessor( CharAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitShortAccessor( ShortAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveShortAccessor( ShortAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitIntegerAccessor( IntegerAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveIntegerAccessor( IntegerAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitLongAccessor( LongAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveLongAccessor( LongAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitFloatAccessor( FloatAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveFloatAccessor( FloatAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitDoubleAccessor( DoubleAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveDoubleAccessor( DoubleAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitBigIntegerAccessor( BigIntegerAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveBigIntegerAccessor( BigIntegerAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitBigDecimalAccessor( BigDecimalAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveBigDecimalAccessor( BigDecimalAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitStringAccessor( StringAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveStringAccessor( StringAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitDateAccessor( DateAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveDateAccessor( DateAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveDefaultAccessor( DefaultAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void visitNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aAccessor
     */
    public void leaveNoDefaultAccessor( NoDefaultAccessorConfig aAccessor )
    {
    }
    

    /**
     * FILLIN
     * 
     * @param       aDataElement
     * 
     * @return
     */
    public boolean shouldVisitNullEquivalents( DataElementConfig aDataElement )
    {
        return false;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     * 
     * @return
     */
    public boolean shouldVisitNullEquivalent( NullEquivalentConfig aNullEquivalent )
    {
        return true;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     */
    public void visitNullEquivalent( NullEquivalentConfig aNullEquivalent )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     */
    public void leaveNullEquivalent( NullEquivalentConfig aNullEquivalent )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     */
    public void visitBlankNullEquivalent( BlankNullEquivalentConfig aNullEquivalent )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     */
    public void leaveBlankNullEquivalent( BlankNullEquivalentConfig aNullEquivalent )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     */
    public void visitConstantNullEquivalent( ConstantNullEquivalentConfig aNullEquivalent )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNullEquivalent
     */
    public void leaveConstantNullEquivalent( ConstantNullEquivalentConfig aNullEquivalent )
    {
    }
}
