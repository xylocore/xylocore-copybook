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

import java.util.List;

import com.xylocore.copybook.generator.domain.DataElement;
import com.xylocore.copybook.generator.domain.Element;
import com.xylocore.copybook.generator.domain.Visitor;
import com.xylocore.copybook.generator.domain.config.AccessorConfig;
import com.xylocore.copybook.generator.domain.config.DataElementConfig;
import com.xylocore.copybook.generator.domain.config.ElementConfig;
import com.xylocore.copybook.generator.domain.config.NullEquivalentConfig;


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
    

    @Override
    public boolean shouldVisitChildren( Element aParent )
    {
        return true;
    }
    
    
    @Override
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
        // TODO: implement
        return false;
    }
    

    /**
     * FILLIN
     * 
     * @param       aLhsNullEquivalent
     * @param       aRhsNullEquivalent
     * 
     * @return
     */
    private boolean checkForNullEquivalentConflicts( NullEquivalentConfig   aLhsNullEquivalent,
                                                     NullEquivalentConfig   aRhsNullEquivalent  )
    {
        // TODO: implement
        return false;
    }
}
