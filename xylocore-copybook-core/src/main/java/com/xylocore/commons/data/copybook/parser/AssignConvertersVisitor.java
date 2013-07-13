package com.xylocore.commons.data.copybook.parser;

import java.util.ArrayList;
import java.util.List;

import com.xylocore.commons.data.copybook.domain.BigDecimalAccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.DataElement;
import com.xylocore.commons.data.copybook.domain.DateAccessorMethodInfo;
import com.xylocore.commons.data.copybook.domain.Element;
import com.xylocore.commons.data.copybook.domain.Visitor;
import com.xylocore.commons.data.copybook.runtime.converters.BigDecimalConverter;
import com.xylocore.commons.data.copybook.runtime.converters.SimpleDateFormatDateConverter;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AssignConvertersVisitor
    extends
        Visitor
{
    //
    // Members
    //
    
    
    private List<DataElement> dataElementStack = new ArrayList<DataElement>();
    
    
    
    
    //
    // Class implementation
    //

    
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
    public void visitDataElement( DataElement aDataElement )
    {
        dataElementStack.add( aDataElement );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitDataElement(com.xylocore.commons.data.copybook.domain.DataElement)
     */
    public void leaveDataElement( DataElement aDataElement )
    {
        dataElementStack.remove( dataElementStack.size()-1 );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitDateAccessorMethodInfo(com.xylocore.commons.data.copybook.domain.DateAccessorMethodInfo)
     */
    public void visitDateAccessorMethodInfo( DateAccessorMethodInfo aMethodInfo )
    {
        SimpleDateFormatDateConverter myConverter = new SimpleDateFormatDateConverter( aMethodInfo.getPattern() );
        
        aMethodInfo.setConverter( myConverter );
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.domain.Visitor#visitBigDecimalAccessorMethodInfo(com.xylocore.commons.data.copybook.domain.BigDecimalAccessorMethodInfo)
     */
    public void visitBigDecimalAccessorMethodInfo( BigDecimalAccessorMethodInfo aMethodInfo )
    {
        BigDecimalConverter myConverter = new BigDecimalConverter( aMethodInfo.getScale() );
        
        aMethodInfo.setConverter( myConverter );
    }
}
