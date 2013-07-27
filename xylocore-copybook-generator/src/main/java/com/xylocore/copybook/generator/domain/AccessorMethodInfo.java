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


package com.xylocore.copybook.generator.domain;

import com.xylocore.copybook.runtime.DataType;
import com.xylocore.copybook.runtime.converters.Converter;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class AccessorMethodInfo
    implements
        Visitable
{
    //
    // Members
    //
    
    
    private DataType    dataType;
    private boolean     isDefault;
    private Converter   converter;
    private String      converterInstanceVariableName;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * Default constructor.
     */
    public AccessorMethodInfo()
    {
        this( null );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     */
    public AccessorMethodInfo( DataType aDataType )
    {
        dataType  = aDataType;
        isDefault = false;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public DataType getDataType()
    {
        return dataType;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataType
     */
    public void setDataType( DataType aDataType )
    {
        dataType = aDataType;
    }
    
    
    /**
     * FILLIN
     *
     * @return
     */
    public boolean isDefault()
    {
        return isDefault;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aIsDefault
     */
    public void setDefault( boolean aIsDefault )
    {
        isDefault = aIsDefault;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public Converter getConverter()
    {
        return converter;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aConverter
     */
    public void setConverter( Converter aConverter )
    {
        converter = aConverter;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    public String getConverterInstanceVariableName()
    {
        return converterInstanceVariableName;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVariableName
     */
    public void setConverterInstanceVariableName( String aVariableName )
    {
        converterInstanceVariableName = aVariableName;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptVisit( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.visitAccessorMethodInfo( this );
    }


    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptChildren( Visitor aVisitor )
    {
        assert aVisitor != null;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aVisitor
     */
    protected void acceptLeave( Visitor aVisitor )
    {
        assert aVisitor != null;
        
        aVisitor.leaveAccessorMethodInfo( this );
    }
    
    
    
    
    
    
    
    //
    // Visitable interface implementation
    //
    
    
    @Override
    public void accept( Visitor aVisitor )
    {
        acceptVisit   ( aVisitor );
        acceptChildren( aVisitor );
        acceptLeave   ( aVisitor );
    }
}
