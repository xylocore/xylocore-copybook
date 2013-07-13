package com.xylocore.commons.data.copybook.parser;

import java.util.List;

import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.Visitor;
import com.xylocore.commons.data.copybook.domain.config.AccessorConfig;
import com.xylocore.commons.data.copybook.domain.config.DataElementConfig;
import com.xylocore.commons.data.copybook.domain.config.ElementConfig;
import com.xylocore.commons.data.copybook.domain.config.NullEquivalentConfig;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class ValidateElementMetadataVisitor
    extends
        Visitor
{
    //
    // Members
    //
    
    
    private boolean conflicts;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void validate( Element aElement )
    {
        conflicts = false;
        
        aElement.accept( this );
    }
    

    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#shouldVisitChildren(com.xylocore.commons.data.copybook.domain.Element)
     */
    public boolean shouldVisitChildren( Element aParent )
    {
        return true;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitDataElement(com.xylocore.commons.data.copybook.domain.DataElement)
     */
    public void visitDataElement( DataElement aElement )
    {
        assert aElement != null;
        
        List<ElementConfig> myMetadataItems     = aElement.getMetadata();
        int                 myMetadataItemCount = myMetadataItems.size();
        
        for ( int i = 0 ; i < myMetadataItemCount ; i++ )
        {
            DataElementConfig myCurrentDataElementConfig = (DataElementConfig) myMetadataItems.get( i );
            
            for ( int j = i+1 ; j < myMetadataItemCount ; j++ )
            {
                DataElementConfig myOtherDataElementConfig = (DataElementConfig) myMetadataItems.get( j );
                checkForConflicts( myCurrentDataElementConfig, myOtherDataElementConfig );
            }
        }

        if ( conflicts )
        {
            // TODO: report the conflicts
            throw new RuntimeException( "Conflicts found in the metadata" );
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhs
     * @param       aRhs
     */
    private void checkForConflicts( DataElementConfig   aLhs,
                                    DataElementConfig   aRhs  )
    {
        for ( AccessorConfig myLhsAccessor : aLhs.getAccessors() )
        {
            for ( AccessorConfig myRhsAccessor : aRhs.getAccessors() )
            {
                checkForAccessorConflicts( myLhsAccessor, myRhsAccessor );
            }
        }
        
        for ( NullEquivalentConfig myLhsNullEquivalent : aLhs.getNullEquivalents() )
        {
            for ( NullEquivalentConfig myRhsNullEquivalent : aRhs.getNullEquivalents() )
            {
                checkForNullEquivalentConflicts( myLhsNullEquivalent, myRhsNullEquivalent );
            }
        }
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aLhsAccessor
     * @param       aRhsAccessor
     * 
     * @return
     */
    private boolean checkForAccessorConflicts( AccessorConfig   aLhsAccessor,
                                               AccessorConfig   aRhsAccessor  )
    {
//        if
//        (
//                aLhsAccessor.g
//        )
//        {
//            
//        }
        
        // TODO: implement
        return false;
    }
    
    
    private boolean checkForNullEquivalentConflicts( NullEquivalentConfig   aLhsNullEquivalent,
                                                     NullEquivalentConfig   aRhsNullEquivalent  )
    {
        // TODO: implement
        return false;
    }
}
