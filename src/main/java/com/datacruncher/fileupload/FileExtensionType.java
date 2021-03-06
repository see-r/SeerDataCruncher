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

package com.datacruncher.fileupload;

public enum FileExtensionType {
	
	EXCEL_97("xls"),
	EXCEL_2007("xlsx"),
	ZIP("zip");	
	
	String abbreviation;
	
	private FileExtensionType(String extension){
		abbreviation = extension;
	}
	
	public String getAbbreviation(){
		return abbreviation;
	}
}
