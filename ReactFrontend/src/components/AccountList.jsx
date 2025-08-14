import React, { useEffect, useState } from 'react';
import axios from 'axios';

const AccountList = () => {
  const [accounts, setAccounts] = useState([]);

  useEffect(() => {
    fetchAccounts();
  }, []);

  const fetchAccounts = async () => {
    try {
      const res = await axios.get('http://localhost:8080/api/accounts');
      setAccounts(res.data);
    } catch (error) {
      console.error('Failed to fetch accounts', error);
    }
  };

  return (
    <div>
      <h2>All Accounts</h2>
      <ul>
        {accounts.map((acc) => (
          <li key={acc.id}>
            {acc.accountHolderName || 'No Name'} - ₹{acc.balance}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default AccountList;
