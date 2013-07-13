package com.xylocore.commons.data.copybook.runtime;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public class TestCopybook
    extends
        AbstractCopybook
{
    //
    // Members
    //
    
    
    private int maximumRecordLength;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * FILLIN
     * 
     * @param       aMaximumRecordLength
     */
    public TestCopybook( int aMaximumRecordLength )
    {
        maximumRecordLength = aMaximumRecordLength;
    }
    
    
    /*
     * (non-Javadoc)
     * @see com.xylocore.commons.data.copybook.runtime.AbstractCopybookBase#getMaximumRecordLength()
     */
    public int getMaximumRecordLength()
    {
        return maximumRecordLength;
    }
}
