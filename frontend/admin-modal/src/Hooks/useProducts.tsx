import { useState } from "react";
import {
  postProduct,
  getById,
  getProducts,
  putProduct,
  deleteProductById,
} from "../Api/productApi";
import { AxiosError } from "axios";
import { getCustomers } from "../Api/customerApi"; // DELETE

type Product = {
  id?: number;
  name: string;
  stock: number;
  price: number;
  description: string;
};

export const usePostProduct = () => {
  const [data, setData] = useState<Product | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const create = async (product: Product) => {
    setLoading(true);
    try {
      const response = await postProduct(product);
      setData(response);
    } catch (err) {
      if (err instanceof AxiosError) {
        setError(err.response?.data?.message || "An error occurred");
      } else {
        setError("Error when creating product");
      }
    } finally {
      setLoading(false);
    }
  };

  return { create, data, error, loading };
};
export const useGetProducts = () => {
  const [data, setData] = useState<Product[] | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const getAllProducts = async () => {
    setLoading(true);
    try {
      await getCustomers(); // DELETE
      const response = await getProducts();
      setData(response);
    } catch (err) {
      if (err instanceof AxiosError) {
        setError(err.response?.data?.message || "An error occurred");
      } else {
        setError("An unexpected problem fetching products");
      }
    } finally {
      setLoading(false);
    }
  };
  return { getAllProducts, data, error, loading };
};
export const useGetProductById = (id: number) => {
  const [data, setData] = useState<Product | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);
  const getProductById = async () => {
    setLoading(true);
    try {
      const response = await getById(id);
      setData(response);
    } catch (err) {
      if (err instanceof AxiosError) {
        setError(err.response?.data?.message || "An error occurred");
      } else {
        setError("Could not find with id: " + id);
      }
    } finally {
      setLoading(false);
    }
  };
  return { getProductById, data, error, loading };
};

export const useUpdateProducts = (id: number) => {
  const [data, setData] = useState<Product | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const updateProduct = async (product: Product) => {
    setLoading(true);
    try {
      const response = await putProduct(id, product);
      setData(response);
    } catch (err) {
      if (err instanceof AxiosError) {
        setError(err.response?.data?.message || "An error occurred");
      } else {
        setError("Update product failed");
      }
    } finally {
      setLoading(false);
    }
  };

  return { updateProduct, data, error, loading };
};

export const useDeleteProduct = () => {
  const [data, setData] = useState<Product | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const deleteProduct = async (id: number) => {
    setLoading(true);
    try {
      await deleteProductById(id);
      setData(null);
    } catch (err) {
      if (err instanceof AxiosError) {
        setError(err.response?.data?.message || "An error occurred");
      } else {
        setError("Error trying to delete product");
      }
    } finally {
      setLoading(false);
    }
  };

  return { deleteProduct, data, error, loading };
};
