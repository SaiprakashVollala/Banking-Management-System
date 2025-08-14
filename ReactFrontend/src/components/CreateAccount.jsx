import React, { useState } from 'react';
import axios from 'axios';

const CreateAccount = ({ onAccountCreated }) => {
  const [form, setForm] = useState({ accountHolderName: '', balance: '' });

  const handleChange = e => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      await axios.post('http://localhost:8080/api/accounts', {
        accountHolderName: form.accountHolderName,
        balance: parseFloat(form.balance)
      });
      alert('Account created');
      setForm({ accountHolderName: '', balance: '' });
      onAccountCreated();
    } catch (err) {
      console.error(err);
      alert('Failed');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Create Account</h2>
      <input name="accountHolderName" placeholder="Name" value={form.accountHolderName} onChange={handleChange} required />
      <input name="balance" type="number" placeholder="Balance" value={form.balance} onChange={handleChange} required />
      <button type="submit">Create</button>
    </form>
  );
};

export default CreateAccount;
