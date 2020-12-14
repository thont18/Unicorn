import axios from "axios";

const PRODUCT_TYPE_BASE_URL = "http://localhost:8080/productTypes";

class ProductTypeService {
  getProductTypes() {
    return axios.get(PRODUCT_TYPE_BASE_URL);
  }

  createProductType(productType) {
    return axios.post(PRODUCT_TYPE_BASE_URL, productType);
  }

  getProductTypeById(id) {
    return axios.get(PRODUCT_TYPE_BASE_URL + "/" + id);
  }

  updateProductType(productType) {
    return axios.put(PRODUCT_TYPE_BASE_URL + "/" + productType.id, productType);
  }

  deleteProductType(id) {
    return axios.delete(PRODUCT_TYPE_BASE_URL + "/" + id);
  }
}

export default new ProductTypeService();
