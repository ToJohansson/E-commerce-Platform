import { useState } from "react";
import { createProduct, getById, getProducts } from "../Api/productApi";
import { AxiosError } from "axios";

type Product = {
  name: string;
  stock: number;
  price: number;
  description: string;
};

export const useCreateProduct = () => {
  const [data, setData] = useState<Product | null>(null);
  const [error, setError] = useState<string | null>(null);
  const [loading, setLoading] = useState<boolean>(false);

  const create = async (product: Product) => {
    setLoading(true);
    try {
      const response = await createProduct(product);
      setData(response);
    } catch (err) {
      if (err instanceof AxiosError) {
        setError(err.response?.data?.message || "An error occurred");
      } else {
        setError("An unexpected error occurred");
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
      const response = await getProducts();
      setData(response);
    } catch (err) {
      if (err instanceof AxiosError) {
        setError(err.response?.data?.message || "An error occurred");
      } else {
        setError("An unexpected error occurred");
      }
    } finally {
      setLoading(false);
    }
  };
  return { getProducts, data, error, loading };
};
export const useGetProductById = (id: string) => {
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
        setError("An unexpected error occurred");
      }
    } finally {
      setLoading(false);
    }
  };
  return { getProductById, data, error, loading };
};
