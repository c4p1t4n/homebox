import React from 'react';
import ReactDOM from 'react-dom/client';

import App from './App';


const body = ReactDOM.createRoot(document.getElementById('body'));
body.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>
);
