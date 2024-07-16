import { customerService } from "./axiosInstance";
import { AxiosResponse, AxiosError } from "axios";

type Customer = {
    id?: number,
    name: string,
    address: string,
    mail: string
}

const url = "/customers";

export const getCustomers = async (): Promise<Customer[]> => {
    try {
      const response: AxiosResponse<Customer[]> = await customerService.get(
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

  export const putCustomer = async (
    id: number,
    customer: Customer
  ): Promise<Customer> => {
    try {
      const response: AxiosResponse<Customer> = await customerService.put(
        url + `/${id}`,
        customer
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
  export const deleteCustomerById = async (id: number): Promise<void> => {
    try {
      await customerService.delete(url + `/${id}`);
    } catch (error) {
      if (error instanceof AxiosError) {
        throw new Error(error.response?.data?.message || "An error occurred");
      } else {
        throw new Error("An unexpected error occurred");
      }
    }
  };


