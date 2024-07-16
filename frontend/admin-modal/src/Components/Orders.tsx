import React, { useEffect, useState } from "react";
import {useGetOrders, useDeleteOrder} from "../Hooks/useOrders"
import ModalOrder from "../Modals/ModalOrder";
import "./components.css";

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

const Orders: React.FC = () => {
  const { getAllOrders, data, error, loading } = useGetOrders();
  const { deleteOrder} = useDeleteOrder();

  const [activeId, setActiveId] = useState<number | null>(null);
  const [isModalOrderOpen, setModalOrderOpen] = useState(false);
  const [selectedOrderId, setSelectedOrderId] = useState<number | null>(
    null
  );

  const openModalOrder = (id: number) => {
    setSelectedOrderId(id);
    setModalOrderOpen(true);
  };

  const closeModalOrder = () => {
    setSelectedOrderId(null);
    setModalOrderOpen(false);
  };

  const toggleAccordion = (id: number) => {
    setActiveId((prevId) => (prevId === id ? null : id));
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteOrder(id);
      console.log("Order with id ", id, " was deleted");
      fetchOrders(); 
    } catch (error) {
      console.error("Error deleting Order:", error);
    }
  };

  const fetchOrders = () => {
    getAllOrders();
  }

  useEffect(() => {
    fetchOrders();
  }, [] 
);

  return (
    <>
      <div>
        <h2>Order</h2>
      </div>
      {loading && <p>Loading products...</p>}
      {error && <p>Error: {error}</p>}

      <div className="accordion" id="accordionExample">
        {data?.map((order: Order) => {
          const collapseId = `collapse${order.id}`;
          const headingId = `heading${order.id}`;
          const isActive = activeId === order.id;
          const backgroundColor = isActive ? "#cfe2ff" : "white";

          return (
            <div
              key={order.id}
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
                  onClick={() => toggleAccordion(order.id as number)}
                >
                <div> 
                Order ID: {order.id} 
                ---- <b>Status</b>: {order.status}
                </div>
                </button>
              </h2>
              <div
                id={collapseId}
                className="accordion-collapse collapse"
                aria-labelledby={headingId}
                data-bs-parent="#accordionExample"
              >
               <div className="accordion-body">
                <div>Total Price: {order.totalPrice}</div>
                <div>Customer ID: {order.customerId}</div>
                <div>
                  <h5>List of Products:</h5>
                  <ul>
                    {order.listOfProducts.map((product) => (
                      <li key={product.id}>
                        Product ID: {product.productId}, Quantity: {product.quantity}
                      </li>
                    ))}
                  </ul>
                </div>
              </div>
                <button
                  type="button"
                  className="btn btn-warning btn-sm product-btn-container"
                  onClick={() => openModalOrder(order.id as number)}
                >
                  Update
                </button>
                <button
                  type="button"
                  className="btn btn-danger btn-sm product-btn-container"
                  onClick={() => handleDelete(order.id as number)}
                >
                  Delete
                </button>
              </div>
            </div>
          );
        })}
      </div>

      {isModalOrderOpen && selectedOrderId !== null && (
        <ModalOrder
          id={selectedOrderId}
          order={data!.find((o) => o.id === selectedOrderId)!}
          onClose={closeModalOrder}
          onSubmit={fetchOrders}
        />
      )}
    </>
  );
};

export default Orders;
