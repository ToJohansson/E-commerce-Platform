import { useState } from "react";
import { getCustomers, putCustomer, deleteCustomerById } from "../Api/customerApi";
import { AxiosError } from "axios";

type Customer = {
    id?: number,
    name: string,
    address: string,
    mail: string
}

export const useGetCustomers = () => {
    const [data, setData] = useState<Customer[] | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const getAllCustomers = async () => {
      setLoading(true);
      try {
        const response = await getCustomers();
        setData(response);
      } catch (err) {
        if (err instanceof AxiosError) {
          setError(err.response?.data?.message || "An error occurred");
        } else {
          setError("An unexpected problem fetching Customers");
        }
      } finally {
        setLoading(false);
      }
    };
    return { getAllCustomers, data, error, loading };
  };

  export const useUpdateCustomer = (id: number) => {
    const [data, setData] = useState<Customer | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
  
    const updateCustomer = async (Customer: Customer) => {
      setLoading(true);
      try {
        const response = await putCustomer(id, Customer);
        setData(response);
      } catch (err) {
        if (err instanceof AxiosError) {
          setError(err.response?.data?.message || "An error occurred");
        } else {
          setError("Update Customer failed");
        }
      } finally {
        setLoading(false);
      }
    };
  
    return { updateCustomer, data, error, loading };
  };

  export const useDeleteCustomer = () => {
    const [data, setData] = useState<Customer | null>(null);
    const [error, setError] = useState<string | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
  
    const deleteCustomer = async (id: number) => {
      setLoading(true);
      try {
        await deleteCustomerById(id);
        setData(null);
      } catch (err) {
        if (err instanceof AxiosError) {
          setError(err.response?.data?.message || "An error occurred");
        } else {
          setError("Error trying to delete Customer");
        }
      } finally {
        setLoading(false);
      }
    };
  
    return { deleteCustomer, data, error, loading };
  };