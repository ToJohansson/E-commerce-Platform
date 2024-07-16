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
      console.log("CUSTOMER DATA ", response.data)
      return response.data;
    } catch (error) {
      if (error instanceof AxiosError) {
        throw new Error(error.response?.data?.message || "An error occurred");
      } else {
        throw new Error("An unexpected error occurred");
      }
    }
  };



