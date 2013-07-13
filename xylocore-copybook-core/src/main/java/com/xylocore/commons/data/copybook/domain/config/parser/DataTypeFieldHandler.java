package com.xylocore.commons.data.copybook.domain.config.parser;

import org.exolab.castor.mapping.GeneralizedFieldHandler;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * This class extends the <code>GeneralizedFieldHandler</code> and provides conversion
 * support for the data type enumerated type.
 * 
 * @author      Eric R. Medley
 */

public class DataTypeFieldHandler
    extends
        GeneralizedFieldHandler
{
    //
    // Class implementation
    //
    
    
    /* (non-Javadoc)
     * @see org.exolab.castor.mapping.GeneralizedFieldHandler#convertUponGet(java.lang.Object)
     */
    public Object convertUponGet( Object aValue )
    {
        return ( aValue != null ) ? aValue.toString() : null;
    }
    
    
    /* (non-Javadoc)
     * @see org.exolab.castor.mapping.GeneralizedFieldHandler#convertUponSet(java.lang.Object)
     */
    public Object convertUponSet( Object aValue )
    {
        return DataType.valueOf( (String) aValue );
    }
    
    
    /* (non-Javadoc)
     * @see org.exolab.castor.mapping.GeneralizedFieldHandler#getFieldType()
     */
    public Class<?> getFieldType()
    {
        return DataType.class;
    }
    
    
    /* (non-Javadoc)
     * @see org.exolab.castor.mapping.FieldHandler#newInstance(java.lang.Object)
     */
    public Object newInstance( Object aParent )
            throws IllegalStateException
    {
        return null;
    }
}
