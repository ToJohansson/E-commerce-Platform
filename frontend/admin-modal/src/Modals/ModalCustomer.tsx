import React from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import { useUpdateCustomer } from "../Hooks/useCustomers";

type Customer = {
  id?: number;
  name: string;
  address: string,
  mail: string
};

type ModalCustomerProps = {
  id: number;
  customer: Customer;
  onClose: () => void;
  onSubmit: () => void;
};

const ModalCustomer: React.FC<ModalCustomerProps> = ({
  customer,
  onClose,
  onSubmit,
}) => {
  const {
    register,
    handleSubmit,
    reset,
    formState: { errors },
  } = useForm<Customer>({ defaultValues: customer });

  const { updateCustomer } = useUpdateCustomer(customer.id || 0); 

  const handleFormSubmit: SubmitHandler<Customer> = async (data) => {
    try {
      const updatedCustomer = { ...data, id: customer.id };
      await updateCustomer(updatedCustomer); 
      onSubmit();
      reset(); 
      onClose(); 
    } catch (error) {
      console.error("Error submitting form:", error);
    }
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
              Update Customer
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
            <form onSubmit={handleSubmit(handleFormSubmit)} noValidate>
              <div className="mb-3">
                <label htmlFor="name" className="form-label">
                  Customer Name
                </label>
                <input
                  id="name"
                  type="text"
                  className={`form-control ${errors.name ? "is-invalid" : ""}`}
                  {...register("name", {
                    required: "Customer name is required",
                  })}
                />
                {errors.name && (
                  <div className="invalid-feedback">{errors.name.message}</div>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="address" className="form-label">
                  Address
                </label>
                <input
                  id="address"
                  type="text"
                  className={`form-control ${errors.address ? "is-invalid" : ""}`}
                  {...register("address", {
                    required: "address is required",
                  })}
                />
                {errors.address && (
                  <div className="invalid-feedback">{errors.address.message}</div>
                )}
              </div>
              <div className="mb-3">
                <label htmlFor="mail" className="form-label">
                  Mail
                </label>
                <input
                  id="mail"
                  type="text"
                  className={`form-control ${errors.mail ? "is-invalid" : ""}`}
                  {...register("mail", {
                    required: "mail is required",
                  })}
                />
                {errors.mail && (
                  <div className="invalid-feedback">{errors.mail.message}</div>
                )}
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

export default ModalCustomer;
