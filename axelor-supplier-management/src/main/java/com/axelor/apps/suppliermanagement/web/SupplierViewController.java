/*
 * Axelor Business Solutions
 *
 * Copyright (C) 2019 Axelor (<http://axelor.com>).
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License, version 3,
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.axelor.apps.suppliermanagement.web;

import com.axelor.apps.suppliermanagement.service.SupplierViewInterface;
import com.axelor.inject.Beans;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import java.util.Map;

public class SupplierViewController {

  public void completeSupplierViewIndicators(ActionRequest request, ActionResponse response) {
    Map<String, Object> map = Beans.get(SupplierViewInterface.class).updateSupplierViewIndicators();

    response.setValues(map);
  }
}
