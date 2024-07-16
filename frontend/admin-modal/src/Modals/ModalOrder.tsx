import React, { useState } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import { useUpdateOrder } from "../Hooks/useOrders";

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

type ModalOrderProps = {
  id: number;
  order: Order;
  onClose: () => void;
  onSubmit: () => void;
};

const ModalOrder: React.FC<ModalOrderProps> = ({
  order,
  onClose,
  onSubmit,
}) => {
  const [selectedStatus, setSelectedStatus] = useState("Select order status");
  const activeStatus = order.status;
  const {
    register,
    handleSubmit,
    setValue,
    reset,
    formState: { errors },
  } = useForm<Order>({ defaultValues: order });

  const { updateOrder } = useUpdateOrder(order.id || 0);

  const handleFormSubmit: SubmitHandler<Order> = async (data) => {
    try {
      const updatedOrder = { ...data, id: order.id };
      await updateOrder(updatedOrder);
      onSubmit();
      reset();
      onClose();
    } catch (error) {
      console.error("Error submitting form:", error);
    }
  };

  const handleKeyDown = (event: React.KeyboardEvent<HTMLFormElement>) => {
    if (event.key === 'Enter') {
      event.preventDefault();
    }
  };

  const handleStatusChange = (status: string) => {
    setSelectedStatus(status);
    setValue("status", status);
  };

  return (
    <div
      className="modal fade show"
      id="exampleModal"
      tabIndex={-1}
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
      style={{ display: "block" }}
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header">
            <h5 className="modal-title" id="exampleModalLabel">
              Update Order
            </h5>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
              onClick={onClose}
            ></button>
          </div>
          <div className="modal-body">
            <form onSubmit={handleSubmit(handleFormSubmit)} noValidate onKeyDown={handleKeyDown}>
              <div className="mb-3">
                <label htmlFor="totalPrice" className="form-label">
                  Total Price
                </label>
                <input
                  id="totalPrice"
                  type="number"
                  className={`form-control ${errors.totalPrice ? "is-invalid" : ""}`}
                  {...register("totalPrice", {
                    required: "Total Price is required",
                    valueAsNumber: true
                  })}
                />
                {errors.totalPrice && (
                  <div className="invalid-feedback">{errors.totalPrice.message}</div>
                )}
              </div>
              <div className="mb-3">
                Avtive status: {activeStatus}
              </div>
              <div className="mb-3">
        
                <div className="dropdown">
                  <div
                    className="btn btn-info dropdown-toggle"
                    id="dropdownMenuButton"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
                  >
                    {selectedStatus || "Select Status"}
                  </div>
                  <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <li>
                      <div
                        className="dropdown-item"
                        onClick={() => handleStatusChange("PENDING")}
                      >
                        PENDING
                      </div>
                    </li>
                    <li>
                      <div
                        className="dropdown-item"
                        onClick={() => handleStatusChange("ACTIVE")}
                      >
                        ACTIVE
                      </div>
                    </li>
                    <li>
                      <div
                        className="dropdown-item"
                        onClick={() => handleStatusChange("SHIPPING")}
                      >
                        SHIPPING
                      </div>
                    </li>
                    <li>
                      <div
                        className="dropdown-item"
                        onClick={() => handleStatusChange("CANCELLED")}
                      >
                        CANCELLED
                      </div>
                    </li>
                  </ul>
                </div>

              </div>

              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  data-bs-dismiss="modal"
                  onClick={onClose}
                >
                  Close
                </button>
                <button type="submit" className="btn btn-primary">
                  Save changes
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ModalOrder;
