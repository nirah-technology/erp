/* 
 * React Home Component
 */
'use strict';

const { Component } = require("react");

const element = React.createElement;

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = { 
        name: props.name, status: props.status, lastmessage: props.lastmessage};
  }

  render() {
	const statusClass = (this.state.status ? 'green' : 'red');
	const srvLetter = this.props.name.charAt(0).toUpperCase();
	return  (
			<div className="list-group-item list-group-item-action d-flex gap-3 py-3" aria-current="true">
					<div className={"i-circle " + statusClass}>{srvLetter}</div>
					 <div className="d-flex gap-2 w-100 justify-content-between">
						<div>
							<h6 className="mb-0">{this.props.name}</h6>
							<p className="mb-0 opacity-75">
							{this.state.lastmessage}
							</p>
						</div>
						<small className="opacity-50 text-nowrap">
						{(new Date()).toLocaleString()}</small>
					</div>
				</div>
			);
			
	
  }
}

// Find all DOM containers, and render Like buttons into them.
document.querySelectorAll('.home')
  .forEach(domContainer => {
    // Read the attributes from a data-* attribute.
    const name = domContainer.dataset.name;
	const status = domContainer.dataset.status.toLowerCase() === "true";
	
    const root = ReactDOM.createRoot(domContainer);
    root.render(
      element(Home, { name: name, status: status })
    );
  });

