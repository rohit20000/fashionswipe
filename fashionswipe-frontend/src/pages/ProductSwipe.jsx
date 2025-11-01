import { useEffect, useState } from "react";
import { fetchProducts, sendSwipe } from "../api/productApi";

export default function ProductSwipe() {
  const [products, setProducts] = useState([]);
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    async function loadProducts() {
      try {
        const data = await fetchProducts();
        setProducts(data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    }
    loadProducts();
  }, []);

  const handleSwipe = async (liked) => {
    if (!products[currentIndex]) return;

    const user = JSON.parse(localStorage.getItem("user"));
    if (!user) {
      alert("Please login first!");
      return;
    }

    try {
      await sendSwipe(user.id, products[currentIndex].id, liked);
      setCurrentIndex((prev) => prev + 1);
    } catch (error) {
      console.error("Error sending swipe:", error);
    }
  };

  if (products.length === 0) {
    return <p>Loading products...</p>;
  }

  const currentProduct = products[currentIndex];
  if (!currentProduct) return <p>No more products!</p>;

  return (
    <div className="swipe-container">
      <h2>{currentProduct.name}</h2>
      <img src={currentProduct.imageUrl} alt={currentProduct.name} width="200" />
      <p>{currentProduct.description}</p>
      <button onClick={() => handleSwipe(true)}>❤️ Like</button>
      <button onClick={() => handleSwipe(false)}>❌ Skip</button>
    </div>
  );
}
