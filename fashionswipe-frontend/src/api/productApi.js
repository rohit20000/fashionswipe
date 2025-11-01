const API_BASE_URL = "http://localhost:8080/api";

export async function fetchProducts() {
  const res = await fetch(`${API_BASE_URL}/products`);
  if (!res.ok) throw new Error("Failed to fetch products");
  return res.json();
}

export async function sendSwipe(userId, productId, liked) {
  const res = await fetch(`${API_BASE_URL}/swipes`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ userId, productId, liked }),
  });

  if (!res.ok) {
    const text = await res.text().catch(() => null);
    throw new Error(text || "Failed to send swipe");
  }

  return res.json().catch(() => null);
}
