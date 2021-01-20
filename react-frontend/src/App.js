// import logo from './logo.svg';
import React, { Component } from 'react';
import { render } from '@testing-library/react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import UserComponent from './components/UserComponent';
import UserCreateComponent from './components/UserCreateComponent';
import TelephoneComponent from './components/TelephoneComponent';
import TelephoneCreateComponent from './components/TelephoneCreateComponent';

class App extends Component{
  render() {
    return (        
      <Router>
        <Switch>
            {/* <Route path='/' exact={true} component={Home}/> */}
            <Route path='/users' exact={true} component={UserComponent}/>
            <Route path='/users/new' exact={true} component={UserCreateComponent}/>
            <Route path='/users/:id' component={UserCreateComponent}/>
            <Route path='/telephones/:userId' exact={true} component={TelephoneComponent}/>
            <Route path='/telephones/:userId/add' component={TelephoneCreateComponent}/>
            <Route path='/telephones/:userId/:id' component={TelephoneCreateComponent}/>
        </Switch>
      </Router>
    );
  }
  
}

export default App;
