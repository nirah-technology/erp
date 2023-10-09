/* 
 * React Home Component
 */
'use strict';

const { Component } = require("react");

class App extends Component {
  constructor(props) {
    super(props);
    this.state = { 
        name: props.name, status: props.status, lastmessage: props.lastmessage};
  }

  render() {
    
	return  (
			<div>
                <p>ok</p>
                <Home />
            </div>
        );
			
	
  }
}

// Find all DOM containers, and render Like buttons into them.
const root = document.getElementById('root');
const dom = ReactDOM.createRoot(root);
dom.render(React.createElement(App, {}));

