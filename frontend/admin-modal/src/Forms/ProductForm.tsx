import React from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import "bootstrap/dist/css/bootstrap.min.css";
import { usePostProduct } from "../Hooks/useProducts";

type ProductFormInputs = {
  name: string;
  stock: number;
  price: number;
  description: string;
};

const ProductForm: React.FC = () => {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<ProductFormInputs>();

  const { create } = usePostProduct();

  const onSubmit: SubmitHandler<ProductFormInputs> = async (data) => {
    try {
      console.log(data);
      await create(data);
      reset();
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  return (
    <>
      <div>
        <h3>Add a new product</h3>
      </div>
      <div className="container mt-5">
        <form onSubmit={handleSubmit(onSubmit)} noValidate>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Product Name
            </label>
            <input
              id="name"
              type="text"
              className={`form-control ${errors.name ? "is-invalid" : ""}`}
              {...register("name", { required: "Product name is required" })}
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
          <button type="submit" className="btn btn-primary">
            Create Product
          </button>
        </form>
      </div>
    </>
  );
};

export default ProductForm;
