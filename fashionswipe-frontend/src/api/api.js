const API_URL = "http://localhost:8080/api/auth";

export async function registerUser(userData) {
  const response = await fetch("http://localhost:8080/api/users/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(userData),
  });

  if (!response.ok) {
    const errorData = await response.json();
    throw new Error(errorData.message || "Registration failed");
  }

  return response.json();
}

export async function loginUser(userData) {
  const res = await fetch(`${API_URL}/login`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(userData),
  });
  if (!res.ok) throw new Error("Invalid login");
  return res.json();
}
