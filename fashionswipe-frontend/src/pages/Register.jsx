// src/pages/Register.jsx
import { useState } from "react";
import { registerUser } from "../api/api";
import { useNavigate } from "react-router-dom";

export default function Register() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      await registerUser({ name, email, password });
      alert("Registration successful! Please login.");
      navigate("/login");
    } catch (err) {
      setError(err.message || "Registration failed!");
    }
  };

  return (
    <div className="flex justify-center items-center min-h-screen bg-gray-100">
      <form
        onSubmit={handleRegister}
        className="bg-white shadow-lg rounded-xl p-8 w-full max-w-sm"
      >
        <h2 className="text-2xl font-semibold mb-6 text-center">Register</h2>
        {error && <p className="text-red-500 mb-4">{error}</p>}

        <input
          type="text"
          placeholder="Name"
          className="w-full border p-2 rounded mb-3"
          value={name}
          onChange={(e) => setName(e.target.value)}
          required
        />

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
          className="bg-green-500 text-white px-4 py-2 w-full rounded hover:bg-green-600"
        >
          Register
        </button>

        <p className="text-center mt-4 text-sm">
          Already have an account?{" "}
          <a href="/login" className="text-blue-500 hover:underline">
            Login
          </a>
        </p>
      </form>
    </div>
  );
}
