import React from "react";
import SwiperCards from "./components/SwiperCards.jsx";

function App() {
  return (
    <div>
      {/* Diagnostic banner to verify mount */}
      <div style={{ background: "#ff0", color: "#000", padding: "8px" }}>
        App mounted — diagnostic banner
      </div>
      <SwiperCards />
    </div>
  );
}

export default App;
