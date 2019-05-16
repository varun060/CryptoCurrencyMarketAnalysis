import React from 'react';
import {Router, Route, IndexRoute, IndexLink, Link} from "react-router";

// Base componenet to start rendering header and footer
var App = React.createClass({
  render: function() {
    return (
   
      <div id="menuwrapper">
        <h1>Market Analysis</h1>
        <ul>
          <li><IndexLink to="/" activeClassName="active">Home</IndexLink></li>
         
         
        </ul>
        <div className="content">
            {this.props.children}
        </div>
      </div>
    )
  }
});

export default App;