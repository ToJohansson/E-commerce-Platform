import { useState } from "react";
import { getOrders, putOrder, deleteOrderById } from "../Api/orderApi";
import { AxiosError } from "axios";

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

export const useGetOrders = () => {
    const [data, setData] = useState<Order[] | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const getAllOrders = async () => {
      setLoading(true);
      try {
        const response = await getOrders();
        setData(response);
      } catch (err) {
        if (err instanceof AxiosError) {
          setError(err.response?.data?.message || "An error occurred");
        } else {
          setError("An unexpected problem fetching orders");
        }
      } finally {
        setLoading(false);
      }
    };
    return { getAllOrders, data, error, loading };
  };

  export const useUpdateOrder = (id: number) => {
    const [data, setData] = useState<Order | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
  
    const updateOrder = async (order: Order) => {
      setLoading(true);
      try {
        const response = await putOrder(id, order);
        setData(response);
      } catch (err) {
        if (err instanceof AxiosError) {
          setError(err.response?.data?.message || "An error occurred");
        } else {
          setError("Update Order failed");
        }
      } finally {
        setLoading(false);
      }
    };
  
    return { updateOrder, data, error, loading };
  };

  export const useDeleteOrder = () => {
    const [data, setData] = useState<Order | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
  
    const deleteOrder = async (id: number) => {
      setLoading(true);
      try {
        await deleteOrderById(id);
        setData(null);
      } catch (err) {
        if (err instanceof AxiosError) {
          setError(err.response?.data?.message || "An error occurred");
        } else {
          setError("Error trying to delete Order");
        }
      } finally {
        setLoading(false);
      }
    };
  
    return { deleteOrder, data, error, loading };
  };