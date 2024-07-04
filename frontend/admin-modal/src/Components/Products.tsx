import { useEffect, useState } from "react";
import { useGetProducts } from "../Hooks/useProducts";

const Products: React.FC = () => {
  const { getAllProducts, data, error, loading } = useGetProducts();

  const [activeId, setActiveId] = useState(null);

  const handleAccordionClick = (id: any) => {
    setActiveId((prevId: any) => (prevId === id ? null : id));
  };

  useEffect(() => {
    getAllProducts();
  }, []);

  return (
    <>
      <div>
        <h2>Products</h2>
      </div>
      {loading && <p>Loading products...</p>}
      {error && <p>Error: {error}</p>}
      {data && <ul></ul>}
      <div>
        <div className="accordion" id="accordionExample">
          {data?.map((product) => {
            const collapseId = `collapse${product.id}`;
            const headingId = `heading${product.id}`;

            const defaultBackgroundColor = "white";
            const activeBackgroundColor = "#cfe2ff";
            const backgroundColor =
              activeId === product.id
                ? activeBackgroundColor
                : defaultBackgroundColor;

            return (
              <div
                key={product.id}
                className="accordion-item"
                style={{ backgroundColor: backgroundColor }}
              >
                <h2 className="accordion-header" id={headingId}>
                  <button
                    className="accordion-button"
                    type="button"
                    data-bs-toggle="collapse"
                    data-bs-target={`#${collapseId}`}
                    aria-expanded="true"
                    aria-controls={collapseId}
                    onClick={() => handleAccordionClick(product.id)}
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
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
};

export default Products;
