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
package com.axelor.apps.suppliermanagement.service;

import java.util.HashMap;
import java.util.Map;

public class SupplierViewInterfaceImpl implements SupplierViewInterface {

  @Override
  public Map<String, Object> updateSupplierViewIndicators() {
    Map<String, Object> map = new HashMap<>();
    map.put("$orders", 2);
    map.put("$quotationInProgress", 4);
    map.put("$lastOrder", "19/02/2019");
    map.put("$lastDelivery", "15/01/2019");
    map.put("$nextDelivery", "28/02/2019");
    map.put("$realizedDelivery", 4);
    map.put("$overdueInvoices", 1);
    map.put("$awaitingInvoices", 1);
    map.put("$totalRemaining", 157);
    map.put("$supplierTickets", 2);
    map.put("$companyTickets", 105);
    map.put("$resolvedTickets", 84);

    return map;
  }
}
