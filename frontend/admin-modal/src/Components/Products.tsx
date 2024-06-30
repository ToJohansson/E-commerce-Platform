import React, { useEffect } from "react";
import { useGetProducts } from "../Hooks/useProducts";

const Products: React.FC = () => {
  const { getAllProducts, data, error, loading } = useGetProducts();

  useEffect(() => {
    getAllProducts();
  }, []);

  return (
    <div>
      <h2>Products</h2>
      {loading && <p>Loading products...</p>}
      {error && <p>Error: {error}</p>}
      {data && (
        <ul>
          {data.map((product) => (
            <li key={product.id}>
              {product.name} - ${product.price}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default Products;
