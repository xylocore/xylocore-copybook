package com.xylocore.commons.data.copybook.domain.config;

import com.xylocore.commons.data.copybook.runtime.DataType;


/**
 * FILLIN
 * 
 * @author      Eric R. Medley
 */

public interface NameConverter
{
    /**
     * FILLIN
     * 
     * @param       aSource
     * 
     * @return
     */
    public String convertToBeanName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateRecordLengthMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateOffsetMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateLengthMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateTotalLengthMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateSingleElementLengthMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateBlankMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateNullMethodName( String aSource );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * @param       aDataType
     * @param       aIsDefault
     * @param       aHasMultipleDatatypes
     * 
     * @return
     */
    public String generateValidMethodName( String     aSource,
                                           DataType   aDataType,
                                           boolean    aIsDefault,
                                           boolean    aHasMultipleDatatypes );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * @param       aDataType
     * @param       aIsDefault
     * @param       aHasMultipleDatatypes
     * 
     * @return
     */
    public String generateGetterMethodName( String     aSource,
                                            DataType   aDataType,
                                            boolean    aIsDefault,
                                            boolean    aHasMultipleDatatypes );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * @param       aDataType
     * @param       aIsDefault
     * @param       aHasMultipleDatatypes
     * 
     * @return
     */
    public String generateSetterMethodName( String     aSource,
                                            DataType   aDataType,
                                            boolean    aIsDefault,
                                            boolean    aHasMultipleDatatypes );
    
    
    /**
     * FILLIN
     *
     * @param       aSource
     * 
     * @return
     */
    public String generateConditionNameMethodName( String aSource );
}
