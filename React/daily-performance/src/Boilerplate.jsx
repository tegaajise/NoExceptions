import { useState } from "react";
import reactLogo from "./assets/react.svg";
import viteLogo from "/vite.svg";
import "./App.css";

/** VITE TEMPLATE */
function Test() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div>
        <a href="https://vitejs.dev" target="_blank">
          <img src={viteLogo} className="logo" alt="Vite logo" />
        </a>
        <a href="https://react.dev" target="_blank">
          <img src={reactLogo} className="logo react" alt="React logo" />
        </a>
      </div>
      <h1>Vite + React</h1>
      <div className="card">
        <button onClick={() => setCount((count) => count + 1)}>
          count is {count}
        </button>
        <p>
          Edit <code>src/App.jsx</code> and save to test HMR
        </p>
      </div>
      <p className="read-the-docs">
        Click on the Vite and React logos to learn more
      </p>
    </>
  );
}

export default Test;

/** CHAT GPT */
// import React, { useState } from "react";

// // Destructured in the argument (skipped a step)
// const Tab = ({ label, content, activeTab, onClick }) => (
//   <div
//     className={`tab ${activeTab === label ? "active" : ""}`}
//     onClick={() => onClick(label)}
//   >
//     {label}
//   </div>
// );

// const TabContent = ({ content }) => <div>{content}</div>;

// const App = () => {
//   const [activeTab, setActiveTab] = useState("Sleep");

//   const handleTabClick = (label) => {
//     setActiveTab(label);
//   };

//   const tabs = [
//     { label: "Sleep", content: "Get at least 8 hours of sleep." },
//     { label: "Food", content: "Eat a balanced and healthy diet." },
//     { label: "Water", content: "Drink at least 8 glasses of water a day." },
//     { label: "Learning", content: "Read books and learn new skills." },
//     { label: "Spirituality", content: "Practice meditation and mindfulness." },
//   ];

//   return (
//     <div className="app">
//       <div className="tabs">
//         {tabs.map((tab) => (
//           <Tab
//             key={tab.label}
//             label={tab.label}
//             content={tab.content}
//             activeTab={activeTab}
//             onClick={handleTabClick}
//           />
//         ))}
//       </div>
//       <div className="tab-content">
//         {tabs.map((tab) =>
//           activeTab === tab.label ? (
//             <TabContent key={tab.label} content={tab.content} />
//           ) : null
//         )}
//       </div>
//     </div>
//   );
// };

// export default App;
