/*
 * DataCruncher
 * Copyright (c) Mario Altimari. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

/*
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
*/
package com.datacruncher.validation.common;

import com.datacruncher.utils.generic.PropertyResource;
import com.datacruncher.utils.validation.SingleValidation;
import com.datacruncher.validation.ResultStepValidation;
import org.apache.commons.lang.Validate;

public class ISOCountries extends PropertyResource implements SingleValidation {
    private static final ISOCountries instance = new ISOCountries();

    /**
     * Default constructor
     */
    protected ISOCountries() {
        super();
    }

    /**
     * Get the unique instance of this object
     * @return the object instance
     */
    public static ISOCountries getInstance() {
        return instance;
    }

    protected String getResourceName() {
        return "data/countries.properties";
    }

    /**
     * Checks the parameter code in the managed property
     * @param code key to look for in the properties
     * @return true if the property contains <code>code</code> as key
     */
    public boolean isValid(String code) {
        Validate.notNull(code);
        return prop.containsKey(code.toUpperCase());
    }
    /**
     * Checks the parameter code in the managed property
     * @param code key to look for in the properties
     * @return <code>ResultStepValidation</code>
     */
    public ResultStepValidation checkValidity(String code) {
        ResultStepValidation result = new ResultStepValidation();
        try{
            if(isValid(code.toUpperCase())){
                result.setValid(true);
            }else{
                result.setValid(false);
                result.setMessageResult("Country: [" + code + "] wrong." );
            }
        }catch(Exception e)  {
            result.setValid(false);
            result.setMessageResult("Country: [" + code + "] wrong. " +e.getMessage());
        }
        return result;
    }
}
