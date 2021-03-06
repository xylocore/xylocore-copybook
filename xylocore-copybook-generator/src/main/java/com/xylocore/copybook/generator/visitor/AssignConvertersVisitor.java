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


package com.xylocore.copybook.generator.visitor;

import java.util.ArrayList;
import java.util.List;

import com.xylocore.copybook.generator.domain.BigDecimalAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.DateAccessorMethodInfo;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.Visitor;
import com.xylocore.copybook.runtime.converters.BigDecimalConverter;
import com.xylocore.copybook.runtime.converters.SimpleDateFormatDateConverter;


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
    
    
    private List<DataElement> dataElementStack = new ArrayList<>();
    
    
    
    
    //
    // Class implementation
    //


    @Override
    public boolean shouldVisitChildren( Element aParent )
    {
        return true;
    }
    
    
    @Override
    public void visitDataElement( DataElement aDataElement )
    {
        dataElementStack.add( aDataElement );
    }
    
    
    @Override
    public void leaveDataElement( DataElement aDataElement )
    {
        dataElementStack.remove( dataElementStack.size()-1 );
    }
    
    
    @Override
    public void visitDateAccessorMethodInfo( DateAccessorMethodInfo aMethodInfo )
    {
        SimpleDateFormatDateConverter myConverter = new SimpleDateFormatDateConverter( aMethodInfo.getPattern() );
        
        aMethodInfo.setConverter( myConverter );
    }
    
    
    @Override
    public void visitBigDecimalAccessorMethodInfo( BigDecimalAccessorMethodInfo aMethodInfo )
    {
        BigDecimalConverter myConverter = new BigDecimalConverter( aMethodInfo.getScale() );
        
        aMethodInfo.setConverter( myConverter );
    }
}
