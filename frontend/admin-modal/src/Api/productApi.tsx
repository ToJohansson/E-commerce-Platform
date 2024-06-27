import { productService } from "./axiosInstance";
import { AxiosResponse, AxiosError } from "axios";

type Product = {
  name: string;
  stock: number;
  price: number;
  description: string;
};

const url = "/products";
export const createProduct = async (product: Product): Promise<Product> => {
  try {
    const response: AxiosResponse<Product> = await productService.post(
      url + "/",
      product
    );
    return response.data;
  } catch (error) {
    if (error instanceof AxiosError) {
      throw new Error(error.response?.data?.message || "An error occurred");
    } else {
      throw new Error("An unexpected error occurred");
    }
  }
};
export const getProducts = async (): Promise<Product[]> => {
  try {
    const response: AxiosResponse<Product[]> = await productService.get(
      url + "/"
    );
    return response.data;
  } catch (error) {
    if (error instanceof AxiosError) {
      throw new Error(error.response?.data?.message || "An error occurred");
    } else {
      throw new Error("An unexpected error occurred");
    }
  }
};
export const getById = async (id: string): Promise<Product> => {
  try {
    const response: AxiosResponse<Product> = await productService.get(
      url + `/${id}`
    );
    return response.data;
  } catch (error) {
    if (error instanceof AxiosError) {
      throw new Error(error.response?.data?.message || "An error occurred");
    } else {
      throw new Error("An unexpected error occurred");
    }
  }
};
export const updateProduct = async (
  id: string,
  product: Product
): Promise<Product> => {
  try {
    const response: AxiosResponse<Product> = await productService.put(
      url + `/${id}`,
      product
    );
    return response.data;
  } catch (error) {
    if (error instanceof AxiosError) {
      throw new Error(error.response?.data?.message || "An error occurred");
    } else {
      throw new Error("An unexpected error occurred");
    }
  }
};
export const deleteProduct = async (id: string): Promise<void> => {
  try {
    await productService.delete(url + `/${id}`);
  } catch (error) {
    if (error instanceof AxiosError) {
      throw new Error(error.response?.data?.message || "An error occurred");
    } else {
      throw new Error("An unexpected error occurred");
    }
  }
};
