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


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class Visitor
{
    /**
     * FILLIN
     * 
     * @param       aCopybook
     */
    public void visitCopybook( Copybook aCopybook )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aCopybook
     */
    public void leaveCopybook( Copybook aCopybook )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aParent
     * 
     * @return
     */
    public boolean shouldVisitChildren( Element aParent )
    {
        return false;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aChild
     * 
     * @return
     */
    public boolean shouldVisitChild( Element aChild )
    {
        return true;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void visitLevel66Element( Level66Element aElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void leaveLevel66Element( Level66Element aElement )
    {
    }
    
    
    /**
     * FILLIN
     *
     * @param       aDataElement
     * 
     * @return
     */
    public boolean shouldVisitLevel88Elements( DataElement aDataElement )
    {
        return false;
    }
    
    
    /**
     * FILLIN
     *
     * @param       aElement
     * 
     * @return
     */
    public boolean shouldVisitLevel88Element( Level88Element aElement )
    {
        return true;
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void visitLevel88Element( Level88Element aElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void leaveLevel88Element( Level88Element aElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataElement
     */
    public void visitDataElement( DataElement aDataElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aDataElement
     */
    public void leaveDataElement( DataElement aDataElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFillerElement
     */
    public void visitFillerElement( FillerElement aFillerElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFillerElement
     */
    public void leaveFillerElement( FillerElement aFillerElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNoNameElement
     */
    public void visitNoNameElement( NoNameElement aNoNameElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aNoNameElement
     */
    public void leaveNoNameElement( NoNameElement aNoNameElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void visitElement( Element aElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aElement
     */
    public void leaveElement( Element aElement )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitAccessorMethodInfo( AccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveAccessorMethodInfo( AccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitByteAccessorMethodInfo( ByteAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveByteAccessorMethodInfo( ByteAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitCharAccessorMethodInfo( CharAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveCharAccessorMethodInfo( CharAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitShortAccessorMethodInfo( ShortAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveShortAccessorMethodInfo( ShortAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitIntegerAccessorMethodInfo( IntegerAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveIntegerAccessorMethodInfo( IntegerAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitLongAccessorMethodInfo( LongAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveLongAccessorMethodInfo( LongAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitFloatAccessorMethodInfo( FloatAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveFloatAccessorMethodInfo( FloatAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitDoubleAccessorMethodInfo( DoubleAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveDoubleAccessorMethodInfo( DoubleAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitBigIntegerAccessorMethodInfo( BigIntegerAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveBigIntegerAccessorMethodInfo( BigIntegerAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitBigDecimalAccessorMethodInfo( BigDecimalAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveBigDecimalAccessorMethodInfo( BigDecimalAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitStringAccessorMethodInfo( StringAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveStringAccessorMethodInfo( StringAccessorMethodInfo aInfo )
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void visitDateAccessorMethodInfo( DateAccessorMethodInfo aInfo )
    {
    }


    /**
     * FILLIN
     * 
     * @param       aInfo
     */
    public void leaveDateAccessorMethodInfo( DateAccessorMethodInfo aInfo )
    {
    }
}
