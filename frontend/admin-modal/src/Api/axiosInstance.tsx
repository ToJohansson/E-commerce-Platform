import axios from "axios";

const createAxiosInstance = (baseURL: string) => {
  return axios.create({
    baseURL: baseURL,
    headers: {
      "Content-Type": "application/json",
    },
  });
};
export const productService = createAxiosInstance("http://localhost:8181/api");
export const customerService = createAxiosInstance("http://localhost:8282/api");
export const orderService = createAxiosInstance("http://localhost:8383/api");