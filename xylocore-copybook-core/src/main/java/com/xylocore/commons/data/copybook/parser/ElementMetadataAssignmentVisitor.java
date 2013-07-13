package com.xylocore.commons.data.copybook.parser;

import java.util.List;

import com.xylocore.commons.data.copybook.domain.Copybook;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.config.ConfigVisitor;
import com.xylocore.commons.data.copybook.domain.config.DataElementConfig;
import com.xylocore.commons.data.copybook.domain.config.Environment;
import com.xylocore.commons.data.copybook.domain.config.GroupElementConfig;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

class ElementMetadataAssignmentVisitor
    extends
        ConfigVisitor
{
    //
    // Members
    //
    
    
    private FilterCollectionVisitor     filterCollectionVisitor     = new FilterCollectionVisitor();
    private Copybook                    copybook;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aCopybook
     */
    public ElementMetadataAssignmentVisitor( Copybook aCopybook )
    {
        assert aCopybook != null;
        
        copybook = aCopybook;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aEnvironment
     */
    public void assign( Environment aEnvironment )
    {
        assert aEnvironment != null;
        
        aEnvironment.accept( this );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitGroupElement(com.xylocore.commons.data.copybook.domain.config.GroupElementConfig)
     */
    public void visitGroupElement( GroupElementConfig aGroupElement )
    {
        
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveGroupElement(com.xylocore.commons.data.copybook.domain.config.GroupElementConfig)
     */
    public void leaveGroupElement( GroupElementConfig aGroupElement )
    {
        
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#visitDataElement(com.xylocore.commons.data.copybook.domain.config.DataElementConfig)
     */
    public void visitDataElement( DataElementConfig aDataElement )
    {
        ElementFilter[] myFilters = filterCollectionVisitor.collectFilters( aDataElement );
        List<Element>   myMatches = copybook.getElementDictionary().getMatchingElements( myFilters );

        for ( Element myElement : myMatches )
        {
//            if ( myElement.getElementType() == ElementType.ElementaryItem )
            {
                myElement.addMetadata( aDataElement );
            }
        }
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.config.ConfigVisitor#leaveDataElement(com.xylocore.commons.data.copybook.domain.config.DataElementConfig)
     */
    public void leaveDataElement( DataElementConfig aDataElement )
    {
        
    }
}
