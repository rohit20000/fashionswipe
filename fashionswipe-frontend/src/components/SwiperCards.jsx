import React, { useEffect, useState } from "react";
import { fetchProducts, sendSwipe } from "../api/productApi.js";

export default function SwiperCards() {
  const [products, setProducts] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    fetchProducts()
      .then(setProducts)
      .catch(console.error);
  }, []);

  const handleSwipe = async (liked) => {
    if (currentIndex >= products.length) return;

    const product = products[currentIndex];

    try {
      await sendSwipe(1, product.id, liked);
      setCurrentIndex(currentIndex + 1);
    } catch (err) {
      console.error("Swipe failed:", err);
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2>Swipe Products</h2>

      {products.length === 0 ? (
        <p>Loading products...</p>
      ) : currentIndex >= products.length ? (
        <p>No more products</p>
      ) : (
        <div>
          <img
            src={products[currentIndex].imageUrl}
            alt={products[currentIndex].name}
            style={{ width: "200px", height: "200px", borderRadius: "8px" }}
          />
          <h3>{products[currentIndex].name}</h3>

          <button
            onClick={() => handleSwipe(true)}
            style={{ marginRight: "10px" }}
          >
            👍 Like
          </button>

          <button onClick={() => handleSwipe(false)}>👎 Skip</button>
        </div>
      )}
    </div>
  );
}
