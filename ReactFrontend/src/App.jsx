import React, { useState, useEffect } from 'react';
import axios from 'axios';

import CreateAccount from './components/CreateAccount';
import GetAccount from './components/GetAccount';
import BankOperations from './components/BankOperations';
import DeleteAccount from './components/DeleteAccount';
import DeleteAllAccounts from './components/DeleteAllAccounts';

function App() {
  const [accounts, setAccounts] = useState([]);

  const fetchAccounts = async () => {
    try {
      const res = await axios.get('http://localhost:8080/api/accounts');
      setAccounts(res.data);
    } catch (err) {
      console.error(err);
    }
  };

  useEffect(() => {
    fetchAccounts();
  }, []);

  return (
    <div style={{ padding: '20px' }}>
      <h1>🏦 Banking App</h1>

      <CreateAccount onAccountCreated={fetchAccounts} />
      <GetAccount />
      <BankOperations refreshAccounts={fetchAccounts} />
      <DeleteAccount onDeleted={fetchAccounts} />
      <DeleteAllAccounts onDeletedAll={fetchAccounts} />

      <hr />
      <h2>All Accounts</h2>
      <ul>
        {accounts.map(acc => (
          <li key={acc.id}>
            {acc.accountHolderName} – ₹{acc.balance}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
