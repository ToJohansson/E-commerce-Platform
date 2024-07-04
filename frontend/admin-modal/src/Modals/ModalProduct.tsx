import React, { useEffect } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import { useUpdateProducts } from "../Hooks/useProducts";

type Product = {
  id?: number;
  name: string;
  stock: number;
  price: number;
  description: string;
};

type ModalProductProps = {
  id: number;
  product: Product;
  onClose: () => void;
  onSubmit: (data: Product) => void;
};

const ModalProduct: React.FC<ModalProductProps> = ({
  product,
  onClose,
  onSubmit,
}) => {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<Product>({ defaultValues: product }); // Använd defaultValues för att fylla i formuläret med produktdata

  const { updateProduct } = useUpdateProducts(product.id || 0); // Anropa useUpdateProducts med produktens id

  const handleFormSubmit: SubmitHandler<Product> = async (data) => {
    try {
      const updatedProduct = { ...data, id: product.id }; // Uppdatera dataobjektet med produktens id
      await updateProduct(updatedProduct); // Anropa updateProduct för att uppdatera produkten
      onSubmit(updatedProduct); // Skicka in det uppdaterade produktobjektet till onSubmit

      console.log("submit ", updatedProduct);
      reset(); // Återställ formuläret efter att det har skickats
      onClose(); // Stäng modalen
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  return (
    <div
      className="modal fade show"
      id="exampleModal"
      tabIndex={-1}
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
      style={{ display: "block" }}
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="exampleModalLabel">
              Update Product
            </h5>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              onClick={onClose}
            ></button>
          </div>
          <div className="modal-body">
            <form onSubmit={handleSubmit(handleFormSubmit)} noValidate>
              <div className="mb-3">
                <label htmlFor="name" className="form-label">
                  Product Name
                </label>
                <input
                  id="name"
                  type="text"
                  className={`form-control ${errors.name ? "is-invalid" : ""}`}
                  {...register("name", {
                    required: "Product name is required",
                  })}
                />
                {errors.name && (
                  <div className="invalid-feedback">{errors.name.message}</div>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="price" className="form-label">
                  Price
                </label>
                <input
                  id="price"
                  type="number"
                  className={`form-control ${errors.price ? "is-invalid" : ""}`}
                  {...register("price", {
                    required: "Price is required",
                    valueAsNumber: true,
                  })}
                />
                {errors.price && (
                  <div className="invalid-feedback">{errors.price.message}</div>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="stock" className="form-label">
                  Stock
                </label>
                <input
                  id="stock"
                  type="number"
                  className={`form-control ${errors.stock ? "is-invalid" : ""}`}
                  {...register("stock", {
                    required: "Stock is required",
                    valueAsNumber: true,
                  })}
                />
                {errors.stock && (
                  <div className="invalid-feedback">{errors.stock.message}</div>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="description" className="form-label">
                  Description
                </label>
                <textarea
                  id="description"
                  className={`form-control ${
                    errors.description ? "is-invalid" : ""
                  }`}
                  {...register("description", {
                    required: "Description is required",
                  })}
                />
                {errors.description && (
                  <div className="invalid-feedback">
                    {errors.description.message}
                  </div>
                )}
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  data-bs-dismiss="modal"
                  onClick={onClose}
                >
                  Close
                </button>
                <button type="submit" className="btn btn-primary">
                  Save changes
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ModalProduct;
