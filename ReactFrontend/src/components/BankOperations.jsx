import React, { useState } from 'react';
import axios from 'axios';

const BankOperations = ({ refreshAccounts }) => {
  const [form, setForm] = useState({ id: '', amount: '', operation: 'deposit' });

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const res = await axios.put(
        `http://localhost:8080/api/accounts/${form.id}/${form.amount}/${form.operation}`
      );

      alert(`Success. New balance: ₹${res.data.balance}`);
      setForm({ ...form, amount: '' });  
      refreshAccounts(); 
    } catch (err) {
      if (err.response && err.response.data) {
        const errorMessage = typeof err.response.data === 'string'
          ? err.response.data
          : (err.response.data.error || 'Operation failed.');
        alert(`Operation failed: ${errorMessage}`);
      } else {
        alert('Operation failed. Please check account ID or try again.');
      }
    }
  };

  return (
    <div>
      <h2>Deposit / Withdraw</h2>
      <input
        name="id"
        placeholder="ID"
        value={form.id}
        onChange={handleChange}
      />
      <input
        name="amount"
        type="number"
        placeholder="Amount"
        value={form.amount}
        onChange={handleChange}
      />
      <select
        name="operation"
        value={form.operation}
        onChange={handleChange}
      >
        <option value="deposit">Deposit</option>
        <option value="withdraw">Withdraw</option>
      </select>
      <button onClick={handleSubmit}>Go</button>
    </div>
  );
};

export default BankOperations;
