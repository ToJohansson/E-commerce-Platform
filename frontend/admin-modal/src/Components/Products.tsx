import React, { useEffect, useState } from "react";
import { useGetProducts, useDeleteProduct } from "../Hooks/useProducts";
import "./components.css";
import ModalProduct from "../Modals/ModalProduct";


interface Product {
  id?: number;
  name: string;
  stock: number;
  price: number;
  description: string;
}
const Products: React.FC = () => {
  const { getAllProducts, data, error, loading } = useGetProducts();
  const { deleteProduct} = useDeleteProduct();

  const [activeId, setActiveId] = useState<number | null>(null);
  const [isModalProductOpen, setModalProductOpen] = useState(false);
  const [selectedProductId, setSelectedProductId] = useState<number | null>(
    null
  );

  const openModalProduct = (id: number) => {
    setSelectedProductId(id);
    setModalProductOpen(true);
  };

  const closeModalProduct = () => {
    setSelectedProductId(null);
    setModalProductOpen(false);
  };

  const toggleAccordion = (id: number) => {
    setActiveId((prevId) => (prevId === id ? null : id));
  };

  const handleDelete = async (id: number) => {
    try {
      await deleteProduct(id);
      console.log("Product with id ", id, " was deleted");
      fetchProducts(); 
    } catch (error) {
      console.error("Error deleting product:", error);
    }
  };

  const fetchProducts = () => {
    getAllProducts();
  }

  useEffect(() => {
    fetchProducts();
  }, [] // eslint-disable-next-line react-hooks/exhaustive-deps
);

  return (
    <>
      <div>
        <h2>Products</h2>
      </div>
      {loading && <p>Loading products...</p>}
      {error && <p>Error: {error}</p>}

      <div className="accordion" id="accordionExample">
        {data?.map((product: Product) => {
          const collapseId = `collapse${product.id}`;
          const headingId = `heading${product.id}`;
          const isActive = activeId === product.id;
          const backgroundColor = isActive ? "#cfe2ff" : "white";

          return (
            <div
              key={product.id}
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
                  onClick={() => toggleAccordion(product.id as number)}
                >
                  {product.name}
                </button>
              </h2>
              <div
                id={collapseId}
                className="accordion-collapse collapse"
                aria-labelledby={headingId}
                data-bs-parent="#accordionExample"
              >
                <div className="accordion-body">
                  <div>Price: ${product.price}</div>
                  <div>Available {product.stock}</div>
                  <div>Description: {product.description}</div>
                </div>
                <button
                  type="button"
                  className="btn btn-warning btn-sm product-btn-container"
                  onClick={() => openModalProduct(product.id as number)}
                >
                  Update
                </button>
                <button
                  type="button"
                  className="btn btn-danger btn-sm product-btn-container"
                  onClick={() => handleDelete(product.id as number)}
                >
                  Delete
                </button>
              </div>
            </div>
          );
        })}
      </div>

      {isModalProductOpen && selectedProductId !== null && (
        <ModalProduct
          id={selectedProductId}
          product={data!.find((p) => p.id === selectedProductId)!}
          onClose={closeModalProduct}
          onSubmit={fetchProducts}
        />
      )}
    </>
  );
};

export default Products;
