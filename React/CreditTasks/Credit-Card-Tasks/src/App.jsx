import "./App.css";
import { Link, useNavigate } from "react-router-dom";

const PaymentApp = () => {
  return (
    <div className="payment-app">
      <nav>
        <button className="payment">
          <Link to="/cardinfo">Payment Info</Link>
        </button>
      </nav>
      <h1>Payment App</h1>
      <div class="container">
        <h4>Task With A Set Deadline</h4>
        <div className="countdown">
          <h2>1d 23h 14m 9s</h2>
        </div>
        <div className="the-wager">
          <h3>$50</h3>
        </div>
      </div>
        <button className="add-task">
          <Link to="/tasks">+</Link>
        </button>
    </div>
  );
};

{
  /* <button onClick={() => processPayment(100.0)}>Payment</button> */
}

export default PaymentApp;
