import { useState } from "react";

const CardInfo = () => {
  const [creditCardInfo, setCreditCardInfo] = useState({});

  const addCreditCardInfo = (cardNumber, expirationDate, cvv) => {
    setCreditCardInfo({
      cardNumber,
      expirationDate,
      cvv,
    });
    console.log("Credit card information added successfully.");
  };

  return (
    <div className="payment-app">
      <form className="credit-card-inputs" onSubmit={submitCreditCardInfo}>
        <input
          type="text"
          name="cardNumber"
          placeholder="Card Number"
          value={creditCardInfo.cardNumber || ""}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="expirationDate"
          placeholder="Expiration Date"
          value={creditCardInfo.expirationDate || ""}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="cvv"
          placeholder="CVV"
          value={creditCardInfo.cvv || ""}
          onChange={handleInputChange}
        />
        <button
          onClick={() => addCreditCardInfo("1234567812345678", "12/24", "123")}
        >
          Submit
        </button>
      </form>
    </div>
  );
};

const processPayment = (amount) => {
  if (creditCardInfo.cardNumber) {
    console.log(
      `Processing payment of ${amount} using card number ${creditCardInfo.cardNumber}.`
    );
    // Here, you would typically integrate with a payment gateway or service to process the payment.
    // This code is just a placeholder to simulate the payment process.
    console.log("Payment processed successfully.");
  } else {
    console.log(
      "No credit card information found. Please add credit card information."
    );
  }
};

const handleInputChange = (event) => {
  const { name, value } = event.target;
  setCreditCardInfo({ ...creditCardInfo, [name]: value });
};

// Adds credit card to the database
const submitCreditCardInfo = (event) => {
  event.preventDefault();
  console.log(creditCardInfo);
};

export default CardInfo;
