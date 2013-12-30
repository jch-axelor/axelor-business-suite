/**
 * Copyright (c) 2012-2013 Axelor. All Rights Reserved.
 *
 * The contents of this file are subject to the Common Public
 * Attribution License Version 1.0 (the “License”); you may not use
 * this file except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://license.axelor.com/.
 *
 * The License is based on the Mozilla Public License Version 1.1 but
 * Sections 14 and 15 have been added to cover use of software over a
 * computer network and provide for limited attribution for the
 * Original Developer. In addition, Exhibit A has been modified to be
 * consistent with Exhibit B.
 *
 * Software distributed under the License is distributed on an “AS IS”
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See
 * the License for the specific language governing rights and limitations
 * under the License.
 *
 * The Original Code is part of "Axelor Business Suite", developed by
 * Axelor exclusively.
 *
 * The Original Developer is the Initial Developer. The Initial Developer of
 * the Original Code is Axelor.
 *
 * All portions of the code written by Axelor are
 * Copyright (c) 2012-2013 Axelor. All Rights Reserved.
 */
package com.axelor.apps.organisation.web;

import java.math.BigDecimal;

import com.axelor.apps.organisation.db.Expense;
import com.axelor.apps.organisation.db.ExpenseLine;
import com.axelor.apps.organisation.service.ExpenseLineService;
import com.axelor.rpc.ActionRequest;
import com.axelor.rpc.ActionResponse;
import com.google.inject.Inject;

public class ExpenseLineController {
	
	@Inject
	private ExpenseLineService expenseLineService;

	public void compute(ActionRequest request, ActionResponse response) {
	
		ExpenseLine expenseLine = request.getContext().asType(ExpenseLine.class);
		
		BigDecimal inTaxTotal = BigDecimal.ZERO;
		BigDecimal companyTotal = BigDecimal.ZERO;
		
		try{
			if (expenseLine.getPrice() != null && expenseLine.getQty() != null){
				
				inTaxTotal = ExpenseLineService.computeAmount(expenseLine.getQty(), expenseLine.getPrice());
			}
			
			if(inTaxTotal != null) {

				Expense expense = expenseLine.getExpense();

				if(expense == null) {
					expense = request.getContext().getParentContext().asType(Expense.class);
				}

				if(expense != null) {
					companyTotal = expenseLineService.getCompanyTotal(inTaxTotal, expense);
				}
			}
			
			response.setValue("inTaxTotal", inTaxTotal);
			response.setValue("companyTotal", companyTotal);
		}
		catch(Exception e)  {
			response.setFlash(e.getMessage());
		}	
	}
	
	
	public void getProductInformation(ActionRequest request, ActionResponse response) {

		ExpenseLine expenseLine = request.getContext().asType(ExpenseLine.class);

		Expense expense = expenseLine.getExpense();
		if(expense == null)  {
			expense = request.getContext().getParentContext().asType(Expense.class);
		}

		if(expense != null && expenseLine.getProduct() != null) {

			try  {
				
				response.setValue("productName", expenseLine.getProduct().getName());
				response.setValue("unit", expenseLine.getProduct().getUnit());
				
				response.setValue("price", expenseLineService.getUnitPrice(expense, expenseLine));
				
			}
			catch(Exception e)  {
				response.setFlash(e.getMessage()); 
				this.resetProductInformation(response);
			}
		}
		else {
			this.resetProductInformation(response);
		}
	}
	
	
	public void resetProductInformation(ActionResponse response)  {
		
		response.setValue("productName", null);
		response.setValue("unit", null);
		response.setValue("price", null);
		
	}
}
