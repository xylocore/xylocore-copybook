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


package com.xylocore.copybook.generator.emit;

import com.xylocore.commons.util.BufferEmitter;
import com.xylocore.copybook.generator.domain.Element;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class IndexOffsets
{
    //
    // Members
    //
    
    
    private int offset      = 0;
    private int indexCount  = 0;
    private int nestedCount = 0;
    
    
    
    
    //
    // Class implementation
    //
    

    /**
     * FILLIN
     *
     * @param       aEmitter
     * @param       aElement
     */
    public void emit( BufferEmitter   aEmitter,
                      Element         aElement  )
    {
        assert aEmitter != null;
        assert aElement != null;
        
        Element myOuterIndexElement = aElement.getOuterIndexedElement();
        if ( myOuterIndexElement != null )
        {
            increaseNesting();
            
            try
            {
                emit( aEmitter, myOuterIndexElement );
            }
            finally
            {
                decreaseNesting();
            }
        }
        
        addOffset( aElement.getOffset() );

        if ( aElement.isOccurs() )
        {
            addIndex();

            if ( getIndexCount() > 1 )
            {
                aEmitter.append( " + " );
            }
            
            int myElementWidth = aElement.getNonIndexedSize();
            
            aEmitter.append( myElementWidth  )
                    .append( "*aIndex"       )
                    .append( getIndexCount() )
                    ;
        }
        
        if ( ! isNested() )
        {
            int myCalculatedOffset = getOffset();
        
            if ( myCalculatedOffset != 0 || getIndexCount() == 0 )
            {
                if ( getIndexCount() != 0 )
                {
                    aEmitter.append( " + " );
                }
                
                aEmitter.append( myCalculatedOffset );
            }
        }
    }

    
    /**
     * FILLIN
     * 
     * @return
     */
    private int getIndexCount()
    {
        return indexCount;
    }
    
    
    /**
     * FILLIN
     */
    private void addIndex()
    {
        indexCount++;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aOffset
     */
    private void addOffset( int aOffset )
    {
        offset += aOffset;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    private int getOffset()
    {
        return offset;
    }
    
    
    /**
     * FILLIN
     */
    private void increaseNesting()
    {
        nestedCount++;
    }
    
    
    /**
     * FILLIN
     */
    private void decreaseNesting()
    {
        nestedCount--;
    }
    
    
    /**
     * FILLIN
     * 
     * @return
     */
    private boolean isNested()
    {
        return ( nestedCount != 0 );
    }
}
