import React, { useEffect, useState } from "react";
import {useGetCustomers, useDeleteCustomer} from "../Hooks/useCustomers"
import ModalCustomer from "../Modals/ModalCustomer";
import "./components.css";


interface Customer  {
  id?: number,
  name: string,
  address: string,
  mail: string
}

const Products: React.FC = () => {
  const { getAllCustomers, data, error, loading } = useGetCustomers();
  const { deleteCustomer} = useDeleteCustomer();

  const [activeId, setActiveId] = useState<number | null>(null);
  const [isModalCustomerOpen, setModalCustomerOpen] = useState(false);
  const [selectedCustomerId, setSelectedCustomerId] = useState<number | null>(
    null
  );

  const openModalCustomer = (id: number) => {
    setSelectedCustomerId(id);
    setModalCustomerOpen(true);
  };

  const closeModalCustomer = () => {
    setSelectedCustomerId(null);
    setModalCustomerOpen(false);
  };

  const toggleAccordion = (id: number) => {
    setActiveId((prevId) => (prevId === id ? null : id));
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteCustomer(id);
      console.log("Customer with id ", id, " was deleted");
      fetchCustomers(); 
    } catch (error) {
      console.error("Error deleting customer:", error);
    }
  };

  const fetchCustomers = () => {
    getAllCustomers();
  }

  useEffect(() => {
    fetchCustomers();
  }, [] 
);

  return (
    <>
      <div>
        <h2>Customer</h2>
      </div>
      {loading && <p>Loading products...</p>}
      {error && <p>Error: {error}</p>}

      <div className="accordion" id="accordionExample">
        {data?.map((customer: Customer) => {
          const collapseId = `collapse${customer.id}`;
          const headingId = `heading${customer.id}`;
          const isActive = activeId === customer.id;
          const backgroundColor = isActive ? "#cfe2ff" : "white";

          return (
            <div
              key={customer.id}
              className="accordion-item"
              style={{ backgroundColor }}
            >
              <h2 className="accordion-header" id={headingId}>
                <button
                  className="accordion-button"
                  type="button"
                  data-bs-toggle="collapse"
                  data-bs-target={`#${collapseId}`}
                  aria-expanded="true"
                  aria-controls={collapseId}
                  onClick={() => toggleAccordion(customer.id as number)}
                >
                  {customer.name}
                </button>
              </h2>
              <div
                id={collapseId}
                className="accordion-collapse collapse"
                aria-labelledby={headingId}
                data-bs-parent="#accordionExample"
              >
                <div className="accordion-body">
                  <div>Address: {customer.address}</div>
                  <div>Mail: {customer.mail}</div>
                </div>
                <button
                  type="button"
                  className="btn btn-warning btn-sm product-btn-container"
                  onClick={() => openModalCustomer(customer.id as number)}
                >
                  Update
                </button>
                <button
                  type="button"
                  className="btn btn-danger btn-sm product-btn-container"
                  onClick={() => handleDelete(customer.id as number)}
                >
                  Delete
                </button>
              </div>
            </div>
          );
        })}
      </div>

      {isModalCustomerOpen && selectedCustomerId !== null && (
        <ModalCustomer
          id={selectedCustomerId}
          customer={data!.find((c) => c.id === selectedCustomerId)!}
          onClose={closeModalCustomer}
          onSubmit={fetchCustomers}
        />
      )}
    </>
  );
};

export default Products;
