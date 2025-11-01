// src/pages/Login.jsx
import { useState } from "react";
import { loginUser } from "../api/api";
import { useNavigate } from "react-router-dom";

export default function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const user = await loginUser({ email, password });
      localStorage.setItem("user", JSON.stringify(user));
      alert("Login successful!");
      navigate("/");
    } catch (err) {
      setError(err.message || "Invalid credentials!");
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <form
        onSubmit={handleLogin}
        className="bg-white shadow-lg rounded-xl p-8 w-full max-w-sm"
      >
        <h2 className="text-2xl font-semibold mb-6 text-center">Login</h2>
        {error && <p className="text-red-500 mb-4">{error}</p>}

        <input
          type="email"
          placeholder="Email"
          className="w-full border p-2 rounded mb-3"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
          required
        />

        <input
          type="password"
          placeholder="Password"
          className="w-full border p-2 rounded mb-4"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />

        <button
          type="submit"
          className="bg-blue-500 text-white px-4 py-2 w-full rounded hover:bg-blue-600"
        >
          Login
        </button>

        <p className="text-center mt-4 text-sm">
          Don’t have an account?{" "}
          <a href="/register" className="text-blue-500 hover:underline">
            Register
          </a>
        </p>
      </form>
    </div>
  );
}
