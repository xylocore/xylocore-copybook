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


package com.xylocore.commons.data.copybook.runtime;


public final class AlphanumericPICFlags
{
    //
    // Members
    //
    
    
    public static final int LEFT_JUSTIFY        = 0x00000000;
    public static final int RIGHT_JUSTIFY       = 0x00000001;
    
    public static final int DISPLAY             = 0x00000000;
    public static final int DBCS                = 0x00000002;
    public static final int NATIONAL            = 0x00000004;
    
    public static final int TRIM_WHITESPACE     = 0x00000000;
    public static final int MAINTAIN_WHITESPACE = 0x00000008;
    
    public static final int NULLS_NOT_ALLOWED   = 0x00000000;
    public static final int NULLS_ALLOWED       = 0x00000010;
    
    public static final int DEFAULT             = LEFT_JUSTIFY | DISPLAY | TRIM_WHITESPACE | NULLS_ALLOWED;
    
    
    
    
    //
    // Class implementation
    //
    
    
    /**
     * This class cannot be instantiated.
     */
    private AlphanumericPICFlags()
    {
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFlags
     * 
     * @return
     */
    public static boolean isLeftJustify( int aFlags )
    {
        return ( (aFlags & RIGHT_JUSTIFY) == 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFlags
     * 
     * @return
     */
    public static boolean isRightJustify( int aFlags )
    {
        return ( (aFlags & RIGHT_JUSTIFY) != 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFlags
     * 
     * @return
     */
    public static boolean isDisplay( int aFlags )
    {
        return ( (aFlags & (NATIONAL | DBCS)) == 0 );
    }

    
    /**
     * FILLIN
     * 
     * @param       aFlags
     * 
     * @return
     */
    public static boolean isDbcs( int aFlags )
    {
        return ( (aFlags & DBCS) != 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFlags
     * 
     * @return
     */
    public static boolean isNational( int aFlags )
    {
        return ( (aFlags & NATIONAL) != 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFlags
     * 
     * @return
     */
    public static boolean isTrimWhitespace( int aFlags )
    {
        return ( (aFlags & MAINTAIN_WHITESPACE) == 0 );
    }
    
    
    /**
     * FILLIN
     * 
     * @param       aFlags
     * 
     * @return
     */
    public static boolean isMaintainWhitespace( int aFlags )
    {
        return ( (aFlags & MAINTAIN_WHITESPACE) != 0 );
    }
    
    
    /**
     * FILLIN
     *
     * @param       aFlags
     * 
     * @return
     */
    public static boolean isNullAllowed( int aFlags )
    {
        return ( (aFlags & NULLS_ALLOWED) != 0 );
    }
}
