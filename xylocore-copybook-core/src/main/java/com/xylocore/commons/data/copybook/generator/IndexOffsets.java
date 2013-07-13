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


package com.xylocore.commons.data.copybook.generator;

import com.xylocore.commons.data.copybook.domain.Element;


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
     * @param       aBuffer
     * @param       aElement
     */
    public void emit( StringBuilder   aBuffer,
                      Element         aElement )
    {
        assert aBuffer  != null;
        assert aElement != null;
        
        Element myOuterIndexElement = aElement.getOuterIndexedElement();
        if ( myOuterIndexElement != null )
        {
            increaseNesting();
            
            try
            {
                emit( aBuffer, myOuterIndexElement );
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
                aBuffer.append( " + " );
            }
            
            int myElementWidth = aElement.getNonIndexedSize();
            
            aBuffer.append( myElementWidth  )
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
                    aBuffer.append( " + " );
                }
                
                aBuffer.append( myCalculatedOffset );
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
