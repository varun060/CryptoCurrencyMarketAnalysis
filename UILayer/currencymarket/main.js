import React from 'react';
import ReactDOM from 'react-dom';
import {Router, Route, IndexRoute, IndexLink, Link} from "react-router";

import App from './src/App.jsx';
import Home from './src/Home.jsx';



ReactDOM.render(
      <Router>
        <Route path="/" component={App}>
          <IndexRoute component={Home}/>
          
        </Route>
      </Router>,
      document.getElementById('app')
    );