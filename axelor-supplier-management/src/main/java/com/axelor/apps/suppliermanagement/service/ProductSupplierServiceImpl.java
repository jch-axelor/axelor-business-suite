package com.axelor.apps.suppliermanagement.service;

import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.repo.ProductRepository;
import com.axelor.apps.suppliermanagement.db.ProductSupplier;
import com.axelor.apps.suppliermanagement.exceptions.IExceptionMessage;
import com.axelor.exception.AxelorException;
import com.axelor.exception.db.repo.TraceBackRepository;
import com.axelor.i18n.I18n;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class ProductSupplierServiceImpl implements ProductSupplierService {

  @Inject protected ProductRepository productRepo;

  @Transactional(rollbackOn = {Exception.class})
  @Override
  public Product addOnCatalog(ProductSupplier productSupplier) throws AxelorException {
    if (productSupplier.getProductCode() == null) {
      throw new AxelorException(
          productSupplier,
          TraceBackRepository.CATEGORY_NO_VALUE,
          I18n.get(IExceptionMessage.PRODUCT_SUPPLIER_NO_CODE));
    }
    if (productSupplier.getProductName() == null) {
      throw new AxelorException(
          productSupplier,
          TraceBackRepository.CATEGORY_NO_VALUE,
          I18n.get(IExceptionMessage.PRODUCT_SUPPLIER_NO_NAME),
          productSupplier.getProductCode());
    }
    if (productRepo.findByCode(productSupplier.getProductCode()) != null) {
      throw new AxelorException(
          productSupplier,
          TraceBackRepository.CATEGORY_CONFIGURATION_ERROR,
          I18n.get(IExceptionMessage.PRODUCT_SUPPLIER_SAME_CODE));
    }
    return productRepo.save(createProductFromProductSupplier(productSupplier));
  }

  protected Product createProductFromProductSupplier(ProductSupplier productSupplier) {
    Product product =
        new Product(
            productSupplier.getProductCode(),
            productSupplier.getProductCode(),
            productSupplier.getDescription(),
            null,
            productSupplier.getImgProduct(),
            null,
            null,
            productSupplier.getPurchaseUnit(),
            null,
            ProductRepository.PRODUCT_TYPE_STORABLE,
            null,
            productSupplier.getPurchaseCurrency(),
            productSupplier.getPurchaseCurrency(),
            null,
            null);
    product.setPurchasePrice(productSupplier.getPurchasePrice());
    return product;
  }
}
