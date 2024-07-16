import { orderService } from "./axiosInstance";
import { AxiosResponse, AxiosError } from "axios";

type Order = {
    id?: number,
    totalPrice: number,
    status: string,
    customerId: number,
    listOfProducts: {
        id: number,
        productId: number,
        quantity: number
    }[]
};

const url = "/orders";

export const getOrders = async (): Promise<Order[]> => {
    try {
      const response: AxiosResponse<Order[]> = await orderService.get(
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

  export const putOrder = async (
    id: number,
    order: Order
  ): Promise<Order> => {
    try {
      const response: AxiosResponse<Order> = await orderService.put(
        url + `/${id}`,
        order
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
  export const deleteOrderById = async (id: number): Promise<void> => {
    try {
      await orderService.delete(url + `/${id}`);
    } catch (error) {
      if (error instanceof AxiosError) {
        throw new Error(error.response?.data?.message || "An error occurred");
      } else {
        throw new Error("An unexpected error occurred");
      }
    }
  };


