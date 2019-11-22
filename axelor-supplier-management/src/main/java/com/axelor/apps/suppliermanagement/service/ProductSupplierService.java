package com.axelor.apps.suppliermanagement.service;

import com.axelor.apps.base.db.Product;
import com.axelor.apps.suppliermanagement.db.ProductSupplier;
import com.axelor.exception.AxelorException;

public interface ProductSupplierService {
  public Product addOnCatalog(ProductSupplier productSupplier) throws AxelorException;
}
