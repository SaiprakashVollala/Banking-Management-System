import React, { useState } from 'react';
import axios from 'axios';

const GetAccount = () => {
  const [id, setId] = useState('');
  const [account, setAccount] = useState(null);

  const handleSearch = async () => {
    try {
      const res = await axios.get(`http://localhost:8080/api/accounts/${id}`);
      setAccount(res.data);
    } catch {
      setAccount(null);
      alert('Account not found');
    }
  };

  return (
    <div>
      <h2>Get Account by ID</h2>
      <input placeholder="ID" value={id} onChange={e => setId(e.target.value)} />
      <button onClick={handleSearch}>Search</button>
      {account && (
        <div>
          ID: {account.id}<br />
          Name: {account.accountHolderName}<br />
          Balance: ₹{account.balance}
        </div>
      )}
    </div>
  );
};

export default GetAccount;
