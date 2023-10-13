/* 
 * React Home Component
 */
'use strict';

import React, { useState } from "react";
import Home from "./Home";

function App(props) {
  return (
    <div>
      <p>AppComponent</p>
    </div>
  );
}

// Find all DOM containers, and render Like buttons into them.
const root = document.getElementById('root');
if (root !== null) {
  const dom = ReactDOM.createRoot(root);
  dom.render(React.createElement(App, {}));
}

