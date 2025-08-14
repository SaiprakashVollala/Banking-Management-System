import React, { useState } from 'react';
import axios from 'axios';

const DeleteAccount = ({ onDeleted }) => {
  const [id, setId] = useState('');

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/api/accounts/${id}`);
      alert('Deleted');
      onDeleted();
    } catch {
      alert('Delete failed');
    }
  };

  return (
    <div>
      <h2>Delete Account</h2>
      <input placeholder="ID" value={id} onChange={e => setId(e.target.value)} />
      <button onClick={handleDelete}>Delete</button>
    </div>
  );
};

export default DeleteAccount;
