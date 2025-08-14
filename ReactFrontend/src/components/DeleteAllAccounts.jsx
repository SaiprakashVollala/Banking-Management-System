import React from 'react';
import axios from 'axios';

const DeleteAllAccounts = ({ onDeletedAll }) => {
  const handleDeleteAll = async () => {
    if (!window.confirm('Delete ALL accounts?')) return;
    try {
      await axios.delete('http://localhost:8080/api/accounts');
      alert('All deleted');
      onDeletedAll();
    } catch {
      alert('Failed to delete all');
    }
  };

  return <button onClick={handleDeleteAll}>Delete All Accounts</button>;
};

export default DeleteAllAccounts;
