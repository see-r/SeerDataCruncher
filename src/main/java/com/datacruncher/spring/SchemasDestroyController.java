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

package com.datacruncher.spring;

import com.datacruncher.services.scheduler.PlannerJobMap;
import com.datacruncher.services.scheduler.SchedulerUtils;
import com.datacruncher.jpa.Destroy;
import com.datacruncher.jpa.dao.DaoSet;
import com.datacruncher.jpa.entity.SchemaEntity;
import com.datacruncher.utils.generic.I18n;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class SchemasDestroyController implements Controller, DaoSet {
	
	private PlannerJobMap plannerJobMap;
    
	public void setPlannerJobMap(PlannerJobMap plannerJobMap) {
		this.plannerJobMap = plannerJobMap;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String json = request.getReader().readLine();
		ObjectMapper mapper = new ObjectMapper();
		SchemaEntity schemaEntity = mapper.readValue(json, SchemaEntity.class);
		String idSchema = String.valueOf(schemaEntity.getIdSchema());		
		boolean isAnyJobDeactivated = SchedulerUtils.stopJobsByConnectedField(plannerJobMap,
				SchedulerUtils.FIELD_SCHEMA, Long.parseLong(idSchema));
		schemasXSDDao.destroy(Long.parseLong(idSchema));
		Destroy destroy = schemasDao.destroy(Long.parseLong(idSchema));
		if (isAnyJobDeactivated)
			destroy.setExtraMessage(I18n.getMessage("success.linkedJobsDeacivated"));		
		 mapper = new ObjectMapper();
		ServletOutputStream out = null;
		response.setContentType("application/json");
		out = response.getOutputStream();
		out.write(mapper.writeValueAsBytes(destroy));
		out.flush();
		out.close();
		return null;
	}
}